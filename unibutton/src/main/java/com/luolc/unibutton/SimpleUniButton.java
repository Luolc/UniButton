package com.luolc.unibutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author LuoLiangchen
 * @since 16/11/25
 */

public class SimpleUniButton extends AppCompatButton {

    protected static final int[][] STATES;
    protected static final int[] STATE_SET_DISABLED;
    protected static final int[] STATE_SET_PRESSED;
    protected static final int[] STATE_SET_FOCUSED;
    protected static final int[] STATE_SET_NORMAL;

    @ColorInt protected int mTextColor;
    @ColorInt protected int mTextColorPressed;
    @ColorInt protected int mTextColorDisabled;

    protected StateListDrawable mBackground;
    protected Drawable mBackgroundNormal;
    protected Drawable mBackgroundPressed;
    protected Drawable mBackgroundDisabled;

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

    public SimpleUniButton(Context context) {
        this(context, null);
    }

    public SimpleUniButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public SimpleUniButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleUniButton);
        initText(typedArray);
        initBackground(typedArray);
        typedArray.recycle();
    }

    protected void initText(TypedArray a) {
        final ColorStateList textColorStateList = getTextColors();
        @ColorInt final int currentTextColor = getCurrentTextColor();
        @ColorInt final int defaultTextColor =
                textColorStateList.getColorForState(STATE_SET_NORMAL, currentTextColor);
        @ColorInt final int defaultTextColorPressed =
                textColorStateList.getColorForState(STATE_SET_PRESSED, currentTextColor);
        @ColorInt final int defaultTextColorDisabled =
                textColorStateList.getColorForState(STATE_SET_DISABLED, currentTextColor);
        mTextColor = a.getColor(R.styleable.SimpleUniButton_android_textColor, defaultTextColor);
        mTextColorPressed = a.getColor(R.styleable.SimpleUniButton_textColorPressed, defaultTextColorPressed);
        mTextColorDisabled = a.getColor(R.styleable.SimpleUniButton_textColorDisabled, defaultTextColorDisabled);
        setTextColor(mTextColorDisabled, mTextColorPressed, mTextColorPressed, mTextColor);
    }

    protected void initBackground(TypedArray a) {
        final Drawable currentBackground = getBackground();
        if (currentBackground == null) {
            throw new IllegalStateException("no background instance exist");
        } else if (currentBackground instanceof StateListDrawable) {
            return;
        }

        mBackground = new StateListDrawable();
        mBackgroundNormal = a.getDrawable(R.styleable.SimpleUniButton_android_background);
        mBackgroundPressed = a.getDrawable(R.styleable.SimpleUniButton_backgroundPressed);
        mBackgroundDisabled = a.getDrawable(R.styleable.SimpleUniButton_backgroundDisabled);
        if (mBackgroundNormal == null) mBackgroundNormal = currentBackground;
        if (mBackgroundPressed == null) mBackgroundPressed = mBackgroundNormal;
        if (mBackgroundDisabled == null) mBackgroundDisabled = mBackgroundNormal;

        mBackground.addState(STATE_SET_DISABLED, mBackgroundDisabled);
        mBackground.addState(STATE_SET_PRESSED, mBackgroundPressed);
        mBackground.addState(STATE_SET_FOCUSED, mBackgroundPressed);
        mBackground.addState(STATE_SET_NORMAL, mBackgroundNormal);

        super.setBackgroundDrawable(mBackground);
    }

    private void setTextColor(@ColorInt int disabled, @ColorInt int pressed, @ColorInt int focused, @ColorInt int normal) {
        @ColorInt final int[] colors = new int[] { disabled, pressed, focused, normal };
        super.setTextColor(new ColorStateList(STATES, colors));
    }
}
