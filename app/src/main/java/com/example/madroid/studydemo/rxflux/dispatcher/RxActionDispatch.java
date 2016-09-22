package com.example.madroid.studydemo.rxflux.dispatcher;


import com.example.madroid.studydemo.rxflux.action.RxAction;

/**
 * This interface must be implemented by the store
 */
public interface RxActionDispatch {

    void onRxAction(RxAction action);
}
