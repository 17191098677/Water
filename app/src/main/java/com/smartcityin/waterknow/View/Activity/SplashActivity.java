package com.smartcityin.waterknow.View.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;

import java.io.File;

import butterknife.ButterKnife;
import cn.bingoogolapple.photopicker.util.BGAPhotoHelper;
import cn.bingoogolapple.photopicker.util.BGAPhotoPickerUtil;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 启动页
 */
public class SplashActivity extends Activity {
    private Handler handler=new Handler();
    private int timer=3;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(timer==0){
                Intent intent=new Intent(SplashActivity.this, GuideActivity.class);
                startActivity(intent);
                finish();
            }else{
                timer--;
                handler.postDelayed(runnable,500);
            }

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_page);
        ButterKnife.bind(this);

        handler.postDelayed(runnable,500);
    }



}
