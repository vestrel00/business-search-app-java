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

package com.vestrel00.business.search.data.net;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.vestrel00.business.search.data.entity.BusinessEntity;

import java.util.List;

/**
 * Consists of a list of businesses.
 */
@AutoValue
@JsonDeserialize(builder = AutoValue_BusinessEntityListResponse.Builder.class)
public abstract class BusinessEntityListResponse {

    @JsonProperty("businesses")
    public abstract List<BusinessEntity> businessEntities();

    /**
     * Builder used to create instances of {@link BusinessEntityListResponse}.
     */
    @AutoValue.Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    abstract static class Builder {

        @JsonProperty("businesses")
        abstract Builder businessEntities(List<BusinessEntity> businessEntities);

        abstract BusinessEntityListResponse build();
    }
}
