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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.list.view;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vestrel00.business.search.common.MathUtils;
import com.vestrel00.business.search.common.StringUtils;
import com.vestrel00.business.search.presentation.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.OnItemViewClickListener;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.OnItemViewClickListenerFactory;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

/**
 * A view adapter that binds a collection of {@link BusinessModel}s to a collection of
 * {@link BusinessListItemViewHolder}s in a recycler view.
 */
@PerFragment
final class BusinessListAdapter extends RecyclerView.Adapter<BusinessListItemViewHolder> {

    private static final String STATE_BUSINESS_MODELS = "BusinessListAdapter.businessModels";

    private final List<BusinessModel> businessModels;
    private final BusinessListItemViewHolderFactory itemViewHolderFactory;
    private final OnItemViewClickListenerFactory<BusinessModel> onItemViewClickListenerFactory;
    private final LayoutInflater layoutInflater;
    private final StringUtils stringUtils;
    private final MathUtils mathUtils;
    private final Resources activityResources;

    @Inject
    BusinessListAdapter(List<BusinessModel> businessModels,
                        BusinessListItemViewHolderFactory itemViewHolderFactory,
                        OnItemViewClickListenerFactory<BusinessModel>
                                onItemViewClickListenerFactory,
                        LayoutInflater layoutInflater,
                        StringUtils stringUtils, MathUtils mathUtils,
                        Resources activityResources) {
        this.businessModels = businessModels;
        this.itemViewHolderFactory = itemViewHolderFactory;
        this.onItemViewClickListenerFactory = onItemViewClickListenerFactory;
        this.layoutInflater = layoutInflater;
        this.stringUtils = stringUtils;
        this.mathUtils = mathUtils;
        this.activityResources = activityResources;
    }

    @Override
    public BusinessListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.business_list_item, parent, false);
        BusinessListItemViewHolder holder = itemViewHolderFactory.create(itemView);
        initializeViewHolder(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(BusinessListItemViewHolder holder, int position) {
        BusinessModel businessModel = businessModels.get(position);
        bindViewHolderWithData(holder, businessModel);

        OnItemViewClickListener onItemViewClickListener
                = onItemViewClickListenerFactory.create(businessModel);
        holder.itemView.setOnClickListener(onItemViewClickListener);
    }

    @Override
    public int getItemCount() {
        return businessModels.size();
    }

    void addBusinessModel(BusinessModel businessModel) {
        businessModels.add(businessModel);
    }

    void clearBusinessModels() {
        businessModels.clear();
    }

    void notifyBusinessModelsChanged() {
        notifyDataSetChanged();
    }

    void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STATE_BUSINESS_MODELS, (Serializable) businessModels);
    }

    @SuppressWarnings("unchecked")
    void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Serializable businessModels = savedInstanceState.getSerializable(STATE_BUSINESS_MODELS);
            addBusinessModels((List<BusinessModel>) businessModels);
        }
    }

    private void addBusinessModels(List<BusinessModel> businessModels) {
        this.businessModels.addAll(businessModels);
    }

    private void initializeViewHolder(BusinessListItemViewHolder holder) {
        holder.rating.setStepSize(0.1f);
        holder.rating.setNumStars(BusinessModel.MAX_RATING);
    }

    private void bindViewHolderWithData(BusinessListItemViewHolder holder,
                                        BusinessModel businessModel) {
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
