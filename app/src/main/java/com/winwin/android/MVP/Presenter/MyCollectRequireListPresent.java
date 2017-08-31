package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.MyCollectRequireListModel;
import com.winwin.android.MVP.View.RequireListView;
import com.winwin.android.UI.Entity.RequireDto;

import java.util.List;

public class MyCollectRequireListPresent implements OnLoadDataListListener<List<RequireDto>> {
    private RequireListView mView;
    private MyCollectRequireListModel mModel;
    private boolean isjz;
    public MyCollectRequireListPresent(RequireListView mView) {
        this.mView = mView;
        this.mModel=new MyCollectRequireListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
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
