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

package com.vestrel00.business.search.presentation.android.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vestrel00.business.search.presentation.android.R;
import com.vestrel00.business.search.presentation.android.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A layout that contains an icon, label, info, and action indicator.
 */
public final class LabeledInfoField extends RelativeLayout {

    @BindView(R2.id.top_border)
    View topBorder;

    @BindView(R2.id.bottom_border)
    View bottomBorder;

    @BindView(R2.id.icon)
    SimpleDraweeView icon;

    @BindView(R2.id.label)
    TextView label;

    @BindView(R2.id.info)
    TextView info;

    @BindView(R2.id.info_icon)
    SimpleDraweeView infoIcon;

    public LabeledInfoField(Context context) {
        super(context);
        initialize(context, null);
    }

    public LabeledInfoField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public LabeledInfoField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabeledInfoField(Context context, AttributeSet attrs, int defStyleAttr,
                            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    public void setShowTopBorder(boolean show) {
        topBorder.setVisibility(show ? VISIBLE : INVISIBLE);
    }

    public void setShowBottomBorder(boolean show) {
        bottomBorder.setVisibility(show ? VISIBLE : INVISIBLE);
    }

    public void setLabelText(String text) {
        label.setText(text);
    }

    public void setInfoText(String text) {
        info.setText(text);
    }

    public void setLabelText(@StringRes int textRes) {
        label.setText(textRes);
    }

    public void setInfoText(@StringRes int textRes) {
        info.setText(textRes);
    }

    public void setIconRes(@DrawableRes int iconRes) {
        setDraweeResource(icon, iconRes);
    }

    public void setInfoIconRes(@DrawableRes int infoIconRes) {
        setDraweeResource(infoIcon, infoIconRes);
    }

    private void setDraweeResource(SimpleDraweeView draweeView, @DrawableRes int resourceId) {
        draweeView.setVisibility(resourceId == 0 ? GONE : VISIBLE);

        if (!isInEditMode()) {
            // FIXME? (FRESCO) - Fresco views do not support edit mode
            draweeView.setActualImageResource(resourceId);
        }
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.labeled_info_field, this);
        ButterKnife.bind(this);
        applyAttributeSet(context, attrs);

        setClickable(true);
        setBackgroundResource(R.drawable.labeled_info_field_background);
    }

    private void applyAttributeSet(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabeledInfoField);
        boolean showTopBorder
                = typedArray.getBoolean(R.styleable.LabeledInfoField_show_top_border, false);
        boolean showBottomBorder
                = typedArray.getBoolean(R.styleable.LabeledInfoField_show_bottom_border, false);
        @DrawableRes int iconRes
                = typedArray.getResourceId(R.styleable.LabeledInfoField_icon_res, 0);
        @DrawableRes int infoIconRes
                = typedArray.getResourceId(R.styleable.LabeledInfoField_info_icon_res, 0);
        String labelText = typedArray.getString(R.styleable.LabeledInfoField_label_text);
        String infoText = typedArray.getString(R.styleable.LabeledInfoField_info_text);
        typedArray.recycle();

        setShowTopBorder(showTopBorder);
        setShowBottomBorder(showBottomBorder);
        setLabelText(labelText);
        setInfoText(infoText);
        setIconRes(iconRes);
        setInfoIconRes(infoIconRes);
    }
}
