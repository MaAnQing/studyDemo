package com.example.madroid.studydemo.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadFragment(int container,Fragment fragment){
        getFragmentManager().beginTransaction().replace(container,fragment).addToBackStack(fragment.getClass().getName()).commit();
    }
}
