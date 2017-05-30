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

import com.vestrel00.business.search.presentation.android.PresentationAndroidModule;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.BusinessDetailsActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.BusinessDetailsActivitySubcomponent;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.BusinessSearchActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.BusinessSearchActivitySubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Provides Android Application dependencies.
 */
// FIXME? (DAGGER) - Migrate over to @ContributesAndroidInjector when multi-layer subcomponent
// binding becomes supported. Currently, there is no way to specify the subcomponents of an
// activity module so that fragment scope subcomponents are bound properly.
// E.G. BusinessSearchActivityModule specifies the following subcomponents:
// - BusinessSearchOptionsFragmentSubcomponent
// - BusinessListFragmentSubcomponent
// There is no way to set this up using @ContributesAndroidInjector as of dagger 2.11-rc2
@Module(includes = {
        AndroidInjectionModule.class,
        PresentationAndroidModule.class
}, subcomponents = {
        BusinessSearchActivitySubcomponent.class,
        BusinessDetailsActivitySubcomponent.class
})
abstract class AndroidApplicationModule {

    @Binds
    @IntoMap
    @ActivityKey(BusinessSearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindBusinessSearchActivityInjectorFactory(BusinessSearchActivitySubcomponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(BusinessDetailsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    businessDetailsActivityInjectorFactory(BusinessDetailsActivitySubcomponent.Builder builder);
}
