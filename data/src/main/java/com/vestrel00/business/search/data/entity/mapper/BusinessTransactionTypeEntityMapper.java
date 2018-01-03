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

package com.vestrel00.business.search.data.entity.mapper;

import com.vestrel00.business.search.data.entity.BusinessTransactionTypeEntity;
import com.vestrel00.business.search.domain.BusinessTransactionType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link BusinessTransactionTypeEntity} to {@link BusinessTransactionType} and vice versa.
 */
@Reusable
final class BusinessTransactionTypeEntityMapper
        implements EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType> {

    private static final Map<BusinessTransactionTypeEntity, BusinessTransactionType> K_MAP;
    private static final Map<BusinessTransactionType, BusinessTransactionTypeEntity> V_MAP;

    static {
        K_MAP = new HashMap<>();
        V_MAP = new HashMap<>();

        K_MAP.put(BusinessTransactionTypeEntity.PICKUP, BusinessTransactionType.PICKUP);
        K_MAP.put(BusinessTransactionTypeEntity.DELIVERY, BusinessTransactionType.DELIVERY);
        K_MAP.put(BusinessTransactionTypeEntity.RESERVATION, BusinessTransactionType.RESERVATION);

        V_MAP.put(BusinessTransactionType.PICKUP, BusinessTransactionTypeEntity.PICKUP);
        V_MAP.put(BusinessTransactionType.DELIVERY, BusinessTransactionTypeEntity.DELIVERY);
        V_MAP.put(BusinessTransactionType.RESERVATION, BusinessTransactionTypeEntity.RESERVATION);
    }

    @Inject
    BusinessTransactionTypeEntityMapper() {
    }

    @Override
    public BusinessTransactionType
    map(BusinessTransactionTypeEntity businessTransactionTypeEntity) {
        return K_MAP.get(businessTransactionTypeEntity);
    }

    @Override
    public BusinessTransactionTypeEntity map(BusinessTransactionType businessTransactionType) {
        return V_MAP.get(businessTransactionType);
    }
}
