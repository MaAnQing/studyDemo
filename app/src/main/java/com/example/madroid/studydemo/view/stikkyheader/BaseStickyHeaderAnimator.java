package com.example.madroid.studydemo.view.stikkyheader;


import android.util.Log;

public class BaseStickyHeaderAnimator extends HeaderAnimator {

    private float mTranslationRatio;

    @Override
    protected void onAnimatorAttached() {
        //nothing to do
    }

    @Override
    protected void onAnimatorReady() {
        //nothing to do
    }

    @Override
    public void onScroll(int scrolledY ,int scrollState) {

        Log.i("StikkyHeader", " onScroll --->>  header scrolledY : " + scrolledY + "press : " + scrollState) ;
        getHeader().setTranslationY(Math.max(scrolledY, getMaxTranslation()));

        mTranslationRatio = calculateTranslationRatio(scrolledY);
    }

    public float getTranslationRatio() {
        return mTranslationRatio;
    }

    private float calculateTranslationRatio(int scrolledY) {
        return (float) scrolledY / (float) getMaxTranslation();
    }

}
