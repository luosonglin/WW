package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.ParkDto;
import com.winwin.app.UI.ItemDetailView.ParkDetailActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * 搜索页进去的园区列表页用
 */
public class ParkAdapter extends BaseQuickAdapter<ParkDto> {
    public ParkAdapter(int layoutResId, List<ParkDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ParkDto item) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(item.getHomeImage())
                .apply(options)
                .into((ImageView) helper.getView(R.id.image));

        helper.setText(R.id.name, item.getName())
            .setText(R.id.status, item.getCurrentRentNum() + "套在租")
            .setText(R.id.money, item.getDayRentStartPi() + "")
            .setText(R.id.location, item.getDistanceMetroDesc());

        helper.getView(R.id.cv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ParkDetailActivity.class);
                intent.putExtra("parkId", item.getId());
                mContext.startActivity(intent);
            }
        });
    }
}

