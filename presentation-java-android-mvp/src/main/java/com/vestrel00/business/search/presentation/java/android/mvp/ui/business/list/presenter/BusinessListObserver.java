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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.list.presenter;

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.list.view.BusinessListView;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.presenter.AbstractLoadContentViewObserver;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperHolder;

/**
 * An observer that renders a list of businesses or retry/error.
 */
final class BusinessListObserver
        extends AbstractLoadContentViewObserver<BusinessListView, Business> {

    private final ModelMapperHolder modelMapperHolder;

    BusinessListObserver(BusinessListView view, ModelMapperHolder modelMapperHolder) {
        super(view);
        this.modelMapperHolder = modelMapperHolder;
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.removeAllBusinessesFromShowing();
    }

    @Override
    public void onNext(Business business) {
        BusinessModel businessModel = modelMapperHolder.businessModelMapper().map(business);
        view.addBusinessToShow(businessModel);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        view.showBusinesses();
    }
}
