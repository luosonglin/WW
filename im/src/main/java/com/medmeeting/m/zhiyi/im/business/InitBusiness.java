package com.medmeeting.m.zhiyi.im.business;

import android.content.Context;
import android.util.Log;

import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;


/**
 * 初始化
 * 包括imsdk等
 */
public class InitBusiness {

    private static final String TAG = InitBusiness.class.getSimpleName();

    private InitBusiness(){}

    public static void start(Context context){
        initImsdk(context, 0, 0);
    }

    public static void start(Context context, int logLevel, int SDK_APPID){
        initImsdk(context, logLevel, SDK_APPID);
    }


    /**
     * 初始化imsdk
     */
    private static void initImsdk(Context context, int logLevel, int SDK_APPID){
        TIMSdkConfig config = new TIMSdkConfig(SDK_APPID);
        config.enableLogPrint(true)
                .setLogLevel(TIMLogLevel.values()[logLevel]);
        //初始化imsdk
        TIMManager.getInstance().init(context, config);
        //禁止服务器自动代替上报已读
        Log.d(TAG, "initIMsdk");

    }


}
