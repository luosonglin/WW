package com.winwin.app.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.ParkDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class ParkAdapter extends BaseQuickAdapter<ParkDto> {
    public ParkAdapter(int layoutResId, List<ParkDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ParkDto item) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(item.getCoverImgs().get(0).getImagePath())
                .apply(options)
                .into((ImageView) helper.getView(R.id.image));

        helper.setText(R.id.name, item.getName())
            .setText(R.id.status, item.getCurrentRentNum() + "套在租")
            .setText(R.id.money, item.getDayRentStartPi() + "")
            .setText(R.id.location, item.getDistanceMetroDesc());
    }
}

