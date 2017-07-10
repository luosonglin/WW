package com.winwin.app.UI.Adapter;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.CreditDto;
import com.winwin.app.Util.DateUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyCreditRequirementAdapter extends BaseQuickAdapter<CreditDto.PublishRequirementList> {
    public MyCreditRequirementAdapter(int layoutResId, List<CreditDto.PublishRequirementList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditDto.PublishRequirementList item) {
        helper.setText(R.id.name, item.getPubRequireName() + "");
        helper.setText(R.id.credit, "+" + item.getId());
        helper.setText(R.id.time, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_06));
    }
}
