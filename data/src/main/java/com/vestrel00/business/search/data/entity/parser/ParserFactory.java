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

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;

/**
 * Creates instance of {@link Parser}.
 */
public final class ParserFactory {

    private ParserFactory() {
    }
    
    public static ParserFactory create() {
        return new ParserFactory();
    }

    public Parser<BusinessEntity> businessEntityParser() {
        return new BusinessEntityParser(locationEntityParser(), coordinatesEntityParser());
    }

    private Parser<LocationEntity> locationEntityParser() {
        return new LocationEntityParser();
    }

    private Parser<CoordinatesEntity> coordinatesEntityParser() {
        return new CoordinatesEntityParser();
    }
}
