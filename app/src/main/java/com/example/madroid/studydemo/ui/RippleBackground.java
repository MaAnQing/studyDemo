package com.example.madroid.studydemo.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.madroid.studydemo.R;

import java.util.ArrayList;

/**
 * Created by fyu on 11/3/14.
 */

public class RippleBackground extends RelativeLayout{

    private static final int DEFAULT_RIPPLE_COUNT=6;
    private static final int DEFAULT_DURATION_TIME=3000;
    private static final float DEFAULT_SCALE=6.0f;
    private static final int DEFAULT_FILL_TYPE=0;
    private static final boolean DEFAULT_ENABLE = true;

    private boolean rippleEnable ;
    private int rippleColor;
    private float rippleStrokeWidth;
    private float rippleRadius;
    private int rippleDurationTime;
    private int rippleAmount;
    private int rippleDelay;
    private float rippleScale;
    private int rippleType;
    private Paint paint;
    private boolean animationRunning=false;
    private AnimatorSet animatorSet;
    private ArrayList<Animator> animatorList;
    private LayoutParams rippleParams;
    private ArrayList<RippleView> rippleViewList=new ArrayList<RippleView>();

    public RippleBackground(Context context) {
        super(context);
    }

    public RippleBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode())
            return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleBackground);
        rippleEnable=typedArray.getBoolean(R.styleable.RippleBackground_rb_enable,DEFAULT_ENABLE) ;

        if(!rippleEnable){
            return;
        }
        rippleColor=typedArray.getColor(R.styleable.RippleBackground_rb_color, getResources().getColor(R.color.rippelColor));
        rippleStrokeWidth=typedArray.getDimension(R.styleable.RippleBackground_rb_strokeWidth, getResources().getDimension(R.dimen.rippleStrokeWidth));
        rippleRadius=typedArray.getDimension(R.styleable.RippleBackground_rb_radius,getResources().getDimension(R.dimen.rippleRadius));
        rippleDurationTime=typedArray.getInt(R.styleable.RippleBackground_rb_duration,DEFAULT_DURATION_TIME);
        rippleAmount=typedArray.getInt(R.styleable.RippleBackground_rb_rippleAmount,DEFAULT_RIPPLE_COUNT);
        rippleScale=typedArray.getFloat(R.styleable.RippleBackground_rb_scale,DEFAULT_SCALE);
        rippleType=typedArray.getInt(R.styleable.RippleBackground_rb_type,DEFAULT_FILL_TYPE);
        typedArray.recycle();

        rippleDelay=rippleDurationTime/rippleAmount;

        paint = new Paint();

        paint.setAntiAlias(true);
        if(rippleType==DEFAULT_FILL_TYPE){
            rippleStrokeWidth=4;
            paint.setStyle(Paint.Style.FILL);
        }else
            paint.setStyle(Paint.Style.STROKE);
        paint.setColor(rippleColor);

        //rippleParams=new LayoutParams((int)(2*(rippleRadius+rippleStrokeWidth)),(int)(2*(rippleRadius+rippleStrokeWidth)));
        rippleParams=new LayoutParams(1080,1080);
        rippleParams.addRule(CENTER_IN_PARENT, TRUE);

        animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorList=new ArrayList<Animator>();

        for(int i=0;i<rippleAmount;i++){
            final RippleView mRippleView=new RippleView(getContext());
            addView(mRippleView,rippleParams);
            rippleViewList.add(mRippleView);

            ValueAnimator animator = ValueAnimator.ofFloat(rippleRadius ,540) ;
            animator.setDuration(rippleDurationTime) ;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.i("update",animation.getAnimatedValue().toString() + " ; RADIUS: "+getWidth()/2 + " ; ripple:"+ rippleRadius) ;
                    mRippleView.setRadius((Float)animation.getAnimatedValue());
                    mRippleView.invalidate();
                }
            });
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setStartDelay(i * rippleDelay);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animatorList.add(animator) ;

            final ValueAnimator alpha = ValueAnimator.ofFloat(1.0f ,0.2f) ;
            animator.setDuration(rippleDurationTime) ;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.i("update",animation.getAnimatedValue().toString() + " ; from: 1.0 ; to:0.2") ;
//                    mRippleView.setPaintAlpha((int)alpha.getAnimatedValue());
//                    mRippleView.invalidate();
                }
            });
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setStartDelay(i * rippleDelay);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animatorList.add(alpha) ;


//            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mRippleView, "ScaleX", 1.0f, rippleScale);
//            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
//            scaleXAnimator.setStartDelay(i * rippleDelay);
//            scaleXAnimator.setDuration(rippleDurationTime);
//            animatorList.add(scaleXAnimator);
//            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mRippleView, "ScaleY", 1.0f, rippleScale);
//            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
//            scaleYAnimator.setStartDelay(i * rippleDelay);
//            scaleYAnimator.setDuration(rippleDurationTime);
//            animatorList.add(scaleYAnimator);

            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mRippleView, "Alpha", 1.0f, 0f);
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
            alphaAnimator.setStartDelay(i * rippleDelay);
            alphaAnimator.setDuration(rippleDurationTime);
            animatorList.add(alphaAnimator);
        }

        animatorSet.playTogether(animatorList);
    }

    private class RippleView extends View{

        private float radius ;
        private int alpha ;

        public RippleView(Context context) {
            super(context);
            this.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int x=(Math.min(getWidth(),getHeight()))/2;
            //canvas.clipRect(0,30,540,570);
            //canvas.drawColor(Color.RED);

            canvas.drawCircle(x,x,getRadius()-rippleStrokeWidth,paint);
        }

        private void setPaintAlpha(int a){
            //alpha = a ;
            paint.setAlpha(a);
        }

        private float getPaintAlpha(){
            return alpha ;
        }
        private void setRadius(float r){
            radius = r ;
        }

        private float getRadius(){
            return radius ;
        }

    }

    public void setRBEnable(boolean b){
        rippleEnable = b ;
    }
    public void startRippleAnimation(){

        if(!rippleEnable){
            return;
        }
        if(!isRippleAnimationRunning()){
            for(RippleView rippleView:rippleViewList){
                rippleView.setVisibility(VISIBLE);
            }
            animatorSet.start();
            animationRunning=true;
        }
    }

    public void stopRippleAnimation(){
        if(isRippleAnimationRunning()){
            animatorSet.end();
            animationRunning=false;
        }
    }

    public boolean isRippleAnimationRunning(){
        return animationRunning;
    }
}
