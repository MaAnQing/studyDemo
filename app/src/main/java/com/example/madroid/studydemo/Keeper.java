package com.example.madroid.studydemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

/**
 * @author: madroid
 * @date: 2015-06-23 11:05
 */
public class Keeper {
    private static final String TAG = "Keeper";
    private static final String KEEPER_NAME = "Keeper";

    private static SharedPreferences mSharedPreferences ;
    private Context mCtx ;

    public static void init(Context context){
        Log.i(TAG,"init") ;
        mSharedPreferences = context.getSharedPreferences(KEEPER_NAME, Context.MODE_PRIVATE) ;
        SharedPreferences.Editor editor = mSharedPreferences.edit() ;
        editor.putBoolean("init",true) ;
        editor.apply();
    }

    public static void keepJsonType(int type){
        SharedPreferences.Editor editor = mSharedPreferences.edit() ;
        editor.putInt(Constant.JSON_TYPE,type) ;
        editor.apply();
    }

    public static int readJsonType(){
        return mSharedPreferences.getInt(Constant.JSON_TYPE,Constant.JSON) ;
    }
}
