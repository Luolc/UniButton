package com.luolc.unibutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;

/**
 * @author LuoLiangchen
 * @since 16/11/24
 */

public class UniButton extends AbstractUniButton {

    @Dimension protected int mRadius;

    public UniButton(Context context) {
        super(context);
    }

    public UniButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UniButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBackgroundRadius(@Dimension int radius) {
        mRadius = radius;
        ((GradientDrawable) mBackgroundNormalWrapper.getCurrent()).setCornerRadius(radius);
        ((GradientDrawable) mBackgroundPressedWrapper.getCurrent()).setCornerRadius(radius);
        ((GradientDrawable) mBackgroundDisabledWrapper.getCurrent()).setCornerRadius(radius);
    }

    @Override
    protected void initBackground(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UniButton);

        @ColorInt final int backgroundColorNormal =
                a.getColor(R.styleable.UniButton_android_background, Color.WHITE);
        @ColorInt final int backgroundColorPressed =
                a.getColor(R.styleable.UniButton_backgroundPressed, backgroundColorNormal);
        @ColorInt final int backgroundColorDisabled =
                a.getColor(R.styleable.UniButton_backgroundDisabled, backgroundColorNormal);
        final GradientDrawable backgroundNormal = new GradientDrawable();
        final GradientDrawable backgroundPressed = new GradientDrawable();
        final GradientDrawable backgroundDisabled = new GradientDrawable();
        backgroundNormal.setColor(backgroundColorNormal);
        backgroundPressed.setColor(backgroundColorPressed);
        backgroundDisabled.setColor(backgroundColorDisabled);

        mRadius = a.getDimensionPixelSize(R.styleable.UniButton_radius, 0);
        backgroundNormal.setCornerRadius(mRadius);
        backgroundPressed.setCornerRadius(mRadius);
        backgroundDisabled.setCornerRadius(mRadius);

        mBackground = new StateListDrawable();
        mBackgroundNormalWrapper = new DrawableWrapper(backgroundNormal);
        mBackgroundPressedWrapper = new DrawableWrapper(backgroundPressed);
        mBackgroundDisabledWrapper = new DrawableWrapper(backgroundDisabled);
        mBackground.addState(STATE_SET_DISABLED, mBackgroundDisabledWrapper);
        mBackground.addState(STATE_SET_PRESSED, mBackgroundPressedWrapper);
        mBackground.addState(STATE_SET_FOCUSED, mBackgroundPressedWrapper);
        mBackground.addState(STATE_SET_NORMAL, mBackgroundNormalWrapper);
        super.setBackgroundDrawable(mBackground);

        a.recycle();
    }
}
