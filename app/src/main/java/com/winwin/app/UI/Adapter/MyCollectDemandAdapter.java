package com.winwin.app.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.BannerDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;
import java.util.List;

public class MyCollectDemandAdapter extends BaseQuickAdapter<BannerDto.BannersBean> {
    public MyCollectDemandAdapter(int layoutResId, List<BannerDto.BannersBean> data) {
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
                .into((ImageView) helper.getView(R.id.image));

        helper.setText(R.id.name, item.getTitle().substring(0, 10) + "")
                .setText(R.id.target, "目标区域：闵行、浦东")
                .setText(R.id.area, "需求面积：2000-3000平方米")
                .setText(R.id.developer, "房地产开发商")
                .setText(R.id.time, "3天前发布")
                .setText(R.id.times, "141次浏览");
    }
}

