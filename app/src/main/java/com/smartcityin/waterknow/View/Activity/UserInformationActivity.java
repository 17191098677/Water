package com.smartcityin.waterknow.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.GlideCircleTransform;
import com.smartcityin.waterknow.Base.LoginDialog;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhotoEntity;
import com.smartcityin.waterknow.Entity.My.MyUserInformationEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.UserInformationPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.BitmapUtils;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.PhotoUtils;
import com.smartcityin.waterknow.Utils.StringUtils;
import com.smartcityin.waterknow.Utils.ValidatorUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 用户信息页面
 */
public class UserInformationActivity extends BaseSwipBackActivity<Commpart.MyInformationViewInf, UserInformationPresenter> implements View.OnClickListener, Commpart.MyInformationViewInf {

    @BindView(R.id.userInformation_head_img)
    ImageView userInformationHeadImg;
    @BindView(R.id.userInformation_head_imgLinear)
    LinearLayout userInformationHeadImgLinear;
    @BindView(R.id.userInfomation)
    RelativeLayout userInfomation;
    @BindView(R.id.userInfomation_tv_name)
    TextView userInfomationTvName;
    @BindView(R.id.userInfomation_bt_updatePhone)
    RelativeLayout userInfomationBtUpdatePhone;
    @BindView(R.id.userInfomation_bt_updatePsw)
    RelativeLayout userInfomationBtUpdatePsw;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.userInformation_tv_Phone)
    TextView userInformationTvPhone;
    // 声明平移动画
    private TranslateAnimation animation;
    private String SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo.png";
    private File fileUri = new File(SD_CARD_TEMP_DIR);
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo.png");
    private Uri imageUri;
    private Uri cropImageUri;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private String token;
    private String phone;
    private String username;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_user_information;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initData();
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        tvTitle.setText("个人信息");
        lineFinish.setOnClickListener(this);
        userInformationHeadImgLinear.setOnClickListener(this);
        username = WaterKnowApp.getToken(this, "username");
        if (username!=null&&ValidatorUtils.isChinaPhoneLegal(username)){
            userInfomationBtUpdatePsw.setVisibility(View.VISIBLE);

        }else{
            userInfomationBtUpdatePsw.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        token = WaterKnowApp.getToken(this, "token");
        mPresenter = new UserInformationPresenter();
        mPresenter.attarchView(this);
        mPresenter.setMyinformation(token);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //此页面结束
            case R.id.line_finish:
                finish();
                break;
            //点击头像弹出PopupWindow拍照或从相册中选取头像
            case R.id.userInformation_head_imgLinear:
                initPopwindow();
                break;
        }
    }

    @OnClick({R.id.userInfomation_tv_name, R.id.userInfomation_bt_updatePhone, R.id.userInfomation_bt_updatePsw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userInfomation_tv_name:
                break;
            case R.id.userInfomation_bt_updatePhone:
                if (username!=null&&ValidatorUtils.isChinaPhoneLegal(username)){
                    initUpdatePhoneDialog();
                }else{
                    initBindingPhoneDialog();
                }
                break;
            case R.id.userInfomation_bt_updatePsw:
                IntentUtils.startIntent(UserInformationActivity.this, UserPSWActivity.class);
                break;
        }
    }

    private void initBindingPhoneDialog() {
        final LoginDialog dialog=new LoginDialog(this);
        dialog.show();
        dialog.setTvContent("您暂未绑定手机号，是否绑定手机号");
        dialog.setLeftButton("确定", new BaseDialog.OnClickerListenerLeft() {
            @Override
            public void setOnClicker() {
                Intent intent=new Intent(UserInformationActivity.this,RegisterActivity.class);
                intent.putExtra("register","绑定手机号");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
            @Override
            public void setOnClicker() {
                dialog.dismiss();
            }
        });
    }

    private void initUpdatePhoneDialog() {
        final LoginDialog dialog=new LoginDialog(this);
        dialog.show();
        dialog.setTvContent("是否修改手机号");
        dialog.setLeftButton("确定", new BaseDialog.OnClickerListenerLeft() {
            @Override
            public void setOnClicker() {
                mPresenter=new UserInformationPresenter();
                mPresenter.setUpdatePhone(token);
                initPopupWindow();
                mPresenter.attarchView(UserInformationActivity.this);
                dialog.dismiss();
            }
        });
        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
            @Override
            public void setOnClicker() {
                dialog.dismiss();
            }
        });
    }
    private PopupWindow popWindow;
    private void initPopupWindow() {
        View popView = View.inflate(UserInformationActivity.this, R.layout.popup_progress, null);
        popView.setAlpha(0.3F);
        popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setFocusable(false);
        popWindow.setOutsideTouchable(false);// 设置同意在外点击消失
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(userInfomation, Gravity.CENTER, 0, 0);
    }
    //像是PopupWindow
    private void initPopwindow() {
        View popView = View.inflate(this, R.layout.camera_pop_menu, null);
        Button btnCamera = (Button) popView.findViewById(R.id.btn_camera_pop_camera);
        Button btnAlbum = (Button) popView.findViewById(R.id.btn_camera_pop_album);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_camera_pop_cancel);
        final PopupWindow popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
        animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(2000);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_camera_pop_camera:
                        //调用相机拍照
                        autoObtainCameraPermission();
                        break;
                    case R.id.btn_camera_pop_album:
                        //调用系统相册
                        initPicter();
                        break;
                    case R.id.btn_camera_pop_cancel:
                        popWindow.dismiss();
                        break;
                }
                popWindow.dismiss();
            }
        };

        btnCamera.setOnClickListener(listener);
        btnAlbum.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(userInfomation, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void initPicter() {
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }


    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                //通过FileProvider创建一个content类型的Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imageUri = FileProvider.getUriForFile(UserInformationActivity.this, "com.smartcityin.waterknow.fileprovider", fileUri);
                }
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_CAMERA_REQUEST && resultCode == RESULT_OK) {
            initSetImage();
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            //访问相册后的回调
            autoObtainStoragePermission(data);
        }else if (requestCode==-1&&resultCode==-2){
            Toast.makeText(this, "接收到回传值", Toast.LENGTH_SHORT).show();
            initBindingPhone(data);
        }
    }

    private void initBindingPhone(Intent data) {
        String phone_number = data.getStringExtra("phone_number");
        if (phone_number!=null){
            phone= StringUtils.subPhone(phone_number);
            userInformationTvPhone.setText(phone);
            userInfomationBtUpdatePsw.setVisibility(View.VISIBLE);
        }
    }

    private void initSetImage() {
        cropImageUri = Uri.fromFile(fileCropUri);
        //裁剪照片
        //PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
        Bitmap myBitmap = BitmapFactory.decodeFile(SD_CARD_TEMP_DIR);
        int angle = BitmapUtils.readPictureDegree(SD_CARD_TEMP_DIR);
        Bitmap bitmap = BitmapUtils.rotaingImageView(angle, myBitmap);
        userInformationHeadImg.setImageBitmap(bitmap);
        BitmapUtils.saveBitmapToSD(bitmap,SD_CARD_TEMP_DIR);
        Bitmap smallBitmap = BitmapUtils.getSmallBitmap(SD_CARD_TEMP_DIR);
        userInformationHeadImg.setImageBitmap(smallBitmap);
        String base64NONseal = BitmapUtils.Bitmap2StrByBase64(smallBitmap);
        mPresenter.updateHeadImage(token,base64NONseal,"jpg");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            //调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(UserInformationActivity.this, "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                    } else {
                        Toast.makeText(this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                        takePhoto();
                    }
                } else {
                    takePhoto();
                    Toast.makeText(this, "请允许打开相机！！", Toast.LENGTH_SHORT).show();
                }
                break;
            //调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {
                    Toast.makeText(this, "请允许打操作SDCard！！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    private static final int REQUEST_CODE_PERMISSION_TAKE_PHOTO = 2;
    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_TAKE_PHOTO)
    public void takePhoto() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {

        } else {
            EasyPermissions.requestPermissions(this, "请开起存储空间和相机权限，以正常使用 Demo", REQUEST_CODE_PERMISSION_TAKE_PHOTO, perms);
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String imagePath = c.getString(columnIndex);
         Bitmap bm = BitmapFactory.decodeFile(imagePath);
        c.close();
        Bitmap smallBitmap = BitmapUtils.getSmallBitmap(imagePath);
        String base64= BitmapUtils.bitmapToBase64NONseal(smallBitmap);
        mPresenter.updateHeadImage(token,base64,"jpg");
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.user_276)
                .error(R.drawable.user_276)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(this));
        Glide.with(this)
                .load(bm)
                .apply(options)
                .into(userInformationHeadImg);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getMyInformation(MyUserInformationEntity userInformationEntity) {
        int code = userInformationEntity.getCode();
        switch (code) {
            case 200:
                String nickname = userInformationEntity.getData().getNickname();
                userInfomationTvName.setText(nickname);
                String phoneNumber = userInformationEntity.getData().getUsername();
                if (!phoneNumber.equals("")&&phoneNumber!=null){
                    phone= StringUtils.subPhone(phoneNumber);
                    userInformationTvPhone.setText(phone);
                }else{
                    userInformationTvPhone.setText("暂未绑定手机号");
                }
                RequestOptions options = new RequestOptions();
                options.placeholder(R.drawable.user_276)
                        .error(R.drawable.user_276)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(this));
                Glide.with(this)
                        .load(userInformationEntity.getData().getHead())
                        .apply(options)
                        .into(userInformationHeadImg);
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    @Override
    public void getUpdateHeadImage(MyUpdatePhotoEntity myUpdatePhotoEntity) {
        switch (myUpdatePhotoEntity.getCode()){
            case 200:
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getMyUpdatePhone(MyUpdatePhoneEntity myUpdatePhoneEntity) {
        popWindow.dismiss();
        int code = myUpdatePhoneEntity.getCode();
        switch (code){
            case 200:
                popWindow.dismiss();
                Intent intent=new Intent(UserInformationActivity.this, UpdatePhoneActivity.class);
                intent.putExtra("phone",phone);
                String msg_id = myUpdatePhoneEntity.getData().getMsg_id();
                intent.putExtra("msg_id",msg_id);
                startActivity(intent);
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

}
