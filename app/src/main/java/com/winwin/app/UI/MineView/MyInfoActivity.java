package com.winwin.app.UI.MineView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料页
 */
public class MyInfoActivity extends AppCompatActivity {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.right_tip)
    ImageView rightTip;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.right_tip2)
    ImageView rightTip2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        toolBar();
        initView();
    }

    private void toolBar() {
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
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(MyInfoActivity.this)
                .load(getIntent().getExtras().getString("UserAvatar"))
                .apply(options)
                .into(avatar);

        name.setText(getIntent().getExtras().getString("UserName"));
    }

    @OnClick({R.id.confirm, R.id.avatar, R.id.name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                break;
            case R.id.avatar:
                break;
            case R.id.name:
                break;
        }
    }
}

