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

package com.vestrel00.business.search.data.entity.mapper;

import com.vestrel00.business.search.data.entity.BusinessTransactionTypeEntity;
import com.vestrel00.business.search.domain.BusinessTransactionType;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps {@link BusinessTransactionTypeEntity} to {@link BusinessTransactionType} and vice versa.
 */
@Singleton
final class BusinessTransactionTypeEntityMapper
        implements EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType> {

    private static final Map<BusinessTransactionTypeEntity, BusinessTransactionType> kMap;
    private static final Map<BusinessTransactionType, BusinessTransactionTypeEntity> vMap;

    static {
        kMap = new HashMap<>();
        vMap = new HashMap<>();

        kMap.put(BusinessTransactionTypeEntity.PICKUP, BusinessTransactionType.PICKUP);
        kMap.put(BusinessTransactionTypeEntity.DELIVERY, BusinessTransactionType.DELIVERY);
        kMap.put(BusinessTransactionTypeEntity.RESERVATION, BusinessTransactionType.RESERVATION);

        vMap.put(BusinessTransactionType.PICKUP, BusinessTransactionTypeEntity.PICKUP);
        vMap.put(BusinessTransactionType.DELIVERY, BusinessTransactionTypeEntity.DELIVERY);
        vMap.put(BusinessTransactionType.RESERVATION, BusinessTransactionTypeEntity.RESERVATION);
    }

    @Inject
    BusinessTransactionTypeEntityMapper() {
    }

    @Override
    public BusinessTransactionType map(BusinessTransactionTypeEntity businessTransactionTypeEntity) {
        return kMap.get(businessTransactionTypeEntity);
    }

    @Override
    public BusinessTransactionTypeEntity map(BusinessTransactionType businessTransactionType) {
        return vMap.get(businessTransactionType);
    }
}
