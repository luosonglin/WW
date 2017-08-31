package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.ParksListModel;
import com.winwin.android.MVP.View.ParkListView;
import com.winwin.android.UI.Entity.ParkDto;
import com.winwin.android.UI.Entity.SelectAppParksVo;

import java.util.List;

public class ParksListPresent implements OnLoadDataListListener<List<ParkDto>> {
    private ParkListView mView;
    private ParksListModel mModel;
    private boolean isjz;
    public ParksListPresent(ParkListView mView) {
        this.mView = mView;
        this.mModel=new ParksListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz, SelectAppParksVo selectAppParksVo){
        this.isjz=isjz;
        mModel.LoadData(this, selectAppParksVo);
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
