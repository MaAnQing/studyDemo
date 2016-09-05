package com.example.madroid.studydemo.particle.property;

import com.example.madroid.studydemo.particle.Particle;

import java.util.Random;

/**
 * created by madroid at 2016-07-15
 */
public class AccelerationProperty implements ParticleProperty{
    private static final String TAG = "AccelerationProperty";

    private float mMinValue ;
    private float mMaxValue ;
    private int mMinAngle ;
    private int mMaxAngle ;

    public AccelerationProperty(float min, float max, int minAngle, int maxAngle) {
        mMinValue = min ;
        mMaxValue = max ;
        mMinAngle = minAngle ;
        mMaxAngle = maxAngle ;
    }

    @Override
    public void init(Particle particle, Random random) {
        float angle = mMinAngle;
        if (mMaxAngle != mMinAngle) {
            angle = random.nextInt(mMaxAngle - mMinAngle) + mMinAngle;
        }
        float angleInRads = (float) (angle*Math.PI/180f);
        float value = random.nextFloat()*(mMaxValue-mMinValue)+mMinValue;
        float accelerationX = (float) (value * Math.cos(angleInRads));
        float accelerationY = (float) (value * Math.sin(angleInRads));
        particle.setAcceleration(accelerationX, accelerationY);
    }

    @Override
    public void apply(Particle particle, long living) {

    }

}
