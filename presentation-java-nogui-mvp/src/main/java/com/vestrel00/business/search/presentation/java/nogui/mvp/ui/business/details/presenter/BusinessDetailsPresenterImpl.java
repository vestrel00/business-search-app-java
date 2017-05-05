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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.details.presenter;

import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessWithId;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter.BusinessObserver;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter.BusinessObserverFactory;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.details.view.BusinessDetailsView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * An implementation of {@link BusinessDetailsPresenter}.
 */
@Singleton
final class BusinessDetailsPresenterImpl implements BusinessDetailsPresenter {

    private final GetBusinessWithId getBusinessWithId;
    private final BusinessObserverFactory businessObserverFactory;
    private final UseCaseHandler useCaseHandler;

    @NonNull
    private BusinessDetailsView view;

    @Inject
    BusinessDetailsPresenterImpl(GetBusinessWithId getBusinessWithId,
                                 BusinessObserverFactory businessObserverFactory,
                                 UseCaseHandler useCaseHandler) {
        this.getBusinessWithId = getBusinessWithId;
        this.businessObserverFactory = businessObserverFactory;
        this.useCaseHandler = useCaseHandler;
    }

    @Override
    public void onViewInitialized(@NonNull BusinessDetailsView view) {
        this.view = view;
    }

    @Override
    public void onShowBusinessDetails() {
        String businessId = view.getBusinessId();
        showBusinessWithIdMessage(businessId);
        showBusinessDetails(businessId);
    }

    private void showBusinessDetails(String businessId) {
        BusinessObserver observer = businessObserverFactory.create(view);
        useCaseHandler.execute(getBusinessWithId, businessId, observer);
    }

    private void showBusinessWithIdMessage(String businessid) {
        view.showMessage("\nShowing business with id " + businessid + "\n");
    }
}
