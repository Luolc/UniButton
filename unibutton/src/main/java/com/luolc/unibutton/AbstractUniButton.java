package com.luolc.unibutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author LuoLiangchen
 * @since 16/11/29
 */

public abstract class AbstractUniButton extends AppCompatButton {

    protected static final int[][] STATES;
    protected static final int[] STATE_SET_DISABLED;
    protected static final int[] STATE_SET_PRESSED;
    protected static final int[] STATE_SET_FOCUSED;
    protected static final int[] STATE_SET_NORMAL;

    @ColorInt protected int mTextColor;
    @ColorInt protected int mTextColorPressed;
    @ColorInt protected int mTextColorDisabled;

    protected StateListDrawable mBackground;
    protected DrawableWrapper mBackgroundNormalWrapper;
    protected DrawableWrapper mBackgroundPressedWrapper;
    protected DrawableWrapper mBackgroundDisabledWrapper;

    static {
        STATE_SET_DISABLED = new int[] { -android.R.attr.state_enabled };
        STATE_SET_PRESSED = new int[] { android.R.attr.state_enabled, android.R.attr.state_pressed };
        STATE_SET_FOCUSED = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        STATE_SET_NORMAL = new int[] { android.R.attr.state_enabled };
        STATES = new int[][] {
                STATE_SET_DISABLED,
                STATE_SET_PRESSED,
                STATE_SET_FOCUSED,
                STATE_SET_NORMAL
        };
    }

    public AbstractUniButton(Context context) {
        this(context, null);
    }

    public AbstractUniButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public AbstractUniButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) init(context, attrs);
    }

    public void setTextColorNormal(@ColorInt int textColor) {
        mTextColor = textColor;
        setTextColor(mTextColorDisabled, mTextColorPressed, mTextColorPressed, mTextColor);
    }

    public void setTextColorPressed(@ColorInt int textColorPressed) {
        mTextColorPressed = textColorPressed;
        setTextColor(mTextColorDisabled, mTextColorPressed, mTextColorPressed, mTextColor);
    }

    public void setTextColorDisabled(@ColorInt int textColorDisabled) {
        mTextColorDisabled = textColorDisabled;
        setTextColor(mTextColorDisabled, mTextColorPressed, mTextColorPressed, mTextColor);
    }

    public void setBackgroundColorNormal(@ColorInt int backgroundColorNormal) {
        ColorDrawable backgroundNormal = new ColorDrawable();
        backgroundNormal.setColor(backgroundColorNormal);
        mBackgroundNormalWrapper.setWrappedDrawable(backgroundNormal);
    }

    public void setBackgroundColorPressed(@ColorInt int backgroundColorPressed) {
        ColorDrawable backgroundPressed = new ColorDrawable();
        backgroundPressed.setColor(backgroundColorPressed);
        mBackgroundNormalWrapper.setWrappedDrawable(backgroundPressed);
    }

    public void setBackgroundColorDisabled(@ColorInt int backgroundColorDisabled) {
        ColorDrawable backgroundDisabled = new ColorDrawable();
        backgroundDisabled.setColor(backgroundColorDisabled);
        mBackgroundNormalWrapper.setWrappedDrawable(backgroundDisabled);
    }

    protected void init(Context context, AttributeSet attrs) {
        initText(context, attrs);
        initBackground(context, attrs);
    }

    protected void initText(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AbstractUniButton);

        final ColorStateList textColorStateList = getTextColors();
        @ColorInt final int currentTextColor = getCurrentTextColor();
        @ColorInt final int defaultTextColor =
                textColorStateList.getColorForState(STATE_SET_NORMAL, currentTextColor);
        @ColorInt final int defaultTextColorPressed =
                textColorStateList.getColorForState(STATE_SET_PRESSED, currentTextColor);
        @ColorInt final int defaultTextColorDisabled =
                textColorStateList.getColorForState(STATE_SET_DISABLED, currentTextColor);
        mTextColor = a.getColor(R.styleable.AbstractUniButton_android_textColor, defaultTextColor);
        mTextColorPressed = a.getColor(R.styleable.AbstractUniButton_textColorPressed, defaultTextColorPressed);
        mTextColorDisabled = a.getColor(R.styleable.AbstractUniButton_textColorDisabled, defaultTextColorDisabled);
        setTextColor(mTextColorDisabled, mTextColorPressed, mTextColorPressed, mTextColor);

        a.recycle();
    }

    protected void initBackground(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AbstractUniButton);

        final Drawable currentBackground = getBackground();
        if (currentBackground == null) {
            throw new IllegalStateException("no background instance exist");
        } else if (currentBackground instanceof StateListDrawable) {
            return;
        }

        Drawable backgroundNormal = a.getDrawable(R.styleable.AbstractUniButton_android_background);
        Drawable backgroundPressed = a.getDrawable(R.styleable.AbstractUniButton_backgroundPressed);
        Drawable backgroundDisabled = a.getDrawable(R.styleable.AbstractUniButton_backgroundDisabled);
        if (backgroundNormal == null) backgroundNormal = currentBackground;
        if (backgroundPressed == null) backgroundPressed = backgroundNormal;
        if (backgroundDisabled == null) backgroundDisabled = backgroundNormal;

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

    protected void setTextColor(@ColorInt int disabled, @ColorInt int pressed, @ColorInt int focused, @ColorInt int normal) {
        @ColorInt final int[] colors = new int[] { disabled, pressed, focused, normal };
        super.setTextColor(new ColorStateList(STATES, colors));
    }
}
