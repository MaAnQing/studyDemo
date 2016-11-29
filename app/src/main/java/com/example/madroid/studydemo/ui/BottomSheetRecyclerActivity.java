package com.example.madroid.studydemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.adapter.MoodAdapter;
import com.example.madroid.studydemo.adapter.MyRecyclerAdapter;
import com.example.madroid.studydemo.model.MoodItem;
import com.example.madroid.studydemo.model.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.madroid.studydemo.R.id.action0;
import static com.example.madroid.studydemo.R.id.list;

public class BottomSheetRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_recycler);

        RecyclerView view = (RecyclerView) findViewById(list);
        view.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this) ;
        view.setLayoutManager(manager);

        List<RecyclerItem> data = new ArrayList<>() ;
        for (int i = 0; i < 20; i++) {
            RecyclerItem item = new RecyclerItem() ;
            item.setTitle("item :" + i);
            data.add(item) ;
        }
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(data, R.layout.recycler_card_item) ;
        view.setAdapter(adapter);

    }
}
