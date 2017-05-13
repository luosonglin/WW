package com.winwin.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 *
 */
public class WinwinApplication extends Application{

    private static final String TAG = "WinwinApplication";
    private static Context context;

    @Override
    public void onCreate() {
        Log.d(TAG, "Winwin Application is running~~~~~~~hahaha~~~~!");
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
