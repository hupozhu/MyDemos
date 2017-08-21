package com.sampson.android.viewplus.common;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.sampson.android.viewplus.R;

/**
 * 代替透明状态栏的view
 * <p>
 * Created by chengyang on 2017/4/13.
 */

public class StatusBarView extends View {

    private ObjectAnimator alphaAnimator;

    private boolean initState = false;

    //设置view是否半透明
    private boolean translucentMode = false;
    //设置view仅用来填充黑色区域
    private boolean fillInMode = false;

    // 这两个值只在translucentMode = true下可用，例如
    // initAlpha < 0  --> initAlpha = LIGHT_INIT_MASK_ALPHA / DARK_INIT_MASK_ALPHA.
    // initAlpha >= 0 --> initAlpha = custom value.
    @FloatRange(to = 1.0)
    private float initAlpha = -1;
    @FloatRange(to = 1.0)
    private float darkerAlpha = -1;

    public static final float LIGHT_INIT_MASK_ALPHA = 0.03f;
    public static final float DARK_INIT_MASK_ALPHA = 0.2f;
    public static final float DARKER_MASK_ALPHA = 0.2f;

    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initalize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StatusBarView, defStyleAttr, defStyleRes);
        this.translucentMode = a.getBoolean(R.styleable.StatusBarView_sbv_translucent_mode, false);
        this.fillInMode = a.getBoolean(R.styleable.StatusBarView_sbv_fill_in_mode, false);
        this.initAlpha = Math.min(1.0f, a.getFloat(R.styleable.StatusBarView_sbv_init_alpha, -1));
        this.darkerAlpha = Math.min(1.0f, a.getFloat(R.styleable.StatusBarView_sbv_darker_alpha, -1));
        a.recycle();

        if (translucentMode) {
            setBackgroundResource(android.R.color.black);
            setInitTranslucentAlpha(true);
        } else if (fillInMode) {
            setBackgroundColor(Color.TRANSPARENT);
        } else {
            //TODO:
        }
    }

    public void setInitTranslucentAlpha(boolean isLightTheme) {
        if (translucentMode) {
            setInitState(true);
            cancelAnimator();
            setAlpha(getTargentAlpha(isLightTheme));
        }
    }

    private void cancelAnimator() {
        if (alphaAnimator != null) {
            alphaAnimator.cancel();
        }
    }

    /**
     * 根据sdk版本，主题和状态获取
     *
     * @param isLightTheme
     * @return
     */
    private float getTargentAlpha(boolean isLightTheme) {
        if (isInitState()) {
            if (initAlpha >= 0) {
                return initAlpha;
            } else if (isLightTheme) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return LIGHT_INIT_MASK_ALPHA;
                } else {
                    return DARKER_MASK_ALPHA;
                }
            } else {
                return DARK_INIT_MASK_ALPHA;
            }
        } else {
            return darkerAlpha >= 0 ? darkerAlpha : DARKER_MASK_ALPHA;
        }
    }

    public boolean isInitState() {
        return initState;
    }

    public void setInitState(boolean initState) {
        this.initState = initState;
    }

}
