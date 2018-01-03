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

package com.vestrel00.business.search.data.entity.validator;

import com.vestrel00.business.search.commons.StringUtils;
import com.vestrel00.business.search.data.entity.LocationEntity;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Validation for {@link LocationEntity}.
 */
@Reusable
final class LocationEntityValidator implements EntityValidator<LocationEntity> {

    private final StringUtils stringUtils;

    @Inject
    LocationEntityValidator(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    @Override
    public void validate(LocationEntity locationEntity) throws InvalidEntityException {
        if (!isValid(locationEntity)) {
            throw new InvalidEntityException("One or more attributes must not be null or empty.");
        }
    }

    @Override
    public boolean isValid(LocationEntity locationEntity) {
        return !stringUtils.allEmpty(locationEntity.address(), locationEntity.city(),
                locationEntity.state(), locationEntity.zipCode(), locationEntity.country());
    }
}
