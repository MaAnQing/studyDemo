package com.example.madroid.studydemo.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.listener.OnFragmentInteractionListener;

public class RecyclerViewActivity extends BaseActivity implements OnFragmentInteractionListener {

    private Context mContext ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //initView() ;
        loadFragment(R.id.container,new SampleRecyclerViewFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_recycler :
                loadFragment(R.id.container, new SampleRecyclerViewFragment());
                break;
            case R.id.action_card :
               loadFragment(R.id.container,new RecyclerCardFragment());
                return true ;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
