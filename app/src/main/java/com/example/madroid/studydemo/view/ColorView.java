package com.example.madroid.studydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.madroid.studydemo.R;

/**
 * created by madroid at 2016-04-15
 */
public class ColorView extends View {
    private static final String TAG = "ColorView";

    Paint mPaint  ;
    int mColor ;

    public ColorView(Context context) {
        this(context, null);
    }

    public ColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "ColorView: ");

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorView,defStyleAttr, 0) ;
        mColor = typedArray.getColor(R.styleable.ColorView_myColor, getResources().getColor(android.R.color.holo_purple)) ;
        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
        invalidate();

    }

    /**
     * Finalize inflating a view from XML.  This is called as the last phase
     * of inflation, after all child views have been added.
     *
     * <p>Even if the subclass overrides onFinishInflate, they should always be
     * sure to call the super method, so that we get called.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG, "onFinishInflate: ");
    }

    /**
     * This is called when the view is attached to a window.  At this point it
     * has a Surface and will start drawing.  Note that this function is
     * guaranteed to be called before {@link #onDraw(android.graphics.Canvas)},
     * however it may be called any time before the first onDraw -- including
     * before or after {@link #onMeasure(int, int)}.
     *
     * @see #onDetachedFromWindow()
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow: ");
    }

    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: ");
    }

    /**
     * This is called when the view is detached from a window.  At this point it
     * no longer has a surface for drawing.
     *
     * @see #onAttachedToWindow()
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure: ");

        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) ;
        int widthResult = 60;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                //具体的值，传入什么值就显示什么值
                widthResult = widthSize ;
                break;

            case MeasureSpec.AT_MOST:
                int mostSize = 1000 ;
                //最大值，当为AT_MOST(match_parent)时设置最大宽度为1000
                if (widthResult >= mostSize) {
                    widthResult = mostSize ;
                }
                break;

            case MeasureSpec.UNSPECIFIED:
                // 很少使用
                break;

            default:
                break;
        }

        //View.resolveSize是系统提供的一个方法，可以简化对测量中三种模式的判断，第一个参数size是你想要显示的值
        //即当宽高设置为wrap_content时所设置的大小；第二个参数measureSpec即是onMeasure中传下来的测量模式
        int heightResult = View.resolveSize(widthSize, heightMeasureSpec) ;

        //调用此方法将想要设置的宽高生效，否则不会有效果
        setMeasuredDimension(widthResult, heightResult);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");

        //获取宽高
        int width = getWidth() ;
        int height = getHeight() ;

        //画圆
        canvas.drawCircle(width/2, height/2, Math.min(width/2, height/2), mPaint);
    }
}
