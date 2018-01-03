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

package com.vestrel00.business.search.data.net.auth;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;

/**
 * Caches an {@link AuthToken} for as long as it is valid (not expired).
 * <p>
 * Note that the token is not cached in disk so that we can revalidate the validity of the token
 * during the first call to our api. It may be that even though a token has not yet reached its
 * expiration date that the api may arbitrarily invalidate the token.
 */
@Singleton
final class AuthTokenCache {

    @Nullable
    private AuthToken authToken;

    private long authTokenExpirationTimeMillis;

    @Inject
    AuthTokenCache() {
    }

    @Nullable
    synchronized AuthToken get() {
        return authToken;
    }

    synchronized void set(AuthToken authToken) {
        this.authToken = authToken;

        long expiresInMillis = TimeUnit.SECONDS.toMillis(authToken.expiresInSeconds());
        authTokenExpirationTimeMillis = System.currentTimeMillis() + expiresInMillis;
    }

    synchronized void clear() {
        authToken = null;
        authTokenExpirationTimeMillis = 0;
    }

    synchronized boolean hasValidTokenCached() {
        return authToken != null && authTokenExpirationTimeMillis < System.currentTimeMillis();
    }
}
