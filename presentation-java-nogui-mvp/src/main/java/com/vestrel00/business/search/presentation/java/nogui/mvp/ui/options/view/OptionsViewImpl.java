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

/**
 * An implementation of {@link ApplicationOptionsView} and {@link OptionsView}.
 */
@Singleton
final class OptionsViewImpl implements ApplicationOptionsView, OptionsView {


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
        return presenter.onChooseOption();
    }

    @Override
    public void showOptions(String options) {
        display.showMessage(options);
    }

    @Override
    public void showError(String error) {
        display.showError(error);
    }

    @Override
    public String getOption() {
        return display.promptInput();
    }
}
