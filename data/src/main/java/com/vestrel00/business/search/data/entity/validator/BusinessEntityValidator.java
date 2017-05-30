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

import com.vestrel00.business.search.util.StringUtils;
import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Validation for {@link BusinessEntity}.
 */
@Singleton
final class BusinessEntityValidator implements EntityValidator<BusinessEntity> {

    private final EntityValidator<LocationEntity> locationEntityValidator;
    private final EntityValidator<CoordinatesEntity> coordinatesEntityValidator;
    private final StringUtils stringUtils;

    @Inject
    BusinessEntityValidator(EntityValidator<LocationEntity> locationEntityValidator,
                            EntityValidator<CoordinatesEntity> coordinatesEntityValidator,
                            StringUtils stringUtils) {
        this.locationEntityValidator = locationEntityValidator;
        this.coordinatesEntityValidator = coordinatesEntityValidator;
        this.stringUtils = stringUtils;
    }

    @Override
    public void validate(BusinessEntity businessEntity) throws InvalidEntityException {
        if (isValidBare(businessEntity)) {
            throw new InvalidEntityException("Business id must not be missing.");
        }
        locationEntityValidator.validate(businessEntity.locationEntity());
        coordinatesEntityValidator.validate(businessEntity.coordinatesEntity());
    }

    @Override
    public boolean isValid(BusinessEntity businessEntity) {
        return isValidBare(businessEntity)
                && locationEntityValidator.isValid(businessEntity.locationEntity())
                && coordinatesEntityValidator.isValid(businessEntity.coordinatesEntity());
    }

    private boolean isValidBare(BusinessEntity businessEntity) {
        return !stringUtils.isEmpty(businessEntity.id());
    }
}
