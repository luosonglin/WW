package com.winwin.app.UI.MineView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winwin.app.R;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.SmartTabLayout;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItemAdapter;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItems;

/**
 * 我的积分页
 */
public class MyCreditActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewGroup tab;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_credit);
        toolBar();
        initFindView();
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFindView() {

        tab = (ViewGroup) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        tab.addView(LayoutInflater.from(this).inflate(R.layout.tab_top_layout, tab, false));

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        FragmentPagerItems pages = new FragmentPagerItems(this);

        pages.add(FragmentPagerItem.of("发布需求", MyCreditRequirementTabFragment.class));
        pages.add(FragmentPagerItem.of("发布园区", MyCreditParkTabFragment.class));
        pages.add(FragmentPagerItem.of("推荐积分", MyCreditCommendTabFragment.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewpager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewpager);
    }
}
