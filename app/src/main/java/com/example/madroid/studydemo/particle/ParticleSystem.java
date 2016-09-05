package com.example.madroid.studydemo.particle;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

import com.example.madroid.studydemo.particle.property.AlphaProperty;
import com.example.madroid.studydemo.particle.property.ParticleProperty;
import com.example.madroid.studydemo.particle.property.ScaleProperty;
import com.example.madroid.studydemo.particle.property.SpeedProperty;

import java.util.ArrayList;
import java.util.Random;

/**
 * created by madroid at 2016-07-14
 */
public class ParticleSystem {
    private static final String TAG = "ParticleSystem";

    private static final int TIME_TO_LIVE = 250 ;
    private static final int PARTICLE_SIZE = 50 ;

    private Builder mBuiler ;

    private View mHostingView ;
    private ViewGroup mParent ;
    private ParticleView mParticleView ;
    private ArrayList<ParticleProperty> mPropertys ;
    private ArrayList<Particle> mParticle ;

    private Random mRandom ;

    protected ParticleSystem(View hostView) {
        mHostingView = hostView ;
        mParent = (ViewGroup) mHostingView.getParent();
        mParticleView = new ParticleView(mHostingView.getContext()) ;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT) ;
        mParticleView.setLayoutParams(params);
        //mParent.removeView(mHostingView);
        mParent.addView(mParticleView) ;
        //mParent.addView(mHostingView);

        init();
    }

    private void apply(Builder builder) {
        mBuiler = builder ;
        mPropertys.add(new SpeedProperty(1, 2.5f, 0, 360));
        mPropertys.add(new AlphaProperty(TIME_TO_LIVE, 1f, 0.2f));
        mPropertys.add(new ScaleProperty(TIME_TO_LIVE, 1f, 0.2f));

        for (Particle particle : mParticle) {
            for (ParticleProperty property : mPropertys) {
                property.init(particle, mRandom);
            }
        }
    }

    private void init() {
        mRandom = new Random() ;
        mPropertys = new ArrayList<>() ;
        mParticle = new ArrayList<>() ;
        for (int i = 0; i < PARTICLE_SIZE; i++) {
            mParticle.add(new Particle());
        }

    }

    public void startAnim() {

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1f) ;
        animator.setDuration(500) ;
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
            }
        });

    }

    public void clearAnim() {
        mParent.removeView(mParticleView);
    }

    public static class Builder {
        float speedMin ;
        float speedMax ;

        float alphaMin;
        float alphaMax;

        float scaleMin ;
        float scaleMax ;

        int angleMin ;
        int angleMax;

        int maxParticles ;

        /**
         * 粒子生命时长
         */
        long liveTime ;

        private View view ;

        public Builder(View hostView) {
            view = hostView ;
        }

        public Builder setSppedRange(float min, float max) {
            speedMin = min ;
            speedMax = max ;
            return this ;
        }

        public Builder setAlphaRange(float min, float max) {
            alphaMax = max ;
            alphaMin = min ;
            return this ;
        }

        public Builder setScaleRange(float min, float max) {
            scaleMax = max ;
            scaleMin = min ;
            return this ;
        }

        public Builder setAngleRange(int min, int max) {
            angleMax = max ;
            angleMin = min ;
            return this ;
        }

        public Builder setMaxParticleSize(int size) {
            maxParticles = size ;
            return this ;
        }

        public ParticleSystem create() {
            ParticleSystem system = new ParticleSystem(view) ;
            system.apply(this);
            return system ;
        }
    }
}
