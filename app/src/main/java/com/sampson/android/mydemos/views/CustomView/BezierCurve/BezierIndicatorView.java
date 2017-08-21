package com.sampson.android.mydemos.views.CustomView.BezierCurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chengyang on 2017/4/10.
 */

public class BezierIndicatorView extends View {

    private PointF p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    private float mRadius;
    private final float BEZIER_MAGIC = 0.551915024494f;

    private Path mBezPath;
    private Paint mBezPaint;

    public BezierIndicatorView(Context context) {
        super(context);
        init();
    }

    public BezierIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBezPath = new Path();
        mBezPaint = new Paint();
        mBezPaint.setColor(Color.parseColor("455678"));
        mBezPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        installBezierPoint();

    }

    /**
     * 初始化贝塞尔球需要用到的点
     */
    private void installBezierPoint() {
        p0 = new PointF(0, -mRadius);//mRadius圆的半径
        p6 = new PointF(0, mRadius);

        p1 = new PointF(mRadius * BEZIER_MAGIC, -mRadius);//bezFactor即0.5519...
        p5 = new PointF(mRadius * BEZIER_MAGIC, mRadius);

        p2 = new PointF(mRadius, -mRadius * BEZIER_MAGIC);
        p4 = new PointF(mRadius, mRadius * BEZIER_MAGIC);

        p3 = new PointF(mRadius, 0);
        p9 = new PointF(-mRadius, 0);

        p11 = new PointF(-mRadius * BEZIER_MAGIC, -mRadius);
        p7 = new PointF(-mRadius * BEZIER_MAGIC, mRadius);

        p10 = new PointF(-mRadius, -mRadius * BEZIER_MAGIC);
        p8 = new PointF(-mRadius, mRadius * BEZIER_MAGIC);

    }

    /**
     * 绘制贝塞尔球
     */
    private void drawBezierBall(Canvas canvas) {
        mBezPath.moveTo(p0.x, p0.y);
        mBezPath.cubicTo(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
        mBezPath.cubicTo(p4.x, p4.y, p5.x, p5.y, p6.x, p6.y);
        mBezPath.cubicTo(p7.x, p7.y, p8.x, p8.y, p9.x, p9.y);
        mBezPath.cubicTo(p10.x, p10.y, p11.x, p11.y, p0.x, p0.y);
        mBezPath.close();

        canvas.drawPath(mBezPath, mBezPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBezierBall(canvas);
    }
}
