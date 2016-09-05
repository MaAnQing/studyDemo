package com.example.madroid.studydemo.particle.property;

import com.example.madroid.studydemo.particle.Particle;

import java.util.Random;

/**
 * created by madroid at 2016-07-15
 */
public interface ParticleProperty {

    void init(Particle particle, Random random) ;

    void apply(Particle particle, long living) ;
}
