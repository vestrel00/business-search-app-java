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

package com.vestrel00.business.search.commons;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Provides utility methods for mathematics.
 */
@Reusable
public final class MathUtils {

    @Inject
    MathUtils() {
    }

    public float toMiles(float meters) {
        return meters * 0.000621371f;
    }
}
