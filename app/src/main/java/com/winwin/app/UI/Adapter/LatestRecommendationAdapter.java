package com.winwin.app.UI.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.IndexRecommandParkDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class LatestRecommendationAdapter extends BaseQuickAdapter<IndexRecommandParkDto> {
    public LatestRecommendationAdapter(int layoutResId, List<IndexRecommandParkDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexRecommandParkDto item) {
        Glide.with(mContext)
                .load(item.getHomeImage())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView) helper.getView(R.id.recommendation_iv));
        helper.setText(R.id.recommendation_location, item.getDistanceMetroDesc())
                .setText(R.id.recommendation_name, item.getName())
                .setText(R.id.recommendation_money, item.getDayRentStartPi()+"元/平米/天 起")
                .setText(R.id.recommendation_city, item.getProName())
                .setText(R.id.recommendation_rule, item.getCurrentRentNum()+"套在租");


    }
}
