package com.example.madroid.studydemo.MPAndroidChart;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.madroid.studydemo.R;

public class ChartBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_base);

    }




    public void onClick(View view){

        switch (view.getId()){
            case R.id.single_line :
                startActivity(ChartActivity.class);
                break;

            case R.id.double_line:
                startActivity(LineChartActivity.class);
                break;

            case R.id.ecg_line :
                startActivity(EcgChartActivity.class);
                break;

            case R.id.ecg_custom :
                startActivity(EcgCustomActivity.class);
                break;

        }
    }

    private void startActivity(Class cls) {
        startActivity(new Intent(this,cls));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chart_base, menu);
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
