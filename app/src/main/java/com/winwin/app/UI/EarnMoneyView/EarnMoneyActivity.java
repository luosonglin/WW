package com.winwin.app.UI.EarnMoneyView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.winwin.app.Constant.Constant;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MVP.Presenter.BannerListPresent;
import com.winwin.app.MVP.View.BannerListView;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.MetaDataAdapter;
import com.winwin.app.UI.Adapter.MyCollectDemandAdapter;
import com.winwin.app.UI.Entity.BannerDto;
import com.winwin.app.UI.Entity.MetaDataDto;
import com.winwin.app.UI.ItemDetailView.DemandDetailActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.container.DefaultHeader;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

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
public class EarnMoneyActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SpringView.OnFreshListener, BannerListView {

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

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private ProgressActivity progress;
    private BaseQuickAdapter mQuickAdapter;
    private int PageIndex = 1;
    private SpringView springView;
    private BannerListPresent present;

    private RecyclerView mCoreRecyclerView;
    private BaseQuickAdapter mCoreQuickAdapter;

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

        progress = (ProgressActivity) findViewById(R.id.progress);
        //设置RecyclerView的显示模式  当前List模式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView.setHasFixedSize(true);
        //设置页面为加载中..
        progress.showLoading();
        //设置适配器
        mQuickAdapter = new MyCollectDemandAdapter(R.layout.item_my_collect_demand, null);
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mQuickAdapter);
//        present = new BookListPresent(this);
        present = new BannerListPresent(this);
        //请求网络数据
//        present.LoadData("1",PageIndex,false);
        present.LoadData(false);
    }

    private void initListener() {
        //设置自动加载监听
        mQuickAdapter.setOnLoadMoreListener(this);

        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(EarnMoneyActivity.this, DemandDetailActivity.class));
                Toast.makeText(EarnMoneyActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mQuickAdapter.setOnRecyclerViewItemLongClickListener(new BaseQuickAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                Toast.makeText(EarnMoneyActivity.this, "长按了" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    //自动加载
    @Override
    public void onLoadMoreRequested() {
        PageIndex++;
//        present.LoadData("1",PageIndex,true);
        present.LoadData(true);
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        PageIndex = 1;
//        present.LoadData("1",PageIndex,false);
        present.LoadData(false);
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
    public void newDatas(List<BannerDto.BannersBean> newsList) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(newsList);//新增数据
        mQuickAdapter.openLoadMore(10, true);//设置是否可以下拉加载  以及加载条数
        springView.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void addDatas(List<BannerDto.BannersBean> addList) {
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
                present.LoadData(false);
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
    }

    @OnClick({R.id.district_rlyt, R.id.area_rlyt, R.id.day_rent_rlyt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.district_rlyt:
                initCoreVIew(1);
                break;
            case R.id.area_rlyt:
                initCoreVIew(2);
                break;
            case R.id.day_rent_rlyt:
                initCoreVIew(3);
                break;
        }
    }

    private void initCoreVIew(int type) {

        mCoreRecyclerView = (RecyclerView) findViewById(R.id.core_rv_list);
        //设置RecyclerView的显示模式  当前List模式
        mCoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果Item高度固定  增加该属性能够提高效率
        mCoreRecyclerView.setHasFixedSize(true);
//        mCoreRecyclerView.addItemDecoration(new DividerItemDecoration(EarnMoneyActivity.this, DividerItemDecoration.VERTICAL));

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
                Log.e(TAG, "onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: "+e.getMessage()
                        +"\n"+e.getCause()
                        +"\n"+e.getLocalizedMessage()
                        +"\n"+e.getStackTrace());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        }, type);
    }
}

