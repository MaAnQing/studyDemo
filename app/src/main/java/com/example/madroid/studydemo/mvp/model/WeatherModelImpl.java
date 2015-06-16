package com.example.madroid.studydemo.mvp.model;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.madroid.studydemo.mvp.GsonRequest;
import com.example.madroid.studydemo.mvp.VolleyRequest;
import com.example.madroid.studydemo.mvp.model.entity.Weather;
import com.example.madroid.studydemo.mvp.persenter.OnGetWeatherListener;

/**
 *
 * @FileName: com.example.madroid.studydemo.mvp.model.WeatherModelImpl.java
 * @author: madroid
 * @date: 2015-06-16 10:52
 */
public class WeatherModelImpl implements IWeatherModel {
    private static final String TAG = "WeatherModelImpl";

    @Override
    public void loadWeather(String cityOn,final OnGetWeatherListener listener) {
        /*数据层操作*/
//        VolleyRequest.newInstance().newGsonRequest("http://www.weather.com.cn/data/sk/" + cityOn + ".html",
//                WeatherInfo.class, new Response.Listener<WeatherInfo>() {
//                    @Override
//                    public void onResponse(WeatherInfo weather) {
//                        if (weather != null) {
//                            listener.onSuccess(weather);
//                        } else {
//                            listener.onError();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i("WeatherActivity","error: " + error.toString()) ;
//                        listener.onError();
//                    }
//                });


        VolleyRequest.newInstance().newGsonRequest("http://www.weather.com.cn/data/sk/" + cityOn + ".html",
                Weather.class, new Response.Listener<Weather>() {
                    @Override
                    public void onResponse(Weather weather) {
                        if (weather != null) {
                            //listener.onSuccess(weather);
                            Log.i("WeatherActivity",weather.toString()) ;
                        } else {
                            listener.onError();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("WeatherActivity","error: " + error.toString()) ;
                        listener.onError();
                    }
                });


    }


}
