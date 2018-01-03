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

package com.vestrel00.business.search.data.config;

import com.google.auto.value.AutoValue;

import javax.inject.Singleton;

/**
 * Contains configuration values used throughout the data module.
 */
@AutoValue
@Singleton
public abstract class DataConfig {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new AutoValue_DataConfig.Builder();
    }

    public abstract String baseUrl();

    public abstract String authGrantType();

    public abstract String authClientId();

    public abstract String authClientSecret();

    public abstract String cacheRootDir();

    public abstract String cacheDir();

    public abstract int cacheMaxSizeMb();

    public abstract int offlineCacheTimeDays();

    public abstract int networkCacheTimeSeconds();

    /**
     * Builder used to create instances of {@link DataConfig}
     */
    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder baseUrl(String baseUrl);

        public abstract Builder authGrantType(String authGrantType);

        public abstract Builder authClientId(String authClientId);

        public abstract Builder authClientSecret(String authClientSecret);

        public abstract Builder cacheRootDir(String cacheRootDir);

        public abstract Builder cacheDir(String cacheDir);

        public abstract Builder cacheMaxSizeMb(int cacheMaxSizeMb);

        public abstract Builder offlineCacheTimeDays(int offlineCacheTimeDays);

        public abstract Builder networkCacheTimeSeconds(int networkCacheTimeSeconds);

        public abstract DataConfig build();
    }
}
