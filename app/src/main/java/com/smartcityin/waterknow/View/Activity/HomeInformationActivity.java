package com.smartcityin.waterknow.View.Activity;


import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.HomeInformationAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.ListViewForScrollView;
import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.HomeInforamtionPresenter;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeInformationActivity extends BaseSwipBackActivity<Commpart.HomeInformationViewInf,HomeInforamtionPresenter>implements Commpart.HomeInformationViewInf{

    @BindView(R.id.information_notice_tv_title)
    TextView informationNoticeTvTitle;
    @BindView(R.id.information_notice_tv_content)
    TextView informationNoticeTvContent;
    @BindView(R.id.line_bt_notice)
    LinearLayout lineBtNotice;
    @BindView(R.id.information_lv)
    ListViewForScrollView informationLv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<HomeInformationEntity.DataBean> entitiesList = new ArrayList<>();
    private String token;
    private SharedPreferences sharedPreferences;
    private HomeInformationAdapert adapert;

    @Override
    protected void initAdapert() {
        adapert = new HomeInformationAdapert(entitiesList, this);
        informationLv.setAdapter(adapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_information;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        tvTitle.setText("消息中心");
        sharedPreferences = WaterKnowApp.getSharedPreferences(this);
        token = sharedPreferences.getString("token", "");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new HomeInforamtionPresenter();
        mPresenter.attarchView(this);
        mPresenter.setHomeInfmation(token);
    }

    @Override
    protected void initListener() {

    }
    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getHomeInformation(HomeInformationEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                List<HomeInformationEntity.DataBean> data = entity.getData();
                entitiesList.addAll(data);
                adapert.notifyDataSetChanged();
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
