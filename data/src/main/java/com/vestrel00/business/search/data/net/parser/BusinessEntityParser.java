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

package com.vestrel00.business.search.data.net.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;

/**
 * Parses {@link JsonNode} to a {@link BusinessEntity}.
 */
final class BusinessEntityParser implements Parser<BusinessEntity> {

    private final Parser<LocationEntity> locationEntityParser;
    private final Parser<CoordinatesEntity> coordinatesEntityParser;

    BusinessEntityParser(Parser<LocationEntity> locationEntityParser,
                         Parser<CoordinatesEntity> coordinatesEntityParser) {
        this.locationEntityParser = locationEntityParser;
        this.coordinatesEntityParser = coordinatesEntityParser;
    }

    @Override
    public BusinessEntity parse(JsonNode node) {
        return BusinessEntity.builder()
                .id(node.path("id").asText())
                .name(node.path("name").asText())
                .phoneNumber(node.path("display_phone").asText())
                .imageUrl(node.path("image_url").asText())
                .price(node.path("price").asText())
                .rating(node.path("rating").asDouble())
                .closed(node.path("is_closed").asBoolean())
                .location(locationEntityParser.parse(node.path("location")))
                .coordinates(coordinatesEntityParser.parse(node.path("coordinates")))
                .build();
    }
}
