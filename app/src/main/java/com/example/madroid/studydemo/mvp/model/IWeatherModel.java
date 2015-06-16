package com.example.madroid.studydemo.mvp.model;

import com.example.madroid.studydemo.mvp.persenter.OnGetWeatherListener;

/**
 * 天气接口
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.example.madroid.studydemo.mvp.model.IWeatherModel.java
 * @author: madroid
 * @date: 2015-06-16 10:47
 */
public interface IWeatherModel {
    void loadWeather(String cityOn,OnGetWeatherListener listener) ;
}
