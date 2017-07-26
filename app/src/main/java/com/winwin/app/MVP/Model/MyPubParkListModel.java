package com.winwin.app.MVP.Model;

import android.util.Log;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.UI.Entity.ParkDto;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MyPubParkListModel {
    private static final String TAG = MyPubParkListModel.class.getSimpleName();

    /**
     *
     * @param listener
     * @param state 需要根据项目审核状态查询 0 : 查询所有状态 ；1=未审核（审核中）；2=已审核（默认展示）；3=审核不通过
     */
    public void LoadData(final OnLoadDataListListener listener, Integer state){
        HttpData.getInstance().HttpDataGetMyPubParks(new Observer<List<ParkDto>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<ParkDto> parkDtos) {
                listener.onSuccess(parkDtos);
                Log.e(TAG, "onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //设置页面为加载错误
                listener.onFailure(e);
                Log.e(TAG, "onError: "+e.getMessage()
                        +"\n"+e.getCause()
                        +"\n"+e.getLocalizedMessage()
                        +"\n"+ Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }
        }, state);
    }
}
