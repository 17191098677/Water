package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseActivity;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.FragmentUtils;
import com.smartcityin.waterknow.Utils.StyleUtils;
import com.smartcityin.waterknow.View.Fragment.HomeFragment;
import com.smartcityin.waterknow.View.Fragment.PersonalFragment;
import com.smartcityin.waterknow.View.Fragment.ServiceFragment;
import com.smartcityin.waterknow.View.Fragment.UseWaterFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.frame_display)
    FrameLayout frameDisplay;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_use_water)
    RadioButton rbUseWater;
    @BindView(R.id.rb_service)
    RadioButton rbService;
    @BindView(R.id.rb_personal)
    RadioButton rbPersonal;
    @BindView(R.id.water_rg)
    RadioGroup waterRg;
    public FragmentManager fragmentManager;
    public HomeFragment homeFragment;
    public UseWaterFragment useWaterFagment;
    public ServiceFragment serviceFragment;
    public PersonalFragment personalFragment;
    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        //改变状态栏字体的颜色
        StyleUtils.setStatusBarMode(this,true);
        //通过Radiogroup来获取第一个按钮 默认为点击状态
        RadioButton childAt = (RadioButton) waterRg.getChildAt(0);
        childAt.setChecked(true);
        //来获取Fragment管理器
        fragmentManager = getSupportFragmentManager();
        //实例化首页Fragment对象
        homeFragment = new HomeFragment();
        //通过Fragment管理器来提交事物，让RadioButton与Fragment关联
        FragmentUtils.addFragment(fragmentManager,homeFragment,R.id.frame_display,false);
    }
    protected void initData(){
    }
    @Override
    protected void initListener() {
        waterRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        hideAllFragment();
        switch (checkedId) {
            case R.id.rb_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    FragmentUtils.addFragment(fragmentManager,homeFragment,R.id.frame_display,false);
                } else {
                    FragmentUtils.showFragment(homeFragment);
                }
                break;
            case R.id.rb_use_water:
                if (useWaterFagment == null) {
                    useWaterFagment = new UseWaterFragment();
                    FragmentUtils.addFragment(fragmentManager,useWaterFagment,R.id.frame_display,false);
                } else {
                    FragmentUtils.showFragment(useWaterFagment);
                }
                break;
            case R.id.rb_service:
                if (serviceFragment == null) {
                    serviceFragment = new ServiceFragment();
                    FragmentUtils.addFragment(fragmentManager,serviceFragment,R.id.frame_display,false);
                } else {
                    FragmentUtils.showFragment(serviceFragment);
                }
                break;
            case R.id.rb_personal:
                if (personalFragment == null) {
                    personalFragment =new PersonalFragment();
                    FragmentUtils.addFragment(fragmentManager,personalFragment,R.id.frame_display,false);
                } else {
                    FragmentUtils.showFragment(personalFragment);
                }
                break;
        }
    }
    private void hideAllFragment() {
        if (homeFragment != null)
            FragmentUtils.hideFragment(homeFragment);
        if (useWaterFagment != null)
            FragmentUtils.hideFragment(useWaterFagment);
        if (serviceFragment != null)
            FragmentUtils.hideFragment(serviceFragment);
        if (personalFragment != null)
            FragmentUtils.hideFragment(personalFragment);
    }
    private boolean isQuit = false;

    @Override
    public void onBackPressed() {
        if (!isQuit) {
            Toast.makeText(HomeActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            isQuit = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        isQuit = false;
                    }
                }
            }).start();
        } else {
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = FragmentUtils.findFragment(getSupportFragmentManager(), HomeFragment.class);
        fragment.onActivityResult(requestCode,resultCode,data);
        if (requestCode==0&&resultCode==-2){
            Fragment fragment1 = FragmentUtils.findFragment(getSupportFragmentManager(), PersonalFragment.class);
            fragment1.onActivityResult(requestCode,resultCode,data);
        }
    }

}
