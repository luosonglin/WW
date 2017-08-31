package com.winwin.android.UI.Adapter;

import android.content.Intent;
import android.view.View;

import com.winwin.android.R;
import com.winwin.android.UI.Entity.RequireDto;
import com.winwin.android.UI.ItemDetailView.RequireDetailActivity;
import com.winwin.android.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyPubRequireAdapter extends BaseQuickAdapter<RequireDto> {
    public MyPubRequireAdapter(int layoutResId, List<RequireDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RequireDto item) {
        helper.setText(R.id.name, item.getRequireTitle());
        helper.setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_04));

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
