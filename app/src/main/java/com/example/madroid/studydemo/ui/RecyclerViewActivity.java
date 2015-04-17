package com.example.madroid.studydemo.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.adapter.MyRecyclerAdapter;
import com.example.madroid.studydemo.model.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends ActionBarActivity {


    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mAdapter ;
    private List<RecyclerItem> dataSet ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView() ;
    }

    private void initView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.myRecycler) ;

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(this) ;
        mRecyclerView.setLayoutManager(mLayoutmanager);

        //如果已知内容的变化不会改变RecyclerView的布局大小，使用这个来提高性能
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyRecyclerAdapter(getDadaSet()) ;
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<RecyclerItem> getDadaSet(){

        dataSet = new ArrayList<>() ;
        for (int i = 0 ; i <100 ; i++){
            RecyclerItem item = new RecyclerItem() ;
            item.setTitle("item : " + i);
            dataSet.add(item);
        }
        return dataSet ;
    }
}
