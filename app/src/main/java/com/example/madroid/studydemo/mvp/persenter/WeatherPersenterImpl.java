package com.example.madroid.studydemo.mvp.persenter;

import com.example.madroid.studydemo.mvp.model.IWeatherModel;
import com.example.madroid.studydemo.mvp.model.WeatherInfo;
import com.example.madroid.studydemo.mvp.model.WeatherModelImpl;
import com.example.madroid.studydemo.mvp.view.IWeatherView;

/**
 * @FileName: com.example.madroid.studydemo.mvp.persenter.WeatherPersenterImpl.java
 * @author: madroid
 * @date: 2015-06-16 11:03
 */
public class WeatherPersenterImpl implements OnGetWeatherListener,IWeatherPersenter {
    private static final String TAG = "WeatherPersenterImpl";
    private IWeatherView mWeatherView ;
    private IWeatherModel mWeatherModel ;

    public WeatherPersenterImpl(IWeatherView weatherView){
        this.mWeatherView = weatherView ;
        mWeatherModel = new WeatherModelImpl() ;
    }

    @Override
    public void onSuccess(WeatherInfo info) {
        mWeatherView.hildLoading();
        mWeatherView.setWeatherInfo(info);
    }

    @Override
    public void onError() {
        mWeatherView.hildLoading();
        mWeatherView.showError();
    }

    @Override
    public void getWeather(String cityOn) {
        mWeatherView.showLoading();
        mWeatherModel.loadWeather(cityOn,this);
    }
}
