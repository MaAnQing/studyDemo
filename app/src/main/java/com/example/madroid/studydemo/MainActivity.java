package com.example.madroid.studydemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.MPAndroidChart.ChartActivity;
import com.example.madroid.studydemo.MPAndroidChart.ChartBaseActivity;
import com.example.madroid.studydemo.MPAndroidChart.LineChartActivity;
import com.example.madroid.studydemo.ebook.PhotoViewActivity;
import com.example.madroid.studydemo.jni.JniActivity;
import com.example.madroid.studydemo.materialDesign.MaterialActivity;
import com.example.madroid.studydemo.mvp.view.WeatherActivity;
import com.example.madroid.studydemo.okhttp.OkHttpActivity;
import com.example.madroid.studydemo.recyclerView.RecyclerDemoActivity;
import com.example.madroid.studydemo.rxjava.RxJavaActivity;
import com.example.madroid.studydemo.ui.BottomSheetActivity;
import com.example.madroid.studydemo.ui.CustomViewActivity;
import com.example.madroid.studydemo.ui.RecyclerViewActivity;
import com.example.madroid.studydemo.ui.RippleBackgroundActivity;
import com.example.madroid.studydemo.ui.RxFluxActivity;
import com.example.madroid.studydemo.ui.ScrollViewActivity;
import com.example.madroid.studydemo.ui.StikkyHeaderActivity;
import com.example.madroid.studydemo.ui.SurfaceViewActivity;
import com.example.madroid.studydemo.ui.rippleActivity;
import com.example.madroid.studydemo.volleyGson.VolleyGsonActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

                    case 8 :
                        startActivity(ChartBaseActivity.class);
                        break;

                    case 9 :
                        startActivity(MaterialActivity.class);
                        break;

                    case 10 :
                        startActivity(PhotoViewActivity.class);
                        break;

                    case 11 :
                        startActivity(JniActivity.class);
                        break;

                    case 12 :
                        startActivity(OkHttpActivity.class);
                        break;

                    case 13 :
                        startActivity(CustomViewActivity.class);
                        break;

                    case 14 :
                        startActivity(RecyclerDemoActivity.class);
                        break;

                    case 15:
                        startActivity(RxJavaActivity.class);
                        break;

                    case 16:
                        startActivity(BottomSheetActivity.class);
                        break;

                    case 17:
                        startActivity(RxFluxActivity.class);
                        break;

                    default:
                        break;
                }
            }
        });
        mData = getResources().getStringArray(R.array.main_title) ;
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mData) ;
        mListView.setAdapter(mAdapter);

        //findViewById(R.id.fab_btn).setOnClickListener(this);
    }

    private void startActivity(Class cla) {
        startActivity(new Intent(mContext, cla));
    }


    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//
//            case R.id.fab_btn:
//                startActivity(CustomViewActivity.class);
//                break;
//        }
    }
}
