package com.example.madroid.studydemo.view.stikkyheader;

public class HeaderStikkyAnimator extends BaseStickyHeaderAnimator {

    protected AnimatorBuilder mAnimatorBuilder;
    private float mBoundedTranslatedRatio;
    private boolean hasAnimatorBundles = false;

    @Override
    protected void onAnimatorReady() {
        super.onAnimatorReady();
        mAnimatorBuilder = getAnimatorBuilder();
        hasAnimatorBundles = (mAnimatorBuilder != null) && (mAnimatorBuilder.hasAnimatorBundles());
    }

    /**
     * Override if you want to load the animator builder
     */
    public AnimatorBuilder getAnimatorBuilder() {
        return null;
    }

    @Override
    public void onScroll(int scrolledY ,int state) {
        super.onScroll(scrolledY,state);

        mBoundedTranslatedRatio = clamp(getTranslationRatio(), 0f, 1f);

        if (hasAnimatorBundles) {
            mAnimatorBuilder.animateOnScroll(mBoundedTranslatedRatio, getHeader().getTranslationY());
        }

    }

    public float getBoundedTranslatedRatio() {
        return mBoundedTranslatedRatio;
    }
}