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

package com.vestrel00.business.search.data.di;

import com.vestrel00.business.search.data.config.DataConfig;
import com.vestrel00.business.search.data.net.BusinessDataService;
import com.vestrel00.business.search.data.net.DataServiceFactory;
import com.vestrel00.business.search.data.net.auth.AuthRequestInterceptor;
import com.vestrel00.business.search.data.net.auth.AuthTokenService;
import com.vestrel00.business.search.data.repository.BusinessDataRepository;
import com.vestrel00.business.search.domain.repository.BusinessRepository;

import java.io.File;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;

/**
 * A dagger module that provides presentation layers dependencies from the data layer.
 */
@Module
public abstract class DataModule {

    @Binds
    @Singleton
    abstract BusinessRepository businessRepository(BusinessDataRepository businessDataRepository);

    @Provides
    @Singleton
    static AuthTokenService authTokenService(DataServiceFactory dataServiceFactory) {
        return dataServiceFactory.create(AuthTokenService.class);
    }

    @Provides
    @Singleton
    static BusinessDataService businessDataService(DataServiceFactory dataServiceFactory,
                                                   AuthRequestInterceptor authRequestInterceptor) {
        return dataServiceFactory.createWithAuth(BusinessDataService.class, authRequestInterceptor);
    }

    @Provides
    @Singleton
    static Cache networkCache(DataConfig dataConfig) {
        return new Cache(new File(dataConfig.cacheDir()),
                dataConfig.cacheMaxSizeMb() * 1024 * 1024);
    }

}
