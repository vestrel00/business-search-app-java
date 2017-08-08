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

package com.vestrel00.business.search.presentation.android.config;

import android.app.Application;

import com.vestrel00.business.search.data.config.DataConfig;
import com.vestrel00.business.search.presentation.java.config.BaseConfigModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides config dependencies.
 */
@Module
public abstract class ConfigModule extends BaseConfigModule {

    @Provides
    @Singleton
    static DataConfig dataConfig(Application application) {
        return BASE_CONFIG_BUILDER
                .cacheRootDir(application.getExternalCacheDir().getPath())
                .build();
    }
}
