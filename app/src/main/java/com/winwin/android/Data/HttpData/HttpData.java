package com.winwin.android.Data.HttpData;


import android.util.Log;

import com.winwin.android.Data.API.APIService;
import com.winwin.android.Data.API.CacheProviders;
import com.winwin.android.Data.Retrofit.ApiException;
import com.winwin.android.Data.Retrofit.RetrofitUtils;
import com.winwin.android.UI.Entity.AppUserVo;
import com.winwin.android.UI.Entity.AreaDto;
import com.winwin.android.UI.Entity.BannerDto;
import com.winwin.android.UI.Entity.BrokerDto;
import com.winwin.android.UI.Entity.CommendCustomerVo;
import com.winwin.android.UI.Entity.CreditDto;
import com.winwin.android.UI.Entity.CustomerDto;
import com.winwin.android.UI.Entity.FileDto;
import com.winwin.android.UI.Entity.HotAreaDto;
import com.winwin.android.UI.Entity.HttpResult;
import com.winwin.android.UI.Entity.IndexBannerDto;
import com.winwin.android.UI.Entity.IndexRecommandParkDto;
import com.winwin.android.UI.Entity.IndexStaticDateDto;
import com.winwin.android.UI.Entity.LoginUserDto;
import com.winwin.android.UI.Entity.MetaDataDto;
import com.winwin.android.UI.Entity.MyInfoDto;
import com.winwin.android.UI.Entity.ParkAppVo;
import com.winwin.android.UI.Entity.ParkDetailDto;
import com.winwin.android.UI.Entity.ParkDto;
import com.winwin.android.UI.Entity.RequireDto;
import com.winwin.android.UI.Entity.SelectAppParksVo;
import com.winwin.android.UI.Entity.SelectRequirementVo;
import com.winwin.android.UI.Entity.UserLoginVo;
import com.winwin.android.UI.Entity.UserRegisterVo;
import com.winwin.android.Util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import okhttp3.MultipartBody;
//import rx.Observable;
//import rx.functions.Func1;
//import rx.Observable;
//import rx.Observer;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;

////1.X
//import rx.Observable;
//import rx.Subscription;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//import rx.functions.Action1;
//
////2.X
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.ObservableTransformer;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import io.reactivex.functions.Consumer;


/*
 *所有的请求数据的方法集中地
 * 根据MovieService的定义编写合适的方法
 */
public class HttpData extends RetrofitUtils {

    private static File cacheDirectory = FileUtil.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory)
            .using(CacheProviders.class);

    protected static final APIService service = getRetrofit().create(APIService.class);
    protected static final APIService service_no_token = getNoTokenRetrofit().create(APIService.class);

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        Log.e("HttpData", "getInstance");
        return SingletonHolder.INSTANCE;
    }

