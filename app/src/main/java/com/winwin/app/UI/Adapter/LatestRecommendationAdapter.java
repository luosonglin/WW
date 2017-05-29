package com.winwin.app.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.winwin.app.R;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class LatestRecommendationAdapter extends BaseQuickAdapter<String> {
    public LatestRecommendationAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext)
                .load(item)
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) helper.getView(R.id.recommendation_iv));


    }
}
