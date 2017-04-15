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

package com.vestrel00.business.search.data.config;

import com.google.auto.value.AutoValue;

/**
 * Contains configuration values used throughout the data module.
 */
@AutoValue
public abstract class DataConfig {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new AutoValue_DataConfig.Builder();
    }

    public abstract String apiBaseUrl();

    public abstract String apiAuthClientId();

    public abstract String apiAuthClientSecret();

    /**
     * Builder used to create instances of {@link DataConfig}
     */
    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder apiBaseUrl(String apiEndpoint);

        public abstract Builder apiAuthClientId(String apiAuthClientId);

        public abstract Builder apiAuthClientSecret(String apiAuthClientSecret);

        public abstract DataConfig build();
    }
}