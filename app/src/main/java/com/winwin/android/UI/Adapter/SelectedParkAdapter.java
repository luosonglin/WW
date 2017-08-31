package com.winwin.android.UI.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.android.R;
import com.winwin.android.UI.Entity.IndexBannerDto;
import com.winwin.android.UI.RecommendView.SelectedParkActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * 精选园区
 */
public class SelectedParkAdapter extends BaseQuickAdapter<IndexBannerDto> {
    public SelectedParkAdapter(int layoutResId, List<IndexBannerDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final IndexBannerDto item) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(item.getBannerPath())
                .apply(options)
                .into((ImageView) helper.getView(R.id.selected_park_iv));

        helper.getView(R.id.selected_park_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SelectedParkActivity.class);
                intent.putExtra("parkSecondType", item.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
