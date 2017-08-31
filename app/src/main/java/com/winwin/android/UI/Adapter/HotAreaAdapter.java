package com.winwin.android.UI.Adapter;

import com.winwin.android.R;
import com.winwin.android.UI.Entity.HotAreaDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * 空间页下拉弹窗
 */
public class HotAreaAdapter extends BaseQuickAdapter<HotAreaDto> {
    public HotAreaAdapter(int layoutResId, List<HotAreaDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HotAreaDto item) {
        helper.setText(R.id.name,item.getName()+"");
    }
}
