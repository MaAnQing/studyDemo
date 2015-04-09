package com.example.madroid.studydemo.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.view.RippleBackground;

import java.util.ArrayList;

public class RippleBackgroundActivity extends ActionBarActivity {

    private ImageView foundDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_background);

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);

        final Handler handler=new Handler();

        //foundDevice=(ImageView)findViewById(R.id.foundDevice);
//        ImageView button=(ImageView)findViewById(R.id.centerImage);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rippleBackground.startRippleAnimation();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        foundDevice();
//                    }
//                },3000);
//            }
//        });

        rippleBackground.setRBEnable(true);
        rippleBackground.startRippleAnimation();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                foundDevice();
//            }
//        },3000);
    }

    private void foundDevice(){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList=new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(foundDevice, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        foundDevice.setVisibility(View.VISIBLE);
        animatorSet.start();
    }
}
