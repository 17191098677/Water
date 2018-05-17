package com.smartcityin.waterknow.View.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.MyAnnualQueryAdapert;
import com.smartcityin.waterknow.Base.BaseFragment;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.View.Fragment.AnnualChildrenFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAnnualQueryActivity extends BaseSwipBackActivity {

    @BindView(R.id.myAnnualQuery_tab)
    TabLayout myAnnualQueryTab;
    @BindView(R.id.myAnnualQuery_vp)
    ViewPager myAnnualQueryVp;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private AnnualChildrenFragment annualChildrenFragment;
    private String token;
    private String meterID;

    @Override
    protected void initAdapert() {
        MyAnnualQueryAdapert myAnnualQueryAdapert = new MyAnnualQueryAdapert(getSupportFragmentManager(), mTitleList, fragmentList);
        myAnnualQueryVp.setAdapter(myAnnualQueryAdapert);
    }

    @Override
    protected void initData() {
        super.initData();
        mTitleList.add("2018年");
        mTitleList.add("2017年");
        mTitleList.add("2016年");
        for (int i = 0; i < mTitleList.size(); i++) {
            AnnualChildrenFragment annualChildrenFragment=new AnnualChildrenFragment().newInstance(getToken(),getMeterID(),getYear(mTitleList.get(i)));
            fragmentList.add(annualChildrenFragment);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_annual_query;
    }

    @Override
    protected void initView() {
        myAnnualQueryTab.setupWithViewPager(myAnnualQueryVp);
        setSupportActionBar(toolbar);
        tvTitle.setText("年度查询");

    }

    @Override
    protected void initListener() {
        myAnnualQueryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                /*String selectTab= tab.getText().toString();
                if (mTitleList.get(0).equals(selectTab)){
                    annualChildrenFragment = AnnualChildrenFragment.newInstance(getToken(),getMeterID(),getYear(mTitleList.get(0)));
                }else if (mTitleList.get(1).equals(selectTab)){
                    annualChildrenFragment = AnnualChildrenFragment.newInstance(getToken(),getMeterID(),getYear(mTitleList.get(1)));
                }else if(mTitleList.get(2).equals(selectTab)){
                    annualChildrenFragment = AnnualChildrenFragment.newInstance(getToken(),getMeterID(),getYear(mTitleList.get(2)));
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private String getToken(){
          token = WaterKnowApp.getToken(this, "token");
        return token;
    }
    private String getMeterID(){
        String username = WaterKnowApp.getToken(this, "username");
        SharedPreferences sp = WaterKnowApp.getUserInformationSharePreferences(this);
        meterID = sp.getString(username + "meterID", "");
        return meterID;
    }
    private String getYear(String s) {
        String substring = s.substring(0, s.length()-1);
        return substring;
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }
}
