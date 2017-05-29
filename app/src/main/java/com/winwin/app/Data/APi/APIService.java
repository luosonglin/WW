package com.winwin.app.Data.APi;


import com.winwin.app.UI.entity.BannerDto;
import com.winwin.app.UI.entity.HttpResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface APIService {

    //test 获取banner
    @GET("api/v1/banner/meeting")
    Observable<HttpResult<BannerDto>> getBannerList();
}
