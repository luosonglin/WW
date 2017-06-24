package com.winwin.app.Data.APi;


import com.winwin.app.UI.Entity.BannerDto;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.UI.Entity.HttpResult2;
import com.winwin.app.UI.Entity.IndexBannerDto;
import com.winwin.app.UI.Entity.IndexRecommandParkDto;
import com.winwin.app.UI.Entity.IndexStaticDateDto;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface APIService {

    //test 获取banner
    @GET("api/v1/banner/meeting")
    Observable<HttpResult2<BannerDto>> getBannerList();

    //app首页获取顶部banner图地址
    @GET("api/v1/parkapp/noLogin/appIndex/banners")
    Observable<HttpResult<List<IndexBannerDto>>> getBanners();

    //app首页获取当前项目，最新空间，转介成功数值
    @GET("api/v1/parkapp/noLogin/appIndex/statisticDate")
    Observable<HttpResult<IndexStaticDateDto>> getStaticDates();

    //首页精选园区banner图列表
    @GET("api/v1/parkapp/noLogin/goodParksBanners")
    Observable<HttpResult<List<IndexBannerDto>>> getParksBanners();

    //首页精选园区列表
    @GET("api/v1/parkapp/noLogin/newRecommandParks")
    Observable<HttpResult<List<IndexRecommandParkDto>>> getRecommandParks();


}
