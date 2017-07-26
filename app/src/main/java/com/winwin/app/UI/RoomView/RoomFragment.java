package com.winwin.app.UI.RoomView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.R;
import com.winwin.app.UI.Adapter.HotAreaAdapter;
import com.winwin.app.UI.Adapter.MapAdapter;
import com.winwin.app.UI.Adapter.MetaDataAdapter;
import com.winwin.app.UI.Entity.HotAreaDto;
import com.winwin.app.UI.Entity.MetaDataDto;
import com.winwin.app.UI.Entity.ParkDto;
import com.winwin.app.UI.Entity.SelectAppParksVo;
import com.winwin.app.UI.SearchView.SearchParkActivity;
import com.winwin.app.Widget.overlay.PoiOverlay;
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

    private ImageView searchIv;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ImageView mMapIv;

    private static final String TAG = RoomFragment.class.getSimpleName();

    private RelativeLayout districtRlyt;
    private RelativeLayout areaRlyt;
    private RelativeLayout dayRentRlyt;
    private TextView districtTv;
    private TextView areaTv;
    private TextView dayRentTv;
    private RecyclerView mCoreRecyclerView;
    private BaseQuickAdapter mCoreQuickAdapter;
    private SelectAppParksVo selectAppParksVo = new SelectAppParksVo();
    private Integer type = 1;//1产业园；2众创空间；3写字楼
    private List<Integer> districts = new ArrayList<>();
    private List<Integer> areas = new ArrayList<>();
    private List<Integer> dayRents = new ArrayList<>();

    /*
     * map
     */
    private AMap aMap;
    private MapView mapView;
    private LatLonPoint centerpoint = new LatLonPoint(31.109626, 121.290789);
    private ViewPoiOverlay poiOverlay;
    private EditText searchEt;
    private String keyword;
    private RelativeLayout mMapRelativeLayout;
    private RecyclerView mMapRecyclerView;
    private BaseQuickAdapter mMapQuickAdapter;

    private boolean isMap = false;

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
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room, container, false);

        searchIv = (ImageView) view.findViewById(R.id.search_iv);
        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchParkActivity.class));
            }
        });
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

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
        districtTv = (TextView) view.findViewById(R.id.district_tv);
        districtRlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                districtTv.setTextColor(Color.BLACK);
                type = 1;
                initCoreView(type, savedInstanceState);
            }
        });
        areaRlyt = (RelativeLayout) view.findViewById(R.id.area_rlyt);
        areaTv = (TextView) view.findViewById(R.id.area_tv);
        areaRlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaTv.setTextColor(Color.BLACK);
                type = 2;
                initCoreView(type, savedInstanceState);
            }
        });
        dayRentRlyt = (RelativeLayout) view.findViewById(R.id.day_rent_rlyt);
        dayRentTv = (TextView) view.findViewById(R.id.day_rent_tv);
        dayRentRlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayRentTv.setTextColor(Color.BLACK);
                type = 3;
                initCoreView(type, savedInstanceState);
            }
        });

        mMapIv = (ImageView) view.findViewById(R.id.map_image);
        mMapIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMap) {
                    isMap = false;
                    setUpViewPager(viewPager, isMap, savedInstanceState, selectAppParksVo);
                } else {
                    isMap = true;
                    setUpViewPager(viewPager, isMap, savedInstanceState, selectAppParksVo);
                }

            }
        });
        mMapRelativeLayout = (RelativeLayout) view.findViewById(R.id.map_rlyt);
        mapView = (MapView) view.findViewById(R.id.map);
        mMapRecyclerView = (RecyclerView) view.findViewById(R.id.map_rv_list);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMapRecyclerView.setLayoutManager(linearLayoutManager);
        //如果Item高度固定  增加该属性能够提高效率
        mMapRecyclerView.setHasFixedSize(true);
        mMapQuickAdapter = new MapAdapter(R.layout.item_map, null);
        //设置加载动画
        mMapQuickAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mMapQuickAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        mMapRecyclerView.setAdapter(mMapQuickAdapter);

