package com.winwin.app.UI.RoomView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.MetaDataAdapter;
import com.winwin.app.UI.Entity.MetaDataDto;
import com.winwin.app.UI.MineView.MyCollectTabFragment;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RoomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ImageView mMapIv;

    private static final String TAG = RoomFragment.class.getSimpleName();

    private RelativeLayout districtRlyt;
    private RelativeLayout areaRlyt;
    private RelativeLayout dayRentRlyt;
    private RecyclerView mCoreRecyclerView;
    private BaseQuickAdapter mCoreQuickAdapter;

    public RoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        mMapIv = (ImageView) view.findViewById(R.id.map_image);
        mMapIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });

        setUpViewPager(viewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tabLayout.setTabMode(TabLayout.SCROLL_AXIS_HORIZONTAL);//tablayout设置可以滑动
        }
        tabLayout.setupWithViewPager(viewPager);

        mCoreRecyclerView = (RecyclerView) view.findViewById(R.id.core_rv_list);
        //设置RecyclerView的显示模式  当前List模式
        mCoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果Item高度固定  增加该属性能够提高效率
        mCoreRecyclerView.setHasFixedSize(true);
        districtRlyt = (RelativeLayout) view.findViewById(R.id.district_rlyt);
        districtRlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCoreView(1);
            }
        });
        areaRlyt = (RelativeLayout) view.findViewById(R.id.area_rlyt);
        areaRlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCoreView(3);
            }
        });
        dayRentRlyt = (RelativeLayout) view.findViewById(R.id.day_rent_rlyt);
        dayRentRlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCoreView(2);
            }
        });

        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {

        IndexChildAdapter mIndexChildAdapter = new IndexChildAdapter(getChildFragmentManager());

        mIndexChildAdapter.addFragment(new MyCollectTabFragment(), "产业园");//推荐
        mIndexChildAdapter.addFragment(new MyCollectTabFragment(), "众创空间");//推荐
        mIndexChildAdapter.addFragment(new MyCollectTabFragment(), "写字楼");//推荐

        viewPager.setOffscreenPageLimit(4);//缓存view 的个数
        viewPager.setAdapter(mIndexChildAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class IndexChildAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public IndexChildAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
//            if("待确认".equals(title)) {
//                TrackerUtils.sendUvLog(getActivity(), "3");
//            } else if ("已确认".equals(title)) {
//                TrackerUtils.sendUvLog(getActivity(), "4");
//            } else if ("配送中".equals(title)) {
//                TrackerUtils.sendUvLog(getActivity(), "5");
//            } else if ("所有".equals(title)) {
//                TrackerUtils.sendUvLog(getActivity(), "6");
//            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


    private void initCoreView(int type) {


        if (mCoreRecyclerView.getVisibility() == View.GONE) {
            mCoreRecyclerView.setVisibility(View.VISIBLE);
            //get data
            //设置适配器
            mCoreQuickAdapter = new MetaDataAdapter(R.layout.item_meta_data, null);
            //设置加载动画
            mCoreQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            //设置是否自动加载以及加载个数
            mCoreQuickAdapter.openLoadMore(6, true);
            //将适配器添加到RecyclerView
            mCoreRecyclerView.setAdapter(mCoreQuickAdapter);
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
        } else {
            mCoreRecyclerView.setVisibility(View.GONE);
        }

    }

}
