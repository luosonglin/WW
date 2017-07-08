package com.winwin.app.MVP.Model;

import android.util.Log;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.UI.Entity.PageDto;
import com.winwin.app.UI.Entity.ParkDto;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ParkListModel {
    private static final String TAG = ParkListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener, Map<String, Object> map) {

        HttpData.getInstance().HttpDataGetParkByConditions(new Observer<PageDto<ParkDto>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PageDto<ParkDto> parkDtoPageDto) {
                listener.onSuccess(parkDtoPageDto.getList());
                Log.e(TAG, "onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //设置页面为加载错误
                listener.onFailure(e);
                Log.e(TAG, "onError: " + e.getMessage()
                        + "\n" + e.getCause()
                        + "\n" + e.getLocalizedMessage()
                        + "\n" + e.getStackTrace());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }
        }, map);
    }
}
