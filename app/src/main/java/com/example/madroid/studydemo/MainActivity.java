package com.example.madroid.studydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.mvp.view.WeatherActivity;
import com.example.madroid.studydemo.ui.RecyclerViewActivity;
import com.example.madroid.studydemo.ui.RippleBackgroundActivity;
import com.example.madroid.studydemo.ui.ScrollViewActivity;
import com.example.madroid.studydemo.ui.StikkyHeaderActivity;
import com.example.madroid.studydemo.ui.SurfaceViewActivity;
import com.example.madroid.studydemo.ui.rippleActivity;
import com.example.madroid.studydemo.volleyGson.VolleyGsonActivity;


public class MainActivity extends ActionBarActivity {

    private ListView mListView ;
    private ArrayAdapter<String> mAdapter ;
    private String[] mData ;
    private Context mContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext() ;
        initView();

    }

    private void initView(){
        mListView = (ListView)findViewById(R.id.listView) ;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, RippleBackgroundActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, rippleActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, SurfaceViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, StikkyHeaderActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mContext, ScrollViewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(mContext, RecyclerViewActivity.class));
                        break;
                    case 6:
                        startActivity(WeatherActivity.class);
                        break;
                    case 7 :
                        startActivity(VolleyGsonActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        mData = getResources().getStringArray(R.array.main_title) ;
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mData) ;
        mListView.setAdapter(mAdapter);
    }

    private void startActivity(Class cla) {
        startActivity(new Intent(mContext, cla));
    }


}
