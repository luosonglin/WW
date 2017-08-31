package com.winwin.android.UI.MineView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.winwin.android.Constant.Data;
import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.MainActivity;
import com.winwin.android.R;
import com.winwin.android.UI.Entity.MyInfoDto;
import com.winwin.android.Util.GlideCircleTransform;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.member_point)
    TextView mMemberPoint;
    @Bind(R.id.right_tip)
    ImageView rightTip;
    @Bind(R.id.credit)
    RelativeLayout credit;
    @Bind(R.id.my_winwin)
    RelativeLayout myWinwin;
    @Bind(R.id.customer_recommend)
    RelativeLayout customerRecommend;
    @Bind(R.id.my_send)
    RelativeLayout mySend;
    @Bind(R.id.my_collect)
    RelativeLayout myCollect;
    @Bind(R.id.my_more)
    RelativeLayout myMore;

    private static final String TAG = MineFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MineFragment() {
        // Required empty public constructor
    }

    private String UserName;
    private String UserAvatar;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        HttpData.getInstance().HttpDataGetMyInformation(new Observer<MyInfoDto>() {
            @Override
            public void onComplete() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e.getMessage()
                        +"\n"+e.getCause()
                        +"\n"+e.getLocalizedMessage()
                        +"\n"+ Arrays.toString(e.getStackTrace()));
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(MyInfoDto myInfoDto) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .transform(new GlideCircleTransform(getActivity()))
//                        .placeholder(R.mipmap.emoji)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(getActivity())
                        .load(myInfoDto.getHeadPic())
                        .apply(options)
                        .into(avatar);

                name.setText(myInfoDto.getUserName());
                mMemberPoint.setText(myInfoDto.getTotalCredits()+" ");

                UserName = myInfoDto.getUserName();
                UserAvatar = myInfoDto.getHeadPic();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
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

    @OnClick({R.id.avatar, R.id.credit, R.id.my_winwin, R.id.customer_recommend, R.id.my_send, R.id.my_collect, R.id.my_more})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.avatar:
                intent = new Intent(getActivity(), MyInfoActivity.class);
                intent.putExtra("UserName", UserName);
                intent.putExtra("UserAvatar", UserAvatar);
                startActivity(intent);
                break;
            case R.id.credit:
                startActivity(new Intent(getActivity(), MyCreditActivity.class));
                break;
            case R.id.my_winwin:
                intent = new Intent(getActivity(), MainActivity.class);
                Data.setPage(1);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.customer_recommend:
                startActivity(new Intent(getActivity(), MyRecommendActivity.class));
                break;
            case R.id.my_send:
                startActivity(new Intent(getActivity(), MySendActivity.class));
                break;
            case R.id.my_collect:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.my_more:
                startActivity(new Intent(getActivity(), MyMoreActivity.class));
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
}
