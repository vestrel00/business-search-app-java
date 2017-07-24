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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.details;

import android.app.Activity;

import com.vestrel00.business.search.presentation.android.inject.PerActivity;
import com.vestrel00.business.search.presentation.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.view.BusinessDetailsFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.view.BusinessDetailsFragmentModule;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.BaseActivityModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides business details activity dependencies.
 */
@Module(includes = BaseActivityModule.class)
public abstract class BusinessDetailsActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = BusinessDetailsFragmentModule.class)
    abstract BusinessDetailsFragment businessDetailsFragmentInjector();

    @Binds
    @PerActivity
    abstract Activity activity(BusinessDetailsActivity businessDetailsActivity);
}
