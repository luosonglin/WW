package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.RequireListModel;
import com.winwin.android.MVP.View.RequireListView;
import com.winwin.android.UI.Entity.RequireDto;
import com.winwin.android.UI.Entity.SelectRequirementVo;

import java.util.List;

public class RequireListPresent implements OnLoadDataListListener<List<RequireDto>> {
    private RequireListView mView;
    private RequireListModel mModel;
    private boolean isjz;
    public RequireListPresent(RequireListView mView) {
        this.mView = mView;
        this.mModel=new RequireListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz, SelectRequirementVo selectRequirementVo){
        this.isjz=isjz;
        mModel.LoadData(this, selectRequirementVo);
    }

    @Override
    public void onSuccess(List<RequireDto> data) {
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
