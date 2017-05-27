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

import com.vestrel00.business.search.common.MathUtils;
import com.vestrel00.business.search.common.StringUtils;
import com.vestrel00.business.search.presentation.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;

import javax.inject.Inject;

/**
 * A view adapter that binds a {@link BusinessModel} to a view held by a
 * {@link BusinessItemViewHolder}.
 */
@PerFragment
public final class BusinessItemAdapter {

    private final StringUtils stringUtils;
    private final MathUtils mathUtils;
    private final Resources activityResources;

    @Inject
    BusinessItemAdapter(StringUtils stringUtils, MathUtils mathUtils, Resources activityResources) {
        this.stringUtils = stringUtils;
        this.mathUtils = mathUtils;
        this.activityResources = activityResources;
    }

    public void initializeViewHolder(BusinessItemViewHolder holder) {
        holder.rating.setStepSize(0.1f);
        holder.rating.setNumStars(BusinessModel.MAX_RATING);
    }

    public void bindViewHolderWithData(BusinessItemViewHolder holder, BusinessModel businessModel) {
        holder.image.setImageURI(Uri.parse(businessModel.imageUrl()));
        holder.name.setText(businessModel.name());
        holder.distance.setText(activityResources.getString(R.string.distance,
                mathUtils.toMiles(businessModel.distanceInMeters())));
        holder.rating.setRating(businessModel.rating());
        holder.reviews.setText(activityResources.getString(R.string.reviews,
                businessModel.reviewCount()));
        holder.price.setText(businessModel.price());

        LocationModel locationModel = businessModel.locationModel();
        holder.location.setText(activityResources.getString(R.string.location,
                locationModel.address(), locationModel.city()));

        String categories = stringUtils.join(businessModel.categories(), ", ");
        holder.categories.setText(categories);
    }

}
