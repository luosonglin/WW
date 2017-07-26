package com.winwin.app.UI.ItemDetailView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.tencent.imsdk.TIMConversationType;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.BrokerAdapter;
import com.winwin.app.UI.Entity.BrokerDto;
import com.winwin.app.UI.Entity.FileDto;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.UI.Entity.IndexBannerDto;
import com.winwin.app.UI.Entity.ParkDetailDto;
import com.winwin.app.UI.ImView.ChatActivity;
import com.winwin.app.Util.ToastUtils;
import com.winwin.app.Widget.GlideImageLoader;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 空间页 底部弹出activity
 */
public class MapParkDetailActivity extends AppCompatActivity implements SpringView.OnFreshListener {
    private static final String TAG = MapParkDetailActivity.class.getSimpleName();
    private Toolbar toolbar;
    private Context context = this;
    private ScrollView scrollView;
//    private ImageView imageView;

    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;

    private DisplayMetrics metric;

    private double longitude;
    private double latitude;

    //显示数据组件
    private TextView amount_textview, name, location,
            usingAreaPercent, checkInCustomers,
            parkDesc, peripheryDesc,
            payDay, proStand, watStand, proArea,
            broker, secretary;;
    private BaseQuickAdapter mBaseQuickAdapter;
    private ImageView collectIv, shareIv;
    private boolean isCollect = false;
    private AMap aMap;
    private MapView mapView;
    private RelativeLayout mapRl;

