package com.example.madroid.studydemo.particle.property;


import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.madroid.studydemo.particle.Particle;

import java.util.Random;

/**
 * created by madroid at 2016-07-15
 */
public class AlphaProperty implements ParticleProperty {
    private static final String TAG = "AlphaProperty";

    private int mFromAlpha = 255 ;
    private int mToAlpha= 0 ;

    private TimeInterpolator mInterpolator ;
    private long mTime2Live ;

    public AlphaProperty(long timeToLive, int fromAlpha, int toAlpha, TimeInterpolator interpolator) {
        mTime2Live = timeToLive ;
        mFromAlpha = fromAlpha ;
        mToAlpha = toAlpha ;
        mInterpolator = interpolator ;
    }

    public AlphaProperty(long timeToLive, float fromAlpha, float toAlpha) {

        this(timeToLive, (int) (fromAlpha * 255), (int) (toAlpha * 255), new LinearInterpolator());
    }

    @Override
    public void init(Particle particle, Random random) {

    }

    @Override
    public void apply(Particle particle, long living) {
        if (living <= mTime2Live) {
            float interpolatorValue = mInterpolator.getInterpolation(living/mTime2Live) ;
            int nowAlpha = (int) ((mToAlpha - mFromAlpha) * interpolatorValue) + mFromAlpha;
            particle.setAlpha(nowAlpha);
        }
    }
}
