package com.winwin.android.MVP.Listener;

public interface OnLoadDataListListener<T> {
    void onSuccess(T data);
    void onFailure(Throwable e);
}
