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

import com.vestrel00.business.search.domain.BusinessTransactionType;
import com.vestrel00.business.search.presentation.java.model.BusinessTransactionTypeModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link BusinessTransactionTypeModel} to {@link BusinessTransactionType} and vice versa.
 */
@Reusable
final class BusinessTransactionTypeModelMapper
        implements ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType> {

    private static final Map<BusinessTransactionTypeModel, BusinessTransactionType> K_MAP;
    private static final Map<BusinessTransactionType, BusinessTransactionTypeModel> V_MAP;

    static {
        K_MAP = new HashMap<>();
        V_MAP = new HashMap<>();

        K_MAP.put(BusinessTransactionTypeModel.PICKUP, BusinessTransactionType.PICKUP);
        K_MAP.put(BusinessTransactionTypeModel.DELIVERY, BusinessTransactionType.DELIVERY);
        K_MAP.put(BusinessTransactionTypeModel.RESERVATION, BusinessTransactionType.RESERVATION);

        V_MAP.put(BusinessTransactionType.PICKUP, BusinessTransactionTypeModel.PICKUP);
        V_MAP.put(BusinessTransactionType.DELIVERY, BusinessTransactionTypeModel.DELIVERY);
        V_MAP.put(BusinessTransactionType.RESERVATION, BusinessTransactionTypeModel.RESERVATION);
    }

    @Inject
    BusinessTransactionTypeModelMapper() {
    }

    @Override
    public BusinessTransactionType map(BusinessTransactionTypeModel businessTransactionTypeModel) {
        return K_MAP.get(businessTransactionTypeModel);
    }

    @Override
    public BusinessTransactionTypeModel map(BusinessTransactionType businessTransactionType) {
        return V_MAP.get(businessTransactionType);
    }
}
