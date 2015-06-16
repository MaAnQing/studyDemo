package com.example.madroid.studydemo.mvp.persenter;

import com.example.madroid.studydemo.mvp.model.WeatherInfo;

/**
 *
 *
 * @FileName: com.example.madroid.studydemo.mvp.persenter.OnGetWeatherListener.java
 * @author: madroid
 * @date: 2015-06-16 10:54
 */
public interface OnGetWeatherListener {

    //成功时回调
    void onSuccess(WeatherInfo info) ;

    //失败时回调
    void onError() ;
}
