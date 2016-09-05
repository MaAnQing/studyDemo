package com.example.madroid.studydemo.particle.property;

import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.madroid.studydemo.particle.Particle;

import java.util.Random;

/**
 * created by madroid at 2016-07-15
 */
public class ScaleProperty implements ParticleProperty {
    private static final String TAG = "ScaleProperty";

    private float mFromScale ;
    private float mToScale ;

    private long mTime2Live ;

    private TimeInterpolator mInterpolator ;

    public ScaleProperty(long time, float from, float to, TimeInterpolator interpolator) {
        mTime2Live = time ;
        mFromScale = from ;
        mToScale = to ;
        mInterpolator = interpolator ;
    }

    public ScaleProperty(long time, float from, float to) {
        this(time, from, to, new LinearInterpolator()) ;
    }

    @Override
    public void init(Particle particle, Random random) {

    }

    @Override
    public void apply(Particle particle, long living) {
        if (living < mTime2Live) {
            float interValue = mInterpolator.getInterpolation(living/mTime2Live) ;
            float nowScale = (mToScale - mFromScale) * interValue + mFromScale ;
            particle.setScale(nowScale);
        }
    }
}
