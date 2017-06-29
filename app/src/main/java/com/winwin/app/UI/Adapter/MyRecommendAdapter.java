package com.winwin.app.UI.Adapter;

import android.view.View;

import com.winwin.app.R;
import com.winwin.app.UI.Entity.CustomerDto;
import com.winwin.app.Util.DateUtils;
import com.winwin.app.Util.ToastUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class MyRecommendAdapter extends BaseQuickAdapter<CustomerDto> {
    public MyRecommendAdapter(int layoutResId, List<CustomerDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CustomerDto item) {
        helper.setText(R.id.name_tv,"推荐"+ item.getCommendCustomerName()+"给"+item.getRecommendName());
        helper.setText(R.id.time_tv, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_06));

        helper.setOnClickListener(R.id.accept_btn, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(helper.getContext(), "1");
            }
        });

        helper.setOnClickListener(R.id.reject_btn, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show(helper.getContext(), "2");
            }
        });

        switch (item.getCommendStatus()) {  //推荐状态（0：待接受，1：已拒绝，2：已接受）
            case 0:
                helper.getView(R.id.accept_btn).setVisibility(View.VISIBLE);
                helper.getView(R.id.reject_btn).setVisibility(View.VISIBLE);
                helper.getView(R.id.accept_status_btn).setVisibility(View.GONE);
                helper.getView(R.id.reject_status_btn).setVisibility(View.GONE);
                break;
            case 1:
                helper.getView(R.id.accept_btn).setVisibility(View.GONE);
                helper.getView(R.id.reject_btn).setVisibility(View.GONE);
                helper.getView(R.id.accept_status_btn).setVisibility(View.GONE);
                helper.getView(R.id.reject_status_btn).setVisibility(View.VISIBLE);
                break;
            case 2:
                helper.getView(R.id.accept_btn).setVisibility(View.GONE);
                helper.getView(R.id.reject_btn).setVisibility(View.GONE);
                helper.getView(R.id.accept_status_btn).setVisibility(View.VISIBLE);
                helper.getView(R.id.reject_status_btn).setVisibility(View.GONE);
                break;

        }
    }
}
