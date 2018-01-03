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

import com.vestrel00.business.search.data.entity.CoordinatesEntity;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Validation for {@link CoordinatesEntity}
 */
@Reusable
final class CoordinatesEntityValidator implements EntityValidator<CoordinatesEntity> {

    @Inject
    CoordinatesEntityValidator() {
    }

    @Override
    public void validate(CoordinatesEntity coordinatesEntity) throws InvalidEntityException {
        if (!isValid(coordinatesEntity)) {
            throw new InvalidEntityException("Latitude and longitude must not be 0.");
        }
    }

    @Override
    public boolean isValid(CoordinatesEntity coordinatesEntity) {
        return coordinatesEntity.latitude() != 0 && coordinatesEntity.longitude() != 0;
    }
}
