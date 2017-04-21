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

package com.vestrel00.business.search.presentation.java.nogui.mvp;

import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.businessdetails.view.BusinessDetailsView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.businesslist.view.BusinessListView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.options.view.Option;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.options.view.OptionsView;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * An application with no GUI. Communications are done through the command line (the UI).
 */
@Singleton
final class Application implements Runnable {

    private final OptionsView optionsView;
    private final BusinessListView businessListView;
    private final BusinessDetailsView businessDetailsView;

    @Inject
    Application(OptionsView optionsView, BusinessListView businessListView,
                BusinessDetailsView businessDetailsView) {
        this.optionsView = optionsView;
        this.businessListView = businessListView;
        this.businessDetailsView = businessDetailsView;
    }

    @Override
    public void run() {
        optionsView.initialize();
        businessListView.initialize();
        businessDetailsView.initialize();

        boolean run = true;
        while (run) {
            Option option = optionsView.chooseOption();
            run = handleOption(option);
        }
    }

    private boolean handleOption(Option option) {
        switch (option) {
            case SHOW_BUSINESSES_AROUND_LOCATION:
                businessListView.showBusinessesAroundLocation();
                return true;
            case SHOW_BUSINESSES_AROUND_COORDINATES:
                businessListView.showBusinessesAroundCoordinates();
                return true;
            case SHOW_BUSINESS_DETAILS:
                businessDetailsView.showBusinessDetails();
                return true;
            default:
                return false;
        }
    }
}