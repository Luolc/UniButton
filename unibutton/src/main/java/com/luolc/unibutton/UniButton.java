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

import java.util.Arrays;

/**
 * @author LuoLiangchen
 * @since 16/11/24
 */

public class UniButton extends AbstractUniButton {

    @Dimension protected float[] mRadii;

    public UniButton(Context context) {
        super(context);
    }

    public UniButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UniButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRadius(@Dimension int radius) {
        Arrays.fill(mRadii, radius);
        internalSetRadius();
    }

    public void setTopLeftRadius(@Dimension int radius) {
        mRadii[0] = mRadii[1] = radius;
        internalSetRadius();
    }

    public void setTopRightRadius(@Dimension int radius) {
        mRadii[2] = mRadii[3] = radius;
        internalSetRadius();
    }

    public void setBottomRightRadius(@Dimension int radius) {
        mRadii[4] = mRadii[5] = radius;
        internalSetRadius();
    }

    public void setBottomLeftRadius(@Dimension int radius) {
        mRadii[6] = mRadii[7] = radius;
        internalSetRadius();
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

        final int radius = a.getDimensionPixelSize(R.styleable.UniButton_radius, 0);
        final int topLeftRadius = a.getDimensionPixelSize(R.styleable.UniButton_topLeftRadius, radius);
        final int topRightRadius = a.getDimensionPixelSize(R.styleable.UniButton_topRightRadius, radius);
        final int bottomRightRadius = a.getDimensionPixelSize(R.styleable.UniButton_bottomRightRadius, radius);
        final int bottomLeftRadius = a.getDimensionPixelSize(R.styleable.UniButton_bottomLeftRadius, radius);
        mRadii = new float[] {
                topLeftRadius,      topLeftRadius,
                topRightRadius,     topRightRadius,
                bottomRightRadius,  bottomRightRadius,
                bottomLeftRadius,   bottomLeftRadius
        };
        backgroundNormal.setCornerRadii(mRadii);
        backgroundPressed.setCornerRadii(mRadii);
        backgroundDisabled.setCornerRadii(mRadii);

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

    private void internalSetRadius() {
        ((GradientDrawable) mBackgroundNormalWrapper.getCurrent()).setCornerRadii(mRadii);
        ((GradientDrawable) mBackgroundPressedWrapper.getCurrent()).setCornerRadii(mRadii);
        ((GradientDrawable) mBackgroundDisabledWrapper.getCurrent()).setCornerRadii(mRadii);
    }
}
