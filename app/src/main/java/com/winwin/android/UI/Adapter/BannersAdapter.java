package com.winwin.android.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.android.R;
import com.winwin.android.UI.Entity.BannerDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class BannersAdapter extends BaseQuickAdapter<BannerDto.BannersBean> {
    public BannersAdapter(int layoutResId, List<BannerDto.BannersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerDto.BannersBean item) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load("http://www.medmeeting.com/upload/banner/" + item.getBanner())
                .apply(options)
                .into((ImageView) helper.getView(R.id.book_info_image_url));
        helper.setText(R.id.book_info_textview_name,item.getId()+"");
        helper.setText(R.id.book_info_textview_author,item.getTitle());
        helper.setText(R.id.book_info_textview_introduction,"简介:"+item.getStartDate());
    }
}
