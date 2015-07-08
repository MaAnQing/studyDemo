package com.example.madroid.studydemo.MPAndroidChart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Debug;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;

import com.example.madroid.studydemo.R;

import java.util.Deque;


/**
 * @author: madroid
 * @date: 2015-07-06 20:23
 */
public class EcgView extends FrameLayout {
    private static final String TAG = "EcgView";

    private boolean mShowGird = true;
    private int mGirdLineColor ;
    private int mGirdWideLineColor ;
    private float mGirdHeight ;
    private float mGirdWidth ;
    private int mGirdLineWidth ;
    private int mGirdWideLineWidth;

    private Paint mPaint ;
    private Canvas mCanvas ;

    public EcgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupAttrs(attrs);
        setWillNotDraw(false);
    }

    public EcgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAttrs(attrs);
        setWillNotDraw(false);
    }

    public EcgView(Context context) {
        super(context);
        setWillNotDraw(false);
    }


    private void setupAttrs(AttributeSet attr) {
        TypedArray type = getContext().getTheme().obtainStyledAttributes(attr, R.styleable.EcgView,0,0) ;

        try {
            mShowGird = type.getBoolean(R.styleable.EcgView_showGird,true) ;
            mGirdLineColor = type.getColor(R.styleable.EcgView_girdColor,Color.rgb(236, 152, 0)) ;
            mGirdWideLineColor = type.getColor(R.styleable.EcgView_girdWideLingColor,Color.rgb(236, 152, 0)) ;
            mGirdHeight = type.getDimension(R.styleable.EcgView_girdHeight, 1) ;
            mGirdWidth = type.getDimension(R.styleable.EcgView_girdWidth, 1) ;
            mGirdLineWidth = type.getInteger(R.styleable.EcgView_girdLineWidth, 2) ;
            mGirdWideLineWidth = type.getInteger(R.styleable.EcgView_girdWideLineWidth, 4) ;

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            type.recycle();
        }

        // mm to pixels
        mGirdHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mGirdHeight, getResources().getDisplayMetrics());
        mGirdWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mGirdWidth, getResources().getDisplayMetrics());

        Log.i(TAG,"height:" + mGirdHeight + "; width:" + mGirdWidth) ;
        mPaint = new Paint();

        Log.i(TAG,"setupAttrs ") ;
    }

    public int getGirdLineWidth() {
        return mGirdLineWidth;
    }

    /**
     *
     * @param mGirdLineWidth
     */
    public void setGirdLineWidth(int mGirdLineWidth) {
        this.mGirdLineWidth = mGirdLineWidth;
        invalidate();
        requestLayout();
    }

    public int getGirdWideLineWidth() {
        return mGirdWideLineWidth;
    }

    public void setmGirdwideLineWidth(int mGirdBigLineWidth) {
        this.mGirdWideLineWidth = mGirdBigLineWidth;
        invalidate();
        requestLayout();
    }

    public int getGirdLineColor() {
        return mGirdLineColor;
    }

    public void setGirdLineColor(int mGirdLineColor) {
        Log.i("madroid-ecg", "setGirdLineColor start") ;
        this.mGirdLineColor = mGirdLineColor;
        invalidate();
        requestLayout();
        Log.i("madroid-ecg", "setGirdLineColor end") ;
    }

    public float getGirdHeight() {
        return mGirdHeight;
    }

    public void setGirdHeight(float mGirdHeight) {
        this.mGirdHeight = mGirdHeight;
        invalidate();
        requestLayout();
    }

    public float getGirdWidth() {
        return mGirdWidth;
    }

    public void setGirdWidth(float mGirdWidth) {
        this.mGirdWidth = mGirdWidth;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas ;
        Log.i(TAG,"onDraw") ;

        if (!mShowGird) {
            return;
        }

        int height = canvas.getHeight() ;
        int width = canvas.getWidth() ;

        mPaint.setColor(mGirdLineColor);//颜色
        mPaint.setStyle(Paint.Style.STROKE);
        int yCount = (int)(height / mGirdHeight) + 1;
        int xCount = (int)(width / mGirdWidth) + 1;
        for (int i = 0; i < yCount; i++) {
            if (i%5 == 0) {
                mPaint.setStrokeWidth(mGirdWideLineWidth);
                mPaint.setColor(mGirdWideLineColor);
            }else {
                mPaint.setStrokeWidth(mGirdLineWidth);
                mPaint.setColor(mGirdLineColor);
            }
            canvas.drawLine(0, i * mGirdHeight - 1, width, i * mGirdHeight - 1, mPaint);

        }
        for (int i = 0; i < xCount; i++) {
            if (i%5 == 0) {
                mPaint.setStrokeWidth(mGirdWideLineWidth);
                mPaint.setColor(mGirdWideLineColor);
            }else {
                mPaint.setStrokeWidth(mGirdLineWidth);
                mPaint.setColor(mGirdLineColor);
            }
            canvas.drawLine(i * mGirdWidth - 1, 0, i * mGirdWidth - 1, height, mPaint);

        }

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        Log.i(TAG,"onMeasure") ;
////        int minw = mCanvas.getWidth() + getPaddingLeft() + getPaddingRight();
////        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);
////
////        int minh = mCanvas.getHeight() + getPaddingBottom() + getPaddingTop();
////
////        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);
////
////        setMeasuredDimension(w, h);
//    }

}