//    //Get请求  视频列表
//    public void verfacationCodeGetCache(Observer<List<VideoListDto>> observer) {
//        Observable observable=service_t.getVideoList().map(new HttpResultFunc<List<VideoListDto>>());
//        Observable observableCahce=providers.getVideoList(observable,new DynamicKey("视频列表"),new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<VideoListDto>>());
//        setSubscribe(observableCahce,observer);
//    }
//
//    //post请求 学校列表
//    public void HttpDataToSchoolList(String type, int pageIndex, Observer<List<BookListDto>> observer){
//        Observable observable=service_t.getBookList(type,pageIndex).map(new HttpResultFunc<List<BookListDto>>());
//        Observable observableCahce=providers.getBookList(observable,new DynamicKey("书籍列表"+pageIndex+type),new EvictDynamicKey(false)).map(new HttpResultFuncCcche<List<BookListDto>>());
//        setSubscribe(observableCahce,observer);
//    }

    //get Constant banner
    public void HttpDataGetBanner(Observer<BannerDto> observer) {
        Observable observable = service.getBannerList();//.map(new HttpResultFunc<BannerDto>());
        setSubscribe(observable, observer);
//        Observable observableCache = providers.getBannerList(observable, new DynamicKey("banner测试"), new EvictDynamicKey(false)).map(new HttpResultFuncCache<BannerDto>());
//        Log.e("HttpData", "HttpDataGetBanner");
//        setSubscribe(observableCache, observer);
    }

    public void HttpDataGetStaticDate(Observer<HttpResult<IndexStaticDateDto>> observer) {
        Observable observable = service.getStaticDates();
        setSubscribe(observable, observer);
    }

    public void HttpDataGetBanners(Observer<HttpResult<List<IndexBannerDto>>> observer) {
        Observable observable = service.getBanners();
        setSubscribe(observable, observer);
    }

    public void HttpDataGetParkBanners(Observer<HttpResult<List<IndexBannerDto>>> observer) {
        Observable observable = service.getParksBanners();
        setSubscribe(observable, observer);
    }

    public void HttpDataGetNewRecommandParks(Observer<HttpResult<List<IndexRecommandParkDto>>> observer) {
        Observable observable = service.getRecommandParks();
        setSubscribe(observable, observer);
    }

    public void HttpDataGetRecommandParkList(Observer<List<ParkDto>> observer, Map<String, Object> map) {
        Observable observable = service.getRecommandParkList(map).map(new HttpResultFunc<List<ParkDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetParkDetail(Observer<ParkDetailDto> observer, long parkId) {
//        Observable observable = service_no_token.getParkDetail(parkId).map(new HttpResultFunc<ParkDetailDto>());
        Observable observable = service.getParkDetail(parkId).map(new HttpResultFunc<ParkDetailDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetBrokers(Observer<List<BrokerDto>> observer, long parkId) {
        Observable observable = service.getBrokers(parkId).map(new HttpResultFunc<List<BrokerDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetMyInformation(Observer<MyInfoDto> observer) {
        Observable observable = service.getMyInformation().map(new HttpResultFunc<MyInfoDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetCredits(Observer<CreditDto> observer) {
        Observable observable = service.getCredits().map(new HttpResultFunc<CreditDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetHotAreas(Observer<List<AreaDto>> observer) {
        Observable observable = service.getHotArea().map(new HttpResultFunc<List<AreaDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetShanghaiHotAreas(Observer<List<HotAreaDto>> observer) {
        Observable observable = service.getShanghaiHotAreas().map(new HttpResultFunc<List<HotAreaDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataSearchParksByName(Observer<List<ParkDto>> observer, String parkName) {
        Observable observable = service.searchParkByName(parkName).map(new HttpResultFunc<List<ParkDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataQueryMyRecommendList(Observer<List<CustomerDto>> observer) {
        Observable observable = service.queryMyRecommendList().map(new HttpResultFunc<List<CustomerDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataQueryRecommendToMeList(Observer<List<CustomerDto>> observer) {
        Observable observable = service.queryRecommendToMeList().map(new HttpResultFunc<List<CustomerDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataUploadFile(Observer<FileDto> observer, MultipartBody.Part file) {
//    public void HttpDataUploadFile(Observer<FileDto> observer, RequestBody file) {
        Observable observable = service.uploadFile(file).map(new HttpResultFunc<FileDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetMetaDataList(Observer<List<MetaDataDto>> observer, Integer type) {
        Observable observable = service.getMetaDatas(type).map(new HttpResultFunc<List<MetaDataDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataLogin(Observer<LoginUserDto> observer, UserLoginVo userLoginVo) {
        Observable observable = service.login(userLoginVo).map(new HttpResultFunc<LoginUserDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetParksByConditions(Observer<List<ParkDto>> observer, SelectAppParksVo selectAppParksVo) {
        Observable observable = service.getParksByConditions(selectAppParksVo).map(new HttpResultFunc<List<ParkDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataUpdateRecommendNews(Observer<HttpResult> observer, CommendCustomerVo commendCustomerVo) {
        Observable observable = service.updateRecommendNews(commendCustomerVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataGetMyPubRequires(Observer<List<RequireDto>> observer) {
        Observable observable = service.getMyPubRequires().map(new HttpResultFunc<List<RequireDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetMyPubParks(Observer<List<ParkDto>> observer, Integer state) {
        Observable observable = service.getMyPubParks(state).map(new HttpResultFunc<List<ParkDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetMyCollectPark(Observer<List<ParkDto>> observer) {
        Observable observable = service.getMyCollectPark().map(new HttpResultFunc<List<ParkDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetMyCollectRequire(Observer<List<RequireDto>> observer) {
        Observable observable = service.getMyCollectRequire().map(new HttpResultFunc<List<RequireDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataCollectPark(Observer<HttpResult> observer, int dataId, Integer dataType) {
        Observable observable = service.collectPark(dataId, dataType);
        setSubscribe(observable, observer);
    }

    public void HttpDataCancelCollectPark(Observer<HttpResult> observer, int dataId, Integer dataType) {
        Observable observable = service.cancelCollectPark(dataId, dataType);
        setSubscribe(observable, observer);
    }

    public void HttpDataSendRequirement(Observer<RequireDto> observer, RequireDto requireDto) {
        Observable observable = service.sendRequirement(requireDto).map(new HttpResultFunc<RequireDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataSendPark(Observer<HttpResult> observer, ParkAppVo parkAppVo) {
        Observable observable = service.sendPark(parkAppVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataGetRequireDetail(Observer<RequireDto> observer, long id) {
        Observable observable = service.getRequireDetail(id).map(new HttpResultFunc<RequireDto>());
        setSubscribe(observable, observer);
    }

    public void HttpDataGetRequireList(Observer<List<RequireDto>> observer, SelectRequirementVo selectRequirementVo) {
        Observable observable = service.getRequireList(selectRequirementVo).map(new HttpResultFunc<List<RequireDto>>());
        setSubscribe(observable, observer);
    }

    public void HttpDataSendMsgCaptcha(Observer<HttpResult> observer, UserRegisterVo userRegisterVo) {
        Observable observable = service.sendMsgCaptcha(userRegisterVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataConfirmRegister(Observer<HttpResult> observer, UserRegisterVo userRegisterVo) {
        Observable observable = service.confirmRegister(userRegisterVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataAddFriend(Observer<HttpResult> observer, Map<String, Object> map) {
        Observable observable = service.addFriend(map);
        setSubscribe(observable, observer);
    }

    public void HttpDataUpdateAppUserInfo(Observer<HttpResult> observer, AppUserVo appUserVo) {
        Observable observable = service.updateAppUserInfo(appUserVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataForgotPassword(Observer<HttpResult> observer, UserRegisterVo userRegisterVo) {
        Observable observable = service.forgotPassword(userRegisterVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataCheckMsg(Observer<HttpResult> observer, UserRegisterVo userRegisterVo) {
        Observable observable = service.checkMsg(userRegisterVo);
        setSubscribe(observable, observer);
    }

    public void HttpDataModifyForgotPassword(Observer<HttpResult> observer, UserRegisterVo userRegisterVo) {
        Observable observable = service.modifyForgotPassword(userRegisterVo);
        setSubscribe(observable, observer);
    }

    /**
     * 插入观察者
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        Log.e("HttpData", "setSubscribe");
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Function<HttpResult<T>, T> {

        @Override
        public T apply(@NonNull HttpResult<T> tHttpResult) throws Exception {

            if (!"0".equals(tHttpResult.getStatus().getCode())) {
                throw new ApiException(tHttpResult);
            }

            return tHttpResult.getData();
        }
    }

    /**
     * 用来统一处理RxCacha的结
     */
    private class HttpResultFuncCache<T> implements Function<Reply<T>, T> {

        @Override
        public T apply(@NonNull Reply<T> tReply) throws Exception {
            return tReply.getData();
        }
    }
}
