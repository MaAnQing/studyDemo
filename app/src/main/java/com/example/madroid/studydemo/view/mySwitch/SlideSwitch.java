package com.example.madroid.studydemo.view.mySwitch;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CompoundButton;

import com.example.madroid.studydemo.R;


/**
 * created by madroid at 2015-10-14
 */

public class SlideSwitch extends CompoundButton {

    private static final float DEFAULT_PADDING = 6;

    private Paint mPaint;
    private Bitmap mDisableBitmap;
    private Bitmap mEnableBitmap;
    private Bitmap mThumbBitmap;
    private float mPaddingLeft;
    private float paddingTop;
    private float mPaddingChecked;
    private float mPaddingUnchecked;
    private float mMaxSlideWidth;

    public SlideSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SlideSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public SlideSwitch(Context context) {
        this(context, null);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.slideSwitch);
        int disableDrawableId = a.getResourceId(R.styleable.slideSwitch_disableDrawable, R.drawable.switch_disable_bg);
        int enableDrawableId = a.getResourceId(R.styleable.slideSwitch_enableDrawable, R.drawable.switch_enable_bg);
        int thumbId = a.getResourceId(R.styleable.slideSwitch_thumb, R.drawable.switch_thumb_drawable);
        a.recycle();

        mDisableBitmap = drawableToBitmap(context.getResources().getDrawable(disableDrawableId));
        mEnableBitmap = drawableToBitmap(context.getResources().getDrawable(enableDrawableId));
        mThumbBitmap = drawableToBitmap(context.getResources().getDrawable(thumbId));

        mMaxSlideWidth = mEnableBitmap.getWidth() - DEFAULT_PADDING - mThumbBitmap.getWidth() - DEFAULT_PADDING;
        mPaddingChecked = mMaxSlideWidth + DEFAULT_PADDING;
        mPaddingUnchecked = DEFAULT_PADDING;
        if (isChecked()) {
            mPaddingLeft = mPaddingChecked;
        } else {
            mPaddingLeft = mPaddingUnchecked;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) return ((BitmapDrawable) drawable).getBitmap();

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.max(mDisableBitmap.getWidth(), mEnableBitmap.getWidth());
        int height = Math.max(mDisableBitmap.getHeight(), mEnableBitmap.getHeight());

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isChecked()) {
            canvas.drawBitmap(mEnableBitmap, 0, 0, mPaint);
            paddingTop = (mEnableBitmap.getHeight() - mThumbBitmap.getHeight()) / 2;
            canvas.drawBitmap(mThumbBitmap, mPaddingLeft, paddingTop, mPaint);
        } else {
            canvas.drawBitmap(mDisableBitmap, 0, 0, mPaint);
            paddingTop = (mDisableBitmap.getHeight() - mThumbBitmap.getHeight()) / 2;
            canvas.drawBitmap(mThumbBitmap, mPaddingLeft, paddingTop, mPaint);
        }
    }

    int mLastX, mCurrentX, mTemp;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                Log.i("madroid", "ACTION_DOWN : invalidateView max " + mMaxSlideWidth);
                break;

            case MotionEvent.ACTION_MOVE:
                mCurrentX = (int) event.getX();
                mTemp = mCurrentX - mLastX;
                if (isChecked()) {
                    if (mTemp <= -mMaxSlideWidth) {
                        mPaddingLeft = mPaddingUnchecked;
                    } else {
                        if (mTemp < 0) {
                            mPaddingLeft = mPaddingChecked + mTemp;
                        }else {
                            mPaddingLeft = mPaddingChecked;
                        }
                    }
                    Log.i("madroid", "padding right : " + mPaddingLeft);
                } else {
                    if (mTemp >= mMaxSlideWidth) {
                        mPaddingLeft = mPaddingChecked;
                    } else {

                        if (mTemp > 0) {
                            mPaddingLeft = mTemp + mPaddingUnchecked;
                        }else {
                            mPaddingLeft = mPaddingUnchecked;
                        }
                    }
                    Log.i("madroid", "padding right : " + mPaddingLeft);

                }
                invalidateView();
                Log.i("madroid", "ACTION_MOVE : invalidateView mTemp " + mTemp);

                break;

            case MotionEvent.ACTION_UP:
                mCurrentX = (int) event.getX();
                mTemp = mCurrentX - mLastX;

                if (mTemp == 0) {
                    toggle();
                }

                if (isChecked()) {
                    if (mTemp < -mMaxSlideWidth/2) {
                        mPaddingLeft = mPaddingUnchecked ;
                        toggle();
                    }else {
                        mPaddingLeft = mPaddingChecked ;
                    }
                }else {
                    if (mTemp > mMaxSlideWidth/2) {
                        mPaddingLeft = mPaddingChecked ;
                        toggle();
                    }else {
                        mPaddingLeft = mPaddingUnchecked ;
                    }
                }

                postInvalidate();
                break;

            default:
                Log.i("madrod", "action: " + event.getAction());
                break;
        }

        return true;
    }

    /**
     * draw again
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

}
