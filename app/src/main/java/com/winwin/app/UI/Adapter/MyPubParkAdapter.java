package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.ParkDto;
import com.winwin.app.UI.ItemDetailView.ParkDetailActivity;
import com.winwin.app.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyPubParkAdapter extends BaseQuickAdapter<ParkDto> {
    public MyPubParkAdapter(int layoutResId, List<ParkDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ParkDto item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_04));

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
