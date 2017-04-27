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

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.view.BusinessListView;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Observer for business lists obtained from a use case.
 */
final class BusinessListObserver extends DisposableSingleObserver<List<Business>> {

    private final BusinessListView view;
    private final ModelMapperProvider modelMapperProvider;

    BusinessListObserver(BusinessListView view, ModelMapperProvider modelMapperProvider) {
        this.view = view;
        this.modelMapperProvider = modelMapperProvider;
    }

    @Override
    public void onSuccess(@NonNull List<Business> businesses) {
        Observable.fromIterable(businesses)
                .map(modelMapperProvider.businessModelMapper()::map)
                .toList(businesses.size())
                .subscribe(view::renderBusinessList);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        view.showError(e.getMessage());
    }
}
