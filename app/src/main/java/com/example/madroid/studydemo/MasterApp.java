package com.example.madroid.studydemo;

import android.app.Application;

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

        Keeper.init(this) ;
    }
}
