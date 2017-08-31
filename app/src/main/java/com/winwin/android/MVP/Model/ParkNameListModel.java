package com.winwin.android.MVP.Model;

import android.util.Log;

import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.UI.Entity.ParkDto;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ParkNameListModel {
    private static final String TAG = ParkNameListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener, String parkName) {

        HttpData.getInstance().HttpDataSearchParksByName(new Observer<List<ParkDto>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<ParkDto> parkDtoPageDto) {
                listener.onSuccess(parkDtoPageDto);
                Log.e(TAG, "onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //设置页面为加载错误
                listener.onFailure(e);
                Log.e(TAG, "onError: " + e.getMessage()
                        + "\n" + e.getCause()
                        + "\n" + e.getLocalizedMessage()
                        + "\n" + Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }
        }, parkName);
    }
}

