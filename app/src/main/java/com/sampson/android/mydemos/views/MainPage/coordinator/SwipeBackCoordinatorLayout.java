package com.sampson.android.mydemos.views.MainPage.coordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chengyang on 2017/4/14.
 */

public class SwipeBackCoordinatorLayout extends CoordinatorLayout {

    //下拉触发距离
    private static float SWIPE_TRIGGE = 100;

    public SwipeBackCoordinatorLayout(Context context) {
        super(context);
        this.initialize();
    }

    public SwipeBackCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize();
    }

    public SwipeBackCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initialize();
    }

    private void initialize() {
        //将下拉触发距离设为屏幕高度的1/5
        SWIPE_TRIGGE = (float) (getResources().getDisplayMetrics().heightPixels / 5.0);
    }

    //调用顺序：1
    //target：滑动时，手指触摸的view
    //dx：x方向上滑动的距离
    //dy：y方向上滑动的距离
    //consumed：横纵方向上消耗的距离（初始状态下为0）
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }


}
