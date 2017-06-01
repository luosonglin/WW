package com.winwin.app.UI.MineView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.winwin.app.R;

/**
 * 个人资料页
 */
public class MyInfoActivity extends AppCompatActivity {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
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
    }
}

