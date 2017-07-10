package com.winwin.app.MVP.Presenter;

import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.MVP.Model.MyPubRequireListModel;
import com.winwin.app.MVP.View.RequireListView;
import com.winwin.app.UI.Entity.RequireDto;

import java.util.List;

public class MyPubRequireListPresent implements OnLoadDataListListener<List<RequireDto>> {
    private RequireListView mView;
    private MyPubRequireListModel mModel;
    private boolean isjz;

    public MyPubRequireListPresent(RequireListView mView) {
        this.mView = mView;
        this.mModel = new MyPubRequireListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz) {
        this.isjz = isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<RequireDto> data) {
        if (isjz) {
            if (data.size() == 0) {
                mView.showLoadCompleteAllData();
            } else {
                //新增自动加载的的数据
                mView.addDatas(data);
            }
        } else {
            if (data.size() == 0) {
                mView.showNoData();
            } else {
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
