package com.winwin.android.MVP.Model;

import android.util.Log;

import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.MVP.Listener.OnLoadDataListListener;
import com.winwin.android.UI.Entity.CustomerDto;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class MyRecommendCustomerToMeListModel {
    private static final String TAG = MyRecommendCustomerToMeListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener){
        HttpData.getInstance().HttpDataQueryRecommendToMeList(new Observer<List<CustomerDto>>() {
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
            public void onNext(List<CustomerDto> data) {
                listener.onSuccess(data);
                Log.e(TAG, "onNext");
            }
        });
    }
}
