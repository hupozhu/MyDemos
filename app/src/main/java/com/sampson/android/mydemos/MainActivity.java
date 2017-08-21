package com.sampson.android.mydemos;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sampson.android.mydemos.service.ServiceManagerActivity;
import com.sampson.android.mydemos.views.CustomView.BezierCurve.BezierActivity;
import com.sampson.android.mydemos.views.NativeViewPlus.ViewPager.ViewPagerDemoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bezierBtn, viewPagerBtn, basicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bezierBtn = (Button) findViewById(R.id.bezierBtn);
        viewPagerBtn = (Button) findViewById(R.id.viewPagerBtn);
        basicService = (Button) findViewById(R.id.basic_service);

        bezierBtn.setOnClickListener(this);
        viewPagerBtn.setOnClickListener(this);
        basicService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bezierBtn:
                startActivity(new Intent(MainActivity.this, BezierActivity.class));
                break;

            case R.id.viewPagerBtn:
                startActivity(new Intent(MainActivity.this, ViewPagerDemoActivity.class));
                break;

            case R.id.basic_service:
                startActivity(new Intent(MainActivity.this, ServiceManagerActivity.class));
                break;
        }
    }


    @ColorInt
    public static int getPrimaryColor(Context context) {
        TypedArray a = context.obtainStyledAttributes(new int[]{R.attr.colorPrimary});
        int color = a.getColor(0, ContextCompat.getColor(context, R.color.colorPrimary));
        a.recycle();
        return color;
    }
}
