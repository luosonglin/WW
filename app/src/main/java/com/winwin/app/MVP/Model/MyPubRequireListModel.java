package com.winwin.app.MVP.Model;

import android.util.Log;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.UI.Entity.RequireDto;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MyPubRequireListModel {
    private static final String TAG = MyPubRequireListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener){
        HttpData.getInstance().HttpDataGetMyPubRequires(new Observer<List<RequireDto>>() {
            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                //设置页面为加载错误
                listener.onFailure(e);
                Log.e(TAG, "onError: "+e.getMessage()
                        +"\n"+e.getCause()
                        +"\n"+e.getLocalizedMessage()
                        +"\n"+e.getStackTrace());
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(List<RequireDto> data) {
                listener.onSuccess(data);
                Log.e(TAG, "onNext");
            }
        });
    }
}
