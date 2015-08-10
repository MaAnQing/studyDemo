package com.example.madroid.studydemo.materialDesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.madroid.studydemo.R;

public class MaterialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

    }

    public void onClick(View view){

        Intent intent = new Intent() ;
        switch (view.getId()) {
            case R.id.btn_normal:
                intent.setClass(this,NormalActivity.class) ;
                break;

            case R.id.btn_pro :
                intent.setClass(this,ProActivity.class) ;
                break;

            default:
                break;
        }
        startActivity(intent);
    }

}
