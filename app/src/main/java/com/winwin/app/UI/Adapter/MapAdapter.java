package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.MapDto;
import com.winwin.app.UI.ItemDetailView.ParkDetailActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MapAdapter extends BaseQuickAdapter<MapDto> {
    private static final String TAG = MapAdapter.class.getSimpleName();

    public MapAdapter(int layoutResId, List<MapDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MapDto item) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.haha)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(item.getCoverImgs())
                .apply(options)
                .into((ImageView) helper.getView(R.id.image));

        helper.setText(R.id.location, item.getDistanceMetroDesc())
                .setText(R.id.type, "自营")
                .setText(R.id.name, item.getName())
                .setText(R.id.money, "¥"+item.getDayRentStartPi()+"元/平米/天 起")
                .setText(R.id.rule, item.getCurrentRentNum()+"套在租");

        helper.getView(R.id.item_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ParkDetailActivity.class);
                intent.putExtra("parkId", item.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
