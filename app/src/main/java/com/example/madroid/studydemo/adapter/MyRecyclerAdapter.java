package com.example.madroid.studydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.madroid.studydemo.listener.RecyclerViewItemClickedListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemLongClickedListener;
import com.example.madroid.studydemo.model.RecyclerItem;
import com.example.madroid.studydemo.viewholder.MyViewHolder;

import java.util.List;


/**
 * Created by madroid on 15-4-15.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<RecyclerItem> mDataSet ;
    private RecyclerViewItemClickedListener mListener ;
    private RecyclerViewItemLongClickedListener mLongListener ;
    private int itemResId ;

    public MyRecyclerAdapter(List<RecyclerItem> data ,int itemId){
        if (data == null) {
            throw new IllegalArgumentException("Recycler item must not be null");
        }
        mDataSet = data ;
        itemResId = itemId ;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        holder.mTextView.setText(mDataSet.get(i).getTitle());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = View.inflate(viewGroup.getContext(),itemResId,null) ;
        MyViewHolder holder = new MyViewHolder(itemView) ;
        if (mListener != null && mLongListener != null){
            holder = new MyViewHolder(itemView,mListener,mLongListener) ;
        }
        return holder;
    }


    //设置监听器
    public void setOnItemClickedListener(RecyclerViewItemClickedListener listener){
        mListener = listener ;
    }

    public void setOnItemLongClickedListener(RecyclerViewItemLongClickedListener longClickedListener){
        mLongListener = longClickedListener ;
    }

    public void addItem(int position){
        notifyItemRemoved(position);
    }

    public void removeitem(int position){
        notifyItemInserted(position);
    }


}
