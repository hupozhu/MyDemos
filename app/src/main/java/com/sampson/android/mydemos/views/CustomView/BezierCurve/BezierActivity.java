package com.sampson.android.mydemos.views.CustomView.BezierCurve;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.sampson.android.mydemos.R;

/**
 * 塞贝尔曲线的绘制在android提供有api，path.quadTo方法，自定义view的绘图需要熟悉PATH类提供的api
 * <p>
 * Created by chengyang on 2017/4/10.
 */

public class BezierActivity extends AppCompatActivity {

    BezierWaveView bezierView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bezier_curve_activity);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        bezierView = (BezierWaveView) findViewById(R.id.bezier);
        int waveCount = 2;
        bezierView.uodate(width / waveCount, 500, 200, waveCount * 2);

    }
}
