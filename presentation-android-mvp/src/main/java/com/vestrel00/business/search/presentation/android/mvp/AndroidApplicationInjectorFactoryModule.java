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

package com.vestrel00.business.search.presentation.android.mvp;

import android.app.Activity;
import android.app.Fragment;

import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.BusinessSearchActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.BusinessSearchActivitySubcomponent;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.view.BusinessListFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.view.BusinessListFragmentSubcomponent;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.options.view.BusinessSearchOptionsFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.options.view.BusinessSearchOptionsFragmentSubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Provides Android Application injector factory dependencies.
 */
// FIXME? (DAGGER) - Migrate over to @ContributesAndroidInjector when multi-layer subcomponent
// binding becomes supported. Currently, there is no way to specify the subcomponents of an
// activity module so that fragment scope subcomponents are bound properly.
// E.G. BusinessSearchActivityModule specifies the following subcomponents:
// - BusinessSearchOptionsFragmentSubcomponent
// - BusinessListFragmentSubcomponent
// There is no way to set this up using @ContributesAndroidInjector as of dagger 2.11-rc2
@Module
abstract class AndroidApplicationInjectorFactoryModule {

    @Binds
    @IntoMap
    @ActivityKey(BusinessSearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindBusinessSearchActivityInjectorFactory(BusinessSearchActivitySubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BusinessListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindBusinessListFragmentInjectorFactory(BusinessListFragmentSubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BusinessSearchOptionsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    businessOptionsFragmentInjectorFactory(
            BusinessSearchOptionsFragmentSubcomponent.Builder builder);
}
