package com.example.madroid.studydemo.materialDesign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.materialDesign.dialog.MyDialog;

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

    public void onShowDialog(View v) {
//        MyDialogFragment.Builder builder = new MyDialogFragment.Builder() ;
//        builder.create(this,getFragmentManager()) ;
//        builder.show() ;

        new MyDialog.Builder(this)
                .setMessage("this is a test message")
                .setPositiveButton("this is ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"onclick",Toast.LENGTH_SHORT).show();
                        Log.i("MyDialog", "on click : " + which);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("MyDialog","on click : " + which) ;
                    }
                })
                .setCancelable(false)
                .show() ;

    }

}
