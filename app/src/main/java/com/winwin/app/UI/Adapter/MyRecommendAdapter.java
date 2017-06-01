package com.winwin.app.UI.Adapter;

import android.view.View;

import com.winwin.app.R;
import com.winwin.app.UI.entity.BannerDto;
import com.winwin.app.util.ToastUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyRecommendAdapter extends BaseQuickAdapter<BannerDto.BannersBean> {
    public MyRecommendAdapter(int layoutResId, List<BannerDto.BannersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, BannerDto.BannersBean item) {
        helper.setText(R.id.name_tv,item.getTitle().substring(0,10)+"");
        helper.setText(R.id.time_tv, item.getStartDate()+"");

        helper.setOnClickListener(R.id.accept_btn, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(helper.getContext(), "1");
            }
        });

        helper.setOnClickListener(R.id.reject_btn, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(helper.getContext(), "2");
            }
        });
    }
}