//        selectAppParksVo.setOfficeSuperType(type);
        setUpViewPager(viewPager, isMap, savedInstanceState, selectAppParksVo);

        return view;
    }

    private void setUpViewPager(final ViewPager viewPager, boolean isMap, Bundle savedInstanceState, SelectAppParksVo selectAppParksVo) {
        Log.e(TAG, selectAppParksVo.getAreaId()
                + "\n" + selectAppParksVo.getAreaRangeId()
                + "\n" + selectAppParksVo.getDayRentId()
                + "\n" + selectAppParksVo.getOfficeSuperType());
        if (isMap) {
            mMapRelativeLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            mapView.onCreate(savedInstanceState);// 此方法必须重写
            initMap();
        } else {
            mMapRelativeLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);
        }

        final IndexChildAdapter mIndexChildAdapter = new IndexChildAdapter(getChildFragmentManager());

        mIndexChildAdapter.addFragment(new RoomTab1Fragment().newInstance(selectAppParksVo), "产业园");
        mIndexChildAdapter.addFragment(new RoomTab2Fragment().newInstance(selectAppParksVo), "众创空间");
        mIndexChildAdapter.addFragment(new RoomTab3Fragment().newInstance(selectAppParksVo), "写字楼");

        viewPager.setOffscreenPageLimit(3);//缓存view 的个数
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

    public interface OnFragmentInteractionListener {
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
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragments.get(position);
            Log.i(TAG, "getItem:position=" + position
                    + ",fragment:" + fragment.getClass().getName()
                    + ",fragment.tag=" + fragment.getTag());
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

    private void initCoreView(final int type, final Bundle savedInstanceState) {
        if (mCoreRecyclerView.getVisibility() == View.GONE) {
            if (type == 1) {
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
                                + "\n" + e.getStackTrace());
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
                        selectAppParksVo.setAreaId(districts.get(position));
                        setUpViewPager(viewPager, isMap, savedInstanceState, selectAppParksVo);
                        mCoreRecyclerView.setVisibility(View.GONE);
                        districtTv.setTextColor(Color.LTGRAY);
                    }
                });
            } else {
                mCoreRecyclerView.setVisibility(View.VISIBLE);
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

                        for (MetaDataDto i : metaDataDtos) {
                            if (type == 2) {
                                areas.add(i.getId());
                            } else if (type == 3) {
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
                                + "\n" + e.getStackTrace());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                }, type);
                mCoreQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (type == 2) {
                            selectAppParksVo.setAreaRangeId(areas.get(position));
                            areaTv.setTextColor(Color.LTGRAY);
                        } else if (type == 3) {
                            selectAppParksVo.setDayRentId(dayRents.get(position));
                            dayRentTv.setTextColor(Color.LTGRAY);
                        }
                        setUpViewPager(viewPager, isMap, savedInstanceState, selectAppParksVo);
                        mCoreRecyclerView.setVisibility(View.GONE);
                    }
                });
            }
        } else {
            mCoreRecyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * map
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.getUiSettings().setLogoBottomMargin(-50);//隐藏logo
            aMap.getUiSettings().setZoomControlsEnabled(false);//内置的缩放控制键，显示在地图的右下角。默认情况下是开启true的
        }

        //刚打开map的第一屏
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(convertToLatLng(centerpoint), 13));

        selectAppParksVo.setOfficeSuperType(1);
        HttpData.getInstance().HttpDataGetParksByConditions(new Observer<List<ParkDto>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<ParkDto> parkDtoPageDto) {
                mMapQuickAdapter.addData(parkDtoPageDto);
                for (ParkDto i : parkDtoPageDto) {
                    aMap.addMarker(new MarkerOptions().position(new LatLng(i.getLatitude(), i.getLongitude()))
                            .title(i.getName())
                            .autoOverturnInfoWindow(true)
                            .snippet(i.getDistanceMetroDesc())
//                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.haha)));
//                            .icon(BitmapDescriptorFactory.fromBitmap(returnBitMap("http://content.52pk.com/files/100623/2230_102437_1_lit.jpg"))));
                            .icon(BitmapDescriptorFactory.fromPath(i.getHomeImage())));
                }
                Log.e(TAG, "onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                //设置页面为加载错误
                Log.e(TAG, "onError: " + e.getMessage()
                        + "\n" + e.getCause()
                        + "\n" + e.getLocalizedMessage()
                        + "\n" + e.getStackTrace());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }
        }, selectAppParksVo);
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


    public class ViewPoiOverlay extends PoiOverlay {

        public ViewPoiOverlay(AMap aMap, List<PoiItem> list) {
            super(aMap, list);
        }

        @Override
        protected BitmapDescriptor getBitmapDescriptor(int index) {
            View view = null;
            view = View.inflate(getActivity(), R.layout.custom_view, null);
            TextView textView = ((TextView) view.findViewById(R.id.title));
            textView.setText(getTitle(index));
            return  BitmapDescriptorFactory.fromView(view);
        }
    }
}
