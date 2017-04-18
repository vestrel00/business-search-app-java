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

package com.vestrel00.business.search.presentation.java.nogui.mvp.view;

/**
 * A view for a no-gui application.
 *
 * @param <T> the type of the value returned by {@link #show()}
 */
public interface NoGuiView<T> {

    /**
     * Start showing this view's contents.
     * <p>
     * This should not be invoked by the presenter, otherwise an infinite call loop may occur.
     *
     * @return the result of viewing this view (if any).
     */
    T show();

    /**
     * @param error the error to show.
     */
    void showError(Throwable error);
}
