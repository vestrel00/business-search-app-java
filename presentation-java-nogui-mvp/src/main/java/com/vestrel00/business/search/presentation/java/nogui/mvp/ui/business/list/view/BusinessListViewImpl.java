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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.view;

import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.ApplicationBusinessListView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.view.AbstractBusinessView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.presenter.BusinessListPresenter;
import com.vestrel00.business.search.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * An implementation of {@link ApplicationBusinessListView} and {@link BusinessListView}.
 */
@Singleton
final class BusinessListViewImpl extends AbstractBusinessView
        implements ApplicationBusinessListView, BusinessListView {

    private static final String SHOW_BUSINESSES_AROUND_MESSAGE
            = "\nShowing businesses around %s\n";

    private final BusinessListPresenter presenter;
    private final StringUtils stringUtils;

    @Inject
    BusinessListViewImpl(Display display, StringUtils stringUtils,
                         BusinessListPresenter presenter) {
        super(display);
        this.stringUtils = stringUtils;
        this.presenter = presenter;
    }

    @Override
    public void initialize() {
        presenter.onViewInitialized(this);
    }

    @Override
    public void showBusinessesAroundLocation() {
        presenter.onShowBusinessesAroundLocation();
    }

    @Override
    public void showBusinessesAroundCoordinates() {
        presenter.onShowBusinessesAroundCoordinates();
    }

    @Override
    public void showBusiness(BusinessModel business) {
        display.showMessage(business.name() + ", id: " + business.id());
    }

    @Override
    public void showBusinessesAroundMessage(String around) {
        showMessage(stringUtils.format(SHOW_BUSINESSES_AROUND_MESSAGE, around));
    }

    @Override
    public String getAddress() {
        return display.promptInput("Enter the address or just enter to skip:");
    }

    @Override
    public String getCity() {
        return display.promptInput("Enter the city or just enter to skip:");
    }

    @Override
    public String getState() {
        return display.promptInput("Enter the state or just enter to skip:");
    }

    @Override
    public String getZip() {
        return display.promptInput("Enter the zip or just enter to skip:");
    }

    @Override
    public String getCountry() {
        return display.promptInput("Enter the country or just enter to skip:");
    }

    @Override
    public double getLatitude() {
        return Double.valueOf(display.promptInput("Enter the latitude or just enter to skip:"));
    }

    @Override
    public double getLongitude() {
        return Double.valueOf(display.promptInput("Enter the longitude or just enter to skip:"));
    }
}
