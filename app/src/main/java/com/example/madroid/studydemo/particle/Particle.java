package com.example.madroid.studydemo.particle;

import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * created by madroid at 2016-07-14
 */
public class Particle {
    private static final String TAG = "Particle";

    protected Paint mPaint ;
    protected int mAlpha ;
    protected float mScale = 1f;
    protected float mRadius ;
    protected int mColor ;
    protected float cx  = 500;
    protected float cy = 500;

    protected float mAccelerationX ;
    protected float mAccelerationY ;
    protected float mSpeedX ;
    protected float mSpeedY ;

    long timeToLive ;

    public Particle(float centerX, float centerY, float radius, int color, float alpha, long time) {
        cx = centerX ;
        cy = centerY ;

        mRadius = radius ;
        timeToLive = time ;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mPaint.setStyle(Paint.Style.FILL);
        mAlpha = (int) (alpha * 255);
        mPaint.setColor(mAlpha);
        mPaint.setColor(color);
    }

    public Particle() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setAlpha(int alpha) {
        mAlpha = alpha ;
        mPaint.setAlpha(mAlpha);
    }

    public void setScale(float scale) {
        mScale = scale ;
    }

    public void setAcceleration(float accelerationX, float accelerationY) {
        mAccelerationX = accelerationX ;
        mAccelerationY = accelerationY ;
    }

    public void setSpeed(float speedX, float speedY) {
        mSpeedX = speedX ;
        mSpeedY = speedY ;
    }

    public void onDraw(Canvas canvas, float cx, float cy) {
        if (null != canvas) {

            canvas.drawCircle(cx, cy, mRadius * mScale, mPaint);
        }
    }




}
