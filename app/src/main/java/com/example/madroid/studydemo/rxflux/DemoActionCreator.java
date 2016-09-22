package com.example.madroid.studydemo.rxflux;

import android.telecom.Call;
import android.util.Log;

import com.example.madroid.studydemo.rxflux.action.RxAction;
import com.example.madroid.studydemo.rxflux.action.RxActionCreator;
import com.example.madroid.studydemo.rxflux.dispatcher.Dispatcher;
import com.example.madroid.studydemo.rxflux.util.SubscriptionManager;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.ObserverSubscriber;

/**
 * created by madroid at 2016-09-20
 */
public class DemoActionCreator extends RxActionCreator {
    private static final String TAG = "RxFlux";

    public DemoActionCreator(Dispatcher dispatcher, SubscriptionManager manager) {
        super(dispatcher, manager);
    }

    public void sendMessage(String text) {
        Log.i(TAG, "sendMessage: " + text);
        final RxAction action = newRxAction("message", "text" , text) ;
        if (hasRxAction(action)) {
            return;
        }

        Log.i(TAG, "addRxAction: " + text);
        postRxAction(action);
//        addRxAction(action, new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.i(TAG, "onCompleted: ");
//                postRxAction(action);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                postError(action, e);
//                Log.i(TAG, "onError: ");
//            }
//
//            @Override
//            public void onNext(String s) {
//                postRxAction(action);
//                Log.i(TAG, "onNext: ");
//            }
//        }) ;
    }
}
