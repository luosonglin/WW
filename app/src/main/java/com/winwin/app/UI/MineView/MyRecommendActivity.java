package com.winwin.app.UI.MineView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winwin.app.Constant.Data;
import com.winwin.app.R;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.SmartTabLayout;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItemAdapter;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItems;

public class MyRecommendActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewGroup tab;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recommend);
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
//                Intent intent = new Intent(MyRecommendActivity.this, MainActivity.class);
//                intent.putExtra("ReturnToMainActivity", 4);
//                startActivity(intent);
                Data.setPage(4);
                finish();
            }
        });
    }

    private void initView() {
        tab = (ViewGroup) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        tab.addView(LayoutInflater.from(this).inflate(R.layout.tab_top_layout, tab, false));

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(this);

        pages.add(FragmentPagerItem.of("我推荐的", MyRecommendTabFragment.class));
        pages.add(FragmentPagerItem.of("我收到的", MyRecommendToMeTabFragment.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewpager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewpager);
    }
}

