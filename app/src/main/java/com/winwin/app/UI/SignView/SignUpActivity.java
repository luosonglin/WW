package com.winwin.app.UI.SignView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.DebugUtil;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.MainActivity;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.FileDto;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.UI.Entity.UserRegisterVo;
import com.winwin.app.Util.ToastUtils;
import com.winwin.app.Widget.gridimage.FullyGridLayoutManager;
import com.winwin.app.Widget.gridimage.GridImageAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignUpActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    // UI references.
    private ImageView mBackgroundImageView;

    public static UserRegisterVo userRegisterVo = new UserRegisterVo();

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init toolbar
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBackgroundImageView = (ImageView) findViewById(R.id.login_background_image);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(SignUpActivity.this, R.anim.login_background_translate_anim);
                mBackgroundImageView.startAnimation(animation);
            }
        }, 1000);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sign_up2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = SignUpPlaceholderFirstFragment.newInstance(position);
                    break;
                case 1:
                    fragment = SignUpPlaceholderSecondFragment.newInstance(position);
                    break;
                case 2:
                    fragment = SignUpPlaceholderThirdFragment.newInstance(position);
                    break;
                default:
                    fragment = SignUpPlaceholderFirstFragment.newInstance(position);
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SignUpPlaceholderFirstFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        // UI Refrence
        private TextView mPhoneView;
        private EditText mCodeView;
        private TextView mGetCodeView;
        private Button mConfirmView;

        // timer
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                mGetCodeView.setEnabled(false);
                mGetCodeView.setText("剩余" + l / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                mGetCodeView.setEnabled(true);
                mGetCodeView.setText("获取验证码");
            }
        };

        public SignUpPlaceholderFirstFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SignUpPlaceholderFirstFragment newInstance(int sectionNumber) {
            SignUpPlaceholderFirstFragment fragment = new SignUpPlaceholderFirstFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up_first, container, false);
            mPhoneView = (TextView) rootView.findViewById(R.id.phone);
            mCodeView = (EditText) rootView.findViewById(R.id.code);
            mGetCodeView = (TextView) rootView.findViewById(R.id.get_code_textview);
            mGetCodeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timer.start();
                    UserRegisterVo userRegisterVo1 = new UserRegisterVo();
                    userRegisterVo1.setCardType(1);
                    userRegisterVo1.setMsgType(0);
                    userRegisterVo1.setUserCard(mPhoneView.getText().toString().trim());
                    HttpData.getInstance().HttpDataSendMsgCaptcha(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            if (!httpResult.getStatus().getCode().equals("0")) {
                                ToastUtils.show(getActivity(), httpResult.getStatus().getMsg());
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            ToastUtils.show(getActivity(), e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    }, userRegisterVo1);
                }
            });
            mConfirmView = (Button) rootView.findViewById(R.id.confirm_button);
            mConfirmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /**
                     * UserRegisterVo {
                        cardType (integer, optional): 用户登录标识类型(1-手机号，2-微信，3-QQ， 0 其他) ,
                        headPicUrl (string, optional): 头像 ,
                        msgCaptcha (string, optional): 短信验证码(当cardType=1时存在，其他为空) ,
                        msgType (integer, optional): 短信验证码类型：0 注册，1 找回密码 ,
                        password (string, optional): 密码 ,
                        userCard (string, optional): 用户登录标识(手机号或者微信或者QQ) ,
                        userName (string, optional): 用户名----可用于注册成功后的登录名
                       }
                     */
                    userRegisterVo.setCardType(1);
                    userRegisterVo.setUserCard(mPhoneView.getText().toString().trim());
                    userRegisterVo.setMsgType(0);
                    userRegisterVo.setMsgCaptcha(mCodeView.getText().toString().trim());
                    mViewPager.setCurrentItem(getArguments().getInt(ARG_SECTION_NUMBER) + 1, true);
                }
            });
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SignUpPlaceholderSecondFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        // UI Refrence
        private EditText mPasswordView;
        private Button mConfirmView;

        public SignUpPlaceholderSecondFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SignUpPlaceholderSecondFragment newInstance(int sectionNumber) {
            SignUpPlaceholderSecondFragment fragment = new SignUpPlaceholderSecondFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up_second, container, false);
            mPasswordView = (EditText) rootView.findViewById(R.id.password);
            mConfirmView = (Button) rootView.findViewById(R.id.confirm_button);
            mConfirmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userRegisterVo.setPassword(mPasswordView.getText().toString().trim());
                    mViewPager.setCurrentItem(getArguments().getInt(ARG_SECTION_NUMBER) + 1, true);
                }
            });
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SignUpPlaceholderThirdFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        // UI Refrence
//        private ImageView mAvatarView;
        private TextView mNameView;
        private RecyclerView mRecyclerView;
        private Button mConfirmView;

        private String TAG = SignUpPlaceholderThirdFragment.class.getSimpleName();

        public SignUpPlaceholderThirdFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SignUpPlaceholderThirdFragment newInstance(int sectionNumber) {
            SignUpPlaceholderThirdFragment fragment = new SignUpPlaceholderThirdFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sign_up_third, container, false);
