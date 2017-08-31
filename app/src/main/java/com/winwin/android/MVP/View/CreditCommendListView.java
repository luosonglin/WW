package com.winwin.android.MVP.View;

import com.winwin.android.UI.Entity.CreditDto;

import java.util.List;

public interface CreditCommendListView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(List<CreditDto.CommendsListBean> newsList);
    //添加更多数据
    void addDatas(List<CreditDto.CommendsListBean> addList);
    //显示加载失败
    void showLoadFailMsg();
    //显示已加载所有数据
    void showLoadCompleteAllData();
    //显示无数据
    void showNoData();
}
