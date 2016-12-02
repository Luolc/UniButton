package com.luolc.unibutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
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

    public void setBackgourndRadius(@Dimension int radius) {
        mRadius = radius;
        ((GradientDrawable) mBackgroundNormal).setCornerRadius(radius);
        ((GradientDrawable) mBackgroundPressed).setCornerRadius(radius);
        ((GradientDrawable) mBackgroundDisabled).setCornerRadius(radius);
    }

    @Override
    protected void initBackground(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UniButton);

        mBackground = new StateListDrawable();
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

        mBackgroundNormal = backgroundNormal;
        mBackgroundPressed = backgroundPressed;
        mBackgroundDisabled = backgroundDisabled;
        mBackground.addState(STATE_SET_DISABLED, mBackgroundDisabled);
        mBackground.addState(STATE_SET_PRESSED, mBackgroundPressed);
        mBackground.addState(STATE_SET_FOCUSED, mBackgroundPressed);
        mBackground.addState(STATE_SET_NORMAL, mBackgroundNormal);

        super.setBackgroundDrawable(mBackground);

        a.recycle();
    }
}
