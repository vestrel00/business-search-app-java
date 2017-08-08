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

import android.app.Application;

import com.vestrel00.business.search.presentation.android.PresentationAndroidModule;
import com.vestrel00.business.search.presentation.android.inject.PerActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.BusinessDetailsActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.BusinessDetailsActivityModule;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.BusinessSearchActivity;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.search.BusinessSearchActivityModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides Android Application dependencies.
 */
@Module(includes = {
        AndroidInjectionModule.class,
        PresentationAndroidModule.class
})
abstract class AndroidApplicationModule {

    @Binds
    @Singleton
    /*
     * Singleton annotation isn't necessary since Application instance is unique but is here for
     * convention. In general, providing Activity, Fragment, BroadcastReceiver, etc does not require
     * them to be scoped since they are the components being injected and their instance is unique.
     *
     * However, having a scope annotation makes the module easier to read. We wouldn't have to look
     * at what is being provided in order to understand its scope.
     */
    abstract Application application(AndroidApplication androidApplication);

    @PerActivity
    @ContributesAndroidInjector(modules = BusinessSearchActivityModule.class)
    abstract BusinessSearchActivity businessSearchActivityInjector();


    @PerActivity
    @ContributesAndroidInjector(modules = BusinessDetailsActivityModule.class)
    abstract BusinessDetailsActivity businessDetailsActivityInjector();
}
