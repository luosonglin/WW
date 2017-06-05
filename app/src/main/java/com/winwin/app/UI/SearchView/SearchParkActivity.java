package com.winwin.app.UI.SearchView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.winwin.app.R;
import com.winwin.app.UI.MineView.MyInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchParkActivity extends AppCompatActivity {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.search_edit)
    EditText searchEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_park);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
