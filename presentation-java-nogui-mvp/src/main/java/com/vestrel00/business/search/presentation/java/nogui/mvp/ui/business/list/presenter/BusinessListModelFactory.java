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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.presenter;

import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.view.BusinessListView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Creates new instances of {@link LocationModel} and {@link CoordinatesModel} using the
 * {@link BusinessListView}.
 */
@Singleton
final class BusinessListModelFactory {

    @Inject
    BusinessListModelFactory() {
    }

    LocationModel getLocation(BusinessListView businessListView) {
        // Use an observable to build the LocationModel for more granular code
        return Observable.just(LocationModel.builder())
                .doOnNext(locationBuilder -> locationBuilder.address(businessListView.getAddress()))
                .doOnNext(locationBuilder -> locationBuilder.city(businessListView.getCity()))
                .doOnNext(locationBuilder -> locationBuilder.city(businessListView.getState()))
                .doOnNext(locationBuilder -> locationBuilder.city(businessListView.getZip()))
                .doOnNext(locationBuilder -> locationBuilder.city(businessListView.getCountry()))
                .map(LocationModel.Builder::build)
                .blockingSingle();
    }

    CoordinatesModel getCoordinates(BusinessListView businessListView) {
        // Use an observable to build the CoordinatesModel in order to wrap any exceptions that may
        // occur when retrieving input
        return Observable.just(CoordinatesModel.builder())
                .doOnNext(coordinatesBuilder -> coordinatesBuilder.latitude(
                        Double.valueOf(businessListView.getLatitude()))
                )
                .doOnNext(coordinatesBuilder -> coordinatesBuilder.longitude(
                        Double.valueOf(businessListView.getLongitude()))
                )
                .map(CoordinatesModel.Builder::build)
                .doOnError(throwable -> businessListView.showError(throwable.getMessage()))
                .retry()
                .blockingSingle();
    }
}
