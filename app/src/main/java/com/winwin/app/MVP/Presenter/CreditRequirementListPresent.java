package com.winwin.app.MVP.Presenter;

import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.MVP.Model.CreditRequirementListModel;
import com.winwin.app.MVP.View.CreditRequirementListView;
import com.winwin.app.UI.Entity.CreditDto;

import java.util.List;

public class CreditRequirementListPresent implements OnLoadDataListListener<List<CreditDto.PublishRequirementList>> {
    private CreditRequirementListView mView;
    private CreditRequirementListModel mModel;
    private boolean isjz;
    public CreditRequirementListPresent(CreditRequirementListView mView) {
        this.mView = mView;
        this.mModel=new CreditRequirementListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<CreditDto.PublishRequirementList> data) {
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
