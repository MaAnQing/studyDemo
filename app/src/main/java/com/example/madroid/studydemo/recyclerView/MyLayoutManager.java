package com.example.madroid.studydemo.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * created by madroid at 2015-12-21
 */
public class MyLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "MyLayoutManager";

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

}
