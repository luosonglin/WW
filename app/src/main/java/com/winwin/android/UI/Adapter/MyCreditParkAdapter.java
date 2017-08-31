package com.winwin.android.UI.Adapter;


import com.winwin.android.R;
import com.winwin.android.UI.Entity.CreditDto;
import com.winwin.android.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyCreditParkAdapter extends BaseQuickAdapter<CreditDto.PublishParkListBean> {
    public MyCreditParkAdapter(int layoutResId, List<CreditDto.PublishParkListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditDto.PublishParkListBean item) {
        helper.setText(R.id.name,item.getName()+"");
        helper.setText(R.id.credit,"+5");
        helper.setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_06));
    }
}
