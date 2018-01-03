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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.list.view;

import com.vestrel00.business.search.presentation.java.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.business.list.presenter.BusinessListPresenter;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.view.OnItemClickListener;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;

import javax.inject.Inject;

/**
 * Listens for clicks on a business item in the list and invokes the
 * {@link BusinessListPresenter#onBusinessClicked(BusinessModel)}.
 */
@PerFragment
final class BusinessListItemClickListener implements OnItemClickListener<BusinessModel> {

    private final BusinessListPresenter presenter;

    @Inject
    BusinessListItemClickListener(BusinessListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClicked(BusinessModel businessModel) {
        presenter.onBusinessClicked(businessModel);
    }
}
