package com.example.madroid.studydemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.madroid.studydemo.R;

/**
 * Created by madroid on 15-3-16.
 */
public class RippleView extends View{

    private static final String TAG = "RippleView" ;

    private static final boolean DEFAULT_ENABLE = true ;
    private static final int DEFAULT_RIPPLE_NUMBER = 6 ;
    private static final int DEFAULT_DURATION = 4000 ;
    private static final int DEFAULT_TYPE = 0 ;

    private boolean enable ;
    private int rippleNumber ;
    private int duration ;
    private int type ;
    private int rippleColor ;
    private int rippleRadius ;

    private Paint mPaint ;

    public RippleView(Context context) {
        super(context);
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (isInEditMode())
            return;

        if (null == attrs) {
            throw new IllegalArgumentException("Attributes should be provided to this view,");
        }
        mPaint = new Paint() ;
        mPaint.setAntiAlias(true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs , R.styleable.RippleView) ;
        enable = typedArray.getBoolean(R.styleable.RippleView_enable, DEFAULT_ENABLE) ;
        if (!enable)
            return;
        rippleNumber = typedArray.getInt(R.styleable.RippleView_rippleNumber, DEFAULT_RIPPLE_NUMBER) ;
        duration = typedArray.getInt(R.styleable.RippleView_duration, DEFAULT_DURATION) ;
        type = typedArray.getInt(R.styleable.RippleView_ripple_type ,DEFAULT_TYPE) ;
        rippleColor = typedArray.getColor(R.styleable.RippleView_ripple_color, getResources().getColor(R.color.rippelColor) ) ;


        if(type == DEFAULT_TYPE){
            mPaint.setStyle(Paint.Style.STROKE);
        }else {
            mPaint.setStyle(Paint.Style.FILL);
        }

        mPaint.setColor(rippleColor);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(540,540,64,mPaint);
        canvas.drawCircle(540,540,128,mPaint);

        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                canvas.drawCircle(540,540,128,mPaint);

            }
        }) ;

//        for(int i=0;i<rippleNumber;i++){
//            ValueAnimator animator = ValueAnimator.ofFloat(rippleRadius ,540) ;
//            animator.setDuration(duration) ;
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    Log.i("update", animation.getAnimatedValue().toString() + " ; RADIUS: " + getWidth() / 2 + " ; ripple:" + rippleRadius) ;
//                    mRippleView.setRadius((Float)animation.getAnimatedValue());
//                    mRippleView.invalidate();
//                }
//            });
//            animator.setRepeatMode(ValueAnimator.RESTART);
//            animator.setStartDelay(rippleDelay);
//            animator.setRepeatCount(ValueAnimator.INFINITE);
//            animatorList.add(animator) ;
//
//            final ValueAnimator alpha = ValueAnimator.ofFloat(1.0f ,0.2f) ;
//            animator.setDuration(rippleDurationTime) ;
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    Log.i("update",animation.getAnimatedValue().toString() + " ; from: 1.0 ; to:0.2") ;
////                    mRippleView.setPaintAlpha((int)alpha.getAnimatedValue());
////                    mRippleView.invalidate();
//                }
//            });
//            animator.setRepeatMode(ValueAnimator.RESTART);
//            animator.setStartDelay(rippleDelay);
//            animator.setRepeatCount(ValueAnimator.INFINITE);
//            animatorList.add(alpha) ;
//
//            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mRippleView, "Alpha", 1.0f, 0f);
//            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
//            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
//            alphaAnimator.setStartDelay(i * rippleDelay);
//            alphaAnimator.setDuration(rippleDurationTime);
//            animatorList.add(alphaAnimator);
//        }
//
//        animatorSet.playTogether(animatorList);


    }
}
