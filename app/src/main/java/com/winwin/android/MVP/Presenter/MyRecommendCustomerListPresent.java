package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.MyRecommendCustomerListModel;
import com.winwin.android.MVP.View.CustomerListView;
import com.winwin.android.UI.Entity.CustomerDto;

import java.util.List;

public class MyRecommendCustomerListPresent implements OnLoadDataListListener<List<CustomerDto>> {
    private CustomerListView mView;
    private MyRecommendCustomerListModel mModel;
    private boolean isjz;
    public MyRecommendCustomerListPresent(CustomerListView mView) {
        this.mView = mView;
        this.mModel=new MyRecommendCustomerListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<CustomerDto> data) {
        if(isjz){
            if(data.size()==0){
                mView.showLoadCompleteAllData();
            }else{
                //新增自动加载的的数据
                mView.addDatas(data);
            }
        }else{
            if(data.size()==0){
                mView.showNoData();
            }else{
                mView.newDatas(data);
            }
        }
        mView.hideProgress();
    }

    @Override
    public void onFailure(Throwable e) {
        mView.showLoadFailMsg();
    }
}
