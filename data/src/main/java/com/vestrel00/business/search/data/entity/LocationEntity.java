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

package com.vestrel00.business.search.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

/**
 * Contains location information.
 * <p>
 * Objects that are not marked with Nullable will have non-null default values.
 * <ul>
 * <li>String -> ""</li>
 * </ul>
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_LocationEntity.Builder.class)
public abstract class LocationEntity {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new AutoValue_LocationEntity.Builder();
    }

    @JsonProperty("address1")
    public abstract String address();

    @JsonProperty("city")
    public abstract String city();

    @JsonProperty("state")
    public abstract String state();

    @JsonProperty("zip_code")
    public abstract String zipCode();

    @JsonProperty("country")
    public abstract String country();

    /**
     * Builder used to create instances of {@link LocationEntity}.
     */
    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("address1")
        public abstract Builder address(String address);

        @JsonProperty("city")
        public abstract Builder city(String city);

        @JsonProperty("state")
        public abstract Builder state(String state);

        @JsonProperty("zip_code")
        public abstract Builder zipCode(String zipCode);

        @JsonProperty("country")
        public abstract Builder country(String country);

        public abstract LocationEntity build();
    }
}
