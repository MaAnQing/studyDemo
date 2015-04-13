package com.example.madroid.studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.ui.RippleBackgroundActivity;
import com.example.madroid.studydemo.ui.ScrollViewActivity;
import com.example.madroid.studydemo.ui.StikkyHeaderActivity;
import com.example.madroid.studydemo.ui.SurfaceViewActivity;
import com.example.madroid.studydemo.ui.rippleActivity;


public class MainActivity extends ActionBarActivity {

    private ListView mListView ;
    private ArrayAdapter<String> mAdapter ;
    private String[] mData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        mListView = (ListView)findViewById(R.id.listView) ;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        startActivity(new Intent(getApplicationContext(), RippleBackgroundActivity.class));
                        break;
                    case 1 :
                        startActivity(new Intent(getApplicationContext(), rippleActivity.class));
                        break;
                    case 2 :
                        startActivity(new Intent(getApplicationContext(), SurfaceViewActivity.class));
                        break;
                    case 3 :
                        startActivity(new Intent(getApplicationContext(), StikkyHeaderActivity.class));
                        break;
                    case 4 :
                        startActivity(new Intent(getApplicationContext(), ScrollViewActivity.class));
                    default:
                        break;
                }
            }
        });
        mData = getResources().getStringArray(R.array.main_title) ;
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mData) ;
        mListView.setAdapter(mAdapter);
    }


}
