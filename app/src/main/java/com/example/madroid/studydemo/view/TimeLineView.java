package com.example.madroid.studydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.madroid.studydemo.R;

/**
 * created by madroid at 2015-11-23
 */
public class TimeLineView extends LinearLayout {
    private static final String TAG = "TimeLineView";
    private ImageView mIcon ;
    private View mTopLine ;
    private View mBottomLine ;
    private Context mContet ;

    public TimeLineView(Context context) {
        this(context, null);
    }

    public TimeLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContet = context ;
        View view = LayoutInflater.from(context).inflate(R.layout.time_line_view,this,true) ;
        mIcon = (ImageView) view.findViewById(R.id.icon) ;
        mTopLine = view.findViewById(R.id.top) ;
        mBottomLine = view.findViewById(R.id.bottom) ;

    }

    public void setImageResource(int resId) {
        mIcon.setImageResource(resId);
    }

    public void setTimeStart() {
        mTopLine.setVisibility(GONE);
    }

    public void setTimeEnd() {
        mBottomLine.setVisibility(GONE);
    }

}
