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

import com.google.auto.value.AutoValue;

/**
 * Contains location information.
 */
@AutoValue
// FIXME (DATABIND JACKSON) - The generated builder could be used for deserialization.
// However, Jackson does not yet support setting default values for null or missing properties
// per setter/getter methods.
// E.G. Missing (or null) property string -> empty string (instead of the default null)
// @JsonDeserialize(builder = AutoValue_LocationEntity.Builder.class)
public abstract class LocationEntity implements Entity {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new AutoValue_LocationEntity.Builder();
    }

    public abstract String address();

    public abstract String city();

    public abstract String state();

    public abstract String zipCode();

    public abstract String country();

    /**
     * Builder used to create instances of {@link LocationEntity}.
     */
    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder address(String address);

        public abstract Builder city(String city);

        public abstract Builder state(String state);

        public abstract Builder zipCode(String zipCode);

        public abstract Builder country(String country);

        public abstract LocationEntity build();
    }
}
