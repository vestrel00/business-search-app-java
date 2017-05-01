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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.list.view;

import android.app.Activity;
import android.app.Fragment;

import com.vestrel00.business.search.presentation.android.inject.PerActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.options.view.BusinessListOptionsFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.options.view.BusinessListOptionsFragmentListener;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.list.options.view.BusinessListOptionsFragmentSubcomponent;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.BaseActivityModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Provides business list activity dependencies.
 */
@Module(includes = BaseActivityModule.class,
        subcomponents = {
                BusinessListFragmentSubcomponent.class,
                BusinessListOptionsFragmentSubcomponent.class
        })
abstract class BusinessListActivityModule {

    @Binds
    @PerActivity
    abstract Activity activity(BusinessListActivity businessListActivity);

    @Binds
    @PerActivity
    abstract BusinessListFragmentListener
    businessListFragmentListener(BusinessListActivity businessListActivity);

    @Binds
    @PerActivity
    abstract BusinessListOptionsFragmentListener
    businessListOptionsFragmentListener(BusinessListActivity businessListActivity);

    @Binds
    @IntoMap
    @FragmentKey(BusinessListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    bindBusinessListFragmentInjectorFactory(BusinessListFragmentSubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BusinessListOptionsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    businessListOptionsFragmentInjectorFactory(
            BusinessListOptionsFragmentSubcomponent.Builder builder);
}
