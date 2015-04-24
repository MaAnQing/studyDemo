package com.example.madroid.studydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.model.MoodItem;
import com.example.madroid.studydemo.viewholder.MoodViewHolder;

import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 * Created by madroid on 15-4-23.
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodViewHolder> {

    private List<MoodItem> mDataSet ;

    private MoodViewHolder.onItemClickedListener mListener ;
    private MoodViewHolder.onItemLongClickedListener mLongListener ;

    public MoodAdapter(List<MoodItem> data){
        mDataSet = data ;

    }

    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_show_item,parent,false) ;
        MoodViewHolder mHolder = new MoodViewHolder(item ) ;
        mHolder.setClickedListener(mListener);
        mHolder.setLongClickedListener(mLongListener);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {
        holder.setValue(mDataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    public void setItemClickedListener(MoodViewHolder.onItemClickedListener listener){
        mListener = listener ;
    }

    public void SetItemLongClickedListener(MoodViewHolder.onItemLongClickedListener listener){
        mLongListener = listener ;
    }

}
