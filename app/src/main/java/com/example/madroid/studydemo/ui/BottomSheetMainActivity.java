package com.example.madroid.studydemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.madroid.studydemo.R;

public class BottomSheetMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_main);
    }

    public void NestedScrollView(View view) {
        startActivity(new Intent(this, BottomSheetActivity.class));
    }

    public void ListView(View view) {
        startActivity(new Intent(this, BottomSheetListActivity.class));
    }

    public void RecyclerView(View view) {
        startActivity(new Intent(this, BottomSheetRecyclerActivity.class));
    }
}
