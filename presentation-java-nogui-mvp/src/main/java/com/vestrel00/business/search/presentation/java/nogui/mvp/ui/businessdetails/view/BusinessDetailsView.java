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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.businessdetails.view;

import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.businessdetails.presenter.BusinessDetailsPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Shows business details.
 */
@Singleton
public final class BusinessDetailsView {

    private final BusinessDetailsPresenter presenter;
    private final Display display;

    @Inject
    BusinessDetailsView(BusinessDetailsPresenter presenter, Display display) {
        this.presenter = presenter;
        this.display = display;
    }

    public void initialize() {
        presenter.setView(this);
    }

    public void showBusinessDetails() {
        presenter.showBusinessDetails();
    }

    public void showBusiness(BusinessModel business) {
        display.showMessage(business.toString().replace(",", "\n"));
    }

    public void showError(Throwable error) {
        display.showError(error);
    }

    public String getBusinessId() {
        return display.promptInput("Enter a business id:");
    }
}
