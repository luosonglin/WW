package com.winwin.app.MVP.Presenter;

import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.MVP.Model.CreditCommendListModel;
import com.winwin.app.MVP.View.CreditCommendListView;
import com.winwin.app.UI.Entity.CreditDto;

import java.util.List;

public class CreditCommendListPresent implements OnLoadDataListListener<List<CreditDto.CommendsListBean>> {
    private CreditCommendListView mView;
    private CreditCommendListModel mModel;
    private boolean isjz;
    public CreditCommendListPresent(CreditCommendListView mView) {
        this.mView = mView;
        this.mModel=new CreditCommendListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<CreditDto.CommendsListBean> data) {
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

