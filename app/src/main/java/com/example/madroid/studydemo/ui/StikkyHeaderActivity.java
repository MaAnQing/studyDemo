package com.example.madroid.studydemo.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.R;

public class StikkyHeaderActivity extends ActionBarActivity implements StikkyHeaderFragment.OnFragmentInteractionListener {



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
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
