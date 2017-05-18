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

package com.vestrel00.business.search.presentation.java.model;

import java.io.Serializable;

/**
 * Type of all models.
 * <p>
 * <b>DEFAULT VALUES</b>
 * None of the getter methods defined here return null. If an attribute is missing or null, the
 * value returned as defaulted to their non-null counterparts:
 * <ul>
 * <li>String -> ""</li>
 * <li>Collection -> empty collection</li>
 * <li>CustomClass -> non-null CustomClass</li>
 * <li>Primitives -> default values. E.G. int -> 0</li>
 * </ul>
 * This done to prevent null or optional checks for consumers.
 * <p>
 * <b>SERIALIZATION</b>
 * All model classes are {@link Serializable} so that presentation applications are able
 * to save/restore these models to retain application/view state during a usage session. These are
 * not meant to be saved to disk for a prolonged period of time outside of a usage session. Such is
 * the case for Android applications. Instead of using Android's Parcelable interface, the
 * Serializable interface is used instead in order for these models to also be used by other
 * frameworks like Swing (or just plain Java).
 * <p>
 * Any additions, removals, or changes to any attributes defined by these models may come at any
 * time. Therefore, as suggested, do not save these models outside of an app session. If it is saved
 * to disk outside of an app session, then changes to these models due to an update may result in
 * null attributes in the new versions.
 */
public interface Model extends Serializable {
}
