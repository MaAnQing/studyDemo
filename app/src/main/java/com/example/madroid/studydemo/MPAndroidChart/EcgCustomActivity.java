package com.example.madroid.studydemo.MPAndroidChart;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.madroid.studydemo.R;

public class EcgCustomActivity extends AppCompatActivity {

    private EcgView mEcgView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg_custom);

        mEcgView = (EcgView) findViewById(R.id.gird) ;


        //mEcgView.setInf(100, 100, 1280, 720);//画网格线
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ecg_custom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_color) {
            Log.i("madroid-ecg", "onOptionsItemSelected start") ;
            if (mEcgView.getGirdLineColor() == Color.BLUE) {
                mEcgView.setGirdLineColor(Color.BLUE);
            } else {
                mEcgView.setGirdLineColor(Color.rgb(242, 103, 16));
            }

            Log.i("EcgView","height:" + mEcgView.getHeight() + "; width:" + mEcgView.getWidth()) ;
        }

        return super.onOptionsItemSelected(item);
    }
}
