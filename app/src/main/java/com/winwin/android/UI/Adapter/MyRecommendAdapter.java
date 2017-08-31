package com.winwin.android.UI.Adapter;

import android.util.Log;
import android.view.View;

import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.R;
import com.winwin.android.UI.Entity.CommendCustomerVo;
import com.winwin.android.UI.Entity.CustomerDto;
import com.winwin.android.UI.Entity.HttpResult;
import com.winwin.android.Util.DateUtils;
import com.winwin.android.Util.ToastUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MyRecommendAdapter extends BaseQuickAdapter<CustomerDto> {
    public MyRecommendAdapter(int layoutResId, List<CustomerDto> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CustomerDto item) {
        helper.setText(R.id.name_tv,"推荐"+ item.getCommendCustomerName()+"给"+item.getRecommendName());
        helper.setText(R.id.time_tv, DateUtils.formatDate(item.getCreateTime(), DateUtils.TYPE_06));

        switch (item.getCommendStatus()) {  //推荐状态（0：待接受，1：已拒绝，2：已接受）
            case 0:
                helper.getView(R.id.accept_btn).setVisibility(View.VISIBLE);
                helper.getView(R.id.reject_btn).setVisibility(View.VISIBLE);
                helper.getView(R.id.accept_status_btn).setVisibility(View.GONE);
                helper.getView(R.id.reject_status_btn).setVisibility(View.GONE);

                helper.getView(R.id.accept_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CommendCustomerVo commendCustomerVo = new CommendCustomerVo(2, item.getId(), "");
                        HttpData.getInstance().HttpDataUpdateRecommendNews(new Observer<HttpResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull HttpResult httpResult) {
                                if ("0".equals(httpResult.getStatus().getCode())) {
                                    ToastUtils.show(mContext, "已接受, \n 手动刷新下^_^");
//                                    notify();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG, "onError: "+e.getMessage()
                                        +"\n"+e.getCause()
                                        +"\n"+e.getLocalizedMessage()
                                        +"\n"+ Arrays.toString(e.getStackTrace()));
                            }

                            @Override
                            public void onComplete() {

                            }
                        }, commendCustomerVo);
                    }
                });
                helper.getView(R.id.reject_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CommendCustomerVo commendCustomerVo = new CommendCustomerVo(1, item.getId(), "");
                        HttpData.getInstance().HttpDataUpdateRecommendNews(new Observer<HttpResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull HttpResult httpResult) {
                                if ("0".equals(httpResult.getStatus().getCode())) {
                                    ToastUtils.show(mContext, "已拒绝, \n 手动刷新下^_^");
                                    notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(TAG, "onError: "+e.getMessage()
                                        +"\n"+e.getCause()
                                        +"\n"+e.getLocalizedMessage()
                                        +"\n"+ Arrays.toString(e.getStackTrace()));
                            }

                            @Override
                            public void onComplete() {

                            }
                        }, commendCustomerVo);
                    }
                });

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
