package com.winwin.android.MVP.Model;

import android.util.Log;

import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.UI.Entity.CreditDto;

import java.util.Arrays;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class CreditParkListModel {
    private static final String TAG = CreditParkListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener){
        HttpData.getInstance().HttpDataGetCredits(new Observer<CreditDto>() {
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
                        +"\n"+ Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(CreditDto data) {
                listener.onSuccess(data.getPublishParkList());
                Log.e(TAG, "onNext");
            }
        });
    }
}
