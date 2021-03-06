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

package com.vestrel00.business.search.data.entity.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.vestrel00.business.search.data.entity.LocationEntity;

/**
 * Parses {@link JsonNode} to a {@link LocationEntity}.
 */
final class LocationEntityParser implements EntityParser<LocationEntity> {

    @Override
    public LocationEntity parse(JsonNode node) {
        return LocationEntity.builder()
                .address(node.path("address1").asText())
                .city(node.path("city").asText())
                .state(node.path("state").asText())
                .zipCode(node.path("zip_code").asText())
                .country(node.path("country").asText())
                .build();
    }
}
