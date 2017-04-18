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

package com.vestrel00.business.search.data.net.auth;

import com.vestrel00.business.search.data.config.DataConfig;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;

/**
 * Provides an {@link AuthToken} from cache or network.
 */
@Singleton
final class AuthTokenProvider {

    private final Lazy<AuthTokenService> authTokenService;
    private final AuthTokenCache cache;
    private final DataConfig config;

    @Inject
    AuthTokenProvider(Lazy<AuthTokenService> authTokenService, AuthTokenCache cache,
                      DataConfig config) {
        this.authTokenService = authTokenService;
        this.cache = cache;
        this.config = config;
    }

    synchronized AuthToken get() {
        if (cache.hasValidTokenCached()) {
            return cache.get();
        }

        cache.clear();
        AuthToken authToken = authTokenService.get().getAuthToken(config.authGrantType(),
                config.authClientId(), config.authClientSecret());
        cache.set(authToken);

        return authToken;
    }
}
