package com.winwin.app.MVP.Model;


import android.util.Log;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Listener.OnLoadDataListListener;
import com.winwin.app.UI.entity.BannerDto;

import rx.Observer;

public class BannerListModel {
    private static final String TAG = BannerListModel.class.getSimpleName();

    public void LoadData(final OnLoadDataListListener listener){
        HttpData.getInstance().HttpDataGetBanner(new Observer<BannerDto>() {
            @Override
            public void onCompleted() {
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
            public void onNext(BannerDto data) {
                listener.onSuccess(data.getBanners());
                Log.e(TAG, "onNext");
            }
        });
    }
}
