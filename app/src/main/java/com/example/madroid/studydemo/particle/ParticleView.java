package com.example.madroid.studydemo.particle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.madroid.studydemo.R;

import java.util.ArrayList;

/**
 * created by madroid at 2016-07-14
 */
public class ParticleView extends View {
    private static final String TAG = "ParticleView";

    private ArrayList<Particle> mParticles;

    private ValueAnimator mAnim ;
    private Context mContext ;

    private int color ;

    public ParticleView(Context context) {
        this(context, null);
    }

    public ParticleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;
        init();
    }

    private void init() {
        color = mContext.getColor(R.color.colorAccent) ;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = resolveSize(500, widthMeasureSpec) ;
        int height = resolveSize(500, heightMeasureSpec) ;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getMeasuredHeight() ;
        int width = getMeasuredWidth() ;
        Log.e(TAG, "height:" + height+ ",width:" + width) ;
        int cx =  width/2 ;
        int cy =  height/2 ;
        //initParticle(cx, cy, 200);
        Log.e(TAG, "cx:" + cx + ",cy:" + cy) ;
        if (null != mParticles && mParticles.size() > 0) {
            for (Particle particle : mParticles) {
                particle.onDraw(canvas, cx, cy);
            }
        }
    }

//    private void initParticle(int cx, int cy, int radius) {
//        Log.e(TAG, "cos(90 :" + Math.cos(Math.toRadians(90)) + ",sin90:" + Math.sin(Math.toRadians(90))) ;
//
//        for (int i = 0; i < 30; i++) {
//            int x = (int) (radius * Math.cos(Math.toRadians(12 * i)) + cx);
//            int y = (int) (radius * Math.sin(Math.toRadians(12 * i)) + cy);
//            Particle particle = new Particle(x, y, 10, color, 1f) ;
//            mParticles.add(particle);
//        }
//    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
    }

    public void setParticle(ArrayList<Particle> particles) {
        mParticles = particles ;
    }

    public void startAnim() {
        mAnim = getAnim() ;
        mAnim.start();
    }

    private ValueAnimator getAnim() {
        if (null == mAnim) {
            mAnim = ValueAnimator.ofFloat(0,1f) ;
            mAnim.setDuration(500) ;
            mAnim.setInterpolator(new LinearInterpolator());
            mAnim.addUpdateListener(mListener);
            mAnim.setInterpolator(new LinearInterpolator());
        }

        return mAnim ;
    }

    private ValueAnimator.AnimatorUpdateListener mListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) animation.getAnimatedValue();
            invalidate();
        }
    } ;
}
