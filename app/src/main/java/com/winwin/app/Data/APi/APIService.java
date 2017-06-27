package com.winwin.app.Data.APi;


import com.winwin.app.UI.Entity.BannerDto;
import com.winwin.app.UI.Entity.BrokerDto;
import com.winwin.app.UI.Entity.CreditDto;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.UI.Entity.HttpResult2;
import com.winwin.app.UI.Entity.IndexBannerDto;
import com.winwin.app.UI.Entity.IndexRecommandParkDto;
import com.winwin.app.UI.Entity.IndexStaticDateDto;
import com.winwin.app.UI.Entity.MyInfoDto;
import com.winwin.app.UI.Entity.ParkDetailDto;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface APIService {

    //test 获取banner
    @GET("api/v1/banner/meeting")
    Observable<HttpResult2<BannerDto>> getBannerList();

    /**
     * 首页
     */
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

    //app获取园区详情
    @GET("api/v1/parkapp/getParkDetail/{parkId}")
    Observable<HttpResult<ParkDetailDto>> getParkDetail(@Path("parkId") long parkId);

    //app获取园区详情页面中的咨询经纪人列表
    @GET("api/v1/parkapp/noLogin/{parkId}/getBrokers")
    Observable<HttpResult<List<BrokerDto>>> getBrokers(@Path("parkId") long parkId);

    /**
     * 搜索页
     */
    //获取上海所有区域列表
    @GET("v1/parkapp/search/shanghai/Areas")
    Observable<HttpResult<List<HotAreaDto>>> getShanghaiHotAreas();

    /**
     * 个人页
     */
    //app获取我的积分包含用户的头像以及姓名等信息
    @GET("api/v1/credits/info")
    Observable<HttpResult<MyInfoDto>> getMyInformation();

    //app获取我的积分列表
    @GET("api/v1/credits/lists")
    Observable<HttpResult<CreditDto>> getCredits();
}
