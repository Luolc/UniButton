package com.luolc.unibutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author LuoLiangchen
 * @since 16/11/24
 */

public class UniButton extends AppCompatButton {

    protected static final int[][] STATES;
    protected static final int[] STATE_SET_DISABLED;
    protected static final int[] STATE_SET_PRESSED;
    protected static final int[] STATE_SET_FOCUSED;
    protected static final int[] STATE_SET_NORMAL;

    @ColorInt protected int mTextColor;
    @ColorInt protected int mTextColorPressed;
    @ColorInt protected int mTextColorDisabled;

    protected StateListDrawable mBackground;
    protected GradientDrawable mBackgroundNormal;
    protected GradientDrawable mBackgroundPressed;
    protected GradientDrawable mBackgroundDisabled;

    @Dimension protected int mRadius;

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

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UniButton);
        initText(typedArray);
        initBackground(typedArray);

        typedArray.recycle();
    }

    protected void initText(TypedArray a) {
        final ColorStateList textColorStateList = getTextColors();
        @ColorInt final int currentTextColor = getCurrentTextColor();
        @ColorInt final int defaultTextColor = textColorStateList.getColorForState(STATE_SET_NORMAL, currentTextColor);
        @ColorInt final int defaultTextColorPressed = textColorStateList.getColorForState(STATE_SET_PRESSED, currentTextColor);
        @ColorInt final int defaultTextColorDisabled = textColorStateList.getColorForState(STATE_SET_DISABLED, currentTextColor);
        mTextColor = defaultTextColor;
        mTextColorPressed = a.getColor(R.styleable.UniButton_textColorPressed, defaultTextColorPressed);
        mTextColorDisabled = a.getColor(R.styleable.UniButton_textColorDisabled, defaultTextColorDisabled);
        setTextColor(mTextColorDisabled, mTextColorPressed, mTextColorPressed, mTextColor);
    }

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
        @ColorInt final int backgroundColorPressed = a.getColor(R.styleable.UniButton_backgroundPressed, backgroundColorNormal);
        @ColorInt final int backgroundColorDisabled = a.getColor(R.styleable.UniButton_backgroundDisabled, backgroundColorNormal);
        mBackgroundNormal = new GradientDrawable();
        mBackgroundPressed = new GradientDrawable();
        mBackgroundDisabled = new GradientDrawable();
        mBackgroundNormal.setColor(backgroundColorNormal);
        mBackgroundPressed.setColor(backgroundColorPressed);
        mBackgroundDisabled.setColor(backgroundColorDisabled);

        mRadius = a.getDimensionPixelSize(R.styleable.UniButton_radius, 0);
        mBackgroundNormal.setCornerRadius(mRadius);
        mBackgroundPressed.setCornerRadius(mRadius);
        mBackgroundDisabled.setCornerRadius(mRadius);

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
