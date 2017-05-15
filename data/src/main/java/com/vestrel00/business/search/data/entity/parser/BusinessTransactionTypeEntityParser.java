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

package com.vestrel00.business.search.data.entity.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.vestrel00.business.search.data.entity.BusinessTransactionTypeEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses {@link JsonNode} to a {@link BusinessTransactionTypeEntity}.
 */
final class BusinessTransactionTypeEntityParser
        implements EntityParser<BusinessTransactionTypeEntity> {

    private static final Map<String, BusinessTransactionTypeEntity> map;

    static {
        map = new HashMap<>();

        map.put("pickup", BusinessTransactionTypeEntity.PICKUP);
        map.put("delivery", BusinessTransactionTypeEntity.DELIVERY);
        map.put("restaurant_reservation", BusinessTransactionTypeEntity.RESERVATION);
    }

    @Override
    public BusinessTransactionTypeEntity parse(JsonNode node) {
        return map.get(node.asText());
    }
}
