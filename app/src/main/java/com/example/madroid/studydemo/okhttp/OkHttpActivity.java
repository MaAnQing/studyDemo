package com.example.madroid.studydemo.okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.madroid.studydemo.R;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = OkHttpActivity.class.getSimpleName() ;

    private static final int MSG_UPDATE_TXT = 0 ;

    private TextView mResponse ;

    private Handler mHandler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                httpGet();
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_UPDATE_TXT:
                        mResponse.setText(msg.obj.toString());
                        break;

                    default:
                        break;
                }
            }
        } ;
    }

    private void initView() {
        mResponse = (TextView) findViewById(R.id.ok_http_response) ;

    }

    private void httpGet() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.networkInterceptors().add(new StethoInterceptor()) ;
        //创建一个Request
        final Request request = new Request.Builder()
                .url("https://github.com/hongyangAndroid")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG,"onFailure") ;

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr =  response.body().string();
                //byte[] htmlStr =  response.body().bytes() ;
                //InputStream htmlStr =  response.body().byteStream() ;
                Log.i(TAG,"onResponse : " + htmlStr) ;
                Message msg = Message.obtain() ;
                msg.what = MSG_UPDATE_TXT ;
                msg.obj = htmlStr ;
                mHandler.sendMessage(msg) ;
            }

        });
    }
    
}
