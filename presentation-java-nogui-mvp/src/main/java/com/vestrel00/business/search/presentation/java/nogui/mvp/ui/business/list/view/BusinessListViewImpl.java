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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.view;

import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.ApplicationBusinessListView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.presenter.BusinessListPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * An implementation of {@link ApplicationBusinessListView} and {@link BusinessListView}.
 */
@Singleton
final class BusinessListViewImpl implements ApplicationBusinessListView, BusinessListView {

    private final BusinessListPresenter presenter;
    private final Display display;

    @Inject
    BusinessListViewImpl(BusinessListPresenter presenter, Display display) {
        this.presenter = presenter;
        this.display = display;
    }

    @Override
    public void initialize() {
        presenter.onViewInitialized(this);
    }

    @Override
    public void showBusinessesAroundLocation() {
        presenter.onShowBusinessesAroundLocation();
    }

    @Override
    public void showBusinessesAroundCoordinates() {
        presenter.onShowBusinessesAroundCoordinates();
    }

    @Override
    public void showBusiness(BusinessModel business) {
        display.showMessage(business.name() + ", id: " + business.id());
    }

    @Override
    public void showError(String error) {
        display.showError(error);
    }

    @Override
    public LocationModel getLocation() {
        // Use an observable to build the LocationModel for more granular code
        return Observable.just("Enter the address or just enter to skip:")
                .map(display::promptInput)
                .map(address -> LocationModel.builder().address(address))
                .doOnNext(locationBuilder -> locationBuilder.city(
                        display.promptInput("Enter the city or just enter to skip:")))
                .doOnNext(locationBuilder -> locationBuilder.state(
                        display.promptInput("Enter the state or just enter to skip:"))
                )
                .doOnNext(locationBuilder -> locationBuilder.zipCode(
                        display.promptInput("Enter the zip or just enter to skip:"))
                )
                .doOnNext(locationBuilder -> locationBuilder.country(
                        display.promptInput("Enter the country or just enter to skip:"))
                )
                .map(LocationModel.Builder::build)
                .blockingSingle();
    }

    @Override
    public CoordinatesModel getCoordinates() {
        // Use an observable to build the CoordinatesModel in order to wrap any exceptions that may
        // occur when retrieving input
        return Observable.just("Enter latitude:")
                .map(display::promptInput)
                .map(Double::valueOf)
                .map(latitude -> CoordinatesModel.builder().latitude(latitude))
                .doOnNext(coordinatesBuilder -> coordinatesBuilder.longitude(
                        Double.valueOf(display.promptInput("Enter longitude:")))
                )
                .map(CoordinatesModel.Builder::build)
                .doOnError(throwable -> showError(throwable.getMessage()))
                .retry()
                .blockingSingle();
    }
}
