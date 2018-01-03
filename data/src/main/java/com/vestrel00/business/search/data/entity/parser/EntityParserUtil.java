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
import com.fasterxml.jackson.databind.node.MissingNode;
import com.vestrel00.business.search.data.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Contains utility methods for parsing list of entities and traversing {@link JsonNode}s.
 */
final class EntityParserUtil {

    EntityParserUtil() {
    }

    <T extends Entity> List<T> parse(EntityParser<T> entityParser, JsonNode nodeArray) {
        List<T> entityList = new ArrayList<>();
        for (JsonNode node : nodeArray) {
            entityList.add(entityParser.parse(node));
        }
        return entityList;
    }

    List<String> parse(JsonNode nodeArray) {
        List<String> strings = new ArrayList<>();
        for (JsonNode node : nodeArray) {
            strings.add(node.asText());
        }
        return strings;
    }

    JsonNode findObjectNode(JsonNode nodeArray, String fieldName, String fieldValue) {
        for (JsonNode node : nodeArray) {
            JsonNode fieldNameMatch = node.path(fieldName);
            if (Objects.equals(fieldValue, fieldNameMatch.asText())) {
                return node;
            }
        }
        return MissingNode.getInstance();
    }
}
