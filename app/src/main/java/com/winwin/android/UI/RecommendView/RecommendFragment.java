package com.winwin.android.UI.RecommendView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winwin.android.Constant.Data;
import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.MainActivity;
import com.winwin.android.R;
import com.winwin.android.UI.Adapter.LatestRecommendationAdapter;
import com.winwin.android.UI.Adapter.SelectedParkAdapter;
import com.winwin.android.UI.EarnMoneyView.EarnMoneyActivity;
import com.winwin.android.UI.Entity.HttpResult;
import com.winwin.android.UI.Entity.IndexBannerDto;
import com.winwin.android.UI.Entity.IndexRecommandParkDto;
import com.winwin.android.UI.Entity.IndexStaticDateDto;
import com.winwin.android.UI.ImView.ConversationActivity;
import com.winwin.android.UI.ItemDetailView.ParkDetailActivity;
import com.winwin.android.UI.MineView.MyCreditActivity;
import com.winwin.android.UI.SearchView.SearchParkActivity;
import com.winwin.android.Widget.GlideImageLoader;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

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
 * 发现Fragment
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.location)
    TextView location;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.conversation_iv)
    ImageView conversation;
    @Bind(R.id.map_llyt)
    LinearLayout mapLlyt;
    @Bind(R.id.earn_llyt)
    LinearLayout earnLlyt;
    @Bind(R.id.my_llyt)
    LinearLayout myLlyt;
    @Bind(R.id.brokerage_llyt)
    LinearLayout brokerageLlyt;
    @Bind(R.id.rv_list)
    RecyclerView rvList;
    @Bind(R.id.rv_list2)
    RecyclerView rvList2;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = RecommendFragment.class.getSimpleName();
    private Banner mBanner;
    private TextView mCurrentProNum, mNewBeraNum, mCommendSuccessNum;
    private RecyclerView mRecyclerView;
    private List<String> bannerImages = new ArrayList<>();
    private List<IndexBannerDto> parkBannerImages = new ArrayList<>();
    private SelectedParkAdapter mSelectedParkAdapter;
    private RecyclerView mRecyclerView2;
    private LatestRecommendationAdapter mLatestRecommendationAdapter;
    private static ImageView msgUnread;
    private OnFragmentInteractionListener mListener;

    public RecommendFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
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
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);

        msgUnread = (ImageView) view.findViewById(R.id.tabUnread);

        mBanner = (Banner) view.findViewById(R.id.banner);
        HttpData.getInstance().HttpDataGetBanners(new Observer<HttpResult<List<IndexBannerDto>>>() {
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
            public void onNext(final HttpResult<List<IndexBannerDto>> indexBannerDto) {

                for (IndexBannerDto i : indexBannerDto.getData()) {
                    bannerImages.add(i.getBannerPath());
                }
                mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Log.e("--", "点击：" + position + "");
                        Intent intent;
                        switch (indexBannerDto.getData().get(position - 1).getBannerType()) {
                            case 0:     //0：表示无动作
                                break;
                            case 1:     //1：跳转页面
                                intent = new Intent(getActivity(), IndexBannerWebActivity.class);
                                intent.putExtra("url", indexBannerDto.getData().get(position - 1).getBannerActionVal() + "");
                                startActivity(intent);
                                break;
                            case 2:     //2：进入详情页面
                                intent = new Intent(getActivity(), ParkDetailActivity.class);
                                intent.putExtra("parkId", indexBannerDto.getData().get(position - 1).getBannerActionVal() + "");
                                startActivity(intent);
                                break;
                        }
                    }
                });
                mBanner.setImages(bannerImages != null ? bannerImages : null)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setBannerAnimation(Transformer.Tablet)
                        .setImageLoader(new GlideImageLoader()).start();

                Log.e(TAG, "onNext");
            }
        });

        mCurrentProNum = (TextView) view.findViewById(R.id.currentProNum);
        mNewBeraNum = (TextView) view.findViewById(R.id.newBeraNum);
        mCommendSuccessNum = (TextView) view.findViewById(R.id.commendSuccessNum);
        HttpData.getInstance().HttpDataGetStaticDate(new Observer<HttpResult<IndexStaticDateDto>>() {

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "indexStaticDateDtoHttpResult onError: " + e.getMessage()
                        + "\n" + e.getCause()
                        + "\n" + e.getLocalizedMessage()
                        + "\n" + Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "indexStaticDateDtoHttpResult onCompleted");
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(HttpResult<IndexStaticDateDto> indexStaticDateDtoHttpResult) {
                mCurrentProNum.setText(indexStaticDateDtoHttpResult.getData().getNewBeraNum() + "");
                mNewBeraNum.setText(indexStaticDateDtoHttpResult.getData().getCurrentProNum() + "");
                mCommendSuccessNum.setText(indexStaticDateDtoHttpResult.getData().getCommendSuccessNum() + "");

                Log.e(TAG, "indexStaticDateDtoHttpResult onNext"
                        + indexStaticDateDtoHttpResult.getData().getCommendSuccessNum()
                        + " " + indexStaticDateDtoHttpResult.getData().getNewBeraNum()
                        + " " + indexStaticDateDtoHttpResult.getData().getCurrentProNum());
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView.setHasFixedSize(true);
        mSelectedParkAdapter = new SelectedParkAdapter(R.layout.item_index_fragment_selected_park, null);
        //设置加载动画
        mSelectedParkAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //设置是否自动加载以及加载个数
        mSelectedParkAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        mRecyclerView.setAdapter(mSelectedParkAdapter);

        HttpData.getInstance().HttpDataGetParkBanners(new Observer<HttpResult<List<IndexBannerDto>>>() {
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
            public void onNext(HttpResult<List<IndexBannerDto>> indexBannerDto) {
                parkBannerImages.addAll(indexBannerDto.getData());
                mSelectedParkAdapter.addData(parkBannerImages);

                Log.e(TAG, "onNext");
            }
        });

        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.rv_list2);
        //设置布局管理器
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        //如果Item高度固定  增加该属性能够提高效率
        mRecyclerView2.setHasFixedSize(true);
        // Constant data
        mLatestRecommendationAdapter = new LatestRecommendationAdapter(R.layout.item_index_fragment_latest_recommendation, null);
        //设置加载动画
        mLatestRecommendationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_CUSTOM);
        //设置是否自动加载以及加载个数
        mLatestRecommendationAdapter.openLoadMore(6, true);
        //将适配器添加到RecyclerView
        mRecyclerView2.setAdapter(mLatestRecommendationAdapter);

        HttpData.getInstance().HttpDataGetNewRecommandParks(new Observer<HttpResult<List<IndexRecommandParkDto>>>() {
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
            public void onNext(HttpResult<List<IndexRecommandParkDto>> indexRecommandParkDtoHttpResult) {
                if (indexRecommandParkDtoHttpResult.getData().size() != 0) {
                    mLatestRecommendationAdapter.addData(indexRecommandParkDtoHttpResult.getData());
                    Log.e(TAG, "onNext");
                }
            }
        });

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //此处避免you cannot start a load for a destroyed activity，因为glide不在主线程
        Glide.with(getActivity().getApplicationContext()).pauseRequests();
    }

    @OnClick({R.id.search_iv, R.id.conversation_iv, R.id.map_llyt, R.id.earn_llyt, R.id.my_llyt, R.id.brokerage_llyt})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.search_iv:
                intent = new Intent(getActivity(), SearchParkActivity.class);
                startActivity(intent);
                break;
            case R.id.conversation_iv:
                intent = new Intent(getActivity(), ConversationActivity.class);
                startActivity(intent);
                break;
            case R.id.map_llyt:
                intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("ReturnToMainActivity", 3);
//                Data.setPage(3);
                Data.setPage(5);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.earn_llyt:
                intent = new Intent(getActivity(), EarnMoneyActivity.class);
                startActivity(intent);
                break;
            case R.id.my_llyt:
//                MainActivity activity = (MainActivity) getActivity();
//                FragmentManager fm = activity.getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                if (activity.mIndexFragment == null) {
//                    activity.mIndexFragment = new IndexFragment();
//                }
//                ft.replace(R.id.container, activity.mIndexFragment, activity.TAB_INDEX_TAG);
////                ft.addToBackStack(null);
//                ft.commit();

                intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("ReturnToMainActivity", 1);
                Data.setPage(1);
                startActivity(intent);
                getActivity().finish();

//                intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("page", "index");
//                startActivityForResult(intent, 0);
//                getActivity().finish();
                break;
            case R.id.brokerage_llyt:
                intent = new Intent(getActivity(), MyCreditActivity.class);
                startActivity(intent);
                break;
        }
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


    /**
     * 设置未读tab显示
     */
    public static void setMsgUnread(boolean noUnread) {
        msgUnread.setVisibility(noUnread ? View.GONE : View.VISIBLE);
    }
}
