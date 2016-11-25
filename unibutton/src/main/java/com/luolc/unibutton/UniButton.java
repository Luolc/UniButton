package com.luolc.unibutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.util.AttributeSet;

/**
 * @author LuoLiangchen
 * @since 16/11/24
 */

public class UniButton extends SimpleUniButton {

    @Dimension protected int mRadius;

    public UniButton(Context context) {
        this(context, null);
    }

    public UniButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public UniButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) init(context, attrs);
    }

    @Override
    protected void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UniButton);
        initText(typedArray);
        initBackground(typedArray);
        typedArray.recycle();
    }

    @Override
    protected void initBackground(TypedArray a) {
        final Drawable currentBackgroundDrawable = getBackground();
        if (currentBackgroundDrawable == null) {
            throw new IllegalStateException("no background instance exist");
        } else if (currentBackgroundDrawable instanceof ColorDrawable) {
            mBackground = new StateListDrawable();
        } else {
            return;
        }

        @ColorInt final int backgroundColorNormal = ((ColorDrawable) currentBackgroundDrawable).getColor();
        @ColorInt final int backgroundColorPressed = a.getColor(R.styleable.SimpleUniButton_backgroundPressed, backgroundColorNormal);
        @ColorInt final int backgroundColorDisabled = a.getColor(R.styleable.SimpleUniButton_backgroundDisabled, backgroundColorNormal);
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
    }
}
