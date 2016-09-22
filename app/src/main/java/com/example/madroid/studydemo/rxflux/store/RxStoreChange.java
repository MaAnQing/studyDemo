package com.example.madroid.studydemo.rxflux.store;


import com.example.madroid.studydemo.rxflux.action.RxAction;

/**
 * Pos a new event to notify that the store has changed
 */
public class RxStoreChange {

    private String storeId;
    private RxAction rxAction;

    public RxStoreChange(String storeId, RxAction rxAction) {
        this.storeId = storeId;
        this.rxAction = rxAction;
    }

    public RxAction getRxAction() {
        return rxAction;
    }

    public String getStoreId() {
        return storeId;
    }
}
