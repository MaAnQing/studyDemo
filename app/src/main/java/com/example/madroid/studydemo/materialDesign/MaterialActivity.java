package com.example.madroid.studydemo.materialDesign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.PopupWindow;
import android.widget.TextView;
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

    public void onShowPopWindow(View view) {
        Log.i("madroid","onShowPopWindow") ;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.pop_window,null, true) ;
        PopupWindow popupWindow ;

        View popupView = getLayoutInflater().inflate(R.layout.pop_window, null);

        popupWindow = new PopupWindow(popupView, AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupWindow.showAsDropDown(view);

    }
}
