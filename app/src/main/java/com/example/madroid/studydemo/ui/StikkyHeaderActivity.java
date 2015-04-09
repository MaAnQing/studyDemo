package com.example.madroid.studydemo.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.ui.tikkyheader.ParallaxStikkyFragment;
import com.example.madroid.studydemo.ui.tikkyheader.SimpleStikkyFragment;

public class StikkyHeaderActivity extends ActionBarActivity implements StikkyHeaderFragment.OnFragmentInteractionListener {

    private static final String TAG = "StikkyHeaderActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stikky_header);
        loadFragment(new StikkyHeaderFragment());

    }

    private void loadFragment(Fragment fragment){
        getFragmentManager().beginTransaction().
                replace(R.id.layout_container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
        Log.i(TAG , "loadFragment name: " + fragment.getClass().getName()) ;
    }

    @Override
    public void onFragmentInteraction(int id) {

        Fragment fragment = new Fragment() ;
        switch (id){
            case 0 :
                fragment = new SimpleStikkyFragment() ;
                break;
            case 1 :
                fragment = new ParallaxStikkyFragment() ;
            default:
                break;
        }

        loadFragment(fragment);
    }
}
