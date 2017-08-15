package com.winwin.app.UI.MineView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendshipManager;
import com.winwin.app.Constant.Data;
import com.winwin.app.Data.HttpData.HttpData;
import com.winwin.app.R;
import com.winwin.app.UI.Entity.AppUserVo;
import com.winwin.app.UI.Entity.FileDto;
import com.winwin.app.UI.Entity.HttpResult;
import com.winwin.app.Util.GlideCircleTransform;
import com.winwin.app.Util.ToastUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 个人资料页
 */
public class MyInfoActivity extends AppCompatActivity {
    private static final String TAG = MyInfoActivity.class.getSimpleName();
    @Bind(R.id.confirm)
    TextView confirm;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.right_tip)
    ImageView rightTip;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.right_tip2)
    ImageView rightTip2;

    RequestOptions options = new RequestOptions()
            .centerCrop()
            .transform(new GlideCircleTransform(MyInfoActivity.this))
            .diskCacheStrategy(DiskCacheStrategy.ALL);
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;

    private String userName = "";
    private String userAvatar = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        toolBar();
        initView();
    }

    private void toolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.back2));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MyInfoActivity.this, MainActivity.class);
//                intent.putExtra("ReturnToMainActivity", 4);
//                startActivity(intent);
                Data.setPage(4);
                finish();
            }
        });
    }

    private void initView() {

        userName = getIntent().getExtras().getString("UserName");
        userAvatar = getIntent().getExtras().getString("UserAvatar");
        Glide.with(MyInfoActivity.this)
                .load(userName)
                .apply(options)
                .into(avatar);
        name.setText(userName);
    }

    @OnClick({R.id.confirm, R.id.avatar, R.id.name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                userName = name.getText().toString().trim();
                if (userName.equals("")) {
                    ToastUtils.show(MyInfoActivity.this, "请输入昵称");
                    return;
                } else if (userAvatar.equals("")) {
                    ToastUtils.show(MyInfoActivity.this, "请选择头像");
                    return;
                } else if (!userName.equals(getIntent().getExtras().getString("UserName")) || !userAvatar.equals(getIntent().getExtras().getString("UserAvatar"))){    //判断昵称或头像是否🈶️被修改
                    //winwin服务器
                    HttpData.getInstance().HttpDataUpdateAppUserInfo(new Observer<HttpResult>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpResult httpResult) {
                            if (!httpResult.getStatus().getCode().equals("0")) {
                                ToastUtils.show(MyInfoActivity.this, httpResult.getStatus().getMsg());
                                return;
                            }

                            //腾讯IM服务器
                            //设置自己的昵称, 初始化参数，修改昵称为“cat”
                            TIMFriendshipManager.ModifyUserProfileParam param = new TIMFriendshipManager.ModifyUserProfileParam();
                            param.setNickname(userName);
                            param.setFaceUrl(userAvatar);
                            TIMFriendshipManager.getInstance().modifyProfile(param, new TIMCallBack() {
                                @Override
                                public void onError(int code, String desc) {
                                    //错误码code和错误描述desc，可用于定位请求失败原因
                                    //错误码code列表请参见错误码表
                                    Log.e(TAG, "modifyProfile failed: " + code + " desc" + desc);
                                }

                                @Override
                                public void onSuccess() {
                                    Log.e(TAG, "modifyProfile succ");
                                    ToastUtils.show(MyInfoActivity.this, "修改成功");
                                    name.setFocusable(false);
                                    name.setFocusableInTouchMode(false);
                                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                    if(imm.isActive()&&getCurrentFocus()!=null){
                                        if (getCurrentFocus().getWindowToken()!=null) {
                                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                        }
                                    }
                                }
                            });


                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    }, new AppUserVo(userAvatar, userName));
                }
                break;
            case R.id.avatar:
                showChoosePicDialog();
                break;
            case R.id.name:
                name.setFocusableInTouchMode(true);
                name.setFocusable(true);
                name.requestFocus();
                break;
        }
    }

    /**
     * 显示修改图片的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
        builder.setTitle("添加图片");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp_image.jpg"));
                        // 将拍照所得的相片保存到SD卡根目录
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyInfoActivity.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            saveAvatarPic(mBitmap);
            avatar.setImageBitmap(mBitmap);//显示图片
        }
    }

    /**
     * 头像保存至手机，并上传服务器
     */
    private void saveAvatarPic(Bitmap mBitmap) {
        final File file = new File(Environment.getExternalStorageDirectory(), "temp_image.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = MultipartBody.create(MediaType.parse("multipart/form-data"), file);
        final MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        HttpData.getInstance().HttpDataUploadFile(new Observer<FileDto>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "uploadFile onSubscribe");
            }

            @Override
            public void onNext(@NonNull FileDto fileDto) {
                userAvatar = fileDto.getImagePath();
                Log.e(TAG, "uploadFile onNext " + userAvatar);
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
                Log.e(TAG, "uploadFile onComplete");
            }
        }, part);
    }
}

