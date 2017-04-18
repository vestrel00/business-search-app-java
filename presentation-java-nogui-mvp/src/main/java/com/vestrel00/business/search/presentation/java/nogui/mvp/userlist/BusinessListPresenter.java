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

package com.vestrel00.business.search.presentation.java.nogui.mvp.userlist;

import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundCoordinates;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundLocation;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.model.mapper.CoordinatesModelMapper;
import com.vestrel00.business.search.presentation.java.model.mapper.LocationModelMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * Presents the user list.
 */
@Singleton
final class BusinessListPresenter {

    private final GetBusinessesAroundLocation getBusinessesAroundLocation;
    private final GetBusinessesAroundCoordinates getBusinessesAroundCoordinates;
    private final LocationModelMapper locationModelMapper;
    private final CoordinatesModelMapper coordinatesModelMapper;
    private final BusinessListObserverFactory businessListObserverFactory;
    private final UseCaseHandler useCaseHandler;

    @NonNull
    private BusinessListView view;

    @Inject
    BusinessListPresenter(GetBusinessesAroundLocation getBusinessesAroundLocation,
                          GetBusinessesAroundCoordinates getBusinessesAroundCoordinates,
                          LocationModelMapper locationModelMapper,
                          CoordinatesModelMapper coordinatesModelMapper,
                          BusinessListObserverFactory businessListObserverFactory,
                          UseCaseHandler useCaseHandler) {
        this.getBusinessesAroundLocation = getBusinessesAroundLocation;
        this.getBusinessesAroundCoordinates = getBusinessesAroundCoordinates;
        this.locationModelMapper = locationModelMapper;
        this.coordinatesModelMapper = coordinatesModelMapper;
        this.businessListObserverFactory = businessListObserverFactory;
        this.useCaseHandler = useCaseHandler;
    }

    void setView(@NonNull BusinessListView view) {
        this.view = view;
    }

    void showBusinessesAroundLocation(LocationModel locationModel) {
        Location location = locationModelMapper.map(locationModel);
        BusinessListObserver observer = businessListObserverFactory.create(view);
        useCaseHandler.execute(getBusinessesAroundLocation, location, observer);
    }

    void showBusinessesAroundCoordinates(CoordinatesModel coordinatesModel) {
        Coordinates coordinates = coordinatesModelMapper.map(coordinatesModel);
        BusinessListObserver observer = businessListObserverFactory.create(view);
        useCaseHandler.execute(getBusinessesAroundCoordinates, coordinates, observer);
    }
}
