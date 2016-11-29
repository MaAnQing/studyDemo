package com.example.madroid.studydemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.madroid.studydemo.R;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_list);
        initList();
    }

    private void initList() {
        ListView view = (ListView) findViewById(R.id.list);
        List<String> data = new ArrayList<>() ;
        for (int i = 0; i < 20; i++) {
            data.add("item : " + i) ;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data) ;
        view.setAdapter(adapter);

    }
}
