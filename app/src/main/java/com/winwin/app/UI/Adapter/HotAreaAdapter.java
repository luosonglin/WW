package com.winwin.app.UI.Adapter;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class HotAreaAdapter extends BaseQuickAdapter<HotAreaDto> {
    public HotAreaAdapter(int layoutResId, List<HotAreaDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotAreaDto item) {
        helper.setText(R.id.name,item.getName()+"");
    }
}
