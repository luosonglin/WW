package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.AreaDto;
import com.winwin.app.UI.SearchView.ParkListActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class AreaAdapter extends BaseQuickAdapter<AreaDto> {
    public AreaAdapter(int layoutResId, List<AreaDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AreaDto item) {
        helper.setText(R.id.name,item.getAreaName());

        helper.getView(R.id.name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ParkListActivity.class);
                intent.putExtra("areaId", item.getAreaId());
                mContext.startActivity(intent);
            }
        });
    }
}

