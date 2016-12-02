package com.luolc.unibutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

/**
 * @author LuoLiangchen
 * @since 16/11/25
 */

public class SimpleUniButton extends AbstractUniButton {

    public SimpleUniButton(Context context) {
        super(context);
    }

    public SimpleUniButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleUniButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBackgroundNormal(Drawable backgroundNormal) {
        mBackgroundNormalWrapper.setWrappedDrawable(backgroundNormal);
    }

    public void setBackgroundNormal(@DrawableRes int resId) {
        mBackgroundNormalWrapper.setWrappedDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setBackgroundPressed(Drawable backgroundPressed) {
        mBackgroundPressedWrapper.setWrappedDrawable(backgroundPressed);
    }

    public void setBackgroundPressed(@DrawableRes int resId) {
        mBackgroundPressedWrapper.setWrappedDrawable(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setBackgroundDisabled(Drawable backgroundDisabled) {
        mBackgroundDisabledWrapper.setWrappedDrawable(backgroundDisabled);
    }

    public void setBackgroundDisabled(@DrawableRes int resId) {
        mBackgroundDisabledWrapper.setWrappedDrawable(ContextCompat.getDrawable(getContext(), resId));
    }
}
