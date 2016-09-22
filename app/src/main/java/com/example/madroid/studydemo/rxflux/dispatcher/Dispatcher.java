package com.example.madroid.studydemo.rxflux.dispatcher;

import android.support.v4.util.ArrayMap;
import android.util.Log;


import com.example.madroid.studydemo.rxflux.action.RxAction;
import com.example.madroid.studydemo.rxflux.action.RxError;
import com.example.madroid.studydemo.rxflux.store.RxStoreChange;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by marcel on 13/08/15.
 * RxFlux dispatcher, contains the the registered actions, stores and the instance of the RxBus
 * responsible to send events to the stores. This class is used as a singleton.
 */
public class Dispatcher {

    private static Dispatcher instance;
    private final RxBus bus;
    private ArrayMap<String, Subscription> rxActionMap;
    private ArrayMap<String, Subscription> rxStoreMap;

    private Dispatcher(RxBus bus) {
        this.bus = bus;
        this.rxActionMap = new ArrayMap<>();
        this.rxStoreMap = new ArrayMap<>();
    }

    public static synchronized Dispatcher getInstance(RxBus rxBus) {
        if (instance == null) instance = new Dispatcher(rxBus);
        return instance;
    }

    public <T extends RxActionDispatch> void subscribeRxStore(final T object) {
        final String tag = object.getClass().getSimpleName();
        Subscription subscription = rxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            rxActionMap.put(tag, bus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    return o instanceof RxAction;
                }
            }).subscribe(new Action1<Object>() {
                @Override
                public void call(Object o) {
                    object.onRxAction((RxAction) o);
                }
            }));
        }
    }

    public <T extends RxViewDispatch> void subscribeRxError(final T object) {
        final String tag = object.getClass().getSimpleName() + "_error";
        Subscription subscription = rxActionMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            rxActionMap.put(tag, bus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    return o instanceof RxError;
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Object>() {
                        @Override
                        public void call(Object o) {
                            object.onRxError((RxError) o);
                        }
                    }));
        }
    }

    public <T extends RxViewDispatch> void subscribeRxView(final T object) {
        final String tag = object.getClass().getSimpleName();
        Subscription subscription = rxStoreMap.get(tag);
        if (subscription == null || subscription.isUnsubscribed()) {
            rxStoreMap.put(tag, bus.get().onBackpressureBuffer().filter(new Func1<Object, Boolean>() {
                @Override
                public Boolean call(Object o) {
                    return o instanceof RxStoreChange;
                }
            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Object>() {
                        @Override
                        public void call(Object o) {
                            object.onRxStoreChanged((RxStoreChange) o);
                        }
                    }));
        }
        subscribeRxError(object);
    }

    public <T extends RxActionDispatch> void unSubscribeRxStore(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = rxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            rxActionMap.remove(tag);
        }
    }

    public <T extends RxViewDispatch> void unSubscribeRxError(final T object) {
        String tag = object.getClass().getSimpleName() + "_error";
        Subscription subscription = rxActionMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            rxActionMap.remove(tag);
        }
    }

    public <T extends RxViewDispatch> void unSubscribeRxView(final T object) {
        String tag = object.getClass().getSimpleName();
        Subscription subscription = rxStoreMap.get(tag);
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            rxStoreMap.remove(tag);
        }
        unSubscribeRxError(object);
    }

    public synchronized void unSubscribeAll() {
        for (Subscription subscription : rxActionMap.values()) {
            subscription.unsubscribe();
        }

        for (Subscription subscription : rxStoreMap.values()) {
            subscription.unsubscribe();
        }

        rxActionMap.clear();
        rxStoreMap.clear();
    }

    public void postRxAction(final RxAction action) {
        bus.send(action);
    }

    public void postRxStoreChange(final RxStoreChange storeChange) {
        bus.send(storeChange);
    }
}
