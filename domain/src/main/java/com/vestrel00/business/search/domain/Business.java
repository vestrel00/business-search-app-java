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

package com.vestrel00.business.search.domain;

import com.google.auto.value.AutoValue;

/**
 * Contains business information.
 * <p>
 * Objects that are not marked with Nullable will have non-null default values.
 * <ul>
 * <li>String -> ""</li>
 * <li>Location -> non null Location object with default values</li>
 * <li>Coordinates -> non null Coordinates object with default values</li>
 * </ul>
 */
@AutoValue
public abstract class Business {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new AutoValue_Business.Builder();
    }

    public abstract String name();

    public abstract String phoneNumber();

    public abstract Location location();

    public abstract Coordinates coordinates();

    /**
     * Builder used to create instances of {@link Business}.
     */
    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder name(String name);

        public abstract Builder phoneNumber(String phoneNumber);

        public abstract Builder location(Location location);

        public abstract Builder coordinates(Coordinates coordinates);

        public abstract Business build();
    }
}
