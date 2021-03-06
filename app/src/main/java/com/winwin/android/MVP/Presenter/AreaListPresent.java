package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.AreaListModel;
import com.winwin.android.MVP.View.AreaListView;
import com.winwin.android.UI.Entity.AreaDto;

import java.util.List;

public class AreaListPresent implements OnLoadDataListListener<List<AreaDto>> {
    private AreaListView mView;
    private AreaListModel mModel;
    private boolean isjz;

    public AreaListPresent(AreaListView mView) {
        this.mView = mView;
        this.mModel = new AreaListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz) {
        this.isjz = isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<AreaDto> data) {
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
