package com.example.madroid.studydemo.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.ui.tikkyheader.ActionBarImageFragment;
import com.example.madroid.studydemo.ui.tikkyheader.IO2014HeaderFragment;
import com.example.madroid.studydemo.ui.tikkyheader.MyStikkyViewFragment;
import com.example.madroid.studydemo.ui.tikkyheader.ParallaxStikkyFragment;
import com.example.madroid.studydemo.ui.tikkyheader.RecyclerStikkyFragment;
import com.example.madroid.studydemo.ui.tikkyheader.SimpleScrollViewFragment;
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
                break;
            case 2 :
                fragment = new ActionBarImageFragment() ;
                break;
            case 3 :
                fragment = new IO2014HeaderFragment() ;
                break;
            case 4 :
                fragment = new RecyclerStikkyFragment() ;
                break;
            case 5 :
                fragment = new SimpleScrollViewFragment() ;
                break;
            case 6 :
                fragment = new MyStikkyViewFragment() ;
                break;
            default:
                break;
        }

        loadFragment(fragment);
    }
}
