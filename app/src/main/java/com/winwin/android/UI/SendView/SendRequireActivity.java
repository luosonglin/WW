package com.winwin.android.UI.SendView;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
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
import com.winwin.android.Data.HttpData.HttpData;
import com.winwin.android.R;
import com.winwin.android.UI.Entity.FileDto;
import com.winwin.android.UI.Entity.HotAreaDto;
import com.winwin.android.UI.Entity.MetaDataDto;
import com.winwin.android.UI.Entity.RequireDto;
import com.winwin.android.UI.ItemDetailView.RequireDetailActivity;
import com.winwin.android.Util.StringUtils;
import com.winwin.android.Util.ToastUtils;
import com.winwin.android.Widget.gridimage.FullyGridLayoutManager;
import com.winwin.android.Widget.gridimage.GridImageAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SendRequireActivity extends AppCompatActivity {

    private static final String TAG = SendRequireActivity.class.getSimpleName();
    Toolbar toolbar;
    @Bind(R.id.requireAreaName)
    TextView requireAreaName;
    @Bind(R.id.belongIndustryDisPlay)
    TextView belongIndustryDisPlay;
    @Bind(R.id.requireAreaRangDisplay)
    TextView requireAreaRangDisplay;
    @Bind(R.id.requireTitle)
    EditText requireTitle;
    @Bind(R.id.pubRequireCompany)
    EditText pubRequireCompany;
    @Bind(R.id.description)
    EditText description;
    private RecyclerView recyclerView;
    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<String> compressPathList = new ArrayList<>();
    private List<FileDto> files = new ArrayList<>();
    private RequireDto requireDto = new RequireDto();
    private int belongIndustryId = 0;
    private int requireAreaRangId = 0;
    private List<HotAreaDto> requireAreaNames = new ArrayList<>();
    private List<MetaDataDto> belongIndustryDisPlays = new ArrayList<>();
    private List<MetaDataDto> requireAreaRangDisplays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_require);
        ButterKnife.bind(this);
        toolBar();
        initFindView();
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFindView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(SendRequireActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(SendRequireActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(9);
        recyclerView.setAdapter(adapter);
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
                            //PictureSelector.create(SendRequireActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(SendRequireActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(SendRequireActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(SendRequireActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(SendRequireActivity.this);
                } else {
                    Toast.makeText(SendRequireActivity.this,
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
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(SendRequireActivity.this)
                    .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                    .theme(R.style.picture_Sina_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(9)// 最大图片选择数量
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                        + "\n" + Arrays.toString(e.getStackTrace()));
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

    private String[] targetAreas = new String[]{};
    private String[] industrys = new String[]{};
    private String[] requestAreas = new String[]{};

    @OnClick({R.id.target_area_rlyt, R.id.industry_rlyt, R.id.request_area_rlyt, R.id.other_rlyt, R.id.buildllyt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.target_area_rlyt:
                if (targetAreas.length > 0) {
                    showCoresPopupWindow(targetAreas, 1);
                } else {
                    HttpData.getInstance().HttpDataGetShanghaiHotAreas(new Observer<List<HotAreaDto>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull List<HotAreaDto> hotAreaDtos) {
                            requireAreaNames.addAll(hotAreaDtos);

                            List<String> h = new ArrayList<>();
                            for (HotAreaDto i : hotAreaDtos) {
                                h.add(i.getName());
                            }
                            targetAreas = h.toArray(new String[h.size()]);

                            showCoresPopupWindow(targetAreas, 1);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage()
                                    + "\n" + e.getCause()
                                    + "\n" + e.getLocalizedMessage()
                                    + "\n" + Arrays.toString(e.getStackTrace()));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
                break;
            case R.id.industry_rlyt:
                if (industrys.length > 0) {
                    showCoresPopupWindow(industrys, 2);
                } else {
                    HttpData.getInstance().HttpDataGetMetaDataList(new Observer<List<MetaDataDto>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            Log.e(TAG, "onSubscribe");
                        }

                        @Override
                        public void onNext(@NonNull List<MetaDataDto> metaDataDtos) {
                            belongIndustryDisPlays.addAll(metaDataDtos);

                            List<String> h = new ArrayList<>();
                            for (MetaDataDto i : metaDataDtos) {
                                h.add(i.getDataDisplay());
                            }

                            industrys = h.toArray(new String[h.size()]);

                            showCoresPopupWindow(industrys, 2);
                            Log.e(TAG, "onNext");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage()
                                    + "\n" + e.getCause()
                                    + "\n" + e.getLocalizedMessage()
                                    + "\n" + Arrays.toString(e.getStackTrace()));
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete");
                        }
                    }, 1);
                }
                break;
            case R.id.request_area_rlyt:
                if (requestAreas.length > 0) {
                    showCoresPopupWindow(requestAreas, 3);
                } else {
                    HttpData.getInstance().HttpDataGetMetaDataList(new Observer<List<MetaDataDto>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            Log.e(TAG, "onSubscribe");
                        }

                        @Override
                        public void onNext(@NonNull List<MetaDataDto> metaDataDtos) {
                            requireAreaRangDisplays.addAll(metaDataDtos);

                            List<String> h = new ArrayList<>();
                            for (MetaDataDto i : metaDataDtos) {
                                h.add(i.getDataDisplay());
                            }
                            requestAreas = h.toArray(new String[h.size()]);

                            showCoresPopupWindow(requestAreas, 3);
                            Log.e(TAG, "onNext");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e(TAG, "onError: " + e.getMessage()
                                    + "\n" + e.getCause()
                                    + "\n" + e.getLocalizedMessage()
                                    + "\n" + Arrays.toString(e.getStackTrace()));
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete");
                        }
                    }, 2);
                }
                break;
            case R.id.other_rlyt:

                break;
            case R.id.buildllyt:
                //非空判断
                if (requireTitle.getText().toString().trim().equals("")) {
                    ToastUtils.show(SendRequireActivity.this, "请填写需求标题");
                    return;
                }
                if (files.size() <= 0) {
                    ToastUtils.show(SendRequireActivity.this, "请添加图片");
                    return;
                }
                if (pubRequireCompany.getText().toString().trim().equals("")) {
                    ToastUtils.show(SendRequireActivity.this, "请填写公司名称");
                    return;
                }
                if (requireDto.getRequireAreaName().equals("")) {
                    ToastUtils.show(SendRequireActivity.this, "请选择目标区域");
                    return;
                }
                if (requireDto.getBelongIndustryDisPlay().equals("")) {
                    ToastUtils.show(SendRequireActivity.this, "请选择目所属行业");
                    return;
                }
                if (requireDto.getRequireAreaRangDisplay().equals("")) {
                    ToastUtils.show(SendRequireActivity.this, "请选择需求面积");
                    return;
                }
                if (description.getText().toString().trim().equals("")) {
                    ToastUtils.show(SendRequireActivity.this, "请填写描述信息");
                    return;
                }

                for (HotAreaDto i : requireAreaNames) {
                    if (requireDto.getRequireAreaName().equals(i.getName())) {
                        requireDto.setRequireAreaIdArray(new String[]{i.getId() + ""});
                    }
                }
                for (MetaDataDto i : belongIndustryDisPlays) {
                    if (requireDto.getBelongIndustryDisPlay().equals(i.getDataDisplay())) {
                        requireDto.setBelongIndustryId(i.getId());
                    }
                }
                for (MetaDataDto j : requireAreaRangDisplays) {
                    if (requireDto.getRequireAreaRangDisplay().equals(j.getDataDisplay())) {
                        requireDto.setRequireAreaRangId(j.getId());
                    }
                }
                requireDto.setRequireTitle(requireTitle.getText().toString().trim());
                requireDto.setPubRequireCompany(pubRequireCompany.getText().toString().trim());
                requireDto.setOtherInfo(description.getText().toString().trim());
                requireDto.setEffectImgs(files);
                HttpData.getInstance().HttpDataSendRequirement(new Observer<RequireDto>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RequireDto requireDto) {
                        Intent intent = new Intent(SendRequireActivity.this, RequireDetailActivity.class);
                        intent.putExtra("id", requireDto.getId());
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage()
                                + "\n" + e.getCause()
                                + "\n" + e.getLocalizedMessage()
                                + "\n" + Arrays.toString(e.getStackTrace()));
                    }

                    @Override
                    public void onComplete() {

                    }
                }, requireDto);
                break;
        }
    }

    /**
     * 筛选之区域弹出窗
     */
    private PopupWindow mSearchPopupWindow;
    private String mChooseCore;
    private String mChooseCoreId;

    /**
     * @param cores
     * @param type  1：目标区域；2：所属行业；3：需求面积
     */
    private void showCoresPopupWindow(final String[] cores, final int type) {
        View coresPopupwindowView = LayoutInflater.from(this).inflate(R.layout.popupwindow_core, null);
        mSearchPopupWindow = new PopupWindow(coresPopupwindowView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        final TextView mCancelTv = (TextView) coresPopupwindowView.findViewById(R.id.cancel);
        final TextView mConfirmTv = (TextView) coresPopupwindowView.findViewById(R.id.purchase_time_confirm);
        NumberPicker schoolNumberPicker = (NumberPicker) coresPopupwindowView.findViewById(R.id.schools_picker);

        if (!StringUtils.isEmpty(Arrays.toString(cores))) {

            schoolNumberPicker.setDisplayedValues(cores);

            schoolNumberPicker.setMinValue(0);
            if (cores.length <= 1) {
                schoolNumberPicker.setMaxValue(1);
            } else {
                schoolNumberPicker.setMaxValue(cores.length - 1);
            }
        } else {
            schoolNumberPicker.setMinValue(0);
            schoolNumberPicker.setDisplayedValues(new String[]{"暂无数据"});
            schoolNumberPicker.setMaxValue(0);
        }

//        schoolNumberPicker.setValue(2);

        schoolNumberPicker.setWrapSelectorWheel(false); //防止NumberPicker无限滚动
        schoolNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); //禁止NumberPicker输入

        schoolNumberPicker.setFocusable(true);
        schoolNumberPicker.setFocusableInTouchMode(true);

        if (cores == null) {
            ToastUtils.show(SendRequireActivity.this, "cores is null");
        } else {
            if (!StringUtils.isEmpty(Arrays.toString(cores))) {
                mChooseCore = cores[0];
            } else {
                mChooseCore = "暂无数据";
            }
        }

        mChooseCore = cores[0];
        mChooseCoreId = null;
        schoolNumberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {    //用于监听滑动状态变化，其状态有SCROLL_STATE_FLING 、 SCROLL_STATE_IDLE 、 SCROLL_STATE_TOUCH_SCROLL
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {   //NumberPicker停止滑动
                    if (numberPicker.getValue() > cores.length) {
                        mChooseCore = cores[cores.length];
                        mChooseCoreId = null;
                    } else {
                        mChooseCore = cores[numberPicker.getValue()];
                        mChooseCoreId = null;
                    }
                } else if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_FLING) {   //正在滑动中的状态
                    if (numberPicker.getValue() > cores.length) {
                        mChooseCore = cores[cores.length];
                        mChooseCoreId = null;
                    } else {
                        mChooseCore = cores[numberPicker.getValue()];
                        mChooseCoreId = null;
                    }
                } else if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {   //用户按下去然后滑动
                    if (numberPicker.getValue() > cores.length) {
                        mChooseCore = cores[cores.length];
                        mChooseCoreId = null;
                    } else {
                        mChooseCore = cores[numberPicker.getValue()];
                        mChooseCoreId = null;
                    }
                }
            }
        });
