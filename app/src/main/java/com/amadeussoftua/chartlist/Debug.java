package com.amadeussoftua.chartlist;

import android.util.Log;

/**
 * Created by Artem on 26.07.2014.
 */
public class Debug {

    public static final boolean ENABLED = BuildConfig.DEBUG;

    public static void logD(String TAG, String msg) {
        if (ENABLED) {
            Log.d(TAG, msg);
        }
    }

    public static void logE(String TAG, String msg) {
        if (ENABLED) {
            Log.e(TAG, msg);
        }
    }

}
