package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.RequireDto;
import com.winwin.app.UI.ItemDetailView.RequireDetailActivity;
import com.winwin.app.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class RequireAdapter extends BaseQuickAdapter<RequireDto> {
    public RequireAdapter(int layoutResId, List<RequireDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RequireDto item) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(item.getRequireBanner())
                .apply(options)
                .into((ImageView) helper.getView(R.id.image));

        helper.setText(R.id.name, item.getRequireTitle())
                .setText(R.id.target, "目标区域：" + item.getRequireAreaName())
                .setText(R.id.area, "需求面积：" + item.getRequireAreaRangDisplay() + "平方米")
                .setText(R.id.developer, item.getPubRequireCompany())
                .setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_04))
                .setText(R.id.times, item.getBrowseNum() + "次浏览");

        helper.getView(R.id.cv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RequireDetailActivity.class);
                intent.putExtra("id", item.getId());
                mContext.startActivity(intent);
            }
        });
    }
}

