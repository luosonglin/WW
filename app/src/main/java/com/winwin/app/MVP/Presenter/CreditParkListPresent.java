package com.winwin.app.MVP.Presenter;

import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.MVP.Model.CreditParkListModel;
import com.winwin.app.MVP.View.CreditParkListView;
import com.winwin.app.UI.Entity.CreditDto;

import java.util.List;

public class CreditParkListPresent implements OnLoadDataListListener<List<CreditDto.PublishParkListBean>> {
    private CreditParkListView mView;
    private CreditParkListModel mModel;
    private boolean isjz;
    public CreditParkListPresent(CreditParkListView mView) {
        this.mView = mView;
        this.mModel=new CreditParkListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<CreditDto.PublishParkListBean> data) {
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