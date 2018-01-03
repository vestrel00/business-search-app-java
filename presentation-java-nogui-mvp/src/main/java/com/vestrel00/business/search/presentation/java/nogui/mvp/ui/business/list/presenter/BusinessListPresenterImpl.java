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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.presenter;

import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundCoordinates;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundLocation;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperHolder;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter.BusinessObserver;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter.BusinessObserverFactory;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.view.BusinessListView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * An implementation of {@link BusinessListPresenter}.
 */
@Singleton
final class BusinessListPresenterImpl implements BusinessListPresenter {

    private final GetBusinessesAroundLocation getBusinessesAroundLocation;
    private final GetBusinessesAroundCoordinates getBusinessesAroundCoordinates;
    private final ModelMapperHolder modelMapperHolder;
    private final BusinessObserverFactory businessObserverFactory;
    private final UseCaseHandler useCaseHandler;
    private final BusinessListModelFactory modelFactory;

    @NonNull
    private BusinessListView view;

    @Inject
    BusinessListPresenterImpl(GetBusinessesAroundLocation getBusinessesAroundLocation,
                              GetBusinessesAroundCoordinates getBusinessesAroundCoordinates,
                              ModelMapperHolder modelMapperHolder,
                              BusinessObserverFactory businessObserverFactory,
                              UseCaseHandler useCaseHandler,
                              BusinessListModelFactory modelFactory) {
        this.getBusinessesAroundLocation = getBusinessesAroundLocation;
        this.getBusinessesAroundCoordinates = getBusinessesAroundCoordinates;
        this.modelMapperHolder = modelMapperHolder;
        this.businessObserverFactory = businessObserverFactory;
        this.useCaseHandler = useCaseHandler;
        this.modelFactory = modelFactory;
    }

    @Override
    public void onViewInitialized(@NonNull BusinessListView view) {
        this.view = view;
    }

    @Override
    public void onShowBusinessesAroundLocation() {
        LocationModel locationModel = modelFactory.getLocation(view);
        showBusinessesAroundMessage(locationModel.toString());
        showBusinessesAroundLocation(locationModel);
    }

    @Override
    public void onShowBusinessesAroundCoordinates() {
        CoordinatesModel coordinatesModel = modelFactory.getCoordinates(view);
        showBusinessesAroundMessage(coordinatesModel.toString());
        showBusinessesAroundCoordinates(coordinatesModel);
    }

    private void showBusinessesAroundLocation(LocationModel locationModel) {
        Location location = modelMapperHolder.locationModelMapper()
                .map(locationModel);
        BusinessObserver observer = businessObserverFactory.create(view);
        useCaseHandler.execute(getBusinessesAroundLocation, location, observer);
    }

    private void showBusinessesAroundCoordinates(CoordinatesModel coordinatesModel) {
        Coordinates coordinates = modelMapperHolder.coordinatesModelMapper()
                .map(coordinatesModel);
        BusinessObserver observer = businessObserverFactory.create(view);
        useCaseHandler.execute(getBusinessesAroundCoordinates, coordinates, observer);
    }

    private void showBusinessesAroundMessage(String around) {
        view.showBusinessesAroundMessage(around);
    }
}
