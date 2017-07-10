package com.winwin.app.UI.Adapter;


import android.content.Intent;
import android.util.Log;
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
 * 精选园区-园区列表
 */
public class SelectParkListAdapter extends BaseQuickAdapter<ParkDto> {
    public SelectParkListAdapter(int layoutResId, List<ParkDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ParkDto item) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
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
                Log.e(TAG, item.getId() + "");
                mContext.startActivity(intent);
            }
        });
    }
}

