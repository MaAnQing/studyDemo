package com.example.madroid.studydemo.view.stikkyheader;


import android.view.View;
import android.view.ViewTreeObserver;

public abstract class HeaderAnimator {

    private View mHeader;
    private int mMinHeightHeader;
    private int mHeightHeader;
    private int mMaxTranslation;

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    public abstract void onScroll(final int scrolledY , int scrollState);

    /**
     * Called by the {@link it.carlom.stikkyheader.core.StikkyHeader} to set the {@link HeaderAnimator} up
     */
    void setupAnimator(final View header, final int minHeightHeader, final int heightHeader, final int maxTranslation) {
        this.mHeader = header;
        this.mMinHeightHeader = minHeightHeader;
        this.mHeightHeader = heightHeader;
        this.mMaxTranslation = maxTranslation;

        onAnimatorAttached();

        mHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeader.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                onAnimatorReady();
            }
        });
    }

    /**
     * Called after that the animator is attached to the header
     */
    protected abstract void onAnimatorAttached();

    /**
     * Called after that the animator is attached and the header measured
     */
    protected abstract void onAnimatorReady();

    void setHeightHeader(int heightHeader, int maxTranslation) {
        mHeightHeader = heightHeader;
        mMaxTranslation = maxTranslation;
    }

    public View getHeader() {
        return mHeader;
    }

    public int getMinHeightHeader() {
        return mMinHeightHeader;
    }

    public int getHeightHeader() {
        return mHeightHeader;
    }

    public int getMaxTranslation() {
        return mMaxTranslation;
    }
}
