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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter;

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.presentation.java.common.AbstractDisposableObserver;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperHolder;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.view.BusinessView;

/**
 * An observer that shows a business or an error.
 */
public final class BusinessObserver extends AbstractDisposableObserver<Business> {

    private final BusinessView view;
    private final ModelMapperHolder modelMapperHolder;

    BusinessObserver(BusinessView view, ModelMapperHolder modelMapperHolder) {
        this.view = view;
        this.modelMapperHolder = modelMapperHolder;
    }

    @Override
    public void onNext(Business business) {
        BusinessModel businessModel = modelMapperHolder.businessModelMapper().map(business);
        view.showBusiness(businessModel);
    }

    @Override
    public void onError(Throwable e) {
        view.showError(e.getMessage());
    }
}
