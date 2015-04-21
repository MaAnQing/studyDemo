package com.example.madroid.studydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madroid.studydemo.R;
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

    public MyRecyclerAdapter(List<RecyclerItem> data){
        if (data == null) {
            throw new IllegalArgumentException("Recycler item must not be null");
        }
        mDataSet = data ;
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
        View itemView = View.inflate(viewGroup.getContext(),R.layout.recycler_view_item,null) ;
        MyViewHolder holder = new MyViewHolder(itemView) ;
        if (mListener != null && mLongListener != null){
            holder = new MyViewHolder(itemView,mListener,mLongListener) ;
            Log.i("madroid", "new holder   22") ;

        }
        return holder;
    }



    public void setOnItemClickedListener(RecyclerViewItemClickedListener listener){
        mListener = listener ;
    }

    public void setOnItemLongClickedListener(RecyclerViewItemLongClickedListener longClickedListener){
        mLongListener = longClickedListener ;
    }


}
