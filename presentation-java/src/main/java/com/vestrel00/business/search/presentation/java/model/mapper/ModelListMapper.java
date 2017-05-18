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

package com.vestrel00.business.search.presentation.java.model.mapper;

import com.vestrel00.business.search.domain.DomainObject;
import com.vestrel00.business.search.presentation.java.model.Model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps lists of models.
 */
@Singleton
final class ModelListMapper {

    @Inject
    ModelListMapper() {
    }

    <K extends Model, V extends DomainObject> List<V> mapToV(ModelMapper<K, V> modelMapper,
                                                             List<K> kList) {
        List<V> vList = new ArrayList<>(kList.size());
        for (K k : kList) {
            vList.add(modelMapper.map(k));
        }
        return vList;
    }

    <K extends Model, V extends DomainObject> List<K> mapToK(ModelMapper<K, V> modelMapper,
                                                             List<V> vList) {
        List<K> kList = new ArrayList<>(vList.size());
        for (V v : vList) {
            kList.add(modelMapper.map(v));
        }
        return kList;
    }
}
