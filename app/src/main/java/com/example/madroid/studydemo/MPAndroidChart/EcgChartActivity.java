package com.example.madroid.studydemo.MPAndroidChart;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.madroid.studydemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class EcgChartActivity extends AppCompatActivity {

    private static final String TAG = "EcgChartActivity" ;
    private LineChart mEcgChart ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg_chart);

        initEcgView() ;
    }

    private void initEcgView() {
        mEcgChart = (LineChart) findViewById(R.id.ecg_chart) ;
        mEcgChart.setOnChartGestureListener(mChartGestureListener);
        mEcgChart.setOnChartValueSelectedListener(mChartValueSelectedListener);
        mEcgChart.setDrawGridBackground(false);

        mEcgChart.setDescription("description");
        mEcgChart.setNoDataTextDescription("no ecg data");

        mEcgChart.setHighlightEnabled(false);

        mEcgChart.setTouchEnabled(true);

        mEcgChart.setDragEnabled(true);

        mEcgChart.setScaleEnabled(true);

        //mEcgChart.setPinchZoom(true);



        //y轴
        YAxis leftAxis = mEcgChart.getAxisLeft() ;
        leftAxis.setEnabled(true);
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(100);
        leftAxis.setStartAtZero(false);
        leftAxis.setGridColor(Color.rgb(242, 103, 16));
//        leftAxis.setAxisLineColor(Color.rgb(242, 103, 16));
//        leftAxis.setAxisLineWidth(2f);

        //LeftAxis.enableGridDashedLine(10f,10f,0f);
        mEcgChart.getAxisRight().setEnabled(false);


        //x轴
        XAxis xAxis = mEcgChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setAvoidFirstLastClipping(true);

        //x轴线
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(Color.rgb(242, 103, 16));
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(Color.rgb(242, 103, 16));


        //testEmpt() ;
        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    initEcgData(100) ;
//                    mHandler.sendEmptyMessage(0) ;
//                }
                for (int i=0 ; i < 10 ;i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    initEcgData(100) ;
                    mHandler.sendEmptyMessage(0) ;
                }

            }
        }).start();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0 :
                        mEcgChart.invalidate();
                        break;
                }
            }
        };




    }

    private Handler mHandler ;

    private void testEmpt() {
        List<Entry> entity = new ArrayList<Entry>();
        entity.add(new Entry(50f,0)) ;
        LineDataSet set = new LineDataSet(entity,"test") ;
        set.setDrawCircles(false);
        set.setDrawValues(false);
        List<String> x = new ArrayList<>() ;
        x.add("") ;
        LineData data = new LineData(x,set) ;
        mEcgChart.setData(data);
    }

    private void initEcgData(int count) {
        if (mEcgChart.getData() != null) {

        }

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(
                    (i) +
                    "");
        }

        ArrayList<Entry> yVales = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float mult = (50 + 1);
            float val = (float) (Math.random() * mult) + 3;
            // + (float)
            // ((mult * 0.1) / 10);
            yVales.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVales,"25mm/s Lead off");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //set1.enableDashedLine(10f, 5f, 0f);

        //是否显示数据点
        set1.setDrawCircles(false);
        set1.setCircleColor(Color.RED);
        set1.setCircleSize(3f);
        set1.setDrawCircleHole(true);



        //曲线，折线
        set1.setDrawCubic(false);
        set1.setColor(Color.BLACK);

        //是否显示数值
        set1.setDrawValues(false) ;

        set1.setValueTextSize(9f);
        set1.setFillAlpha(65);
        set1.setFillColor(Color.BLACK);
//        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mEcgChart.setData(data);

    }


    private OnChartValueSelectedListener mChartValueSelectedListener = new OnChartValueSelectedListener() {
        @Override
        public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
            Log.i(TAG,"OnChartValueSelectedListener----onValueSelected") ;
        }

        @Override
        public void onNothingSelected() {
            Log.i(TAG,"OnChartValueSelectedListener----onNothingSelected") ;
        }
    };

    private OnChartGestureListener mChartGestureListener = new OnChartGestureListener() {
        @Override
        public void onChartLongPressed(MotionEvent me) {
            Log.i(TAG,"OnChartGestureListener----onChartLongPressed") ;
        }

        @Override
        public void onChartDoubleTapped(MotionEvent me) {
            Log.i(TAG,"OnChartGestureListener----onChartDoubleTapped") ;
        }

        @Override
        public void onChartSingleTapped(MotionEvent me) {
            Log.i(TAG,"OnChartGestureListener----onChartSingleTapped") ;
        }

        @Override
        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            Log.i(TAG,"OnChartGestureListener----onChartFling") ;
        }

        @Override
        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
            Log.i(TAG,"OnChartGestureListener----onChartScale") ;
        }

        @Override
        public void onChartTranslate(MotionEvent me, float dX, float dY) {
            Log.i(TAG,"OnChartGestureListener----onChartTranslate") ;
        }
    } ;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ecg_chart, menu);
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

}
