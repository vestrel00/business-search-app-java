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

package com.vestrel00.business.search.presentation.java.nogui.mvp.display;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A {@link Display} using {@link DisplayInput} for input prompts and {@link DisplayOutput} for
 * displaying messages.
 */
@Singleton
final class DisplayIO implements Display {

    private final DisplayInput displayInput;
    private final DisplayOutput displayOutput;

    @Inject
    DisplayIO(DisplayInput displayInput, DisplayOutput displayOutput) {
        this.displayInput = displayInput;
        this.displayOutput = displayOutput;
    }

    @Override
    public void showMessage(String message) {
        displayOutput.printMessage(message + '\n');
    }

    @Override
    public void showError(Throwable error) {
        showMessage(error.getMessage());
    }

    @Override
    public String promptInput() {
        return displayInput.getInput();
    }

    @Override
    public String promptInput(String inputPrompt) {
        displayOutput.printMessage(inputPrompt + " ");
        return promptInput();
    }
}
