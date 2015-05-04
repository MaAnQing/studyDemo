package com.example.madroid.studydemo.viewholder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madroid.studydemo.R;
import com.example.madroid.studydemo.model.MoodItem;

/**
 * Created by madroid on 15-4-23.
 */
public class MoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    private TextView name ;
    private TextView date ;
    private TextView address ;
    private TextView duration ;
    private ImageView picId ;

    private onItemClickedListener mListener ;
    private onItemLongClickedListener mLongListener ;

    public MoodViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        initViews(itemView);

    }


    private void initViews(View itemView){
        name = (TextView)itemView.findViewById(R.id.tv_file_name) ;
        date = (TextView)itemView.findViewById(R.id.tv_file_date) ;
        address = (TextView)itemView.findViewById(R.id.tv_file_address) ;
        duration = (TextView)itemView.findViewById(R.id.tv_file_duration) ;
        picId = (ImageView)itemView.findViewById(R.id.image_pic) ;

    }

    public void setValue(MoodItem value){
        name.setText(value.getName());
        date.setText(value.getDate());
        duration.setText(value.getDuration());
        address.setText(value.getAddress());
        picId.setImageResource(value.getPicID());
    }

    public void setClickedListener(onItemClickedListener listener){
        mListener = listener ;
    }

    public void setLongClickedListener(onItemLongClickedListener listener){
        mLongListener = listener ;
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v,getPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        mLongListener.onLongClick(v,getPosition());
        return false;
    }


    public interface onItemClickedListener{
        public void onClick(View view,int position) ;
    }

    public interface onItemLongClickedListener{
        public void onLongClick(View view,int position) ;
    }

}
