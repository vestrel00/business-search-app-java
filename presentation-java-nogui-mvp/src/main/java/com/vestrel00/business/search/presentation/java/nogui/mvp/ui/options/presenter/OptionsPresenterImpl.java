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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.options.presenter;


import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.ApplicationOption;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.options.view.OptionsView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * An implementatio of {@link OptionsPresenter}.
 */
@Singleton
final class OptionsPresenterImpl implements OptionsPresenter {

    @NonNull
    private OptionsView view;

    @Inject
    OptionsPresenterImpl() {
    }

    @Override
    public void onViewInitialized(@NonNull OptionsView view) {
        this.view = view;
    }

    @Override
    public ApplicationOption onHandleOption(ApplicationOption option) {
        if (option == ApplicationOption.UNKNOWN) {
            view.showError("You must choose a valid option.");
        }
        return option;
    }
}
