package com.smartcityin.waterknow.View.Activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.HomeRegionAdapert;
import com.smartcityin.waterknow.Base.BaseEditText;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Dao.UserEntityDao;
import com.smartcityin.waterknow.Entity.Home.RegionEntity;
import com.smartcityin.waterknow.Entity.UserEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.HomeRegionPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.DaoUtils;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.View.Fragment.HomeFragment;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeRegionActivity extends BaseSwipBackActivity<Commpart.HomeRegionViewInf, HomeRegionPresenter> implements Commpart.HomeRegionViewInf {


    @BindView(R.id.region_reLv)
    RecyclerView regionReLv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private HomeRegionAdapert homeRegionAdapert;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mPresenter = new HomeRegionPresenter();
        return R.layout.activity_home_region;
    }

    @Override
    protected void initView() {
        tvTitle.setText("省市选择");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        super.initData();
        String token = WaterKnowApp.getToken(this, "token");
        mPresenter=new HomeRegionPresenter();
        mPresenter.attarchView(this);
        mPresenter.setRegion(token);
    }

    @OnClick({R.id.line_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
        }
    }


    @Override
    public void getHomeRegionData(final RegionEntity regionEntity) {
        switch (regionEntity.getCode()){
            case 200:
                 final ArrayList<String> list=new ArrayList<>();
                for (int i = 0; i <=regionEntity.getData().size()-1; i++) {
                    list.add(regionEntity.getData().get(i).getName());
                }
                LinearLayoutManager manager = new LinearLayoutManager(this) {
                    @Override
                    public boolean canScrollVertically() {
                        // 直接禁止垂直滑动
                        return false;
                    }
                };
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                regionReLv.setLayoutManager(manager);
                homeRegionAdapert = new HomeRegionAdapert(list, this);
                regionReLv.setAdapter(homeRegionAdapert);
                homeRegionAdapert.notifyDataSetChanged();
                regionReLv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                homeRegionAdapert.setOnClicker(new HomeRegionAdapert.OnClicker() {
                    @Override
                    public void setOnClickerListener(int position) {
                        Intent intent=getIntent();
                        intent.putExtra("region",list.get(position));
                        setResult(-1,intent);
                        finish();
                    }
                });
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
