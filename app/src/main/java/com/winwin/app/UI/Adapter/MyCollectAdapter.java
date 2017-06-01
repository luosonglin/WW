package com.winwin.app.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.winwin.app.R;
import com.winwin.app.UI.entity.BannerDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyCollectAdapter extends BaseQuickAdapter<BannerDto.BannersBean> {
    public MyCollectAdapter(int layoutResId, List<BannerDto.BannersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerDto.BannersBean item) {
        Glide.with(mContext)
                .load("http://www.medmeeting.com/upload/banner/" + item.getBanner())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) helper.getView(R.id.image));
        helper.setText(R.id.name, item.getTitle().substring(0, 10) + "")
            .setText(R.id.status, "2套在租")
            .setText(R.id.money, item.getId() + "")
            .setText(R.id.location, item.getEndDate() + "");
    }
}
