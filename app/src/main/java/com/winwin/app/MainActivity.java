package com.winwin.app;

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

import com.winwin.app.UI.ListvView.ListvViewActivity;
import com.winwin.app.UI.entity.BookListDto;
import com.winwin.app.view.BlankFragment;
import com.winwin.app.view.IndexFragment;
import com.winwin.app.widget.popmenu.PopMenu;
import com.winwin.app.widget.popmenu.PopMenuItem;
import com.winwin.app.widget.popmenu.PopMenuItemListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements IndexFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener{

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
    ImageView tabRoomImg;
    @Bind(R.id.tab_room_title)
    TextView tabRoomTitle;
    @Bind(R.id.tab_room)
    LinearLayout tabRoom;

    @Bind(R.id.tab_plus_img)
    ImageView tabPlusImg;
    @Bind(R.id.tab_plus)
    LinearLayout tabPlus;

    @Bind(R.id.tab_customer_img)
    ImageView tabCustomerImg;
    @Bind(R.id.tab_customer_title)
    TextView tabCustomerTitle;
    @Bind(R.id.tab_customer)
    LinearLayout tabCustomer;

    @Bind(R.id.tab_mine_img)
    ImageView tabMineImg;
    @Bind(R.id.tab_mine_title)
    TextView tabMineTitle;
    @Bind(R.id.tab_mine)
    LinearLayout tabMine;

    private static final String TAB_INDEX_TAG = "TAB_INDEX_TAG";
    private static final String TAB_ROOM_TAG = "TAB_ROOM_TAG";
    private static final String TAB_CUSTOMER_TAG = "TAB_CUSTOMER_TAG";
    private static final String TAB_MINE_TAG = "TAB_MINE_TAG";

    private PopMenu mPopMenu;

    private FragmentManager mFragmentManager;
    private IndexFragment mIndexFragment;
    private BlankFragment mMeetingFragment;
    private BlankFragment mcustomerFragment;
    private BlankFragment mMineFragment;

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
            mMeetingFragment = (BlankFragment) mFragmentManager.findFragmentByTag(TAB_ROOM_TAG);
            mcustomerFragment = (BlankFragment) mFragmentManager.findFragmentByTag(TAB_CUSTOMER_TAG);
            mMineFragment = (BlankFragment) mFragmentManager.findFragmentByTag(TAB_MINE_TAG);
        }
        setTabSelection(tabIndex);
    }

    //版本  大于等于 19  才会生效
    private void initStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @OnClick({R.id.tab_index, R.id.tab_room, R.id.tab_plus, R.id.tab_customer, R.id.tab_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_index:
                setTabSelection(tabIndex);
                break;
            case R.id.tab_room:
                setTabSelection(tabRoom);
                break;
            case R.id.tab_plus:
                setTabSelection(tabPlus);
                break;
            case R.id.tab_customer:
                setTabSelection(tabCustomer);
                break;
            case R.id.tab_mine:
                setTabSelection(tabMine);
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

                tabRoomImg.setImageResource(R.mipmap.tab2_b);
                tabRoomTitle.setTextColor(activeColorRecourse);
                if (mMeetingFragment == null) {
                    mMeetingFragment = new BlankFragment();
                    fragmentTransaction.add(R.id.container, mMeetingFragment, TAB_ROOM_TAG);
                } else {
                    fragmentTransaction.show(mMeetingFragment);
                }
                break;

            case R.id.tab_plus:
                initPopMenu(true);
                if (mPopMenu.isShowing()) return;
                mPopMenu.show();
                break;

            case R.id.tab_customer:
                hideFragments(fragmentTransaction);

                tabCustomerImg.setImageResource(R.mipmap.tab4_b);
                tabCustomerTitle.setTextColor(activeColorRecourse);
                if (mcustomerFragment == null) {
                    mcustomerFragment = new BlankFragment();
                    fragmentTransaction.add(R.id.container, mcustomerFragment, TAB_CUSTOMER_TAG);
                } else {
                    fragmentTransaction.show(mcustomerFragment);
                }
                break;

            case R.id.tab_mine:
                hideFragments(fragmentTransaction);

                tabMineImg.setImageResource(R.mipmap.tab5_b);
                tabMineTitle.setTextColor(activeColorRecourse);
                if (mMineFragment == null) {
                    mMineFragment = new BlankFragment();
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
     * @param isProfessor   是否是大咖
     */
    private void initPopMenu(boolean isProfessor){
        if (isProfessor) {
            mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                    .addMenuItem(new PopMenuItem("大会签到", getResources().getDrawable(R.mipmap.tabbar_compose_idea)))
                    .addMenuItem(new PopMenuItem("交换名片", getResources().getDrawable(R.mipmap.tabbar_compose_photo)))
                    .addMenuItem(new PopMenuItem("发帖子", getResources().getDrawable(R.mipmap.tabbar_compose_headlines)))
                    .addMenuItem(new PopMenuItem("发病例", getResources().getDrawable(R.mipmap.tabbar_compose_lbs)))
                    .addMenuItem(new PopMenuItem("我的钱包", getResources().getDrawable(R.mipmap.tabbar_compose_review)))
                    .addMenuItem(new PopMenuItem("发起直播", getResources().getDrawable(R.mipmap.tabbar_compose_more)))
                    .setOnItemClickListener(new PopMenuItemListener() {
                        @Override
                        public void onItemClick(PopMenu popMenu, int position) {
                            PopMenuItemClick(position);
                        }
                    })
                    .build();
        } else {
            mPopMenu = new PopMenu.Builder().attachToActivity(MainActivity.this)
                    .addMenuItem(new PopMenuItem("大会签到", getResources().getDrawable(R.mipmap.tabbar_compose_idea)))
                    .addMenuItem(new PopMenuItem("交换名片", getResources().getDrawable(R.mipmap.tabbar_compose_photo)))
                    .addMenuItem(new PopMenuItem("发帖子", getResources().getDrawable(R.mipmap.tabbar_compose_headlines)))
                    .addMenuItem(new PopMenuItem("发病例", getResources().getDrawable(R.mipmap.tabbar_compose_lbs)))
                    .addMenuItem(new PopMenuItem("我的钱包", getResources().getDrawable(R.mipmap.tabbar_compose_review)))
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
     *
     * @param position
     */
    private void PopMenuItemClick(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(MainActivity.this, ListvViewActivity.class);
                startActivity(intent);
                break;
            case 1:
                // 唤出RecoveryActivity
                BookListDto bookListDto=null;
                Toast.makeText(MainActivity.this, bookListDto.getBookName(), Toast.LENGTH_SHORT).show();

//                String confirmNumber = "";
//                final String TAG_CARD = "002";
//                try {
//                    if (!DBUtils.isSet(MainActivity.this, "tokenId") && !DBUtils.isSet(MainActivity.this, "openId")) {
//                        Intent loginIntent = new Intent(MainActivity.this, SignUpByPhoneCodeActivity.class);
//                        int requestCode = 2;
//                        startActivityForResult(loginIntent, requestCode);
//                        return;
//                    }
//                    confirmNumber = DBUtils.get(MainActivity.this, "confirmNumber");
//                } catch (SnappydbException e) {
//                    e.printStackTrace();
//                }
//                Intent intent2 = new Intent(MainActivity.this, MyBusinessCardActivity.class);
//                Bundle bundle2 = new Bundle();
//                bundle2.putString("confirmNumber", confirmNumber);
//                bundle2.putString("card_type", TAG_CARD);
//                intent2.putExtras(bundle2);
//                startActivity(intent2);
                break;
            case 2:
//                startActivity(new Intent(MainActivity.this, SendBlogActivity.class));
                break;
            case 3:
//                startActivity(new Intent(MainActivity.this, SendAidsCaseActivity.class));
                break;
            case 4:
//                startActivity(new Intent(MainActivity.this, WalletActivity.class));
                break;
            case 5:
//                startActivity(new Intent(MainActivity.this, LiveBuildActivity.class));
                break;
        }
    }

    /**
     * 每次选中之前先清除上次的选中状态
     */
    public void initSelection(int inactiveResources) {
        tabIndexImg.setImageResource(R.mipmap.tab1_g);
        tabIndexTitle.setTextColor(inactiveResources);

        tabRoomImg.setImageResource(R.mipmap.tab2_g);
        tabRoomTitle.setTextColor(inactiveResources);

        tabPlusImg.setImageResource(R.mipmap.tab3_g);

        tabCustomerImg.setImageResource(R.mipmap.tab4_g);
        tabCustomerTitle.setTextColor(inactiveResources);

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
        if (mMeetingFragment != null) {
            transaction.hide(mMeetingFragment);
        }
        if (mcustomerFragment != null) {
            transaction.hide(mcustomerFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    @Override
    protected void onResume() {
//        int id = getIntent().getIntExtra("ReturnToMiniSupplierActivity", 0);
//        if (id == 1) {
//            setTabSelection(tabCustomer);
//        } else if (id == 2) {
//            setTabSelection(tabIndex);
//        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
