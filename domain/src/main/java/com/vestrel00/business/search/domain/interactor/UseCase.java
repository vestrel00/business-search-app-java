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

package com.vestrel00.business.search.domain.interactor;

import io.reactivex.Observable;

/**
 * Use cases are interactors in terms of "clean architecture", which encapsulate a focused unit of
 * work. These do not, by themselves, determine where (what thread) the work will be done.
 *
 * @param <K> the type of the input passed as a parameter to {@link #execute(K)}
 * @param <V> the type of the item emitted by the {@link Observable} returned by {@link #execute(V)}
 */
public interface UseCase<K, V> {

    Observable<V> execute(K params);
}
