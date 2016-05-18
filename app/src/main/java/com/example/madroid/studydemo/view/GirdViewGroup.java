package com.example.madroid.studydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * created by madroid at 2016-05-11
 */
public class GirdViewGroup extends ViewGroup {
    private static final String TAG = "GirdViewGrop";

    public GirdViewGroup(Context context) {
        super(context);
    }

    public GirdViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GirdViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
