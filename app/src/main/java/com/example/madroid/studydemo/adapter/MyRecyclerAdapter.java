package com.example.madroid.studydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.model.RecyclerItem;

import java.util.List;


/**
 * Created by madroid on 15-4-15.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<RecyclerItem> mDataSet ;

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
        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public  TextView mTextView;

        /**
         * @Description: TODO
         * @param itemView
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.recycler_title);
        }
    }

}
