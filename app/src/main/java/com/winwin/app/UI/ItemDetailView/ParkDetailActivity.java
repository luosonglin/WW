package com.winwin.app.UI.ItemDetailView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.BrokerAdapter;
import com.winwin.app.UI.Entity.BrokerDto;
import com.winwin.app.UI.Entity.ParkDetailDto;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ParkDetailActivity extends AppCompatActivity implements SpringView.OnFreshListener{
    private static final String TAG = ParkDetailActivity.class.getSimpleName();
    private Toolbar toolbar;
    private Context context = this;
    private ScrollView scrollView;
    private ImageView imageView;

    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;

    private DisplayMetrics metric;

    //显示数据组件
    private TextView amount_textview, name, location,
            usingAreaPercent, checkInCustomers,
            parkDesc, peripheryDesc,
            payDay, proStand, watStand, proArea,
            broker;
    private BaseQuickAdapter mBaseQuickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);
        toolBar();
        initView(getIntent().getExtras().getInt("parkId"));
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String[] datas = {"选项1", "选项2", "选项3", "选项4", "选项5"};

    private void initView(final long parkId) {
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
                Log.e(TAG, "onError: "+e.getMessage()
                        +"\n"+e.getCause()
                        +"\n"+e.getLocalizedMessage()
                        +"\n"+e.getStackTrace());
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(ParkDetailDto parkDetailDtoHttpResult) {
                Glide.with(ParkDetailActivity.this)
                        .load(parkDetailDtoHttpResult.getParkVo().getHomeImage())
//                        .crossFade()
                        .into((ImageView) findViewById(R.id.img));
                amount_textview.setText("¥"+parkDetailDtoHttpResult.getParkVo().getDayRentStartPi());
                name.setText(parkDetailDtoHttpResult.getParkVo().getName());
                location.setText(parkDetailDtoHttpResult.getParkVo().getDistanceMetroDesc());
                usingAreaPercent.setText("租赁统计（"+ parkDetailDtoHttpResult.getUsingAreaPercent()+"）");
                checkInCustomers.setText("入驻企业（"+ parkDetailDtoHttpResult.getCheckInCustomers()+"家）");
                parkDesc.setText(parkDetailDtoHttpResult.getParkVo().getParkDesc());
                peripheryDesc.setText(parkDetailDtoHttpResult.getParkVo().getPeripheryDesc());
                payDay.setText(parkDetailDtoHttpResult.getParkVo().getPayDay()+"元/天 或 " +parkDetailDtoHttpResult.getParkVo().getPayMon()+"元/月");
                proStand.setText(parkDetailDtoHttpResult.getParkVo().getProStand()+"元/度");
                watStand.setText(parkDetailDtoHttpResult.getParkVo().getWatStand()+"元/吨");
                proArea.setText(parkDetailDtoHttpResult.getParkVo().getProArea()+"平方米");
                Log.e(TAG, "onNext");
            }
        }, parkId);

        broker = (TextView) findViewById(R.id.broker);
        mBaseQuickAdapter = new BrokerAdapter(R.layout.item_broker, null);
        broker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View popupView = ParkDetailActivity.this.getLayoutInflater().inflate(R.layout.popupwindow_broker, null);

                RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.brokers);
                recyclerView.setLayoutManager(new LinearLayoutManager(popupView.getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.addItemDecoration(new DividerItemDecoration(ParkDetailActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.setAdapter(mBaseQuickAdapter);

                HttpData.getInstance().HttpDataGetBrokers(new Observer<List<BrokerDto>>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage()
                                +"\n"+e.getCause()
                                +"\n"+e.getLocalizedMessage()
                                +"\n"+e.getStackTrace());
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

        // 以下为顶部拖动图
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
        });
    }

    // 回弹动画 (使用了属性动画)
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

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadmore() {

    }
}
