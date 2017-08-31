package com.winwin.android.UI.Adapter;

import com.winwin.android.R;
import com.winwin.android.UI.Entity.CreditDto;
import com.winwin.android.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyCreditRequirementAdapter extends BaseQuickAdapter<CreditDto.PublishRequirementList> {
    public MyCreditRequirementAdapter(int layoutResId, List<CreditDto.PublishRequirementList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditDto.PublishRequirementList item) {
        helper.setText(R.id.name, item.getRequireTitle() + "");
        helper.setText(R.id.credit, "+1");
        helper.setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_06));
    }
}
