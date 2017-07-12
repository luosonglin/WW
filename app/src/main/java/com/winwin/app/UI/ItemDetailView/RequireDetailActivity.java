package com.winwin.app.UI.ItemDetailView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.UI.Entity.RequireDto;
import com.winwin.app.Util.DateUtils;
import com.winwin.app.Util.GlideCircleTransform;
import com.winwin.app.Util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RequireDetailActivity extends AppCompatActivity {
    private static final String TAG = RequireDetailActivity.class.getSimpleName();
    @Bind(R.id.img)
    ImageView imageView;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.times)
    TextView times;
    @Bind(R.id.author)
    TextView author;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.a1)
    ImageView a1;
    @Bind(R.id.a2)
    ImageView a2;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pubRequireCompany)
    TextView pubRequireCompany;
    @Bind(R.id.belongIndustryDisPlay)
    TextView belongIndustryDisPlay;
    @Bind(R.id.requireAreaRangDisplay)
    TextView requireAreaRangDisplay;
    @Bind(R.id.requireAreaName)
    TextView requireAreaName;
    @Bind(R.id.otherInfo)
    TextView otherInfo;
    @Bind(R.id.pubReqManDescInfo)
    TextView pubReqManDescInfo;

    private Context context = this;

    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;

    private DisplayMetrics metric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_detail);
        ButterKnife.bind(this);

        toolBar();
        initView();
    }

    private void toolBar() {
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

    private void initView() {

        HttpData.getInstance().HttpDataGetRequireDetail(new Observer<RequireDto>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull RequireDto requireDto) {
                Glide.with(RequireDetailActivity.this)
                        .load(requireDto.getEffectImgs().get(0).getImagePath())
                        .into((ImageView) findViewById(R.id.img));
                name.setText(requireDto.getRequireTitle());
                time.setText(DateUtils.formatDate(requireDto.getCreateTime(), DateUtils.TYPE_04));
                times.setText(requireDto.getBrowseNum() + "次浏览");

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .transform(new GlideCircleTransform(RequireDetailActivity.this))
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(RequireDetailActivity.this)
                        .load(requireDto.getPubRequireHeaderPic())
                        .apply(options)
                        .into((ImageView) findViewById(R.id.avatar));
                author.setText(requireDto.getPubRequireName());
                pubReqManDescInfo.setText(requireDto.getPubReqManDescInfo());
                pubRequireCompany.setText(requireDto.getPubRequireCompany());
                belongIndustryDisPlay.setText(requireDto.getBelongIndustryDisPlay());
                requireAreaRangDisplay.setText(requireDto.getRequireAreaRangDisplay() + "平方米");
                requireAreaName.setText(requireDto.getRequireAreaName());
                otherInfo.setText(requireDto.getOtherInfo());

                if (requireDto.getCollect()) {
                    a1.setTag("collected");
                    a1.setImageResource(R.mipmap.parkdetailactivity_fravorite_red);
                } else {
                    a1.setTag("not_collect");
                    a1.setImageResource(R.mipmap.parkdetailactivity_fravorite);
                }
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

            }
        }, getIntent().getExtras().getInt("id"));

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (a1.getTag().equals("not_collected")) {
                    HttpData.getInstance().HttpDataCollectPark(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            if (httpResult.getStatus().getMsg().equals("ok")) {
                                a1.setTag("collected");
                                a1.setImageResource(R.mipmap.parkdetailactivity_fravorite_red);
                            }
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
                            ToastUtils.show(RequireDetailActivity.this, "收藏成功");
                        }
                    }, getIntent().getExtras().getInt("id"), 1);
                } else {
                    HttpData.getInstance().HttpDataCancelCollectPark(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            if (httpResult.getStatus().getMsg().equals("ok")) {
                                a1.setTag("not_collected");
                                a1.setImageResource(R.mipmap.parkdetailactivity_fravorite);
                            }
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
                            ToastUtils.show(RequireDetailActivity.this, "取消收藏");
                        }
                    }, getIntent().getExtras().getInt("id"), 1);
                }
            }
        });


        // 获取屏幕宽高
        metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

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
}

