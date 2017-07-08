package com.winwin.app.MVP.View;

import com.winwin.app.UI.Entity.ParkDto;

import java.util.List;

public interface ParkListView {
    //显示加载页
    void showProgress();
    //关闭加载页
    void hideProgress();
    //加载新数据
    void newDatas(List<ParkDto> newsList);
    //添加更多数据
    void addDatas(List<ParkDto> addList);
    //显示加载失败
    void showLoadFailMsg();
    //显示已加载所有数据
    void showLoadCompleteAllData();
    //显示无数据
    void showNoData();
}
