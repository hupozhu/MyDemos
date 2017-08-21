package com.sampson.android.mydemos.views.CustomView.BezierCurve;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 塞贝尔曲线的介绍：
 * https://juejin.im/post/58ce7f0461ff4b006c9a5e66
 * <p>
 *
 * 完成一个简单的例子，画波形
 * http://blog.csdn.net/qq_30379689/article/details/53098481
 *
 * Created by chengyang on 2017/4/10.
 */

public class BezierWaveView extends View {

    private float wareLenght;
    private float centerY;
    private float wareHeight;

    private int waveCount;

    private float offset;

    private int mScreenHeight;
    private int mScreenWidth;

    public BezierWaveView(Context context) {
        super(context);
    }

    public BezierWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenHeight = h;
        mScreenWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (wareLenght == 0)
            return;

        Path path = new Path();
        path.moveTo(-wareLenght + offset, centerY);

        for (int i = 0; i < waveCount; i++) {
            path.quadTo((-wareLenght / 4 * 3) + (i * wareLenght) + offset, centerY + wareHeight, (-wareLenght / 2) + (i * wareLenght) + offset, centerY);
            path.quadTo((-wareLenght / 4) + (i * wareLenght) + offset, centerY - wareHeight, 0 + (i * wareLenght) + offset, centerY); //画出第一段波纹的第二条曲线
        }
        path.lineTo(mScreenWidth, mScreenHeight);
        path.lineTo(0, mScreenHeight);
        path.close();

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawPath(path, paint);
    }


    public void uodate(int wareLenght, int centerY, int wareHeight, int waveCount) {
        this.wareLenght = wareLenght;
        this.centerY = centerY;
        this.wareHeight = wareHeight;
        this.waveCount = waveCount;

//        invalidate();

        ValueAnimator animator = ValueAnimator.ofInt(0, wareLenght); //mWL是一段波纹的长度
        animator.setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (Integer) animation.getAnimatedValue(); //offset 的值的范围在[0,mWL]之间。
                postInvalidate();
            }
        });
        animator.start();
    }
}
