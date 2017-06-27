package com.winwin.app.MVP.Presenter;

import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.MVP.Model.HotAreaListModel;
import com.winwin.app.MVP.View.HotAreaListView;
import com.winwin.app.UI.Entity.HotAreaDto;

import java.util.List;

public class HotAreaListPresent implements OnLoadDataListListener<List<HotAreaDto>> {
    private HotAreaListView mView;
    private HotAreaListModel mModel;
    private boolean isjz;
    public HotAreaListPresent(HotAreaListView mView) {
        this.mView = mView;
        this.mModel=new HotAreaListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<HotAreaDto> data) {
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
