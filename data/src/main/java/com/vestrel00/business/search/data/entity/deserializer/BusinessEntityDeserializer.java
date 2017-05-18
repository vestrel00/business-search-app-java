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

package com.vestrel00.business.search.data.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.parser.EntityParser;
import com.vestrel00.business.search.data.entity.parser.EntityParserFactory;

import java.io.IOException;

/**
 * Deserializer for {@link BusinessEntity}.
 */
public final class BusinessEntityDeserializer extends JsonDeserializer<BusinessEntity> {

    @Override
    public BusinessEntity deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = parser.readValueAsTree();
        EntityParser<BusinessEntity> businessEntityParser
                = EntityParserFactory.create().businessEntityParser();
        
        return businessEntityParser.parse(node);
    }
}
