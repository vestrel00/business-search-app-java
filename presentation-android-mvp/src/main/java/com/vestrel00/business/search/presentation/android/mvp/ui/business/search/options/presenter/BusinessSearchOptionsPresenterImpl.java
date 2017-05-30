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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.search.options.presenter;

import android.os.Bundle;

import com.vestrel00.business.search.presentation.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.options.view.BusinessSearchOptionsView;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.presenter.BasePresenter;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;

/**
 * An implementation of {@link BusinessSearchOptionsPresenter}.
 */
@PerFragment
final class BusinessSearchOptionsPresenterImpl extends BasePresenter<BusinessSearchOptionsView>
        implements BusinessSearchOptionsPresenter {

    private static final String STATE_CURRENT_LOCATION_INPUT
            = "BusinessSearchOptionsPresenterImpl.currentLocationInput";

    private static final String STATE_USE_CURRENT_LOCATION
            = "BusinessSearchOptionsPresenterImpl.useCurrentLocationInput";

    private String currentLocationInput;
    private boolean useCurrentLocation;

    @Inject
    BusinessSearchOptionsPresenterImpl(BusinessSearchOptionsView view) {
        super(view);
    }

    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentLocationInput = savedInstanceState.getString(STATE_CURRENT_LOCATION_INPUT);
            useCurrentLocation = savedInstanceState.getBoolean(STATE_USE_CURRENT_LOCATION);
        }

        view.setLocationInputEnabled(!useCurrentLocation);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_CURRENT_LOCATION_INPUT, currentLocationInput);
        outState.putBoolean(STATE_USE_CURRENT_LOCATION, view.useCurrentLocationChecked());
    }

    @Override
    public void onSearchAroundLocationClicked() {
        showBusinessesAroundLocationInput();
    }

    @Override
    public void onToggleSearchAroundCurrentLocation(boolean checked) {
        view.setLocationInputEnabled(!checked);

        if (checked) {
            currentLocationInput = view.getLocationInput();
            view.setLocationInputToUsingCurrentLocation();
            view.showBusinessesAroundCurrentLocation();
        } else {
            view.setLocationInput(currentLocationInput);
            showBusinessesAroundLocationInput();
        }
    }

    @Override
    public void onToggleMapListView(boolean checked) {
        if (checked) {
            view.showMapView();
        } else {
            view.showListView();
        }
    }

    private void showBusinessesAroundLocationInput() {
        view.showBusinessesAroundLocation(view.getLocationInput());
    }
}
