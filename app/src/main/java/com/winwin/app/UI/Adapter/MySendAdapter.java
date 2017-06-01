package com.winwin.app.UI.Adapter;

import com.winwin.app.R;
import com.winwin.app.UI.entity.BannerDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MySendAdapter extends BaseQuickAdapter<BannerDto.BannersBean> {
    public MySendAdapter(int layoutResId, List<BannerDto.BannersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerDto.BannersBean item) {
        helper.setText(R.id.name, item.getTitle().substring(0, 10) + "");
        helper.setText(R.id.time, item.getStartDate() + "");
    }
}
