package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.HomeWaterRepotPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.BitmapUtils;
import com.smartcityin.waterknow.Utils.PhotoUtils;
import com.smartcityin.waterknow.Utils.ValidatorUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HomeWaterRepotActivity extends BaseSwipBackActivity<Commpart.HomeWaterRepotViewInf, HomeWaterRepotPresenter> implements Commpart.HomeWaterRepotViewInf {
    @BindView(R.id.region_tv_title)
    TextView regionTvTitle;
    @BindView(R.id.wantrepot_finish)
    LinearLayout wantrepotFinish;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.region_tool_bar)
    Toolbar regionToolBar;
    @BindView(R.id.wantrepot_ed_userName)
    EditText wantrepotEdUserName;
    @BindView(R.id.wantrepot_ed_phone)
    EditText wantrepotEdPhone;
    @BindView(R.id.wantrepot_ed_address)
    EditText wantrepotEdAddress;
    @BindView(R.id.ed_userName)
    EditText edUserName;
    @BindView(R.id.img_fault_one)
    ImageView imgFaultOne;
    @BindView(R.id.img_fault_two)
    ImageView imgFaultTwo;
    @BindView(R.id.img_fault_three)
    ImageView imgFaultThree;
    @BindView(R.id.ed_fault)
    EditText edFault;
    @BindView(R.id.img_fault_one_delete)
    ImageView imgFaultOneDelete;
    @BindView(R.id.img_fault_two_delete)
    ImageView imgFaultTwoDelete;
    @BindView(R.id.img_fault_three_delete)
    ImageView imgFaultThreeDelete;
    private SimpleDateFormat formatter    =   new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
    private Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
    private String    str    =    formatter.format(curDate);
    private String SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo1"+str+".png";
    private File fileUri = new File(SD_CARD_TEMP_DIR);
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo1"+str+".png");
    private String SD_CARD_TEMP_DIR2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo1"+str+".png";
    private File fileUri2 = new File(SD_CARD_TEMP_DIR2);
    private File fileCropUri2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo1"+str+".png");
    private String SD_CARD_TEMP_DIR3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo1"+str+".png";
    private File fileUri3 = new File(SD_CARD_TEMP_DIR3);
    private File fileCropUri3 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/waterknow_photo1"+str+".png");
    private static final int CODE_CAMERA_REQUEST_ONE = 0x1;
    private static final int CODE_RESULT_REQUEST_ONE = 0x2;

    private static final int CODECAMERA_REQUEST_TWO = 0x3;
    private static final int CODERESULT_REQUEST_TWO = 0X4;

    private static final int CODECAMERA_REQUEST_THREE = 0x5;
    private static final int CODERESULT_REQUEST_THREE = 0x6;

    private String token;
    private ArrayList<String> imgList = new ArrayList<>();
    private Uri imageUri;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_water_repot;
    }

    @Override
    protected void initView() {
        token = WaterKnowApp.getToken(this, "token");
        imgList.clear();
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.wantrepot_finish, R.id.commit, R.id.img_fault_one, R.id.img_fault_two, R.id.img_fault_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wantrepot_finish:
                finish();
                break;
            case R.id.commit:
                mPresenter = new HomeWaterRepotPresenter();
                if (!wantrepotEdUserName.getText().toString().trim().isEmpty()) {
                    if (ValidatorUtils.isMobile(wantrepotEdPhone.getText().toString().trim())) {
                        if (!wantrepotEdAddress.getText().toString().isEmpty()) {
                            if (!edUserName.getText().toString().isEmpty()) {
                                if (imgList != null) {
                                    StringBuffer imgArrs=new StringBuffer();
                                    StringBuffer imgFoots=new StringBuffer();
                                    for (int i = 0; i < imgList.size(); i++) {
                                        if (i<imgList.size()-1){
                                            imgArrs.append(imgList.get(i)).append(",");
                                            imgFoots.append("jpg").append(",");
                                        }else {
                                            imgArrs.append(imgList.get(i));
                                            imgFoots.append("jpg");
                                        }
                                    }
                                    if (!edFault.getText().toString().isEmpty()) {
                                        Map<String, String> map = new HashMap<>();
                                        map.put("token", token);
                                        map.put("contacts", wantrepotEdUserName.getText().toString().trim());
                                        map.put("contacts_number", wantrepotEdPhone.getText().toString().trim());
                                        map.put("fail_address", wantrepotEdAddress.getText().toString().trim() + edUserName.getText().toString().trim());
                                        map.put("fail_description", edFault.getText().toString().trim());
                                        mPresenter.setHomeWaterRepot(map, imgArrs.toString(), imgFoots.toString());
                                        mPresenter.attarchView(this);
                                    } else{
                                        Toast.makeText(this, "故障描述请您描述一下", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "请拍几张故障图片", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "故障地址不能为空", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "您的手机格式不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "联系人不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_fault_one:
                initImage(CODE_CAMERA_REQUEST_ONE,fileUri);
                break;
            case R.id.img_fault_two:
                initImage(CODECAMERA_REQUEST_TWO,fileUri2);
                break;
            case R.id.img_fault_three:
                initImage(CODECAMERA_REQUEST_THREE,fileUri3);
                break;
        }
    }

    private void initImage(int CODE_CAMERA_REQUEST, File file) {
        if (hasSdcard()) {
            imageUri = Uri.fromFile(file);
            //通过FileProvider创建一个content类型的Uri
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                imageUri = FileProvider.getUriForFile(HomeWaterRepotActivity.this, "com.smartcityin.waterknow.fileprovider", fileUri);
            }
            PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_CAMERA_REQUEST_ONE && resultCode == RESULT_OK) {
            String base64NONseal = initSetImage(CODE_RESULT_REQUEST_ONE, imgFaultOne,SD_CARD_TEMP_DIR,fileCropUri);
            imgList.add(base64NONseal);
            imgFaultTwo.setVisibility(View.VISIBLE);
            initImageDelete(imgFaultOneDelete,base64NONseal,imgFaultOne);
        } else if (requestCode == CODECAMERA_REQUEST_TWO && resultCode == RESULT_OK) {
            String base64NONseal = initSetImage(CODERESULT_REQUEST_TWO, imgFaultTwo,SD_CARD_TEMP_DIR2,fileCropUri2);
            imgList.add(base64NONseal);
            imgFaultThree.setVisibility(View.VISIBLE);
            initImageDelete(imgFaultTwoDelete,base64NONseal,imgFaultTwo);
        } else if (requestCode == CODECAMERA_REQUEST_THREE && resultCode == RESULT_OK) {
            String base64NONseal = initSetImage(CODERESULT_REQUEST_THREE, imgFaultThree,SD_CARD_TEMP_DIR3,fileCropUri3);
            imgList.add(base64NONseal);
            initImageDelete(imgFaultThreeDelete,base64NONseal,imgFaultThree);
        }
    }

    private void initImageDelete(final ImageView view, final String base64NONseal, final ImageView imgView) {
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.guzhangtupian);
                imgList.remove(base64NONseal);
                view.setVisibility(View.GONE);
                switch (v.getId()){
                    case R.id.img_fault_one_delete:
                        imgFaultTwo.setVisibility(View.GONE);
                        imgFaultThree.setVisibility(View.GONE);
                        imgFaultTwoDelete.setVisibility(View.GONE);
                        imgFaultThreeDelete.setVisibility(View.GONE);
                        break;
                    case R.id.img_fault_two_delete:
                        imgFaultThree.setVisibility(View.GONE);
                        imgFaultThreeDelete.setVisibility(View.GONE);
                        break;
                }
                imgView.setOnClickListener(this);
            }
        });
    }
    private static final int OUTPUT_X = 480;
    private static final int OUTPUT_Y = 480;
    private String initSetImage(int CODE_RESULT_REQUEST, ImageView view,String SD,File file) {
        Uri cropImageUri = Uri.fromFile(file);
        PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 0, 0, OUTPUT_X, OUTPUT_Y, CODE_RESULT_REQUEST);
        Bitmap myBitmap = BitmapFactory.decodeFile(SD);
        int angle = BitmapUtils.readPictureDegree(SD);
        Bitmap bitmap = BitmapUtils.rotaingImageView(angle, myBitmap);
        switch (view.getId()){
            case R.id.img_fault_one:
                view.setImageBitmap(bitmap);
                break;
            case R.id.img_fault_two:
                view.setImageBitmap(bitmap);
                break;
            case R.id.img_fault_three:
                view.setImageBitmap(bitmap);
                break;
        }
        view.setOnClickListener(null);
        Bitmap smallBitmap = BitmapUtils.getSmallBitmap(SD);
        String base64NONseal = BitmapUtils.Bitmap2StrByBase64(smallBitmap);
        return base64NONseal;
    }
    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void getHomeWaterRepot(MyInToolPAymentEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                Intent intent=new Intent(HomeWaterRepotActivity.this,CommitSuccessActivity.class);
                intent.putExtra("title","我要报修");
                intent.putExtra("content","提交成功");
                startActivity(intent);
                finish();
                break;
            case 501:
                WaterKnowApp.reNewLogin(this);
                break;
            case 502:
                WaterKnowApp.reNewLogin(this);
                break;
        }
    }
}
