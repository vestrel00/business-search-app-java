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

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides entity validator dependencies.
 */
@Module
public abstract class EntityValidatorModule {

    @Binds
    @Reusable
    abstract EntityValidator<BusinessEntity>
    businessEntityValidator(BusinessEntityValidator businessEntityValidator);

    @Binds
    @Reusable
    abstract EntityValidator<LocationEntity>
    locationEntityValidator(LocationEntityValidator locationEntityValidator);

    @Binds
    @Reusable
    abstract EntityValidator<CoordinatesEntity>
    coordinatesEntityValidator(CoordinatesEntityValidator coordinatesEntityValidator);

}
