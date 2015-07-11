package com.example.madroid.studydemo.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.adapter.MyRecyclerAdapter;
import com.example.madroid.studydemo.listener.OnFragmentInteractionListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemClickedListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemLongClickedListener;
import com.example.madroid.studydemo.model.RecyclerItem;

import java.util.ArrayList;
import java.util.List;


public class SampleRecyclerViewFragment extends Fragment implements RecyclerViewItemClickedListener,RecyclerViewItemLongClickedListener {

    private static final String RECYCLER_LAYOUT_ITEM = "recycler_layout_item";

    // TODO: Rename and change types of parameters
    private int mItemResId;

    private RecyclerView mRecyclerView ;
    private MyRecyclerAdapter mAdapter ;
    private List<RecyclerItem> dataSet ;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param layoutId recycler view item id.
     * @return A new instance of fragment SampleRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SampleRecyclerViewFragment newInstance(int layoutId) {
        SampleRecyclerViewFragment fragment = new SampleRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(RECYCLER_LAYOUT_ITEM, layoutId);
        fragment.setArguments(args);
        return fragment;
    }

    public SampleRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemResId = getArguments().getInt(RECYCLER_LAYOUT_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view,container,false) ;
        initView(view) ;
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.myRecycler) ;

        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(getActivity()) ;
        mRecyclerView.setLayoutManager(mLayoutmanager);

        //如果已知内容的变化不会改变RecyclerView的布局大小，使用这个来提高性能
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyRecyclerAdapter(getDadaSet(),mItemResId) ;
        mAdapter.setOnItemClickedListener(this);
        mAdapter.setOnItemLongClickedListener(this);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.ItemDecoration decoration = new MyDecoration(getActivity());
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
        Toast.makeText(getActivity(), "onclick : " +position, Toast.LENGTH_SHORT).show();
        Log.i("madroid", "onclick : " +position) ;
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getActivity(),"onLongClicked : " + position,Toast.LENGTH_SHORT).show();
        Log.i("madroid", "onclick : " + position) ;

    }

    public class MyDecoration extends RecyclerView.ItemDecoration {

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
