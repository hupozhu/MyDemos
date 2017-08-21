package com.sampson.android.mydemos.views.MainPage.coordinator;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.ViewConfiguration;

/**
 * Created by chengyang on 2017/4/14.
 */

public class NestedScrollAppBarLayout extends AppBarLayout implements NestedScrollingChild {

    private NestedScrollingChildHelper nestedScrollingChildHelper;

    // data
    private float touchSlop;

    public NestedScrollAppBarLayout(Context context) {
        super(context);
        initialize();
    }

    public NestedScrollAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        this.nestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setNestedScrollingEnabled(true);
        }

        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public static class Behavior extends AppBarLayout.Behavior {

        private NestedScrollAppBarLayout appBarLayout = null;

        public Behavior(){
            super();
        }

    }

}
