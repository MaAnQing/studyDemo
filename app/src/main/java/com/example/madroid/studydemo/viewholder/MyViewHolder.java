package com.example.madroid.studydemo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.listener.RecyclerViewItemClickedListener;
import com.example.madroid.studydemo.listener.RecyclerViewItemLongClickedListener;

/**
 * Created by madroid on 15-4-21.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public TextView mTextView;
    private RecyclerViewItemClickedListener mListener ;
    private RecyclerViewItemLongClickedListener mLongListener ;
    /**
     * @Description: TODO
     * @param itemView
     */
    public MyViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.recycler_title);
    }

    public MyViewHolder(View item ,RecyclerViewItemClickedListener listener, RecyclerViewItemLongClickedListener longListener){
        super(item);
        mTextView = (TextView) itemView.findViewById(R.id.recycler_title);
        mListener = listener ;
        mLongListener = longListener ;
        item.setOnClickListener(this);
        item.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ( v != null){
            mListener.onItemClick(v ,getPosition());
        }

    }

    @Override
    public boolean onLongClick(View v) {
        if (v != null){
            mLongListener.onItemLongClick(v, getPosition());
        }
        return false;
    }
}
