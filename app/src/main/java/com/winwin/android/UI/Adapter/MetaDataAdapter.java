package com.winwin.android.UI.Adapter;

import com.winwin.android.R;
import com.winwin.android.UI.Entity.MetaDataDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MetaDataAdapter extends BaseQuickAdapter<MetaDataDto> {
    public MetaDataAdapter(int layoutResId, List<MetaDataDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MetaDataDto item) {
        switch (item.getMetaType()) {
            case 1:
                helper.setText(R.id.name,item.getDataDisplay());
                break;
            case 2:
                helper.setText(R.id.name,item.getDataDisplay() + " 平方米");
                break;
            case 3:
                helper.setText(R.id.name,item.getDataDisplay());
                break;
            default:
                helper.setText(R.id.name, item.getDataDisplay());
        }
    }
}
