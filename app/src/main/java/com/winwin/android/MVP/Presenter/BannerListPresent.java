package com.winwin.android.MVP.Presenter;


import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.BannerListModel;
import com.winwin.android.MVP.View.BannerListView;
import com.winwin.android.UI.Entity.BannerDto;

import java.util.List;

public class BannerListPresent implements OnLoadDataListListener<List<BannerDto.BannersBean>> {
    private BannerListView mView;
    private BannerListModel mModel;
    private boolean isjz;
    public BannerListPresent(BannerListView mView) {
        this.mView = mView;
        this.mModel=new BannerListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz){
        this.isjz=isjz;
        mModel.LoadData(this);
    }

    @Override
    public void onSuccess(List<BannerDto.BannersBean> data) {
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
