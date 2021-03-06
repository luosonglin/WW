package com.winwin.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.snappydb.SnappydbException;
import com.winwin.android.Constant.Data;
import com.winwin.android.UI.Entity.BookListDto;
import com.winwin.android.UI.MineView.MineFragment;
import com.winwin.android.UI.OtherView.IndexFragment;
import com.winwin.android.UI.RecommendView.RecommendFragment;
import com.winwin.android.UI.RoomView.RoomFragment;
import com.winwin.android.UI.SendView.SendParkActivity;
import com.winwin.android.UI.SendView.SendRequireActivity;
import com.winwin.android.Util.DBUtils;
import com.winwin.android.Widget.popmenu.PopMenu;
import com.winwin.android.Widget.popmenu.PopMenuItem;
import com.winwin.android.Widget.popmenu.PopMenuItemListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements IndexFragment.OnFragmentInteractionListener,
        RecommendFragment.OnFragmentInteractionListener,
        RoomFragment.OnFragmentInteractionListener,
        MineFragment.OnFragmentInteractionListener {

    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.line)
    View line;

    @Bind(R.id.tab_index_img)
    ImageView tabIndexImg;
    @Bind(R.id.tab_index_title)
    TextView tabIndexTitle;
    @Bind(R.id.tab_index)
    LinearLayout tabIndex;

    @Bind(R.id.tab_room_img)
    ImageView tabRecommendImg;
    @Bind(R.id.tab_room_title)
    TextView tabRecommendTitle;
    @Bind(R.id.tab_room)
    LinearLayout tabRecommend;

    @Bind(R.id.tab_plus_img)
    ImageView tabPlusImg;
    @Bind(R.id.tab_plus)
    LinearLayout tabPlus;

    @Bind(R.id.tab_message_img)
    ImageView tabRoomImg;
    @Bind(R.id.tab_message_title)
    TextView tabRoomTitle;
    @Bind(R.id.tab_message)
    LinearLayout tabRoom;

    @Bind(R.id.tab_mine_img)
    ImageView tabMineImg;
    @Bind(R.id.tab_mine_title)
    TextView tabMineTitle;
    @Bind(R.id.tab_mine)
    LinearLayout tabMine;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String TAB_INDEX_TAG = "TAB_INDEX_TAG";
    private static final String TAB_RECOMMEND_TAG = "TAB_RECOMMEND_TAG";
    private static final String TAB_ROOM_TAG = "TAB_ROOM_TAG";
    private static final String TAB_MINE_TAG = "TAB_MINE_TAG";

    private PopMenu mPopMenu;

    private FragmentManager mFragmentManager;
    private IndexFragment mIndexFragment;
    private RecommendFragment mRecommendFragment;
    private RoomFragment mRoomFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initStatusBar();

        //默认第一项选中
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mIndexFragment = (IndexFragment) mFragmentManager.findFragmentByTag(TAB_INDEX_TAG);
            mRecommendFragment = (RecommendFragment) mFragmentManager.findFragmentByTag(TAB_RECOMMEND_TAG);
            mRoomFragment = (RoomFragment) mFragmentManager.findFragmentByTag(TAB_ROOM_TAG);
            mMineFragment = (MineFragment) mFragmentManager.findFragmentByTag(TAB_MINE_TAG);
        }
        setTabSelection(tabIndex);

        initUserToken();
    }

    private void initUserToken() {
        if ("".equals(Data.getUserToken())) {
            try {
                Data.setUserToken(DBUtils.get(MainActivity.this, "userToken"));
                Data.setAvatar(DBUtils.get(MainActivity.this, "userAvatar"));
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }
    }

    //版本  大于等于 19  才会生效
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @OnClick({R.id.tab_index, R.id.tab_room, R.id.tab_plus, R.id.tab_message, R.id.tab_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_index:
                setTabSelection(tabIndex);
                Data.setPage(1);
                break;
            case R.id.tab_room:
                setTabSelection(tabRecommend);
                Data.setPage(2);
                break;
            case R.id.tab_plus:
                setTabSelection(tabPlus);
                break;
            case R.id.tab_message:
                setTabSelection(tabRoom);
                Data.setPage(3);
                break;
            case R.id.tab_mine:
                setTabSelection(tabMine);
                Data.setPage(4);
                break;
        }
    }

    private void setTabSelection(View view) {
        int id = view.getId();

        int activeColorRecourse = getResources().getColor(R.color.black);
        int inactiveColorRecourse = getResources().getColor(R.color.grey);

        initSelection(inactiveColorRecourse);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        // 开启一个Fragment事务

        switch (id) {
            case R.id.tab_index:
                // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
                hideFragments(fragmentTransaction);

                tabIndexImg.setImageResource(R.mipmap.tab1_b);
                tabIndexTitle.setTextColor(activeColorRecourse);
                if (mIndexFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    mIndexFragment = new IndexFragment();
                    fragmentTransaction.add(R.id.container, mIndexFragment, TAB_INDEX_TAG);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    fragmentTransaction.show(mIndexFragment);
                }
                break;

            case R.id.tab_room:
                hideFragments(fragmentTransaction);

                tabRecommendImg.setImageResource(R.mipmap.tab4_b);
                tabRecommendTitle.setTextColor(activeColorRecourse);
                if (mRecommendFragment == null) {
                    mRecommendFragment = new RecommendFragment();
                    fragmentTransaction.add(R.id.container, mRecommendFragment, TAB_RECOMMEND_TAG);
                } else {
                    fragmentTransaction.show(mRecommendFragment);
                }
                break;

            case R.id.tab_plus:
                initPopMenu(true);
                if (mPopMenu.isShowing()) return;
                mPopMenu.show();
                break;

            case R.id.tab_message:
                hideFragments(fragmentTransaction);

                tabRoomImg.setImageResource(R.mipmap.tab2_b);
                tabRoomTitle.setTextColor(activeColorRecourse);
                if (mRoomFragment == null) {
                    mRoomFragment = new RoomFragment();
                    fragmentTransaction.add(R.id.container, mRoomFragment, TAB_ROOM_TAG);
                } else {
                    fragmentTransaction.show(mRoomFragment);
                }
                break;

            case R.id.tab_mine:
                hideFragments(fragmentTransaction);

                tabMineImg.setImageResource(R.mipmap.tab5_b);
                tabMineTitle.setTextColor(activeColorRecourse);
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.container, mMineFragment, TAB_MINE_TAG);
                } else {
                    fragmentTransaction.show(mMineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }


    /**
     * 仿微博弹出菜单
     *
     * @param isProfessor 是否是大咖
     */
    private void initPopMenu(boolean isProfessor) {
        if (isProfessor) {
            mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                    .addMenuItem(new PopMenuItem("发布项目", getResources().getDrawable(R.mipmap.tab_btn_project_nor)))
                    .addMenuItem(new PopMenuItem("发布需求", getResources().getDrawable(R.mipmap.tab_btn_demand_nor)))
//                    .addMenuItem(new PopMenuItem("Constant", getResources().getDrawable(R.mipmap.tab_btn_demand_nor)))
                    .setOnItemClickListener(new PopMenuItemListener() {
                        @Override
                        public void onItemClick(PopMenu popMenu, int position) {
                            PopMenuItemClick(position);
                        }
                    })
                    .build();
        } else {
            mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                    .addMenuItem(new PopMenuItem("发布项目", getResources().getDrawable(R.mipmap.tab_btn_project_nor)))
                    .addMenuItem(new PopMenuItem("发布需求", getResources().getDrawable(R.mipmap.tab_btn_demand_nor)))
//                    .addMenuItem(new PopMenuItem("Constant", getResources().getDrawable(R.mipmap.tab_btn_demand_nor)))
                    .setOnItemClickListener(new PopMenuItemListener() {
                        @Override
                        public void onItemClick(PopMenu popMenu, int position) {
                            PopMenuItemClick(position);
                        }
                    })
                    .build();
        }
    }

    /**
     * @param position
     */
    private void PopMenuItemClick(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, SendParkActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(MainActivity.this, SendRequireActivity.class);
                startActivity(intent);
                break;
            case 2:
                // 唤出RecoveryActivity
                BookListDto bookListDto = null;
                Toast.makeText(MainActivity.this, bookListDto.getBookName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 每次选中之前先清除上次的选中状态
     */
    public void initSelection(int inactiveResources) {
        tabIndexImg.setImageResource(R.mipmap.tab1_g);
        tabIndexTitle.setTextColor(inactiveResources);

        tabRecommendImg.setImageResource(R.mipmap.tab4_g);
        tabRecommendTitle.setTextColor(inactiveResources);

        tabPlusImg.setImageResource(R.mipmap.tab3_g);

        tabRoomImg.setImageResource(R.mipmap.tab2_g);
        tabRoomTitle.setTextColor(inactiveResources);

        tabMineImg.setImageResource(R.mipmap.tab5_g);
        tabMineTitle.setTextColor(inactiveResources);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mIndexFragment != null) {
            transaction.hide(mIndexFragment);
        }
        if (mRecommendFragment != null) {
            transaction.hide(mRecommendFragment);
        }
        if (mRoomFragment != null) {
            transaction.hide(mRoomFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    @Override
    protected void onResume() {
        switch (Data.getPage()) {
            case 1:
                setTabSelection(tabIndex);
                break;
            case 2:
                setTabSelection(tabRecommend);
                break;
            case 3:
                setTabSelection(tabRoom);
                break;
            case 4:
                setTabSelection(tabMine);
                break;
            case 5:
                setTabSelection(tabRoom);
        }
        super.onResume();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i(TAG, "onActivityResult"+"requestCode"+requestCode+"\n resultCode="+resultCode);
//        switch (data.getStringExtra("page")) {
//            case "index":
//                setTabSelection(tabIndex);
//                break;
//            case "map":
//                setTabSelection(tabRoom);
//                break;
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
