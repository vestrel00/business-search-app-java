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

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.BusinessHourEntity;
import com.vestrel00.business.search.data.entity.BusinessHoursEntity;
import com.vestrel00.business.search.data.entity.BusinessTransactionTypeEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;

/**
 * Creates instance of {@link EntityParser}.
 */
public final class EntityParserFactory {

    private EntityParserFactory() {
    }

    public static EntityParserFactory create() {
        return new EntityParserFactory();
    }

    public EntityParser<BusinessEntity> businessEntityParser() {
        return new BusinessEntityParser(businessTransactionTypeEntityParser(),
                businessHoursEntityParser(), locationEntityParser(), coordinatesEntityParser(),
                entityParserUtil());
    }

    private EntityParser<BusinessTransactionTypeEntity> businessTransactionTypeEntityParser() {
        return new BusinessTransactionTypeEntityParser();
    }

    private EntityParser<BusinessHoursEntity> businessHoursEntityParser() {
        return new BusinessHoursEntityParser(businessHourEntityParser(), entityParserUtil());
    }

    private EntityParser<BusinessHourEntity> businessHourEntityParser() {
        return new BusinessHourEntityParser();
    }

    private EntityParser<LocationEntity> locationEntityParser() {
        return new LocationEntityParser();
    }

    private EntityParser<CoordinatesEntity> coordinatesEntityParser() {
        return new CoordinatesEntityParser();
    }

    private EntityParserUtil entityParserUtil() {
        return new EntityParserUtil();
    }
}
