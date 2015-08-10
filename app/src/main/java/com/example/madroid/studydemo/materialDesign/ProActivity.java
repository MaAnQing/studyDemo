package com.example.madroid.studydemo.materialDesign;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.madroid.studydemo.R;
import com.google.gson.internal.bind.MapTypeAdapterFactory;

public class ProActivity extends AppCompatActivity {


    private static final String TAG = "ProActivity" ;
    private DrawerLayout mDrawerLayout ;
    private ActionBarDrawerToggle mDrawerToggle ;
    private FloatingActionButton mButton ;
    private View mRootLayout ;
    private Toolbar mToolBar ;
    private TabLayout mTabLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_pro);
        init();
    }

    private void init() {

        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(ProActivity.this, mDrawerLayout, R.string.hello_world, R.string.hello_world);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mTabLayout = (TabLayout) findViewById(R.id.material_tab_layout) ;

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mRootLayout = findViewById(R.id.root_layout) ;
        mButton = (FloatingActionButton) findViewById(R.id.btn_fab) ;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mRootLayout, "hello,I am Snackbar!", Snackbar.LENGTH_SHORT).
                        setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ProActivity.this, "undo", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        enableTab(true);
    }

    private void enableTab(boolean enable) {
        if (enable) {
            mTabLayout.setVisibility(View.VISIBLE);
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB3"));
//            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    if ("TAB1".equals(tab.getTag())) {
//                        //showMoreTab(false);
//                        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//                        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
//                        mTabLayout.invalidate();
//                    } else if ("TAB2".equals(tab.getText())) {
//                        //showMoreTab(false);
//                        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//                        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//                        mTabLayout.invalidate();
//                    } else if ("TAB3".equals(tab.getText())) {
//                        //showMoreTab(true);
//                        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//                        mTabLayout.invalidate();
//
//                    }
//                    Log.i(TAG,"tab :" + tab.getText()) ;
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//
//                }
//            });
            //mTabLayout.setupWithViewPager();
        } else {
            mTabLayout.setVisibility(View.GONE);
        }

    }

    private void showMoreTab(boolean show) {
        if (show) {
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB3"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB4"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB5"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB6"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB7"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB8"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB9"));
        } else {
            mTabLayout.removeAllTabs();
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("TAB3"));
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_material_pro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()) {
            case R.id.action_enable_tab:
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
