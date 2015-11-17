package com.example.madroid.studydemo.materialDesign.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * created by madroid at 2015-11-13
 */
public class BaseDialog extends Dialog {
    private static final String TAG = "BaseDialog";


    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
