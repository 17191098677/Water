package com.smartcityin.waterknow.View.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.LoginDialog;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.PresenterCountry.UserInformationPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.CoreUtils;
import com.smartcityin.waterknow.Utils.DataCleanManager;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.LocalVersionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySetActivity extends BaseSwipBackActivity {

    @BindView(R.id.mySet_img_app)
    ImageView mySetImgApp;
    @BindView(R.id.mySet_tv_Edition)
    TextView mySetTvEdition;
    @BindView(R.id.mySet_tv_cache)
    TextView mySetTvCache;
    @BindView(R.id.mySet_cleanCache_bt)
    RelativeLayout mySetCleanCacheBt;
    @BindView(R.id.mySet_informationSet_bt)
    RelativeLayout mySetInformationSetBt;
    @BindView(R.id.mySet_My_bt)
    RelativeLayout mySetMyBt;
    @BindView(R.id.mySet_About_service_bt)
    RelativeLayout mySetAboutServiceBt;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bt_exit)
    TextView btExit;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_set;
    }

    @Override
    protected void initView() {
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            mySetTvCache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvTitle.setText("设置");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        super.initData();
        String localVersionName = LocalVersionUtils.getLocalVersionName(this);
        mySetTvEdition.setText(localVersionName);
    }

    @OnClick({R.id.line_finish, R.id.mySet_cleanCache_bt, R.id.mySet_informationSet_bt, R.id.mySet_My_bt, R.id.mySet_About_service_bt, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.mySet_cleanCache_bt:

                initCleanCache();
                break;
            case R.id.mySet_informationSet_bt:
                IntentUtils.startIntent(this, MySetUserInformationActivity.class);
                break;
            //服务协议
            case R.id.mySet_My_bt:
                IntentUtils.putExtra(this, MyCompanyProfileActivity.class, "title", "服务协议");
                break;
            //公司简介
            case R.id.mySet_About_service_bt:
                IntentUtils.putExtra(this, MyCompanyProfileActivity.class, "title", "公司简介");
                break;
            case R.id.bt_exit:
                SharedPreferences.Editor editor= WaterKnowApp.getSharedPreferencesEditor(this);
                editor.clear();
                editor.commit();
                Toast.makeText(this, "您已退出登录，请重新登录", Toast.LENGTH_LONG).show();
                Intent intent=getIntent();
                intent.putExtra("exit",true);
                setResult(-2,intent);
                finish();
                break;
        }
    }

    private void initCleanCache() {
        final LoginDialog dialog=new LoginDialog(this);
        dialog.show();
        dialog.setTvContent("是否清楚缓存？ ");
        dialog.setLeftButton("确定", new BaseDialog.OnClickerListenerLeft() {
            @Override
            public void setOnClicker() {
                DataCleanManager.clearAllCache(MySetActivity.this);
                try {
                    String totalCacheSize = DataCleanManager.getTotalCacheSize(MySetActivity.this);
                    mySetTvCache.setText(totalCacheSize);
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
            @Override
            public void setOnClicker() {
                dialog.dismiss();
            }
        });
    }
}
