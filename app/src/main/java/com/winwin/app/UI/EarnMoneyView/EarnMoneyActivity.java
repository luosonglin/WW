package com.winwin.app.UI.EarnMoneyView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.winwin.app.Constant.Constant;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Presenter.RequireListPresent;
import com.winwin.app.MVP.View.RequireListView;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.HotAreaAdapter;
import com.winwin.app.UI.Adapter.MetaDataAdapter;
import com.winwin.app.UI.Adapter.RequireAdapter;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.winwin.app.UI.Entity.MetaDataDto;
import com.winwin.app.UI.Entity.RequireDto;
import com.winwin.app.UI.Entity.SelectRequirementVo;
import com.winwin.app.Util.ToastUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.container.DefaultHeader;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 挣钱快车页
 */
public class EarnMoneyActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SpringView.OnFreshListener, RequireListView {

    private static final String TAG = EarnMoneyActivity.class.getSimpleName();

    @Bind(R.id.district_rlyt)
    RelativeLayout districtRlyt;
    @Bind(R.id.line1)
    View line1;
    @Bind(R.id.area_rlyt)
    RelativeLayout areaRlyt;
    @Bind(R.id.line2)
    View line2;
    @Bind(R.id.day_rent_rlyt)
    RelativeLayout dayRentRlyt;
    @Bind(R.id.district_tv)
    TextView districtTv;
    @Bind(R.id.area_tv)
    TextView areaTv;
    @Bind(R.id.day_rent_tv)
    TextView dayRentTv;
    @Bind(R.id.followDesc)
    TextView followDesc;
    @Bind(R.id.springview)
    SpringView springview;
    @Bind(R.id.core_rv_list)
    RecyclerView coreRvList;
    @Bind(R.id.publicDateDesc)
    TextView publicDateDesc;
    @Bind(R.id.progress)
    ProgressActivity progress;

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private BaseQuickAdapter mQuickAdapter;
    private int PageIndex = 1;
    private SpringView springView;
    private RequireListPresent present;

    private RecyclerView mCoreRecyclerView;
    private BaseQuickAdapter mCoreQuickAdapter;
    private SelectRequirementVo selectRequirementVo = new SelectRequirementVo();

    private List<Integer> districts = new ArrayList<>();
    private List<Integer> areas = new ArrayList<>();
    private List<Integer> dayRents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_money);
        ButterKnife.bind(this);
        toolBar();
        initView();
        initListener();
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        springView = (SpringView) findViewById(R.id.springview);
        //设置下拉刷新监听
        springView.setListener(this);
        //设置下拉刷新样式
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new DefaultHeader(this));

        //设置RecyclerView的显示模式  当前List模式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView.setHasFixedSize(true);
        //设置页面为加载中..
        progress.showLoading();
        //设置适配器
        mQuickAdapter = new RequireAdapter(R.layout.item_require, null);
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mQuickAdapter);
//        present = new BookListPresent(this);
        present = new RequireListPresent(this);
        //请求网络数据
