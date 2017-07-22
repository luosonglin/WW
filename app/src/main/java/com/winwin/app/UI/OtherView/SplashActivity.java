package com.winwin.app.UI.OtherView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.winwin.app.Constant.Constant;
import com.winwin.app.MainActivity;
import com.winwin.app.R;
import com.winwin.app.UI.CustomView.DialogActivity;
import com.winwin.app.UI.SignView.LoginActivity;
import com.winwin.app.Util.SpUtils;
import com.winwin.app.im.business.InitBusiness;
import com.winwin.app.im.business.LoginBusiness;
import com.winwin.app.im.event.FriendshipEvent;
import com.winwin.app.im.event.MessageEvent;
import com.winwin.app.im.event.RefreshEvent;
import com.winwin.app.im.model.UserInfo;
import com.winwin.app.im.presenter.SplashPresenter;
import com.winwin.app.im.ui.NotifyDialog;
import com.winwin.app.im.viewfeatures.SplashView;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends AppCompatActivity implements SplashView {
    private static final String TAG = SplashActivity.class.getSimpleName();
    SplashPresenter presenter;
    private int LOGIN_RESULT_CODE = 100;
    private final int REQUEST_PHONE_PERMISSIONS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, Constant.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_welcome);
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionsList.size() == 0) {
                init();
            } else {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PHONE_PERMISSIONS);
            }
        } else {
            init();
        }
    }

    private void init() {
        Log.e(TAG, "init");
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int loglvl = pref.getInt("loglvl", TIMLogLevel.DEBUG.ordinal());
        //初始化IMSDK
        InitBusiness.start(getApplicationContext(), loglvl, Constant.SDK_APPID);
//        //初始化TLS
//        TlsBusiness.init(getApplicationContext());
//        String id =  TLSService.getInstance().getLastUserIdentifier();
//        UserInfo.getInstance().setId(id);
//        UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));
        presenter = new SplashPresenter(this);
        presenter.start();
    }


    @Override
    public void navToHome() {
        Log.e(TAG, "navToHome");
        //登录之前要初始化群和好友关系链缓存
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                //被其他终端踢下线
                Log.d(TAG, "receive force offline message");
                Intent intent = new Intent(SplashActivity.this, DialogActivity.class);
                startActivity(intent);
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要重新登录
                new NotifyDialog().show(getString(R.string.tls_expire), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            logout();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                });
            }
        })
                //设置连接状态事件监听器
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        Log.i(TAG, "onConnected");
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.i(TAG, "onDisconnected");
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Log.i(TAG, "onWifiNeedAuth");
                    }
                });
        //设置会话刷刷新监听
        RefreshEvent.getInstance().init(userConfig);
        userConfig = FriendshipEvent.getInstance().init(userConfig);
//        userConfig = GroupEvent.getInstance().init(userConfig);
        userConfig = MessageEvent.getInstance().init(userConfig);
        TIMManager.getInstance().setUserConfig(userConfig);
        LoginBusiness.loginIm(UserInfo.getInstance().getId(), UserInfo.getInstance().getUserSig(), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "login error : code " + i + " " + s);
                switch (i) {
                    case 6208:
                        //离线状态下被其他终端踢下线
//                NotifyDialog dialog = new NotifyDialog();
//                dialog.show(getString(R.string.kick_logout), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        navToHome();
//                    }
//                });
                        break;
                    case 6200:
                        Toast.makeText(SplashActivity.this, getString(R.string.login_error_timeout), Toast.LENGTH_SHORT).show();
                        navToLogin();
                        break;
                    default:
                        Toast.makeText(SplashActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                        navToLogin();
                        break;
                }
            }

            @Override
            public void onSuccess() {
                //初始化程序后台后消息推送
//                PushUtil.getInstance();
                //初始化消息监听
                MessageEvent.getInstance();

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void navToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivityForResult(intent, LOGIN_RESULT_CODE);
        startActivity(intent);
    }

    @Override
    public boolean isUserLogin() {
        return UserInfo.getInstance().getId() != null;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d(TAG, "onActivityResult code:" + requestCode);
//        if (LOGIN_RESULT_CODE == requestCode) {
//            Log.d(TAG, "login error no " + TLSService.getInstance().getLastErrno());
//            if (0 == TLSService.getInstance().getLastErrno()){
//                String id = TLSService.getInstance().getLastUserIdentifier();
//                UserInfo.getInstance().setId(id);
//                UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));
//                navToHome();
//            } else if (resultCode == RESULT_CANCELED){
//                finish();
//            }
//        }
//    }
}
