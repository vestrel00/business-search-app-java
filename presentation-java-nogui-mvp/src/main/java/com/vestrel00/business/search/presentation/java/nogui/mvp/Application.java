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

package com.vestrel00.business.search.presentation.java.nogui.mvp;

import com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListView;
import com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListViewResult;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * An application with no UI. Communications are done through the command line.
 */
@Singleton
public final class Application implements Runnable {

    private final BusinessListView businessListView;

    @Inject
    Application(BusinessListView businessListView) {
        this.businessListView = businessListView;
    }

    @Override
    public void run() {
        businessListView.initialize();

        boolean run = true;
        while (run) {
            BusinessListViewResult result = businessListView.showOptions();
            run = handleBusinessListViewResult(result);
        }
    }

    private boolean handleBusinessListViewResult(BusinessListViewResult result) {
        switch (result.code()) {
            case SHOW_BUSINESS_DETAILS:
                // TODO
                return true;
            case QUIT:
                return false;
            default:
                return true;
        }
    }
}