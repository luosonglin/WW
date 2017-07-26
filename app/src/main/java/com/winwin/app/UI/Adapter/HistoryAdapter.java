package com.winwin.app.UI.Adapter;

import android.content.Intent;
import android.view.View;

import com.winwin.app.R;
import com.winwin.app.UI.SearchView.ParkNameListActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class HistoryAdapter extends BaseQuickAdapter<String> {
    public HistoryAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        helper.setText(R.id.name,item);

        helper.getView(R.id.name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ParkNameListActivity.class);
                intent.putExtra("parkName", item);
                mContext.startActivity(intent);
            }
        });
    }
}

