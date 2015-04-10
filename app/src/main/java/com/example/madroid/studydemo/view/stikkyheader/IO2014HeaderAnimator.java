package com.example.madroid.studydemo.view.stikkyheader;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.example.madroid.studydemo.R;


public class IO2014HeaderAnimator extends HeaderStikkyAnimator {

    private final Context mContext;
    private View mHeaderText;
    private int mHeightStartAnimation;
    private int mMinHeightTextHeader;
    private ValueAnimator valueAnimator;

    private boolean isCovering = false;

    public IO2014HeaderAnimator(Context context) {
        mContext = context;
    }

    @Override
    public AnimatorBuilder getAnimatorBuilder() {

        View image = getHeader().findViewById(R.id.header_image);

        return new AnimatorBuilder()
                .applyVerticalParallax(image, 0.5f);
    }

    @Override
    protected void onAnimatorAttached() {
        super.onAnimatorAttached();

        mHeaderText = getHeader().findViewById(R.id.header_text_layout);
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
        }
        mMinHeightTextHeader = mContext.getResources().getDimensionPixelSize(R.dimen.min_height_textheader_materiallike);

        mHeightStartAnimation = actionBarHeight + mMinHeightTextHeader;

        valueAnimator = ValueAnimator.ofInt(0).setDuration(mContext.getResources().getInteger(android.R.integer.config_shortAnimTime));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                ViewGroup.LayoutParams layoutParams = mHeaderText.getLayoutParams();
                layoutParams.height = (Integer) animation.getAnimatedValue();
                mHeaderText.setLayoutParams(layoutParams);

            }
        });


    }

    @Override
    public void onScroll(int scrolledY , int state) {
        super.onScroll(scrolledY ,state);
        float translatedY = getHeader().getTranslationY();

        float visibleHeightHeader = getHeightHeader() + translatedY;

        if (visibleHeightHeader <= mHeightStartAnimation && !isCovering) {

            valueAnimator.setIntValues(mHeaderText.getHeight(), mHeightStartAnimation);
            if (valueAnimator.isRunning()) {
                valueAnimator.end();
            }
            valueAnimator.start();

            isCovering = true;

        } else if (visibleHeightHeader > mHeightStartAnimation && isCovering) {

            valueAnimator.setIntValues(mHeaderText.getHeight(), mMinHeightTextHeader);
            if (valueAnimator.isRunning()) {
                valueAnimator.end();
            }
            valueAnimator.start();

            isCovering = false;

        }


    }
}