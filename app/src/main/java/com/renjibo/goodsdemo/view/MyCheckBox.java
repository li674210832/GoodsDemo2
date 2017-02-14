package com.renjibo.goodsdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 任继波
 * Created by Administrator on 2017/2/10.
 */

public class MyCheckBox extends View implements View.OnTouchListener{

    private Paint mPaint;
    //绘制圆弧的进度值
    private int progress = 0;
    //线1的x轴
    private int line1_x = 0;
    //线1的y轴
    private int line1_y = 0;
    //线2的x轴
    private int line2_x = 0;
    //线2的y轴
    private int line2_y = 0;
    public MyCheckBox(Context context) {
        this(context,null);
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       //this.mPaint.setARGB(155,167,190,206);
        //this.mPaint.setStrokeWidth(2);
//        mPaint.setColor(Color.RED);
//        canvas.drawCircle(getHeight(),getWidth(),20,mPaint);
//        //圆弧半径
//        int radius = getWidth() / 2 - 5;
//        //获取圆心的x坐标
//        int center = getWidth() / 2;
//        int center1 = center - getWidth() / 5;
//        //画第一根线
//        canvas.drawLine(center1, center, center1 + line1_x, center + line1_y, paint);
//
//        if (line1_x == radius / 3) {
//            line2_x = line1_x;
//            line2_y = line1_y;
//            line1_x++;
//            line1_y++;
//        }
//        if (line1_x >= radius / 3 && line2_x <= radius) {
//            line2_x++;
//            line2_y--;
//        }
//        //画第二根线
//        canvas.drawLine(center1 + line1_x - 1, center + line1_y, center1 + line2_x, center + line2_y, paint);
        progress++;

        /**
         * 绘制圆弧
         */
        Paint paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.RED);
        //设置圆弧的宽度
        paint.setStrokeWidth(5);
        //设置圆弧为空心
        paint.setStyle(Paint.Style.STROKE);
        //消除锯齿
        paint.setAntiAlias(true);

        //获取圆心的x坐标
        int center = getWidth() / 2;
        int center1 = center - getWidth() / 5;
        //圆弧半径
        int radius = getWidth() / 2 - 5;

        //定义的圆弧的形状和大小的界限
        RectF rectF = new RectF(center - radius -1, center - radius -1 ,center + radius + 1, center + radius + 1);

        //根据进度画圆弧
        canvas.drawArc(rectF, 235, -360 * progress / 100, false, paint);

        /**
         * 绘制对勾
         */
        //先等圆弧画完，才话对勾
        if(progress >= 100) {
            if(line1_x < radius / 3) {
                line1_x++;
                line1_y++;
            }
            //画第一根线
            canvas.drawLine(center1, center, center1 + line1_x, center + line1_y, paint);

            if (line1_x == radius / 3) {
                line2_x = line1_x;
                line2_y = line1_y;
                line1_x++;
                line1_y++;
            }
            if (line1_x >= radius / 3 && line2_x <= radius) {
                line2_x++;
                line2_y--;
            }
            //画第二根线
            canvas.drawLine(center1 + line1_x - 1, center + line1_y, center1 + line2_x, center + line2_y, paint);
        }

        //每隔10毫秒界面刷新
        postInvalidateDelayed(0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        System.out.print("出发");
        return true;
    }
}
