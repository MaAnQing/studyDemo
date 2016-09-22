package com.example.madroid.studydemo.rxflux.store;


import com.example.madroid.studydemo.rxflux.dispatcher.Dispatcher;
import com.example.madroid.studydemo.rxflux.dispatcher.RxActionDispatch;

/**
 * This class must be extended by each store of the app in order to recieve the actions dispatched
 * by the
 */
public abstract class RxStore implements RxActionDispatch {

    private final Dispatcher dispatcher;

    public RxStore(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void register() {
        dispatcher.subscribeRxStore(this);
    }

    public void unregister() {
        dispatcher.unSubscribeRxStore(this);
    }

    protected void postChange(RxStoreChange change) {
        dispatcher.postRxStoreChange(change);
    }
}
