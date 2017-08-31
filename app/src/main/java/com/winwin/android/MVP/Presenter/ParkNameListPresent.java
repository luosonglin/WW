package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.ParkNameListModel;
import com.winwin.android.MVP.View.ParkListView;
import com.winwin.android.UI.Entity.ParkDto;

import java.util.List;

public class ParkNameListPresent implements OnLoadDataListListener<List<ParkDto>> {
    private ParkListView mView;
    private ParkNameListModel mModel;
    private boolean isjz;
    public ParkNameListPresent(ParkListView mView) {
        this.mView = mView;
        this.mModel=new ParkNameListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz, String parkName){
        this.isjz=isjz;
        mModel.LoadData(this, parkName);
    }

    @Override
    public void onSuccess(List<ParkDto> data) {
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

