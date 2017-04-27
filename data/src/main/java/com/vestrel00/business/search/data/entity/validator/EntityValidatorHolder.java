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

package com.vestrel00.business.search.data.entity.validator;

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Holds instances of {@link EntityValidator}s.
 */
@Singleton
public final class EntityValidatorHolder {

    private final EntityValidator<BusinessEntity> businessEntityValidator;
    private final EntityValidator<LocationEntity> locationEntityValidator;
    private final EntityValidator<CoordinatesEntity> coordinatesEntityValidator;

    @Inject
    EntityValidatorHolder(EntityValidator<BusinessEntity> businessEntityValidator,
                          EntityValidator<LocationEntity> locationEntityValidator,
                          EntityValidator<CoordinatesEntity> coordinatesEntityValidator) {
        this.businessEntityValidator = businessEntityValidator;
        this.locationEntityValidator = locationEntityValidator;
        this.coordinatesEntityValidator = coordinatesEntityValidator;
    }

    public EntityValidator<BusinessEntity> businessEntityValidator() {
        return businessEntityValidator;
    }

    public EntityValidator<LocationEntity> locationEntityValidator() {
        return locationEntityValidator;
    }

    public EntityValidator<CoordinatesEntity> coordinatesEntityValidator() {
        return coordinatesEntityValidator;
    }
}
