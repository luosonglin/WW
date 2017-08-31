package com.winwin.android.UI.Adapter;

import com.winwin.android.R;
import com.winwin.android.UI.Entity.CreditDto;
import com.winwin.android.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyCreditCommendAdapter extends BaseQuickAdapter<CreditDto.CommendsListBean> {
    public MyCreditCommendAdapter(int layoutResId, List<CreditDto.CommendsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditDto.CommendsListBean item) {
        helper.setText(R.id.name,item.getRemarks()+"");
        helper.setText(R.id.credit,"+1");
        helper.setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_06));
    }
}

