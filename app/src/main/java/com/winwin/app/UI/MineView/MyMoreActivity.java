package com.winwin.app.UI.MineView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.medmeeting.m.zhiyi.im.business.LoginBusiness;
import com.medmeeting.m.zhiyi.im.event.MessageEvent;
import com.medmeeting.m.zhiyi.im.model.UserInfo;
import com.tencent.imsdk.TIMCallBack;
import com.winwin.app.R;
import com.winwin.app.UI.OtherView.SplashActivity;
import com.winwin.app.Util.CleanUtils;
import com.winwin.app.Util.CustomUtils;

public class MyMoreActivity extends AppCompatActivity {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    private Toolbar toolbar;
    private TextView versionTv;
    private RelativeLayout clean;
    private TextView cache;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_more);

        toolBar();
        initView();
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        versionTv = (TextView) findViewById(R.id.versionTv);
        versionTv.setText(CustomUtils.getVersion(this) + "");

        cache = (TextView) findViewById(R.id.cacheTv);
        try {
            cache.setText(CleanUtils.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        clean = (RelativeLayout) findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

        logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginBusiness.logout(new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(MyMoreActivity.this, getResources().getString(R.string.setting_logout_fail), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        UserInfo.getInstance().setId(null);
                        UserInfo.getInstance().setUserSig(null);
                        MessageEvent.getInstance().clear();
                        Intent intent = new Intent(MyMoreActivity.this, SplashActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
            }
        });
    }

    class clearCacheRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Toast.makeText(MyMoreActivity.this, "开始清理", Toast.LENGTH_SHORT).show();
                CleanUtils.clearAllCache(MyMoreActivity.this);
                Thread.sleep(3000);
                if (CleanUtils.getTotalCacheSize(MyMoreActivity.this).startsWith("0")) {
                    handler.sendEmptyMessage(0);
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(MyMoreActivity.this, "清理完成", Toast.LENGTH_SHORT).show();
                    try {
                        cache.setText(CleanUtils.getTotalCacheSize(MyMoreActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }

        ;
    };

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyMoreActivity.this);
        builder.setMessage("确认清理缓存吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                new clearCacheRunnable().run();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}

