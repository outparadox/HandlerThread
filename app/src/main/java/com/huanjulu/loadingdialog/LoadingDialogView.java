package com.huanjulu.loadingdialog;/*
 * Copyright (C) 2016，上海宅米贸易有限公司
 * Author: huanjulu on 16/7/5
 * to: 
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LoadingDialogView extends View {

    private int xCenter;
    private int yCenter;
    private int mRadius;

    private Paint paint;
    private Context context;


    public LoadingDialogView(Context context) {
        super(context);
    }

    public LoadingDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);  //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE);


    }

    public LoadingDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadingDialogView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int center = getWidth() / 2;
        int innerCircle = dip2px(context, 83);  //内圆半径
        int ringWidth = dip2px(context, 10);    //圆环宽度

        // 第一种方法绘制圆环
        //绘制内圆
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, this.paint);

        //绘制圆环
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);

        //绘制外圆
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);
        super.onDraw(canvas);

//        xCenter = getWidth() / 2;
//        yCenter = getHeight() / 2;
//        mRadius = getWidth() / 7;


    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
