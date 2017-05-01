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

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.options.view.BusinessListOptionsFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.options.view.BusinessListOptionsFragmentListener;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.BaseActivity;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;

/**
 * An activity that uses a {@link BusinessListFragment} to display a list of businesses around
 * a given location or the current location.
 */
public final class BusinessListActivity extends BaseActivity
        implements BusinessListOptionsFragmentListener, BusinessListFragmentListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_list_activity);

        if (savedInstanceState == null) {
            addFragment(R.id.options_container, new BusinessListOptionsFragment());
            addFragment(R.id.content_container, new BusinessListFragment());
        }
    }

    @Override
    public void onSearchAroundLocationClicked(String location) {
        businessListFragment().showBusinessesAroundLocation(location);
    }

    @Override
    public void onSearchAroundCurrentLocationClicked() {
        businessListFragment().showBusinessesAroundCurrentLocation();
    }

    @Override
    public void onShowListView() {
        // TODO (IMPLEMENTATION) - onShowListView();
    }

    @Override
    public void onShowMapView() {
        // TODO (IMPLEMENTATION) - onShowListView();
    }

    @Override
    public void onShowBusinessDetails(BusinessModel businessModel) {
        navigator.toBusinessDetails(businessModel.id());
    }

    private BusinessListFragment businessListFragment() {
        return (BusinessListFragment) fragmentManager.findFragmentById(R.id.content_container);
    }
}
