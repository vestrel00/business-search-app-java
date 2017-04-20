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

package com.vestrel00.business.search.presentation.java.nogui.mvp.businesslist.presenter;

import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundCoordinates;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundLocation;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperFactory;
import com.vestrel00.business.search.presentation.java.nogui.mvp.businesslist.view.BusinessListView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.businesslist.view.BusinessListViewOption;
import com.vestrel00.business.search.presentation.java.nogui.mvp.businesslist.view.BusinessListViewResult;
import com.vestrel00.business.search.presentation.java.nogui.mvp.businesslist.view.BusinessListViewResultFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * Presents the business list.
 */
@Singleton
public final class BusinessListPresenter {

    private final GetBusinessesAroundLocation getBusinessesAroundLocation;
    private final GetBusinessesAroundCoordinates getBusinessesAroundCoordinates;
    private final ModelMapperFactory modelMapperFactory;
    private final BusinessListObserverFactory businessListObserverFactory;
    private final UseCaseHandler useCaseHandler;
    private final BusinessListViewResultFactory viewResultFactory;

    @NonNull
    private BusinessListView view;

    @Inject
    BusinessListPresenter(GetBusinessesAroundLocation getBusinessesAroundLocation,
                          GetBusinessesAroundCoordinates getBusinessesAroundCoordinates,
                          ModelMapperFactory modelMapperFactory,
                          BusinessListObserverFactory businessListObserverFactory,
                          UseCaseHandler useCaseHandler,
                          BusinessListViewResultFactory viewResultFactory) {
        this.getBusinessesAroundLocation = getBusinessesAroundLocation;
        this.getBusinessesAroundCoordinates = getBusinessesAroundCoordinates;
        this.modelMapperFactory = modelMapperFactory;
        this.businessListObserverFactory = businessListObserverFactory;
        this.useCaseHandler = useCaseHandler;
        this.viewResultFactory = viewResultFactory;
    }

    public void setView(@NonNull BusinessListView view) {
        this.view = view;
    }

    public BusinessListViewResult handleOption(BusinessListViewOption option) {
        switch (option) {
            case SHOW_AROUND_LOCATION:
                showBusinessesAroundLocation(view.getLocation());
                return viewResultFactory.showOptions();
            case SHOW_AROUND_COORDINATES:
                showBusinessesAroundCoordinates(view.getCoordinates());
                return viewResultFactory.showOptions();
            case SHOW_BUSINESS_DETAILS:
                return viewResultFactory.showBusinessDetails(view.getBusinessId());
            case QUIT:
                return viewResultFactory.quit();
            default:
                return viewResultFactory.showOptions();
        }
    }

    private void showBusinessesAroundLocation(LocationModel locationModel) {
        Location location = modelMapperFactory.locationModelMapper()
                .map(locationModel);
        BusinessListObserver observer = businessListObserverFactory.create(view);
        useCaseHandler.execute(getBusinessesAroundLocation, location, observer);
    }

    private void showBusinessesAroundCoordinates(CoordinatesModel coordinatesModel) {
        Coordinates coordinates = modelMapperFactory.coordinatesEntityMapper()
                .map(coordinatesModel);
        BusinessListObserver observer = businessListObserverFactory.create(view);
        useCaseHandler.execute(getBusinessesAroundCoordinates, coordinates, observer);
    }
}
