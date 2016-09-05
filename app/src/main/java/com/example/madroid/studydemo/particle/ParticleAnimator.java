package com.example.madroid.studydemo.particle;

import android.animation.ValueAnimator;

/**
 * created by madroid at 2016-07-14
 */
public class ParticleAnimator extends ValueAnimator {

    private ParticleAnimator(long duration) {
        setFloatValues(0, 1);
        setDuration(duration) ;
    }
}
