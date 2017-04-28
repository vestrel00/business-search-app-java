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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.options.view;

import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.ApplicationOption;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.ApplicationOptionsView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.options.presenter.OptionsPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * An implementation of {@link ApplicationOptionsView} and {@link OptionsView}.
 */
@Singleton
final class OptionsViewImpl implements ApplicationOptionsView, OptionsView {

    private static final String OPTIONS = "\nYou have the following options:\n"
            + "l: list businesses around a location (address, city, state, zip, and/or country)\n"
            + "c: list businesses around coordinates (latitude, longitude)\n"
            + "s: show more business details\n"
            + "q: quit\n";

    private final OptionsPresenter presenter;
    private final Display display;

    @Inject
    OptionsViewImpl(OptionsPresenter presenter, Display display) {
        this.presenter = presenter;
        this.display = display;
    }

    @Override
    public void initialize() {
        presenter.onViewInitialized(this);
    }

    @Override
    public ApplicationOption chooseOption() {
        return Observable.just(OPTIONS)
                .map(display::promptInput)
                .map(this::parseOption)
                .map(presenter::onHandleOption)
                .blockingSingle();
    }

    @Override
    public void showError(String error) {
        display.showError(error);
    }

    private ApplicationOption parseOption(String option) {
        switch (option.toLowerCase()) {
            case "l":
                return ApplicationOption.SHOW_BUSINESSES_AROUND_LOCATION;
            case "c":
                return ApplicationOption.SHOW_BUSINESSES_AROUND_COORDINATES;
            case "s":
                return ApplicationOption.SHOW_BUSINESS_DETAILS;
            case "q":
                return ApplicationOption.QUIT;
            default:
                return ApplicationOption.UNKNOWN;
        }
    }
}
