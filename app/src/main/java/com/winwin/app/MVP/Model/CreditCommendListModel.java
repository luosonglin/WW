package com.winwin.app.MVP.Model;

import android.util.Log;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.UI.Entity.CreditDto;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class CreditCommendListModel {
    private static final String TAG = CreditCommendListModel.class.getSimpleName();

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
                        +"\n"+e.getStackTrace());
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(CreditDto data) {
                listener.onSuccess(data.getCommendsList());
                Log.e(TAG, "onNext");
            }
        });
    }
}
