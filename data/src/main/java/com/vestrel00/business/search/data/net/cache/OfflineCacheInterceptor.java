/*
 * Copyright 2018 Vandolf Estrellado
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
import com.vestrel00.business.search.data.net.NetworkStatus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An interceptor that uses stale responses if offline.
 */
public final class OfflineCacheInterceptor implements Interceptor {

    private final DataConfig config;
    private final NetworkStatus networkStatus;

    @Inject
    OfflineCacheInterceptor(DataConfig config, NetworkStatus networkStatus) {
        this.config = config;
        this.networkStatus = networkStatus;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!networkStatus.hasNetworkConnectivity()) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(config.offlineCacheTimeDays(), TimeUnit.DAYS)
                    .build();
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }

        return chain.proceed(request);
    }
}
