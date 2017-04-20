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

package com.vestrel00.business.search.presentation.java.nogui.mvp.businessdetails.presenter;

import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessWithId;
import com.vestrel00.business.search.presentation.java.nogui.mvp.businessdetails.view.BusinessDetailsView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * Presents business details.
 */
@Singleton
public final class BusinessDetailsPresenter {

    private final GetBusinessWithId getBusinessWithId;
    private final BusinessDetailsObserverFactory businessDetailsObserverFactory;
    private final UseCaseHandler useCaseHandler;

    @NonNull
    private BusinessDetailsView view;

    @Inject
    BusinessDetailsPresenter(GetBusinessWithId getBusinessWithId,
                             BusinessDetailsObserverFactory businessDetailsObserverFactory,
                             UseCaseHandler useCaseHandler) {
        this.getBusinessWithId = getBusinessWithId;
        this.businessDetailsObserverFactory = businessDetailsObserverFactory;
        this.useCaseHandler = useCaseHandler;
    }

    public void setView(@NonNull BusinessDetailsView view) {
        this.view = view;
    }

    public void showBusinessDetails(String businessId) {
        BusinessDetailsObserver observer = businessDetailsObserverFactory.create(view);
        useCaseHandler.execute(getBusinessWithId, businessId, observer);
    }
}
