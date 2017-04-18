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

package com.vestrel00.business.search.data.util;

import com.vestrel00.business.search.data.entity.LocationEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Formats {@link LocationEntity} into various different types.
 */
@Singleton
public final class LocationFormatter {

    @Inject
    LocationFormatter() {
    }

    public String formatLocation(LocationEntity location) {
        StringBuilder formattedLocation = new StringBuilder();
        addToFormattedLocation(formattedLocation, location.address());
        addToFormattedLocation(formattedLocation, location.city());
        addToFormattedLocation(formattedLocation, location.state());
        addToFormattedLocation(formattedLocation, location.zipCode());
        addToFormattedLocation(formattedLocation, location.country());
        return formattedLocation.toString().trim();
    }

    private void addToFormattedLocation(StringBuilder formattedLocation, String locationItem) {
        if (!locationItem.isEmpty()) {
            formattedLocation.append(locationItem);
            formattedLocation.append(' ');
        }
    }
}
