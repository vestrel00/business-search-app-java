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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.list.presenter;

import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundCoordinates;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundLocationString;
import com.vestrel00.business.search.presentation.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.view.BusinessListView;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.presenter.BasePresenter;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;

import javax.inject.Inject;

/**
 * An implementation of {@link BusinessListPresenter}.
 */
@PerFragment
final class BusinessListPresenterImpl extends BasePresenter<BusinessListView>
        implements BusinessListPresenter {

    private final GetBusinessesAroundLocationString getBusinessesAroundLocation;
    private final GetBusinessesAroundCoordinates getBusinessesAroundCoordinates;
    private final BusinessListObserverFactory businessListObserverFactory;
    private final UseCaseHandler useCaseHandler;

    @Inject
    BusinessListPresenterImpl(BusinessListView view,
                              GetBusinessesAroundLocationString getBusinessesAroundLocation,
                              GetBusinessesAroundCoordinates getBusinessesAroundCoordinates,
                              BusinessListObserverFactory businessListObserverFactory,
                              UseCaseHandler useCaseHandler) {
        super(view);
        this.getBusinessesAroundLocation = getBusinessesAroundLocation;
        this.getBusinessesAroundCoordinates = getBusinessesAroundCoordinates;
        this.businessListObserverFactory = businessListObserverFactory;
        this.useCaseHandler = useCaseHandler;
    }

    @Override
    public void onListBusinessesAroundLocation(String location) {
        clearUseCases();
        useCaseHandler.execute(getBusinessesAroundLocation, location,
                businessListObserverFactory.create());
    }

    @Override
    public void onListBusinessesAroundCurrentLocation() {
        clearUseCases();
        // TODO (IMPLEMENTATION) - onListBusinessesAroundCurrentLocation
        // https://developer.android.com/training/location/retrieve-current.html
    }

    @Override
    public void onRetryPreviousAction() {
        clearUseCases();
        useCaseHandler.executePreviousUseCase(businessListObserverFactory.create());
    }

    @Override
    public void onBusinessClicked(BusinessModel businessModel) {
        view.viewBusinessDetails(businessModel);
    }

    @Override
    public void onEnd() {
        super.onEnd();
        useCaseHandler.clear();
    }

    private void clearUseCases() {
        useCaseHandler.clear();
    }
}
