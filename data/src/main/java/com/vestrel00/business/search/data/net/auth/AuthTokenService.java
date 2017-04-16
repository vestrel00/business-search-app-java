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

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Client API to the backend REST API that provides an {@link AuthToken}.
 */
interface AuthTokenService {

    @POST("oauth2/token")
    @FormUrlEncoded
    AuthToken getAuthToken(@Field("grant_type") String grantType,
                           @Field("client_id") String clientId,
                           @Field("client_secret") String clientSecret);
}
