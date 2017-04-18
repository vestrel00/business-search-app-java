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
import com.vestrel00.business.search.data.net.auth.AuthRequestInterceptor;
import com.vestrel00.business.search.data.net.cache.NetworkCacheInterceptor;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Creates concrete data services.
 */
@Singleton
public final class DataServiceFactory {

    private final DataConfig config;
    private final Lazy<Cache> cache;
    private final NetworkCacheInterceptor networkCacheInterceptor;

    @Inject
    DataServiceFactory(DataConfig config, Lazy<Cache> cache,
                       NetworkCacheInterceptor networkCacheInterceptor) {
        this.config = config;
        this.cache = cache;
        this.networkCacheInterceptor = networkCacheInterceptor;
    }

    public <T> T create(Class<T> serviceClass) {
        return retrofit(okHttpClientBuilder()).create(serviceClass);
    }

    public <T> T createWithAuth(Class<T> serviceClass, AuthRequestInterceptor authRequestInterceptor) {
        // AuthRequestInterceptor has to be passed in as a parameter to avoid dependency cycle
        OkHttpClient.Builder okHttpClientBuilder = okHttpClientBuilder();
        okHttpClientBuilder.addInterceptor(authRequestInterceptor);
        return retrofit(okHttpClientBuilder).create(serviceClass);
    }

    private OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .cache(cache.get())
                .addNetworkInterceptor(networkCacheInterceptor);
    }

    private Retrofit retrofit(OkHttpClient.Builder okHttpClientBuilder) {
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(config.baseUrl())
                .client(okHttpClientBuilder.build())
                .build();
    }
}
