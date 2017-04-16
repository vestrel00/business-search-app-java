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

import com.vestrel00.business.search.data.config.DataConfig;
import com.vestrel00.business.search.data.net.auth.AuthRequestInterceptorFactory;
import com.vestrel00.business.search.data.net.cache.NetworkCacheInterceptorFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Provides concrete data services.
 */
@Singleton
public final class DataServiceProvider {

    private final DataConfig config;
    private final Cache cache;
    private final NetworkCacheInterceptorFactory networkCacheInterceptorFactory;
    private final AuthRequestInterceptorFactory authRequestInterceptorFactory;

    @Inject
    DataServiceProvider(DataConfig config, Cache cache,
                        NetworkCacheInterceptorFactory networkCacheInterceptorFactory,
                        AuthRequestInterceptorFactory authRequestInterceptorFactory) {
        this.config = config;
        this.cache = cache;
        this.networkCacheInterceptorFactory = networkCacheInterceptorFactory;
        this.authRequestInterceptorFactory = authRequestInterceptorFactory;
    }

    public <T> T get(Class<T> serviceClass) {
        return builder(false).build().create(serviceClass);
    }

    public <T> T getWithAuth(Class<T> serviceClass) {
        return builder(true).build().create(serviceClass);
    }

    private Retrofit.Builder builder(boolean withAuth) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptorFactory.create());

        if (withAuth) {
            clientBuilder.addInterceptor(authRequestInterceptorFactory.get());
        }

        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(config.baseUrl())
                .client(clientBuilder.build());
    }
}
