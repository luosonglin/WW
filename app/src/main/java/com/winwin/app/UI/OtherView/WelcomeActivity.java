package com.winwin.app.UI.OtherView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.winwin.app.Constant.Constant;
import com.winwin.app.MainActivity;
import com.winwin.app.R;
import com.winwin.app.UI.ListvView.ListvViewActivity;
import com.winwin.app.Util.SpUtils;


public class WelcomeActivity extends ListvViewActivity {
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 1);
    }
}
