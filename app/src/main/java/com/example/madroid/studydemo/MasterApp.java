package com.example.madroid.studydemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * @FileName: com.example.madroid.studydemo.MasterApp.java
 * @author: madroid
 * @date: 2015-06-23 11:20
 */
public class MasterApp extends Application {

    private static final String TAG = "MasterApp";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Keeper.init(this) ;
    }
}
