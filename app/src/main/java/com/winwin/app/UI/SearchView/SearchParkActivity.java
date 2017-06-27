package com.winwin.app.UI.SearchView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.winwin.app.MVP.Presenter.HotAreaListPresent;
import com.winwin.app.MVP.View.HotAreaListView;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.HotAreaAdapter;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.winwin.app.UI.MineView.MyInfoActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchParkActivity extends AppCompatActivity implements HotAreaListView {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.search_edit)
    EditText searchEdit;

    RecyclerView mRecyclerView;
    private BaseQuickAdapter mQuickAdapter;
    private int PageIndex=1;
    private HotAreaListPresent present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_park);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        //设置RecyclerView的显示模式  当前List模式
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView.setHasFixedSize(true);
        //设置适配器
        mQuickAdapter = new HotAreaAdapter(R.layout.item_hot_area, null);
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6,true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mQuickAdapter);
        present = new HotAreaListPresent(this);
        //请求网络数据
        present.LoadData(false);
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void newDatas(List<HotAreaDto> newsList) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(newsList);//新增数据
        mQuickAdapter.openLoadMore(10,true);//设置是否可以下拉加载  以及加载条数
    }

    @Override
    public void addDatas(List<HotAreaDto> addList) {
        //新增自动加载的的数据
        mQuickAdapter.notifyDataChangedAfterLoadMore(addList, true);
    }

    @Override
    public void showLoadFailMsg() {

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

    }


}