//        schoolNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {     //用于监听数值变化，通过此接口可以得到变化前的值和当前值
//            @Override
//            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
//                mChooseCore = cores[cores.length];
//                mChooseCoreId = null;
//            }
//        });

        LinearLayout popupParentLayout2 = (LinearLayout) coresPopupwindowView.findViewById(R.id.popup_parent);
        popupParentLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchPopupWindow != null && mSearchPopupWindow.isShowing()) {
                    mChooseCore = null;
                    mChooseCoreId = null;
                    mSearchPopupWindow.dismiss();
                }
            }
        });

        mSearchPopupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mSearchPopupWindow.setBackgroundDrawable(dw);
        mSearchPopupWindow.showAtLocation(coresPopupwindowView, Gravity.BOTTOM, 0, 0);

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchPopupWindow.dismiss();
            }
        });
        mConfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type) {
                    case 1:
                        requireAreaName.setText(mChooseCore);
                        requireDto.setRequireAreaName(mChooseCore);
                        break;
                    case 2:
                        belongIndustryDisPlay.setText(mChooseCore);
                        requireDto.setBelongIndustryDisPlay(mChooseCore);

                        break;
                    case 3:
                        requireAreaRangDisplay.setText(mChooseCore + " 平方米");
                        requireDto.setRequireAreaRangDisplay(mChooseCore);
                        break;
                }
                mSearchPopupWindow.dismiss();
            }
        });
    }

}
