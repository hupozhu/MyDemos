package com.sampson.android.mydemos.hencoder.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chengyang on 2017/8/21.
 */

public class PaintTestView extends View {

    public PaintTestView(Context context) {
        super(context);
        init();
    }

    public PaintTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1、paint的简单用法
        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);   //设置paint的样式
        paint1.setColor(Color.parseColor("#E91E63"));   //设置颜色值
        paint1.setStrokeWidth(10);                      //设置画笔宽度
        canvas.drawLine(50, 50, 250, 250, paint1);

        //2、还有其他的几种渐变自行看笔记
        Paint paint2 = new Paint();
        Shader shader = new LinearGradient(300, 50, 500, 250, Color.parseColor("#00EEEE"), Color.parseColor("#FFF68F"), Shader.TileMode.CLAMP); //TileMode代表着色模式
        paint2.setStyle(Paint.Style.FILL);   //设置paint的样式
        paint2.setShader(shader);
        canvas.drawCircle(450, 200, 150, paint2);
    }
}
