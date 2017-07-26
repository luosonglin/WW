package com.winwin.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.winwin.app.im.business.InitBusiness;
import com.tencent.imsdk.TIMLogLevel;
import com.winwin.app.Constant.Constant;
import com.winwin.app.UI.OtherView.SplashActivity;
import com.winwin.app.Util.Foreground;
import com.xiaochao.lcrapiddeveloplibrary.Exception.core.Recovery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BaseApplication extends Application{

    private static final String TAG = "BaseApplication";
    private static Context context;
    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<>();
    //记录需要一次性关闭的页面
    private List<Activity> activitys = new ArrayList<>();

    @Override
    public void onCreate() {
        Log.d(TAG, "Winwin Application is running~~~~~~~hahaha~~~~!");
        super.onCreate();
        Foreground.init(this);
        context = getApplicationContext();
        instance = this;

//        //初始化异常管理工具
        Recovery.getInstance()
                .debug(true)//关闭后 在错误统一管理页面不显示异常数据
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(SplashActivity.class)//恢复页面
                .init(this);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int loglvl = pref.getInt("loglvl", TIMLogLevel.DEBUG.ordinal());
        //初始化IMSDK
        InitBusiness.start(getApplicationContext(), loglvl, Constant.SDK_APPID);

        UMShareAPI.get(this);
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 应用实例
     **/
    private static BaseApplication instance;

    /**
     * 获得实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 给临时Activitys添加activity
     */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 退出指定的Activity
     */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin(Constant.WeChat_AppID, Constant.WeChat_AppSecret);
        PlatformConfig.setQQZone(Constant.QQ_AppID, Constant.QQ_AppSecret);
//        PlatformConfig.setSinaWeibo("188948618", "416592ff15fdad47403ad89e894d5fd4", "http://sns.whalecloud.com");
//        PlatformConfig.setAlipay("2015111700822536");
    }

}