    private Banner banner;
    private List<String> bannerImages = new ArrayList<>();
    private List<IndexBannerDto> parkBannerImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_park_detail);
        toolBar();
        initView(getIntent().getExtras().getInt("parkId"));

        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
        // MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        //qq微信新浪授权防杀死, 在onCreate中再设置一次回调
        UMShareAPI.get(this).fetchAuthResultWithBundle(this, savedInstanceState, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {

            }
        });
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(final long parkId) {
        banner = (Banner) findViewById(R.id.banner);
        amount_textview = (TextView) findViewById(R.id.amount_textview);
        name = (TextView) findViewById(R.id.name);
        location = (TextView) findViewById(R.id.location);
        usingAreaPercent = (TextView) findViewById(R.id.usingAreaPercent);
        checkInCustomers = (TextView) findViewById(R.id.checkInCustomers);
        parkDesc = (TextView) findViewById(R.id.parkDesc);
        peripheryDesc = (TextView) findViewById(R.id.peripheryDesc);
        payDay = (TextView) findViewById(R.id.payDay);
        proStand = (TextView) findViewById(R.id.proStand);
        watStand = (TextView) findViewById(R.id.watStand);
        proArea = (TextView) findViewById(R.id.proArea);
        HttpData.getInstance().HttpDataGetParkDetail(new Observer<ParkDetailDto>() {
            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage()
                        + "\n" + e.getCause()
                        + "\n" + e.getLocalizedMessage()
                        + "\n" + e.getStackTrace());
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(ParkDetailDto parkDetailDtoHttpResult) {
//                Glide.with(MapParkDetailActivity.this)
//                        .load(parkDetailDtoHttpResult.getParkVo().getHomeImage())
//                        .into((ImageView) findViewById(R.id.img));
                for (FileDto i : parkDetailDtoHttpResult.getParkVo().getCoverImgs()) {
                    bannerImages.add(i.getImagePath());
                    Log.e(TAG,i.getImagePath());
                }

                if (bannerImages.size() == 0) bannerImages.add(parkDetailDtoHttpResult.getParkVo().getHomeImage());

                banner.setImages(bannerImages != null ? bannerImages : null)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setBannerAnimation(Transformer.Tablet)
                        .setImageLoader(new GlideImageLoader()).start();

                amount_textview.setText("¥" + parkDetailDtoHttpResult.getParkVo().getDayRentStartPi());
                name.setText(parkDetailDtoHttpResult.getParkVo().getName());
                location.setText(parkDetailDtoHttpResult.getParkVo().getDistanceMetroDesc());
                usingAreaPercent.setText("租赁统计（" + parkDetailDtoHttpResult.getUsingAreaPercent() + "）");
                checkInCustomers.setText("入驻企业（" + parkDetailDtoHttpResult.getCheckInCustomers() + "家）");
                parkDesc.setText(parkDetailDtoHttpResult.getParkVo().getParkDesc());
                peripheryDesc.setText(parkDetailDtoHttpResult.getParkVo().getPeripheryDesc());
                payDay.setText(parkDetailDtoHttpResult.getParkVo().getPayDay() + "元/天 或 " + parkDetailDtoHttpResult.getParkVo().getPayMon() + "元/月");
                proStand.setText(parkDetailDtoHttpResult.getParkVo().getProStand() + "元/度");
                watStand.setText(parkDetailDtoHttpResult.getParkVo().getWatStand() + "元/吨");
                proArea.setText(parkDetailDtoHttpResult.getParkVo().getProArea() + "平方米");

                isCollect = parkDetailDtoHttpResult.getParkVo().isCollection();
                if (isCollect) {
                    collectIv.setTag("collected");
                    collectIv.setImageResource(R.mipmap.parkdetailactivity_fravorite_red);
                } else {
                    collectIv.setTag("not_collected");
                    collectIv.setImageResource(R.mipmap.parkdetailactivity_fravorite);
                }
                initMap(new LatLonPoint(parkDetailDtoHttpResult.getParkVo().getLatitude(), parkDetailDtoHttpResult.getParkVo().getLongitude()));

                initShare(parkDetailDtoHttpResult.getParkVo().getName(),
                        parkDetailDtoHttpResult.getParkVo().getHomeImage(),
                        parkDetailDtoHttpResult.getParkVo().getParkDesc(),
                        parkDetailDtoHttpResult.getParkVo().getShareUrl());

                longitude = parkDetailDtoHttpResult.getParkVo().getLongitude();
                latitude = parkDetailDtoHttpResult.getParkVo().getLatitude();

                Log.e(TAG, "onNext");
            }
        }, parkId);

        mapRl = (RelativeLayout) findViewById(R.id.map_ry);
        mapRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapParkDetailActivity.this, ParkMapActivity.class);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                Log.e(TAG, longitude+" "+latitude);
                startActivity(intent);
            }
        });

        broker = (TextView) findViewById(R.id.broker);
        mBaseQuickAdapter = new BrokerAdapter(R.layout.item_broker, null);
        broker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View popupView = MapParkDetailActivity.this.getLayoutInflater().inflate(R.layout.popupwindow_broker, null);

                RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.brokers);
                recyclerView.setLayoutManager(new LinearLayoutManager(popupView.getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(MapParkDetailActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.setAdapter(mBaseQuickAdapter);

                HttpData.getInstance().HttpDataGetBrokers(new Observer<List<BrokerDto>>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage()
                                + "\n" + e.getCause()
                                + "\n" + e.getLocalizedMessage()
                                + "\n" + e.getStackTrace());
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(List<BrokerDto> brokerDto) {
                        mBaseQuickAdapter.addData(brokerDto);
                        Log.e(TAG, "onNext");
                    }
                }, parkId);

                // 创建PopupWindow对象，指定宽度和高度
//                PopupWindow window = new PopupWindow(popupView, 400, 600);
                final PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, 1600);
                // 设置动画
                window.setAnimationStyle(R.style.popup_window_anim);
                // 设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));

                // 设置可以获取焦点
                window.setFocusable(true);
                // 设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);

                // 更新popupwindow的状态
                window.update();
                // 以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(broker, 0, 20);

                ImageView cancelIv = (ImageView) popupView.findViewById(R.id.cancel);
                cancelIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.dismiss();
                    }
                });
            }
        });

       /* // 以下为顶部拖动图
        // 获取屏幕宽高
        metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        // 获取控件
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        imageView = (ImageView) findViewById(R.id.img);

        // 设置图片初始大小 这里我设为满屏的16:9
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = metric.widthPixels;
        lp.height = metric.widthPixels * 9 / 16;
        imageView.setLayoutParams(lp);

        // 监听滚动事件
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) imageView
                        .getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 手指离开后恢复图片
                        mScaling = false;
                        replyImage();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!mScaling) {
                            if (scrollView.getScrollY() == 0) {
                                mFirstPosition = event.getY();// 滚动到顶部时记录位置，否则正常返回
                            } else {
                                break;
                            }
                        }
                        int distance = (int) ((event.getY() - mFirstPosition) * 0.6); // 滚动距离乘以一个系数
                        if (distance < 0) { // 当前位置比记录位置要小，正常返回
                            break;
                        }

                        // 处理放大
                        mScaling = true;
                        lp.width = metric.widthPixels + distance;
                        lp.height = (metric.widthPixels + distance) * 9 / 16;
                        imageView.setLayoutParams(lp);
                        return true; // 返回true表示已经完成触摸事件，不再处理
                }
                return false;
            }
        });*/

        /**
         * 收藏
         */
        collectIv = (ImageView) findViewById(R.id.collect_iv);
        collectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (collectIv.getTag().equals("not_collected")) {
                    HttpData.getInstance().HttpDataCollectPark(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            collectIv.setTag("collected");
                            collectIv.setImageResource(R.mipmap.parkdetailactivity_fravorite_red);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage()
                                    + "\n" + e.getCause()
                                    + "\n" + e.getLocalizedMessage()
                                    + "\n" + e.getStackTrace());
                        }

                        @Override
                        public void onComplete() {
                            ToastUtils.show(MapParkDetailActivity.this, "收藏成功");
                        }
                    }, getIntent().getExtras().getInt("parkId"), 2);
                } else {
                    HttpData.getInstance().HttpDataCancelCollectPark(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            collectIv.setTag("not_collected");
                            collectIv.setImageResource(R.mipmap.parkdetailactivity_fravorite);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage()
                                    + "\n" + e.getCause()
                                    + "\n" + e.getLocalizedMessage()
                                    + "\n" + e.getStackTrace());
                        }

                        @Override
                        public void onComplete() {
                            ToastUtils.show(MapParkDetailActivity.this, "取消收藏");
                        }
                    }, getIntent().getExtras().getInt("parkId"), 2);
                }
            }
        });


        secretary = (TextView) findViewById(R.id.secretary);
        secretary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("needId", 19);
                map.put("addSource", 1);
                HttpData.getInstance().HttpDataAddFriend(new Observer<HttpResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpResult httpResult) {
                        if (!httpResult.getStatus().getCode().equals("0")) return;
                        Intent intent = new Intent(MapParkDetailActivity.this, ChatActivity.class);
                        intent.putExtra("identify", "19");
                        intent.putExtra("userName", "winwin助手");
                        intent.putExtra("type", TIMConversationType.C2C);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }, map);

            }
        });
    }

   /* // 回弹动画 (使用了属性动画)
    public void replyImage() {
        final ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) imageView
                .getLayoutParams();
        final float w = imageView.getLayoutParams().width;// 图片当前宽度
        final float h = imageView.getLayoutParams().height;// 图片当前高度
        final float newW = metric.widthPixels;// 图片原宽度
        final float newH = metric.widthPixels * 9 / 16;// 图片原高度

        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F)
                .setDuration(200);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - newW) * cVal);
                lp.height = (int) (h - (h - newH) * cVal);
                imageView.setLayoutParams(lp);
            }
        });
        anim.start();
    }*/

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }

    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        if (latLonPoint == null) {
            return null;
        }
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    private void initMap(LatLonPoint centerpoint) {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.getUiSettings().setLogoBottomMargin(-50);//隐藏logo
            aMap.getUiSettings().setZoomControlsEnabled(false);//内置的缩放控制键，显示在地图的右下角。默认情况下是开启true的
        }
        //刚打开map的第一屏
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(convertToLatLng(centerpoint), 13));
    }

    /**
     * 社会化分享
     */
    public void initShare(final String title, final String photo, final String description, final String url) {
        shareIv = (ImageView) findViewById(R.id.share);
        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareBoardConfig config = new ShareBoardConfig();
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                mShareAction.open(config);
            }
        });
        //因为分享授权中需要使用一些对应的权限，如果你的targetSdkVersion设置的是23或更高，需要提前获取权限。
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }

        mShareListener = new MapParkDetailActivity.CustomShareListener(this);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(MapParkDetailActivity.this)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ)//, SHARE_MEDIA.MORE
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                        UMWeb web = new UMWeb(url);
                        web.setTitle(title);//标题
                        web.setThumb(new UMImage(MapParkDetailActivity.this, photo));  //缩略图
                        web.setDescription(description);//描述
                        new ShareAction(MapParkDetailActivity.this)
                                .withMedia(web)
                                .setPlatform(share_media)
                                .setCallback(mShareListener)
                                .share();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener mShareListener;
    private ShareAction mShareAction;

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<MapParkDetailActivity> mActivity;

        private CustomShareListener(MapParkDetailActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE) {
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE) {
                Toast.makeText(mActivity.get(), platform + "分享失败啦~~ \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                if (t != null) {
                    com.umeng.socialize.utils.Log.e(TAG, "umeng throw:" + t.getMessage());
                }
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //qq微信新浪授权防杀死
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏，在使用分享或者授权的Activity中，重写onDestory()方法：
        UMShareAPI.get(this).release();
    }

    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
}
