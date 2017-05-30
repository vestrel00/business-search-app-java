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
import android.app.Fragment;

import com.vestrel00.business.search.presentation.android.inject.PerActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.view.BusinessDetailsFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.view.BusinessDetailsFragmentSubcomponent;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.BaseActivityModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Provides business details activity dependencies.
 */
@Module(includes = BaseActivityModule.class,
        subcomponents = BusinessDetailsFragmentSubcomponent.class)
abstract class BusinessDetailsActivityModule {

    @Binds
    @IntoMap
    @FragmentKey(BusinessDetailsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindBusinessDetailsFragmentInjectorFactory(BusinessDetailsFragmentSubcomponent.Builder builder);

    @Binds
    @PerActivity
    abstract Activity activity(BusinessDetailsActivity businessDetailsActivity);
}
