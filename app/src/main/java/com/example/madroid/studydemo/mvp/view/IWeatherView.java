package com.example.madroid.studydemo.mvp.view;

import com.example.madroid.studydemo.mvp.model.WeatherInfo;

/**
 * @FileName: com.example.madroid.studydemo.mvp.view.IWeatherView.java
 * @author: madroid
 * @date: 2015-06-16 11:12
 */
public interface IWeatherView {

    void showLoading() ;

    void hildLoading() ;

    void showError() ;

    void setWeatherInfo(WeatherInfo info) ;
}
