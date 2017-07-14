package com.winwin.app.Data.API;


import com.winwin.app.UI.Entity.AreaDto;
import com.winwin.app.UI.Entity.BannerDto;
import com.winwin.app.UI.Entity.BrokerDto;
import com.winwin.app.UI.Entity.CommendCustomerVo;
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
import com.winwin.app.UI.Entity.PageDto;
import com.winwin.app.UI.Entity.ParkAppVo;
import com.winwin.app.UI.Entity.ParkDetailDto;
import com.winwin.app.UI.Entity.ParkDto;
import com.winwin.app.UI.Entity.RequireDto;
import com.winwin.app.UI.Entity.SelectAppParksVo;
import com.winwin.app.UI.Entity.SelectRequirementVo;
import com.winwin.app.UI.Entity.UserLoginVo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
//import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface APIService {

    //Constant 获取banner
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

    //首页精选园区列表获取 传值1：文化创意园区；2：互联网创意园区；3：物流园区；4：科技园区；5：金融商务园区；6：工业园区
    @GET("/api/v1/parkapp/noLogin/goodParks")
    Observable<HttpResult<List<ParkDto>>> getRecommandParkList(@QueryMap Map<String, Object> map);

    //app获取园区详情页面中的咨询经纪人列表
    @GET("api/v1/parkapp/noLogin/{parkId}/getBrokers")
    Observable<HttpResult<List<BrokerDto>>> getBrokers(@Path("parkId") long parkId);

    /**
     * 搜索页
     */
    //app获取热门区域列表
    @GET("v1/parkapp/search/hotAreas")
    Observable<HttpResult<List<AreaDto>>> getHotArea();


    //获取上海所有区域列表
    @GET("v1/parkapp/search/shanghai/Areas")
    Observable<HttpResult<List<HotAreaDto>>> getShanghaiHotAreas();

    //根据名称搜索我要找的项目或园区
    @GET("api/v1/parkapp/noLogin/searchParksByName")
    Observable<HttpResult<List<MapDto>>> searchParkByName(@Query("parkName") String parkName);

    /**
     * 园区详情
     */
    //app获取园区详情
    @GET("api/v1/parkapp/getParkDetail/{parkId}")
    Observable<HttpResult<ParkDetailDto>> getParkDetail(@Path("parkId") long parkId);

    //此处分为收藏园区还是收藏需求，收藏数据类型（1：收藏的需求，2：收藏的项目）
    @POST("api/v1/app/stores/{dataId}/{dataType}")
    Observable<HttpResult> collectPark(@Path("dataId") long dataId, @Path("dataType") Integer dataType);

    //此处分为取消收藏园区还是取消收藏需求，取消收藏数据类型（1：取消收藏需求，2：取消收藏项目）
    @POST("api/v1/app/stores/del/{dataId}/{dataType}")
    Observable<HttpResult> cancelCollectPark(@Path("dataId") long dataId, @Path("dataType") Integer dataType);



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

    //app修改推荐的消息，传递值时取消息中的dataId传递给我
    @POST("api/v1/recommendCustomer/updateRecommendNews")
    Observable<HttpResult> updateRecommendNews(@Body CommendCustomerVo commendCustomerVo);

    //app我的发布需求列表
    @GET("api/v1/requirements/myPubRequires")
    Observable<HttpResult<List<RequireDto>>> getMyPubRequires();

    //app我的发布园区列表 需要根据项目审核状态查询 0 : 查询所有状态 ；1=未审核（审核中）；2=已审核（默认展示）；3=审核不通过
    @GET("/api/v1/requirements/myPubParks/{state}")
    Observable<HttpResult<List<ParkDto>>> getMyPubParks(@Path("state") Integer state);

    //app中获取我的收藏园区列表
    @GET("api/v1/app/stores/parks")
    Observable<HttpResult<List<ParkDto>>> getMyCollectPark();

    //app中获取我的收藏需求列表
    @GET("api/v1/app/stores/requires")
    Observable<HttpResult<List<RequireDto>>> getMyCollectRequire();


    /**
     * 发布页
     */
    //单张图片（此处配合后端，暂无批量上传API）
    @Multipart
    @POST("api/v1/image/uploadFile")
//    Observable<HttpResult<FileDto>> uploadFile(@Part() RequestBody file);
    Observable<HttpResult<FileDto>> uploadFile(@Part MultipartBody.Part file);

    //发布需求接口
    @POST("api/v1/requirements")
    Observable<HttpResult<RequireDto>> sendRequirement(@Body RequireDto requireDto);

    //发布园区项目
    @POST("/api/v1/requirements/publishPark")
    Observable<HttpResult> sendPark(@Body ParkAppVo parkAppVo);


    /**
     * 挣钱快车
     */
    //app获取平台元数据信息接口
    @GET("api/v1/requirements/commonMetaData/{metaType}")
    Observable<HttpResult<List<MetaDataDto>>> getMetaDatas(@Path("metaType") Integer metaType);

    //获取赚钱快车列表接口---一期不分页
    @POST("api/v1/makeMoney")
    Observable<HttpResult<List<RequireDto>>> getRequireList(@Body SelectRequirementVo selectRequirementVo);

    //赚钱快车详情、需求详情
    @GET("api/v1/makeMoney/{id}")
    Observable<HttpResult<RequireDto>> getRequireDetail(@Path("id") long id);



    /**
     * login
     */
    //POST /v1/sessions/applogin (新App)创建会话，用户登录
    @POST("/v1/sessions/applogin")
    Observable<HttpResult<LoginUserDto>> login(@Body UserLoginVo userLoginVo);

    /**
     * 园区列表
     */
    //综合条件筛选园区信息
    @GET("api/v1/park/getParkByConditions")
    Observable<HttpResult<PageDto<ParkDto>>> getParkByConditions(@QueryMap Map<String, Object> map);

    /**
     * 空间页
     */
    //POST /api/v1/parkapp/noLogin/getParksByConditions （**App**）多条件搜索项目或园区列表
    @POST("api/v1/parkapp/noLogin/getParksByConditions")
    Observable<HttpResult<List<ParkDto>>> getParksByConditions(@Body SelectAppParksVo selectAppParksVo);
}