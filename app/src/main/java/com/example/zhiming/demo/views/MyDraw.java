package com.example.zhiming.demo.views;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiming on 2016/4/14.
 */
public class MyDraw extends View {
    public MyDraw(Context context) {
        super(context);
    }

    public MyDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint= new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //canvas.drawLine(100,100,400,400,paint);
        /*canvas.drawLines(new float[]{500,500,20,20,40,40,200,800},paint);
        //矩形
        canvas.drawRect(300,500,600,800,paint);
        //圆形
        canvas.drawCircle(700,100,80,paint);

        RectF rectF=new RectF(200,200,900,900);
        //
        canvas.drawArc(rectF,45,135,true,paint);

        Path path=new Path();
        path.moveTo(100,200);
        path.lineTo(300,400);
        path.lineTo(500,600);
        path.lineTo(700,300);
        path.lineTo(800,200);
        path.lineTo(100,200);
        paint.setColor(Color.GREEN);
        canvas.drawPath(path,paint);

        Path path1=new Path();
        path1.addRect(rectF, Path.Direction.CW);
        String str="dhafjhbc dsasdhfashfoweugbfkasdgawebfk";


        canvas.drawPath(path1,paint);
        canvas.drawTextOnPath(str,path1,0,0,paint);*/
        int x=getMeasuredWidth()/2;
        int y=getMeasuredHeight()/2;
        RectF rectF=new RectF(x-300,y-400,x+300,y+400);
        canvas.drawRoundRect(rectF,10,10,paint);
        rectF=new RectF(x-300,y-600,x-300,x-410);
        canvas.drawArc(rectF,180,0,true,paint);
        invalidate();
        super.onDraw(canvas);
    }
}
