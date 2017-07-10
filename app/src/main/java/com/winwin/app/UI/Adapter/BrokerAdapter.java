package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.BrokerDto;
import com.winwin.app.Util.GlideCircleTransform;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class BrokerAdapter extends BaseQuickAdapter<BrokerDto> {
    private static final String TAG = LatestRecommendationAdapter.class.getSimpleName();

    public BrokerAdapter(int layoutResId, List<BrokerDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BrokerDto item) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new GlideCircleTransform(mContext))
                .placeholder(R.mipmap.emoji)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(item.getImage())
                .apply(options)
                .into((ImageView) helper.getView(R.id.avatar));

        helper.setText(R.id.name, item.getRealName())
                .setText(R.id.attitude, "态度好，服务热情");

        helper.getView(R.id.message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, item.getId() + "");
            }
        });
        helper.getView(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ item.getMobile()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }
}
