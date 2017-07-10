package com.winwin.app.UI.Adapter;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * 空间页下拉弹窗
 */
public class HotArea2Adapter extends BaseQuickAdapter<HotAreaDto> {
    public HotArea2Adapter(int layoutResId, List<HotAreaDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HotAreaDto item) {
        helper.setText(R.id.name,item.getName()+"");
    }
}
