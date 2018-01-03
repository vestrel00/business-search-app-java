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

package com.vestrel00.business.search.data.util;

import com.vestrel00.business.search.commons.StringUtils;
import com.vestrel00.business.search.data.entity.LocationEntity;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Formats {@link LocationEntity} into various different types.
 */
@Singleton
public final class LocationFormatter {

    private final StringUtils stringUtils;

    @Inject
    LocationFormatter(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    public String formatLocation(LocationEntity location) {
        return stringUtils.join(Arrays.asList(location.address(), location.city(), location.state(),
                location.zipCode(), location.country()), " ");
    }
}
