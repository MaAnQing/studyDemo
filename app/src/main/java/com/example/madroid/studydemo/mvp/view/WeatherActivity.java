package com.example.madroid.studydemo.mvp.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.mvp.VolleyRequest;
import com.example.madroid.studydemo.mvp.model.WeatherInfo;
import com.example.madroid.studydemo.mvp.persenter.WeatherPersenterImpl;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener,IWeatherView{

    private static final String TAG = "WeatherActivity" ;

    private EditText mCityCode ;
    private Button mLoad;
    private TextView mWeatherInfo;
    private ProgressDialog mLoadingDialog ;
    private WeatherPersenterImpl mPersenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        init();
    }

    private void init() {
        mCityCode = (EditText) findViewById(R.id.city_code) ;
        mLoad = (Button) findViewById(R.id.load_weather) ;
        mLoad.setOnClickListener(this);
        mCityCode.setText("101010100");
        mWeatherInfo = (TextView) findViewById(R.id.weather_text) ;

        mPersenter = new WeatherPersenterImpl(this) ;
        VolleyRequest.buildRequestQueue(getApplicationContext());
        mLoadingDialog = new ProgressDialog(this) ;
        mLoadingDialog.setTitle("正在加载天气中...");
        Log.i(TAG, "init") ;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_weather:
                //mPersenter.getWeather(mLoad.getText().toString().trim());
                loadWeather();
                break;

        }
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hildLoading() {
        mLoadingDialog.hide();
    }

    @Override
    public void showError() {
        Toast.makeText(this,"load weather error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void setWeatherInfo(WeatherInfo info) {
        WeatherInfo.WeatherInfoEntity entity = info.getWeatherInfo() ;
        mWeatherInfo.append("城市：" + entity.getCity() + "\n" );
        mWeatherInfo.append("时间：" + entity.getTime() + "\n" );
        mWeatherInfo.append("WD：" + entity.getWD() + "\n");
    }


    private void loadWeather() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext()) ;
        String url = "http://www.weather.com.cn/data/sk/" + mLoad.getText().toString().trim() + ".html" ;
        //String url = "http://www.baidu.com" ;
        JsonObjectRequest json = new JsonObjectRequest(url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject obj) {
                Log.i(TAG,"  -------  obj:" + obj);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"  -------  error:" + error.toString());
                Toast.makeText(getApplicationContext(),"load weather error",Toast.LENGTH_LONG).show();
            }

        });
        queue.add(json) ;
    }
}
