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

package com.vestrel00.business.search.presentation.java.nogui.mvp.display;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Uses {@link Logger} for displaying messages to the console.
 */
@Reusable
final class DisplayOutput {

    // Defined here and not injected as for PMD
    private static final Logger LOGGER = Logger.getLogger("Application");

    static {
        // ConsoleHandler outputs to System.err but it is simpler and more functional
        // than StreamHandler
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                return logRecord.getMessage();
            }
        });

        LOGGER.addHandler(handler);
        LOGGER.setLevel(Level.FINE);
    }

    @Inject
    DisplayOutput() {
    }

    void printMessage(String message) {
        LOGGER.fine(message);
    }
}
