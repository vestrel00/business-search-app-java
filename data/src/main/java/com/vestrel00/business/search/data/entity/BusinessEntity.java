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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.vestrel00.business.search.data.entity.deserialize.BusinessEntityDeserializer;

import java.util.List;

/**
 * Contains business information.
 * <p>
 * <b>DEFAULT VALUES</b>
 * None of the getter methods defined here return null. If an attribute is missing or null, the
 * value returned as defaulted to their non-null counterparts:
 * <ul>
 * <li>String -> ""</li>
 * <li>Collection -> empty collection</li>
 * <li>CustomClass -> non-null CustomClass</li>
 * <li>Primitives -> default values. E.G. int -> 0</li>
 * </ul>
 * This done to prevent null checks and null exceptions for consumers.
 */
@AutoValue
// FIXME (DATABIND JACKSON) - The generated builder could be used for deserialization.
// However, Jackson does not yet support setting default values for null or missing properties
// per setter/getter methods.
// E.G. Missing (or null) property string -> empty string (instead of the default null)
// @JsonDeserialize(builder = AutoValue_BusinessEntity.Builder.class)
@JsonDeserialize(using = BusinessEntityDeserializer.class)
public abstract class BusinessEntity implements Entity {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new AutoValue_BusinessEntity.Builder();
    }

    public abstract String id();

    public abstract String name();

    public abstract String phoneNumber();

    public abstract String imageUrl();

    public abstract String price();

    public abstract List<String> categories();

    public abstract int reviewCount();

    public abstract float rating();

    public abstract boolean closed();

    public abstract LocationEntity locationEntity();

    public abstract CoordinatesEntity coordinatesEntity();

    /**
     * Builder used to create instances of {@link BusinessEntity}.
     */
    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder id(String id);

        public abstract Builder name(String name);

        public abstract Builder phoneNumber(String phoneNumber);

        public abstract Builder imageUrl(String imageUrl);

        public abstract Builder price(String price);

        public abstract Builder categories(List<String> categories);

        public abstract Builder reviewCount(int reviewCount);

        public abstract Builder rating(float rating);

        public abstract Builder closed(boolean closed);

        public abstract Builder locationEntity(LocationEntity locationEntity);

        public abstract Builder coordinatesEntity(CoordinatesEntity coordinatesEntity);

        public abstract BusinessEntity build();
    }
}
