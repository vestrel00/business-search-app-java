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

package com.vestrel00.business.search.data.net.cache;

import com.vestrel00.business.search.data.config.DataConfig;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

/**
 * Provides data network cache dependencies.
 */
@Module
public abstract class DataNetCacheModule {

    @Provides
    @Singleton
    static Cache networkCache(DataConfig dataConfig) {
        return new Cache(new File(dataConfig.cacheDir()),
                dataConfig.cacheMaxSizeMb() * 1024 * 1024);
    }
}
