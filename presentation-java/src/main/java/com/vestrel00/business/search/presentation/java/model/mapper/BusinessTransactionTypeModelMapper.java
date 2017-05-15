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

import com.vestrel00.business.search.domain.BusinessTransactionType;
import com.vestrel00.business.search.presentation.java.model.BusinessTransactionTypeModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps {@link BusinessTransactionTypeModel} to {@link BusinessTransactionType} and vice versa.
 */
@Singleton
final class BusinessTransactionTypeModelMapper
        implements ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType> {

    private static final Map<BusinessTransactionTypeModel, BusinessTransactionType> kMap;
    private static final Map<BusinessTransactionType, BusinessTransactionTypeModel> vMap;

    static {
        kMap = new HashMap<>();
        vMap = new HashMap<>();

        kMap.put(BusinessTransactionTypeModel.PICKUP, BusinessTransactionType.PICKUP);
        kMap.put(BusinessTransactionTypeModel.DELIVERY, BusinessTransactionType.DELIVERY);
        kMap.put(BusinessTransactionTypeModel.RESERVATION, BusinessTransactionType.RESERVATION);

        vMap.put(BusinessTransactionType.PICKUP, BusinessTransactionTypeModel.PICKUP);
        vMap.put(BusinessTransactionType.DELIVERY, BusinessTransactionTypeModel.DELIVERY);
        vMap.put(BusinessTransactionType.RESERVATION, BusinessTransactionTypeModel.RESERVATION);
    }

    @Inject
    BusinessTransactionTypeModelMapper() {
    }

    @Override
    public BusinessTransactionType map(BusinessTransactionTypeModel businessTransactionTypeModel) {
        return kMap.get(businessTransactionTypeModel);
    }

    @Override
    public BusinessTransactionTypeModel map(BusinessTransactionType businessTransactionType) {
        return vMap.get(businessTransactionType);
    }
}
