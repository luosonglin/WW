package com.winwin.app.UI.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.winwin.app.MVP.Presenter.AreaListPresent;
import com.winwin.app.MVP.View.AreaListView;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.AreaAdapter;
import com.winwin.app.UI.Entity.AreaDto;
import com.winwin.app.UI.MineView.MyInfoActivity;
import com.winwin.app.UI.SendView.SendRequireActivity;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchParkActivity extends AppCompatActivity implements AreaListView {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.search_edit)
    EditText searchEdit;
    @Bind(R.id.search_btn)
    TextView searchBtn;

    RecyclerView mRecyclerView;
    private BaseQuickAdapter mQuickAdapter;
    private int PageIndex = 1;
    private AreaListPresent present;

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
        mQuickAdapter = new AreaAdapter(R.layout.item_hot_area, null);
        //设置加载动画
        mQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mQuickAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mQuickAdapter);
        present = new AreaListPresent(this);
        //请求网络数据
        present.LoadData(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void newDatas(List<AreaDto> newsList) {
        //进入显示的初始数据或者下拉刷新显示的数据
        mQuickAdapter.setNewData(newsList);//新增数据
        mQuickAdapter.openLoadMore(10, true);//设置是否可以下拉加载  以及加载条数
    }

    @Override
    public void addDatas(List<AreaDto> addList) {
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

    @OnClick({R.id.back, R.id.search_btn, R.id.phone_sign_in_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search_btn:
                Intent intent;
                if (!searchEdit.getText().toString().trim().equals("")) {
                    intent = new Intent(SearchParkActivity.this, ParkNameListActivity.class);
                    intent.putExtra("parkName", searchEdit.getText().toString().trim());
                    startActivity(intent);
                }
                break;
            case R.id.phone_sign_in_button:
                startActivity(new Intent(SearchParkActivity.this, SendRequireActivity.class));
                break;
        }
    }
}
