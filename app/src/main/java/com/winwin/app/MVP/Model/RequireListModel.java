package com.winwin.app.MVP.Model;

import android.util.Log;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.UI.Entity.RequireDto;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RequireListModel {
    private static final String TAG = RequireListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener) {

        HttpData.getInstance().HttpDataGetMyCollectRequire(new Observer<List<RequireDto>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<RequireDto> requireDtos) {
                listener.onSuccess(requireDtos);
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
        });
    }
}
