package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.winwin.app.UI.SearchView.ParkListActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class HotAreaAdapter extends BaseQuickAdapter<HotAreaDto> {
    public HotAreaAdapter(int layoutResId, List<HotAreaDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HotAreaDto item) {
        helper.setText(R.id.name,item.getName()+"");

        helper.getView(R.id.name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ParkListActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("areaId", item.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
