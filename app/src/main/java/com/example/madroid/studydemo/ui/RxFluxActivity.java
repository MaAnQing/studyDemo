package com.example.madroid.studydemo.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.rxflux.DemoActionCreator;
import com.example.madroid.studydemo.rxflux.DemoStore;
import com.example.madroid.studydemo.rxflux.action.RxError;
import com.example.madroid.studydemo.rxflux.dispatcher.Dispatcher;
import com.example.madroid.studydemo.rxflux.dispatcher.RxBus;
import com.example.madroid.studydemo.rxflux.dispatcher.RxViewDispatch;
import com.example.madroid.studydemo.rxflux.store.RxStore;
import com.example.madroid.studydemo.rxflux.store.RxStoreChange;
import com.example.madroid.studydemo.rxflux.util.SubscriptionManager;

import java.util.List;

public class RxFluxActivity extends AppCompatActivity implements RxViewDispatch, View.OnClickListener {


    private static final String TAG = "RxFluxActivity";
    private DemoActionCreator mActionCreator ;
    private DemoStore mStore ;
    private Dispatcher mDispatcher ;

    private EditText mEditText;
    private Button mButton ;
    private TextView mText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_flux);

        mDispatcher = Dispatcher.getInstance(RxBus.getInstance()) ;
        mActionCreator = new DemoActionCreator(mDispatcher, SubscriptionManager.getInstance()) ;
        mStore = new DemoStore(mDispatcher) ;

        mDispatcher.subscribeRxStore(mStore);
        mDispatcher.subscribeRxView(this);


        mEditText = (EditText) findViewById(R.id.edit_text) ;
        mButton = (Button) findViewById(R.id.button) ;
        mButton.setOnClickListener(this);
        mText = (TextView) findViewById(R.id.text) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDispatcher.unSubscribeRxStore(mStore);
        mDispatcher.unSubscribeRxView(this);
    }



    @Override
    public void onRxStoreChanged(@NonNull RxStoreChange change) {
        Log.i(TAG, "onRxStoreChanged: " + change.getRxAction());
        mText.setText(change.getRxAction().get("text") + "");
    }

    @Override
    public void onRxError(@NonNull RxError error) {

    }

    @Override
    public void onRxViewRegistered() {

    }

    @Override
    public void onRxViewUnRegistered() {

    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToRegister() {
        return null;
    }

    @Nullable
    @Override
    public List<RxStore> getRxStoreListToUnRegister() {
        return null;
    }

    @Override
    public void onClick(View v) {
        mActionCreator.sendMessage(mEditText.getText().toString());
    }
}