//        present.LoadData("1",PageIndex,false);

        present.LoadData(false, selectRequirementVo);

        mCoreRecyclerView = (RecyclerView) findViewById(R.id.core_rv_list);
        //设置RecyclerView的显示模式  当前List模式
        mCoreRecyclerView.setLayoutManager(new LinearLayoutManager(EarnMoneyActivity.this));
        //如果Item高度固定  增加该属性能够提高效率
        mCoreRecyclerView.setHasFixedSize(true);

    }

    private void initListener() {
        //设置自动加载监听
        mQuickAdapter.setOnLoadMoreListener(this);
    }

    //自动加载
    @Override
    public void onLoadMoreRequested() {
        PageIndex++;
//        present.LoadData("1",PageIndex,true);
        present.LoadData(true, selectRequirementVo);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        PageIndex = 1;
//        present.LoadData("1",PageIndex,false);
        present.LoadData(false, selectRequirementVo);
    }

    //上啦加载  mRecyclerView内部集成的自动加载  上啦加载用不上   在其他View使用
    @Override
    public void onLoadmore() {

    }

    /*
    * MVP模式的相关状态
    *
    * */
    @Override
    public void showProgress() {
        progress.showLoading();
    }

    @Override
    public void hideProgress() {
        progress.showContent();
    }

    @Override
    public void newDatas(List<RequireDto> newsList) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(newsList);//新增数据
        mQuickAdapter.openLoadMore(10, true);//设置是否可以下拉加载  以及加载条数
        springView.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void addDatas(List<RequireDto> addList) {
        //新增自动加载的的数据
        mQuickAdapter.notifyDataChangedAfterLoadMore(addList, true);
    }

    @Override
    public void showLoadFailMsg() {
        //设置加载错误页显示
        progress.showError(getResources().getDrawable(R.mipmap.monkey_cry), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageIndex = 1;
//                present.LoadData("1",PageIndex,false);
                present.LoadData(false, selectRequirementVo);
            }
        });
    }

    @Override
    public void showLoadCompleteAllData() {
        //所有数据加载完成后显示
        mQuickAdapter.notifyDataChangedAfterLoadMore(false);
        View view = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mQuickAdapter.addFooterView(view);
    }

    @Override
    public void showNoData() {
        //设置无数据显示页面
        progress.showEmpty(getResources().getDrawable(R.mipmap.monkey_nodata), Constant.EMPTY_TITLE, Constant.EMPTY_CONTEXT);
        ToastUtils.show(EarnMoneyActivity.this, "暂无数据");
    }

    @OnClick({R.id.publicDateDesc, R.id.followDesc, R.id.district_rlyt, R.id.area_rlyt, R.id.day_rent_rlyt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.publicDateDesc:
                if (publicDateDesc.getText().toString().equals("发布时间 ↓")) {
                    publicDateDesc.setText("发布时间 ↑");
                    selectRequirementVo.setPublicDateDesc(false);
                    present.LoadData(false, selectRequirementVo);
                } else if (publicDateDesc.getText().toString().equals("发布时间 ↑")) {
                    publicDateDesc.setText("发布时间 ↓");
                    selectRequirementVo.setPublicDateDesc(true);
                    present.LoadData(false, selectRequirementVo);
                }
                break;
            case R.id.followDesc:
                if (followDesc.getText().toString().equals("关注度 ↓")) {
                    followDesc.setText("关注度 ↑");
                    selectRequirementVo.setFollowDesc(false);
                    present.LoadData(false, selectRequirementVo);
                } else if (followDesc.getText().toString().equals("关注度 ↑")) {
                    followDesc.setText("关注度 ↓");
                    selectRequirementVo.setFollowDesc(true);
                    present.LoadData(false, selectRequirementVo);
                }
                break;
            case R.id.district_rlyt:
                districtTv.setTextColor(Color.BLACK);
                initCoreVIew(3);
                break;
            case R.id.area_rlyt:
                areaTv.setTextColor(Color.BLACK);
                initCoreVIew(1);
                break;
            case R.id.day_rent_rlyt:
                dayRentTv.setTextColor(Color.BLACK);
                initCoreVIew(2);
                break;
        }
    }

    /**
     * @param type 1行业；2需求面积；3区域
     */
    private void initCoreVIew(final int type) {
        if (mCoreRecyclerView.getVisibility() == View.GONE) {
            if (type == 3) {
                mCoreRecyclerView.setVisibility(View.VISIBLE);
                //设置适配器
                mCoreQuickAdapter = new HotAreaAdapter(R.layout.item_meta_data, null);
                //设置加载动画
                mCoreQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                //设置是否自动加载以及加载个数
                mCoreQuickAdapter.openLoadMore(6, true);
                //将适配器添加到RecyclerView
                mCoreRecyclerView.setAdapter(mCoreQuickAdapter);
                HttpData.getInstance().HttpDataGetShanghaiHotAreas(new Observer<List<HotAreaDto>>() {
                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage()
                                + "\n" + e.getCause()
                                + "\n" + e.getLocalizedMessage()
                                + "\n" + Arrays.toString(e.getStackTrace()));
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(List<HotAreaDto> data) {
                        mCoreQuickAdapter.addData(data);
                        for (HotAreaDto i : data) {
                            districts.add(i.getId());
                        }
                        Log.e(TAG, "onNext");
                    }
                });
                mCoreQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        selectRequirementVo.setAreaId(districts.get(position));
//                        setUpViewPager(viewPager, isMap, savedInstanceState, selectAppParksVo);
                        mCoreRecyclerView.setVisibility(View.GONE);
                        districtTv.setTextColor(Color.GRAY);
//                        present.LoadData(true, selectRequirementVo);
                        onRefresh();
                    }
                });
            } else {
                mCoreRecyclerView = (RecyclerView) findViewById(R.id.core_rv_list);
                //设置RecyclerView的显示模式  当前List模式
                mCoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                //如果Item高度固定  增加该属性能够提高效率
                mCoreRecyclerView.setHasFixedSize(true);
                //设置适配器
                mCoreQuickAdapter = new MetaDataAdapter(R.layout.item_meta_data, null);
                //设置加载动画
                mCoreQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                //设置是否自动加载以及加载个数
                mCoreQuickAdapter.openLoadMore(6, true);
                //将适配器添加到RecyclerView
                mCoreRecyclerView.setAdapter(mCoreQuickAdapter);
                if (mCoreRecyclerView.getVisibility() == View.GONE) {
                    mCoreRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    mCoreRecyclerView.setVisibility(View.GONE);
                }
                //get data
                HttpData.getInstance().HttpDataGetMetaDataList(new Observer<List<MetaDataDto>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<MetaDataDto> metaDataDtos) {
                        mCoreQuickAdapter.addData(metaDataDtos);
                        for (MetaDataDto i : metaDataDtos) {
                            if (type == 1) {
                                areas.add(i.getId());
                            } else if (type == 2) {
                                dayRents.add(i.getId());
                            }
                        }

                        Log.e(TAG, "onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage()
                                + "\n" + e.getCause()
                                + "\n" + e.getLocalizedMessage()
                                + "\n" + Arrays.toString(e.getStackTrace()));
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                }, type);
                mCoreQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (type == 1) {
                            selectRequirementVo.setBelongNetId(areas.get(position));
                            areaTv.setTextColor(Color.GRAY);
                        } else if (type == 2) {
                            selectRequirementVo.setRequireAreaRangId(dayRents.get(position));
                            dayRentTv.setTextColor(Color.GRAY);
                        }
                        mCoreRecyclerView.setVisibility(View.GONE);

//                        present.LoadData(true, selectRequirementVo);
                        onRefresh();
                    }
                });
            }
        } else {
            mCoreRecyclerView.setVisibility(View.GONE);
        }
    }

}

