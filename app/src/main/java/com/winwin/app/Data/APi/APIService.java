package com.winwin.app.Data.APi;


import com.winwin.app.UI.Entity.BannerDto;
import com.winwin.app.UI.Entity.BrokerDto;
import com.winwin.app.UI.Entity.CreditDto;
import com.winwin.app.UI.Entity.CustomerDto;
import com.winwin.app.UI.Entity.FileDto;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.UI.Entity.HttpResult2;
import com.winwin.app.UI.Entity.IndexBannerDto;
import com.winwin.app.UI.Entity.IndexRecommandParkDto;
import com.winwin.app.UI.Entity.IndexStaticDateDto;
import com.winwin.app.UI.Entity.LoginUserDto;
import com.winwin.app.UI.Entity.MapDto;
import com.winwin.app.UI.Entity.MetaDataDto;
import com.winwin.app.UI.Entity.MyInfoDto;
import com.winwin.app.UI.Entity.ParkDetailDto;
import com.winwin.app.UI.Entity.UserLoginVo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
//import rx.Observable;

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

    //根据名称搜索我要找的项目或园区
    @GET("api/v1/parkapp/noLogin/searchParksByName")
    Observable<HttpResult<List<MapDto>>> searchParkByName(@Query("parkName") String parkName);

    /**
     * 个人页
     */
    //app获取我的积分包含用户的头像以及姓名等信息
    @GET("api/v1/credits/info")
    Observable<HttpResult<MyInfoDto>> getMyInformation();

    //app获取我的积分列表
    @GET("api/v1/credits/lists")
    Observable<HttpResult<CreditDto>> getCredits();

    //根据当前登录人查询我推荐的客户列表 commendStatus推荐状态（0：待接受，1：已拒绝，2：已接受）
    @GET("api/v1/recommendCustomer/queryMyRecommendList")
    Observable<HttpResult<List<CustomerDto>>> queryMyRecommendList();

    //查询(我收到的推荐) commendStatus推荐状态（0：待接受，1：已拒绝，2：已接受）
    @GET("api/v1/recommendCustomer/queryRecommendToMeList")
    Observable<HttpResult<List<CustomerDto>>> queryRecommendToMeList();

    /**
     * 发布页
     */
    //单张图片（此处配合后端，暂无批量上传API）
    @Multipart
    @POST("api/v1/image/uploadFile")
//    Observable<HttpResult<FileDto>> uploadFile(@Part() RequestBody file);
    Observable<HttpResult<FileDto>> uploadFile(@Part MultipartBody.Part file);

    /**
     * 挣钱快车
     */
    //app获取平台元数据信息接口
    @GET("api/v1/requirements/commonMetaData/{metaType}")
    Observable<HttpResult<List<MetaDataDto>>> getMetaDatas(@Path("metaType") Integer metaType);

    /**
     * login
     */
    //POST /v1/sessions/applogin (新App)创建会话，用户登录
    @POST("/v1/sessions/applogin")
    Observable<HttpResult<LoginUserDto>> login(@Body UserLoginVo userLoginVo);

}
