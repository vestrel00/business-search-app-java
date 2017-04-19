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

package com.vestrel00.business.search.data.net;

import com.vestrel00.business.search.data.net.auth.AuthRequestInterceptor;
import com.vestrel00.business.search.data.net.auth.DataNetAuthModule;
import com.vestrel00.business.search.data.net.cache.DataNetCacheModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides data network dependencies.
 */
@Module(includes = {
        DataNetAuthModule.class,
        DataNetCacheModule.class
})
public abstract class DataNetModule {

    @Provides
    @Singleton
    static BusinessDataService businessDataService(DataServiceFactory dataServiceFactory,
                                                   AuthRequestInterceptor authRequestInterceptor) {
        return dataServiceFactory.createWithAuth(BusinessDataService.class, authRequestInterceptor);
    }
}
