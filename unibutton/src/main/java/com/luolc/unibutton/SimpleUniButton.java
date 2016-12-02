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
        mBackgroundNormal = backgroundNormal;
        refreshBackgroundState();
    }

    public void setBackgroundPressed(Drawable backgroundPressed) {
        mBackgroundPressed = backgroundPressed;
        refreshBackgroundState();
    }

    public void setBackgroundDisabled(Drawable backgroundDisabled) {
        mBackgroundDisabled = backgroundDisabled;
        refreshBackgroundState();
    }
}