//            mAvatarView = (ImageView) rootView.findViewById(R.id.avatar);
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
            FullyGridLayoutManager manager = new FullyGridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);
            adapter = new GridImageAdapter(getActivity(), onAddPicClickListener);
            adapter.setList(selectList);
            adapter.setSelectMax(9);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    if (selectList.size() > 0) {
                        LocalMedia media = selectList.get(position);
                        String pictureType = media.getPictureType();
                        int mediaType = PictureMimeType.pictureToVideo(pictureType);
                        switch (mediaType) {
                            case 1:
                                // 预览图片 可自定长按保存路径
                                //PictureSelector.create(getActivity()).externalPicturePreview(position, "/custom_file", selectList);
                                PictureSelector.create(getActivity()).externalPicturePreview(position, selectList);
                                break;
                            case 2:
                                // 预览视频
                                PictureSelector.create(getActivity()).externalPictureVideo(media.getPath());
                                break;
                            case 3:
                                // 预览音频
                                PictureSelector.create(getActivity()).externalPictureAudio(media.getPath());
                                break;
                        }
                    }
                }
            });

            // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
            RxPermissions permissions = new RxPermissions(getActivity());
            permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(Boolean aBoolean) {
                    if (aBoolean) {
                        PictureFileUtils.deleteCacheDirFile(getActivity());
                    } else {
                        Toast.makeText(getActivity(),
                                getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });

            mNameView = (TextView) rootView.findViewById(R.id.name);
            mConfirmView = (Button) rootView.findViewById(R.id.confirm_button);
            mConfirmView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (files.size() <= 0) {
//                        ToastUtils.show(getActivity(), "请选择一张图片作为头像");
//                        return;
//                    }
                    if (files.size() > 0) {
                        userRegisterVo.setHeadPicUrl(files.get(0).getImagePath());
                    } else {
                        userRegisterVo.setHeadPicUrl("");
                    }
                    userRegisterVo.setUserName(mNameView.getText().toString().trim());
                    HttpData.getInstance().HttpDataConfirmRegister(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            if (!httpResult.getStatus().getCode().equals("0")) {
                                ToastUtils.show(getActivity(), httpResult.getStatus().getMsg());
                                return;
                            }
                            mViewPager.setCurrentItem(getArguments().getInt(ARG_SECTION_NUMBER) + 1, true);
                            getActivity().finish();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    }, userRegisterVo);


                }
            });
            return rootView;
        }

        private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                    .theme(R.style.picture_Sina_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选: PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片
                        .previewVideo(true)// 是否可预览视频
                        .enablePreviewAudio(true) // 是否可播放音频
                        .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                        .isCamera(true)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(false)// 是否压缩
                        .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(true)// 是否显示gif图片
//                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//                    .circleDimmedLayer(true)// 是否圆形裁剪
//                    .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                    .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                        //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }

        };




    }


    private static GridImageAdapter adapter;
    private static List<LocalMedia> selectList = new ArrayList<>();
    private static List<String> compressPathList = new ArrayList<>();
    private static List<FileDto> files = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    DebugUtil.i(TAG, "onActivityResult:" + selectList.size());
                    for (LocalMedia i : selectList) {
                        Log.e(TAG, i.getPath());
                        File file = new File(i.getPath());
                        RequestBody requestBody = MultipartBody.create(MediaType.parse("multipart/form-data"), file);
                        final MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                        new Thread() {
                            @Override
                            public void run() {
                                // 更新UI
                                uploadFile(part);
                            }
                        }.start();

                    }
                    break;
            }
        }
    }

    private void uploadFile(final MultipartBody.Part file) {

        HttpData.getInstance().HttpDataUploadFile(new Observer<FileDto>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "uploadFile onSubscribe");
            }

            @Override
            public void onNext(@NonNull FileDto fileDto) {
                compressPathList.add(fileDto.getCompressPath());
                files.add(fileDto);
                Log.e(TAG, "uploadFile onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "uploadFile onError: " + e.getMessage()
                        + "\n" + e.getCause()
                        + "\n" + e.getLocalizedMessage()
                        + "\n" + e.getStackTrace());
            }

            @Override
            public void onComplete() {
                for (String i : compressPathList) {
                    Log.e(TAG, "compressPathList " + i);
                }
                Log.e(TAG, "uploadFile onComplete");
            }
        }, file);
    }

}
