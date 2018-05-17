package com.smartcityin.waterknow.Base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Utils.StyleUtils;

import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */

/**
 *用来达到统一管理Activity
 */
public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
    protected P mPresenter ;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        //沉浸式状态栏
        initStatusBar();
        //改变状态栏字体颜色
        StyleUtils.setStatusBarMode(this,true);
        WaterKnowApp.WATER_CONTEXT = this;
        //绑定ButterKnife
        ButterKnife.bind(this);

        //用来关联V和P
        if(mPresenter!=null)
            mPresenter.attarchView((V) this);
        initView();
        initData();
        initAdapert();
        initListener();
    }

    protected  void initStatusBar(){
        //得到当前界面的装饰视图
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected abstract void initAdapert();

    //用来关联XML文件
    protected abstract int getLayoutId();

    //做试图操作
    protected abstract void initView();
    //数据的相关操作
    protected  void initData(){

    }
    //做监听
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null)
            mPresenter.detachView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
