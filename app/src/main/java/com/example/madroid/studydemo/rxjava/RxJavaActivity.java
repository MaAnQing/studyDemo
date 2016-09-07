package com.example.madroid.studydemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.madroid.studydemo.R;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "RxJavaActivity";

    private EditText mEditText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        mEditText = (EditText) findViewById(R.id.edit) ;

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

    }

    private void rxjavaLog() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }
        } ;

        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
                setMessage("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
                setMessage(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
                setMessage(s);
            }
        } ;

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hi,");
                subscriber.onNext("my name is ");
                subscriber.onNext("madroid");
                subscriber.onCompleted();
            }
        }) ;
        observable.subscribe(subscriber) ;
    }

    private void setMessage(String msg) {
        mEditText.append(msg + "\n");

    }

    private void cleanMessage() {
        mEditText.setText("");
    }

    private void parseNumber() {
        String string = mEditText.getText().toString();

        String[] tmp = string.split("-");
        Observable.from(tmp)
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        cleanMessage();
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        setMessage("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                        setMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext: ");
                        setMessage(s + "\n");
                    }
                }) ;


    }

    private void testThread() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("start");
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                    subscriber.onNext("index:" + i);
                }
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        cleanMessage();
                    }

                    @Override
                    public void onCompleted() {
                        setMessage("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        setMessage(e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        setMessage(s);
                    }
                }) ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                rxjavaLog();
                break;

            case R.id.button2:
                parseNumber();
                break;

            case R.id.button3:
                testThread();
                break;

            default:
                break;
        }
    }
}
