package com.winwin.app.MVP.Presenter;

import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.MVP.Model.MyRecommendCustomerToMeListModel;
import com.winwin.app.MVP.View.CustomerListView;
import com.winwin.app.UI.Entity.CustomerDto;

import java.util.List;

public class MyRecommendCustomerToMeListPresent implements OnLoadDataListListener<List<CustomerDto>> {
    private CustomerListView mView;
    private MyRecommendCustomerToMeListModel mModel;
    private boolean isjz;
    public MyRecommendCustomerToMeListPresent(CustomerListView mView) {
        this.mView = mView;
        this.mModel=new MyRecommendCustomerToMeListModel();
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