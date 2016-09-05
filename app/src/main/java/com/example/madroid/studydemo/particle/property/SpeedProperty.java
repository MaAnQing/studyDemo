package com.example.madroid.studydemo.particle.property;

import com.example.madroid.studydemo.particle.Particle;

import java.util.Random;

/**
 * created by madroid at 2016-07-15
 */
public class SpeedProperty implements ParticleProperty {
    private static final String TAG = "SpeedProperty";

    private float mSpeedMin;
    private float mSpeedMax;
    private int mMinAngle;
    private int mMaxAngle;

    public SpeedProperty(float speedMin, float speedMax, int minAngle, int maxAngle) {
       mSpeedMin = speedMin;
       mSpeedMax = speedMax;
       mMinAngle = minAngle;
       mMaxAngle = maxAngle;
       // Make sure the angles are in the [0-360) range
       while (mMinAngle < 0) {
           mMinAngle+=360;
       }
       while (mMaxAngle < 0) {
           mMaxAngle+=360;
       }
       // Also make sure that mMinAngle is the smaller
       if (mMinAngle > mMaxAngle) {
           int tmp = mMinAngle;
           mMinAngle = mMaxAngle;
           mMaxAngle = tmp;
       }
    }

    @Override
    public void init(Particle particle, Random random) {
        float speed = random.nextFloat()*(mSpeedMax-mSpeedMin) + mSpeedMin;
        int angle;
        if (mMaxAngle == mMinAngle) {
            angle = mMinAngle;
        }
        else {
            angle = random.nextInt(mMaxAngle - mMinAngle) + mMinAngle;
        }
        float angleInRads = (float) (angle*Math.PI/180f);
        float speedX = (float) (speed * Math.cos(angleInRads));
        float speedY = (float) (speed * Math.sin(angleInRads));
        particle.setSpeed(speedX, speedY);
    }

    @Override
    public void apply(Particle particle, long living) {

    }
}
