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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.details.view;

import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.ApplicationBusinessDetailsView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.details.presenter.BusinessDetailsPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * An implementation of {@link ApplicationBusinessDetailsView} and {@link BusinessDetailsView}.
 */
@Singleton
final class BusinessDetailsViewImpl implements ApplicationBusinessDetailsView, BusinessDetailsView {

    private final BusinessDetailsPresenter presenter;
    private final Display display;

    @Inject
    BusinessDetailsViewImpl(BusinessDetailsPresenter presenter, Display display) {
        this.presenter = presenter;
        this.display = display;
    }

    @Override
    public void initialize() {
        presenter.onViewInitialized(this);
    }

    @Override
    public void showBusinessDetails() {
        presenter.onShowBusinessDetails();
    }

    @Override
    public void showBusiness(BusinessModel business) {
        display.showMessage(business.toString().replace(",", "\n"));
    }

    @Override
    public void showError(String error) {
        display.showError(error);
    }

    @Override
    public String getBusinessId() {
        return display.promptInput("Enter a business id:");
    }
}
