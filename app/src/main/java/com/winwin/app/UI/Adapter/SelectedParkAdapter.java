package com.winwin.app.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.IndexBannerDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class SelectedParkAdapter extends BaseQuickAdapter<IndexBannerDto> {
    public SelectedParkAdapter(int layoutResId, List<IndexBannerDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexBannerDto item) {
        Glide.with(mContext)
                .load(item.getBannerPath())
                .crossFade()
//                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) helper.getView(R.id.selected_park_iv));
    }
}
