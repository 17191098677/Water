package com.smartcityin.waterknow.View.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartcityin.waterknow.Adapert.GuideVpAdapert;
import com.smartcityin.waterknow.Base.BaseActivity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class GuideActivity extends BaseActivity implements  View.OnClickListener {

    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.spot_First)
    ImageView spotFirst;
    @BindView(R.id.spot_Two)
    ImageView spotTwo;
    @BindView(R.id.spot_Three)
    ImageView spotThree;
    @BindView(R.id.guide_spot)
    LinearLayout guideSpot;
    @BindView(R.id.bt_guide_start)
    Button btGuideStart;
    private ArrayList<String> img_urls = new ArrayList<>();
    private ArrayList<ImageView> imgView = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    /**
     * 添加引导页图片地址
     */
    protected void initData() {
        img_urls.add("http://img1.imgtn.bdimg.com/it/u=989341999,236255160&fm=27&gp=0.jpg");
        img_urls.add("http://www.zhlzw.com/UploadFiles/Article_UploadFiles/201210/20121006154614772.jpg");
        img_urls.add("http://www.zhlzw.com/UploadFiles/Article_UploadFiles/201204/20120412123917884.jpg");
        for (int i = 0; i < img_urls.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.add(imageView);
        }
    }

    protected void initView() {
        sharedPreferences = getSharedPreferences("WaterKnow",MODE_PRIVATE);
        int waterKnow = sharedPreferences.getInt("WaterKnow", 0);
        if(waterKnow!=0){
            intentStartPage();
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt("WaterKnow",++waterKnow);
        edit.commit();
    }
    private void intentStartPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * 让引导页轮播图与指示器相连接
     */
    protected void initListener() {
        guideVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        spotFirst.setImageResource(R.drawable.guide_shader_selector);
                        spotTwo.setImageResource(R.drawable.guide_shader);
                        spotThree.setImageResource(R.drawable.guide_shader);
                        btGuideStart.setVisibility(View.GONE);
                        guideSpot.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        spotTwo.setImageResource(R.drawable.guide_shader_selector);
                        spotFirst.setImageResource(R.drawable.guide_shader);
                        spotThree.setImageResource(R.drawable.guide_shader);
                        btGuideStart.setVisibility(View.GONE);
                        guideSpot.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btGuideStart.setVisibility(View.VISIBLE);
                        guideSpot.setVisibility(View.GONE);
                        spotThree.setImageResource(R.drawable.guide_shader_selector);
                        spotTwo.setImageResource(R.drawable.guide_shader);
                        spotFirst.setImageResource(R.drawable.guide_shader);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btGuideStart.setOnClickListener(this);
    }

    //给引导页设置适配器
    protected void initAdapert() {
        GuideVpAdapert adapert = new GuideVpAdapert(this, img_urls, imgView);
        guideVp.setAdapter(adapert);
    }

    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        return R.layout.activity_guide;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_guide_start:
                intentStartPage();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        takePhoto();
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
}
