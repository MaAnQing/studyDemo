package com.example.madroid.studydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madroid.studydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by madroid at 2016-05-11
 */
public class GirdViewGroup extends ViewGroup {
    private static final String TAG = "GirdViewGroup";

    private Context mContext ;

    public GirdViewGroup(Context context) {
        this(context, null);
    }

    public GirdViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GirdViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec) ;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        Log.i(TAG, "onMeasure: max width = " + maxWidth);
        Log.i(TAG, "onMeasure: width mode:" + widthMode);

        int width = getPaddingLeft() + getPaddingRight() ;
        int lineWidth = width ;
        int height = getPaddingTop() + getPaddingBottom() ;
        int lineHeight = height ;

        int count = getChildCount() ;
        Log.i(TAG, "onMeasure: count=" + count);

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i) ;
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin ;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin ;

            Log.i(TAG, "onMeasure: child width=" + childWidth + ", height:" + childHeight);
            if (lineWidth + childWidth > maxWidth) {
                width = Math.max(lineWidth, childHeight) ;
                height += lineHeight ;
                lineHeight = childHeight ;
            }else {
                lineWidth += childWidth ;
                lineHeight = Math.max(lineHeight, childHeight) ;
            }

        }

        Log.i(TAG, "onMeasure: width=" + width + ", height:" + height);
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));

    }

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int measureWidth = getMeasuredWidth() ;
        Log.i(TAG, "onLayout: width = " + measureWidth);

        int count = getChildCount() ;
        Log.i(TAG, "onLayout: child view count" + count);
        Log.i(TAG, "onLayout: [" + left + "," + top + "," + right + "," + bottom + "]");

        int mPainterPosX = getPaddingLeft() ;
        int mPainterPosY = getPaddingTop() ;

        int lineHeight ;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i) ;

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int width = child.getMeasuredWidth() ;
            int height = child.getMeasuredHeight() ;
            lineHeight = height + lp.topMargin + lp.bottomMargin ;

            if (mPainterPosX + width + + lp.leftMargin + lp.rightMargin > measureWidth) {
                mPainterPosX = getPaddingLeft() + lp.leftMargin ;
                mPainterPosY += lineHeight + lp.topMargin;
                lineHeight += mPainterPosY + height + lp.bottomMargin ;
            }else {
                mPainterPosX  += lp.leftMargin ;
                mPainterPosY = lp.topMargin + getPaddingTop();
                lineHeight = Math.max(lineHeight, mPainterPosY + height + lp.bottomMargin) ;
            }

            Log.i(TAG, "onLayout: x:" + mPainterPosX + ", y:" + mPainterPosY);
            setChildFrame(child, mPainterPosX, mPainterPosY, width, height);

            mPainterPosX += width + lp.rightMargin;
            mPainterPosY += lp.bottomMargin ;
        }


        //layout(changed, left, top, right, bottom);

    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }

    private void layout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        // 遍历所有的孩子
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果已经需要换行
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width)
            {
                // 记录这一行所有的View以及最大高度
                mLineHeight.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                lineWidth = 0;// 重置行宽
                lineViews = new ArrayList<View>();
            }
            /**
             * 如果不需要换行，则累加
             */
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            lineViews.add(child);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++)
        {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc =lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e(TAG, child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }

    public class LayoutParams extends ViewGroup.LayoutParams{

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(mContext, attrs);
    }

}
