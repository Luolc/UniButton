package com.luolc.unibutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

    public void setBackgroundPressed(Drawable backgroundPressed) {
        mBackgroundPressedWrapper.setWrappedDrawable(backgroundPressed);
    }

    public void setBackgroundDisabled(Drawable backgroundDisabled) {
        mBackgroundDisabledWrapper.setWrappedDrawable(backgroundDisabled);
    }
}
