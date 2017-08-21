package com.sampson.android.mydemos.views.NativeViewPlus.ViewPager;

import android.support.v7.widget.CardView;

/**
 * Created by chengyang on 2017/4/11.
 */

public interface CardAdapter {

    CardView getCardViewAt(int position);

    int getCount();

    void setMaxElevationFactor(int evaluateFactor);

    int getMaxElevationFactor();

}
