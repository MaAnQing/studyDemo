package com.hm.madroid.ecg;

/**
 * created by madroid at 2015-08-12
 */
public class EcgFilter {
    private static final String TAG = "EcgFilter";

    public native String getVersion() ;

    public native int processEcg(int rawEcg) ;

    static {
        System.loadLibrary("ecg");
    }
}
