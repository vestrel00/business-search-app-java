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

package com.vestrel00.business.search.presentation.java.model.mapper;

import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link CoordinatesModel} to {@link Coordinates} and vice versa.
 */
@Reusable
final class CoordinatesModelMapper implements ModelMapper<CoordinatesModel, Coordinates> {

    @Inject
    CoordinatesModelMapper() {
    }

    @Override
    public Coordinates map(CoordinatesModel coordinatesModel) {
        return Coordinates.builder()
                .latitude(coordinatesModel.latitude())
                .longitude(coordinatesModel.longitude())
                .build();
    }

    @Override
    public CoordinatesModel map(Coordinates coordinates) {
        return CoordinatesModel.builder()
                .latitude(coordinates.latitude())
                .longitude(coordinates.longitude())
                .build();
    }
}
