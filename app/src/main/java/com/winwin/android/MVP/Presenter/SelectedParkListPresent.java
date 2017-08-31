package com.winwin.android.MVP.Presenter;

import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.MVP.Model.SelectedParkListModel;
import com.winwin.android.MVP.View.ParkListView;
import com.winwin.android.UI.Entity.ParkDto;

import java.util.List;
import java.util.Map;

public class SelectedParkListPresent  implements OnLoadDataListListener<List<ParkDto>> {
    private ParkListView mView;
    private SelectedParkListModel mModel;
    private boolean isjz;
    public SelectedParkListPresent(ParkListView mView) {
        this.mView = mView;
        this.mModel=new SelectedParkListModel();
        mView.showProgress();
    }

    public void LoadData(boolean isjz, Map<String, Object> map){
        this.isjz=isjz;
        mModel.LoadData(this, map);
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

