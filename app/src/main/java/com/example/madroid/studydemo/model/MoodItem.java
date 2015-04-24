package com.example.madroid.studydemo.model;

/**
 * Created by madroid on 15-4-23.
 */
public class MoodItem {

    private String mName ;
    private String mDate ;
    private String mDuration ;
    private int mPicID ;
    private String mAddress ;

    public MoodItem(String name,String date,String duration,int id ,String address){
        mDate = date;
        mName = name ;
        mDuration = duration ;
        mPicID = id ;
        mAddress = address ;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getAddress() {

        return mAddress;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public void setDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public void setPicID(int mPicID) {
        this.mPicID = mPicID;
    }

    public String getName() {

        return mName;
    }

    public String getDate() {
        return mDate;
    }

    public String getDuration() {
        return mDuration;
    }

    public int getPicID() {
        return mPicID;
    }
}
