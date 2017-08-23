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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.details.presenter;

import com.vestrel00.business.search.presentation.java.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.details.view.BusinessDetailsView;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * An implementation of {@link BusinessDetailsPresenter}.
 */
@PerFragment
final class BusinessDetailsPresenterImpl extends BasePresenter<BusinessDetailsView>
        implements BusinessDetailsPresenter {

    @Inject
    BusinessDetailsPresenterImpl(BusinessDetailsView view) {
        super(view);
    }

    @Override
    public void onShowBusinessDetails(String businessId) {
        // TODO (IMPLEMENTATION) - execute get business details use case
    }
}
