package com.winwin.app.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winwin.app.R;
import com.winwin.app.UI.Adapter.LatestRecommendationAdapter;
import com.winwin.app.UI.Adapter.SelectedParkAdapter;
import com.winwin.app.widget.GlideImageLoader;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IndexFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IndexFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndexFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Banner mBanner;
    private RecyclerView mRecyclerView;
    private List<String> bannerImages = new ArrayList<>();
    private SelectedParkAdapter mSelectedParkAdapter;
    private RecyclerView mRecyclerView2;
    private LatestRecommendationAdapter mLatestRecommendationAdapter;

    private OnFragmentInteractionListener mListener;

    public IndexFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndexFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
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
        View view = inflater.inflate(R.layout.fragment_index, container, false);

        mBanner = (Banner) view.findViewById(R.id.banner);
        //test data
        bannerImages.add("http://wx1.sinaimg.cn/mw1024/a601622bgy1fdl511w7f5j20dv0aqgm1.jpg");
        bannerImages.add("http://wx1.sinaimg.cn/mw1024/a601622bly1fbdk9hstbmj20qo0qodja.jpg");
        bannerImages.add("http://ww4.sinaimg.cn/mw1024/a601622bgw1f8xr5r8n2gj20ku0go0tu.jpg");
        bannerImages.add("http://ww4.sinaimg.cn/mw1024/a601622bgw1f8ro576rmkj20qy0zktb4.jpg");
        mBanner.setImages(bannerImages != null ? bannerImages : null)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
//                .setBannerTitles(bannerTitles)
                .setBannerAnimation(Transformer.Tablet)
                .setImageLoader(new GlideImageLoader()).start();
        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.e("--", "点击：" + position + "");
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView.setHasFixedSize(true);
        // test data
        mSelectedParkAdapter = new SelectedParkAdapter(R.layout.item_index_fragment_selected_park, bannerImages);
        //设置加载动画
        mSelectedParkAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mSelectedParkAdapter.openLoadMore(6,true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mSelectedParkAdapter);

        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.rv_list2);
        //设置布局管理器
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView2.setHasFixedSize(true);
        // test data
        mLatestRecommendationAdapter = new LatestRecommendationAdapter(R.layout.item_index_fragment_latest_recommendation, bannerImages);
        //设置加载动画
        mLatestRecommendationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_CUSTOM);
        //设置是否自动加载以及加载个数
        mLatestRecommendationAdapter.openLoadMore(6,true);
        //将适配器添加到RecyclerView
        mRecyclerView2.setAdapter(mLatestRecommendationAdapter);

        return view;
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
}
