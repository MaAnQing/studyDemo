package com.example.madroid.studydemo.rxflux;

import android.util.Log;

import com.example.madroid.studydemo.rxflux.action.RxAction;
import com.example.madroid.studydemo.rxflux.dispatcher.Dispatcher;
import com.example.madroid.studydemo.rxflux.store.RxStore;
import com.example.madroid.studydemo.rxflux.store.RxStoreChange;

/**
 * created by madroid at 2016-09-20
 */
public class DemoStore extends RxStore {
    private static final String TAG = "RxFlux";

    public DemoStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void onRxAction(RxAction action) {
        Log.i(TAG, "onRxAction: " + action.toString());
        postChange(new RxStoreChange("message", action));
    }
}
