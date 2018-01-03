/*
 * Copyright 2018 Vandolf Estrellado
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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.details.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vestrel00.business.search.presentation.java.android.mvp.R;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.common.view.BusinessAdapter;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.common.view.BusinessViewHolder;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.common.view.BusinessViewHolderFactory;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.details.presenter.BusinessDetailsPresenter;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.view.AbstractLoadContentFragment;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;

import javax.inject.Inject;

/**
 * A fragment implementation of {@link BusinessDetailsView}.
 */
public final class BusinessDetailsFragment
        extends AbstractLoadContentFragment<BusinessDetailsPresenter>
        implements BusinessDetailsView {

    private static final String ARG_BUSINESS_ID = "BusinessDetailsFragment.businessId";

    @Inject
    BusinessAdapter businessAdapter;

    @Inject
    BusinessViewHolderFactory businessViewHolderFactory;

    public static BusinessDetailsFragment forBusinessId(String businessId) {
        Bundle args = new Bundle();
        args.putString(ARG_BUSINESS_ID, businessId);

        BusinessDetailsFragment businessDetailsFragment = new BusinessDetailsFragment();
        businessDetailsFragment.setArguments(args);
        return businessDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.business_details_fragment, container, false);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        presenter.onShowBusinessDetails(getBusinessIdArg());
    }

    @Override
    public void showBusinessDetails(BusinessModel businessModel) {
        BusinessViewHolder businessViewHolder = businessViewHolderFactory.create(getView());
        businessAdapter.initializeViewHolder(businessViewHolder);
        businessAdapter.bindViewHolderWithData(businessViewHolder, businessModel);
    }

    private String getBusinessIdArg() {
        return getArguments().getString(ARG_BUSINESS_ID);
    }
}
