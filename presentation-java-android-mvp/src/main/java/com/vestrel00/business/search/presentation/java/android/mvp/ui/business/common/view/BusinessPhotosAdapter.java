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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.common.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.vestrel00.business.search.presentation.java.android.inject.PerFragment;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

/**
 * A view adapter that binds a collection of business model photosUrls urls to a view pager that
 * displays it.
 */
@PerFragment
final class BusinessPhotosAdapter extends PagerAdapter {

    private static final String STATE_PHOTOS_URLS = "BusinessPhotosAdapter.photosUrls";

    private final List<String> photosUrls;

    @Inject
    BusinessPhotosAdapter(List<String> photosUrls) {
        this.photosUrls = photosUrls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        Bundle savedInstanceState = (Bundle) state;
        addPhotosUrls((List<String>) savedInstanceState.getSerializable(STATE_PHOTOS_URLS));
    }

    @Override
    public Parcelable saveState() {
        Bundle outState = new Bundle();
        outState.putSerializable(STATE_PHOTOS_URLS, (Serializable) photosUrls);
        return outState;
    }

    @Override
    public int getCount() {
        return photosUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    void addPhotosUrls(List<String> photosUrls) {
        this.photosUrls.addAll(photosUrls);
    }

    void notifyPhotosUrlsChanged() {
        notifyDataSetChanged();
    }
}
