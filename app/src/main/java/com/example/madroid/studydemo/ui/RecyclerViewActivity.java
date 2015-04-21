package com.example.madroid.studydemo.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.adapter.MyRecyclerAdapter;
import com.example.madroid.studydemo.listener.RecyclerViewItemClickedListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemLongClickedListener;
import com.example.madroid.studydemo.model.RecyclerItem;
import com.example.madroid.studydemo.view.MyLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends ActionBarActivity implements RecyclerViewItemClickedListener,RecyclerViewItemLongClickedListener{


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
        mAdapter.setOnItemClickedListener(this);
        mAdapter.setOnItemLongClickedListener(this);
        mRecyclerView.setAdapter(mAdapter);

        ItemDecoration decoration = new MyDecoration(this);
        mRecyclerView.addItemDecoration(decoration);

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

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"onClicked",Toast.LENGTH_LONG).show();
        Log.i("madroid", "onclick") ;
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(this,"onLongClicked",Toast.LENGTH_LONG).show();
        Log.i("madroid", "onclick") ;

    }

    public class MyDecoration extends ItemDecoration {

        // 系统默认分隔符资源的ID
        private final int[] ATTRS = {android.R.attr.listDivider};
        // 分隔条Drawable对象
        private Drawable mDivider;

        // 构造函数初始化
        public MyDecoration(Context context) {
            TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
            // 获得系统提供的分隔条Drawable对象
            mDivider = typedArray.getDrawable(0);
            // 回收typedArray所占用的资源
            typedArray.recycle();
        }

        // 绘制所有列表项之间的分隔条
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            // 获取列表项距离左边缘的距离
            int left = parent.getPaddingLeft();

            // 获取列表项距离右边缘的距离
            int right = parent.getWidth() - parent.getPaddingRight();

            // 获取列表项的总数
            int childCount = parent.getChildCount();

            // 绘制所有的列表项之间的分割线
            for (int i = 0; i < childCount; i++) {
                // 获得当前的列表项
                View child = parent.getChildAt(i);

                // 获得当前列表项的布局参数信息
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                // 计算分隔条左上角的纵坐标
                int top = child.getBottom() + params.bottomMargin;

                // 计算分隔条右下角的纵坐标
                int bottom = top + mDivider.getIntrinsicHeight();

                // 设置分隔条的绘制范围
                mDivider.setBounds(left, top, right, bottom);

                // 开始绘制当前列表项下面的分隔条
                mDivider.draw(c);

            }
        }
    }
}
