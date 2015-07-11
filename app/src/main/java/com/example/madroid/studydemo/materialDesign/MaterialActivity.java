package com.example.madroid.studydemo.materialDesign;

import android.content.res.Configuration;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.madroid.studydemo.R;

public class MaterialActivity extends AppCompatActivity implements View.OnClickListener {

    private CoordinatorLayout rootView ;
    private static final String TAG = "MaterialActivity" ;
    private FloatingActionButton mFabBtn ;
    private Toolbar toolBar ;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        initInstances();
        //initView() ;
    }

    private void initInstances() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(MaterialActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    private void initView() {
//
//        mFabBtn = (FloatingActionButton) findViewById(R.id.fabbtn) ;
//        mFabBtn.setOnClickListener(this);
//        rootView = (CoordinatorLayout) findViewById(R.id.root_layout) ;
//
//        toolBar = (Toolbar) findViewById(R.id.toolbar) ;
//        setSupportActionBar(toolBar);
//    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_material, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.fabbtn:
//                Snackbar.make(rootView,"hello, I am SnackBar",Snackbar.LENGTH_LONG)
//                        .setAction("Undo", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(getApplicationContext(),"sanckbar",Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
//                //Toast.makeText(getApplicationContext(),"fab button",Toast.LENGTH_SHORT).show();
//                break;
        }
    }
}
