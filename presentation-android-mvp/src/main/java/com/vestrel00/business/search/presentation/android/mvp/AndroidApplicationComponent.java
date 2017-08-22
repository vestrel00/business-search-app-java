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

import com.vestrel00.business.search.data.DataModule;
import com.vestrel00.business.search.domain.DomainModule;
import com.vestrel00.business.search.commons.UtilModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Injects Android Application dependencies.
 */
@Singleton
@Component(modules = {
        UtilModule.class,
        DataModule.class,
        DomainModule.class,
        AndroidApplicationModule.class,
})
interface AndroidApplicationComponent extends AndroidInjector<AndroidApplication> {

    /**
     * Builder for this component.
     */
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AndroidApplication> {
    }
}
