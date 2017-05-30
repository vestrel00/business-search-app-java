/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vestrel00.business.search.presentation.android.mvp.ui.business.common.view;

import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.StringRes;

import com.vestrel00.business.search.presentation.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.BusinessTransactionTypeModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.util.MathUtils;
import com.vestrel00.business.search.util.StringUtils;

import javax.inject.Inject;

/**
 * An adapter that binds a {@link BusinessModel} to a view held by a
 * {@link BusinessViewHolder}, which defines all views as optional (nullable) in order to
 * support different layout configurations.
 * <p>
 * Note that unlike other conventional adapters, this adapter does not create the root view for
 * the purposes mentioned above.
 */
@PerFragment
public final class BusinessAdapter {

    private final BusinessPhotosAdapter photosAdapter;
    private final BusinessHoursAdapter hoursAdapter;
    private final StringUtils stringUtils;
    private final MathUtils mathUtils;
    private final Resources activityResources;

    @Inject
    BusinessAdapter(BusinessPhotosAdapter photosAdapter, BusinessHoursAdapter hoursAdapter,
                    StringUtils stringUtils, MathUtils mathUtils, Resources activityResources) {
        this.photosAdapter = photosAdapter;
        this.hoursAdapter = hoursAdapter;
        this.stringUtils = stringUtils;
        this.mathUtils = mathUtils;
        this.activityResources = activityResources;
    }

    public void initializeViewHolder(BusinessViewHolder holder) {
        initializeRating(holder);
    }

    public void bindViewHolderWithData(BusinessViewHolder holder, BusinessModel businessModel) {
        bindImage(holder, businessModel);
        bindName(holder, businessModel);
        bindDistance(holder, businessModel);
        bindRating(holder, businessModel);
        bindReviews(holder, businessModel);
        bindPrice(holder, businessModel);
        bindLocation(holder, businessModel);
        bindCategories(holder, businessModel);
        // TODO (IMPLEMENTATION) - bindPhotos, bindPhotosIndicator
        // bindPhotos(holder);
        // bindPhotosIndicator(holder);
        bindHours(holder, businessModel);
        bindOpenIndicator(holder, businessModel);
        bindPhoneNumber(holder, businessModel);
        bindWebsite(holder, businessModel);
        bindPickup(holder, businessModel);
        bindDelivery(holder, businessModel);
        bindReservation(holder, businessModel);
    }

    private void initializeRating(BusinessViewHolder holder) {
        if (holder.rating != null) {
            holder.rating.setStepSize(0.1f);
            holder.rating.setNumStars(BusinessModel.MAX_RATING);
        }
    }

    private void bindImage(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.image != null) {
            holder.image.setImageURI(Uri.parse(businessModel.imageUrl()));
        }
    }

    private void bindName(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.name != null) {
            holder.name.setText(businessModel.name());
        }
    }

    private void bindDistance(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.distance != null) {
            holder.distance.setText(activityResources.getString(R.string.distance,
                    mathUtils.toMiles(businessModel.distanceInMeters())));
        }
    }

    private void bindRating(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.rating != null) {
            holder.rating.setRating(businessModel.rating());
        }
    }

    private void bindReviews(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.reviews != null) {
            holder.reviews.setText(activityResources.getString(R.string.reviews,
                    businessModel.reviewCount()));
        }
    }

    private void bindPrice(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.price != null) {
            holder.price.setText(businessModel.price());
        }
    }

    private void bindLocation(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.location != null) {
            LocationModel locationModel = businessModel.locationModel();
            holder.location.setText(activityResources.getString(R.string.location,
                    locationModel.address(), locationModel.city()));
        }
    }

    private void bindCategories(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.categories != null) {
            String categories = stringUtils.join(businessModel.categories(), ", ");
            holder.categories.setText(categories);
        }
    }

    private void bindPhotos(BusinessViewHolder holder) {
        if (holder.photos != null) {
            holder.photos.setAdapter(photosAdapter);
        }
    }

    private void bindPhotosIndicator(BusinessViewHolder holder) {
        if (holder.photosIndicator != null && holder.photos != null) {
            holder.photosIndicator.setViewPager(holder.photos);
        }
    }

    private void bindHours(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.hours != null) {
            hoursAdapter.bindHours(holder.hours, businessModel.hoursModel());
        }
    }

    private void bindOpenIndicator(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.openIndicator != null) {
            hoursAdapter.bindOpenIndicator(holder.openIndicator, businessModel.hoursModel());
        }
    }

    private void bindPhoneNumber(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.phoneNumber != null) {
            holder.phoneNumber.setInfoText(businessModel.phoneNumber());
        }
    }

    private void bindWebsite(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.website != null) {
            holder.website.setInfoText(businessModel.url());
        }
    }

    private void bindPickup(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.pickup != null) {
            holder.pickup.setInfoText(getTransactionTypeSupportIndicatorRes(businessModel,
                    BusinessTransactionTypeModel.PICKUP));
        }
    }

    private void bindDelivery(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.delivery != null) {
            holder.delivery.setInfoText(getTransactionTypeSupportIndicatorRes(businessModel,
                    BusinessTransactionTypeModel.DELIVERY));
        }
    }

    private void bindReservation(BusinessViewHolder holder, BusinessModel businessModel) {
        if (holder.reservation != null) {
            holder.reservation.setInfoText(getTransactionTypeSupportIndicatorRes(businessModel,
                    BusinessTransactionTypeModel.RESERVATION));
        }
    }

    @StringRes
    private int getTransactionTypeSupportIndicatorRes(BusinessModel businessModel,
                                                      BusinessTransactionTypeModel transactionType) {
        return businessModel.transactionTypes().contains(transactionType)
                ? R.string.yes : R.string.no;
    }

}
