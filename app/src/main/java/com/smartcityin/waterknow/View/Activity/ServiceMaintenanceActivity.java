package com.smartcityin.waterknow.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.ServiceMaintenanceAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.ServiceMaintenancePresenter;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceMaintenanceActivity extends BaseSwipBackActivity<Commpart.ServiceMaintenanceViewInf,ServiceMaintenancePresenter> implements Commpart.ServiceMaintenanceViewInf{


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.service_maintenance_lv)
    ListView serviceMaintenanceLv;
    private ArrayList<ServiceMaintenanceEntity.DataBean> mList=new ArrayList<>();
    private ServiceMaintenanceAdapert mAdapert;

    @Override
    protected void initAdapert() {
        mAdapert = new ServiceMaintenanceAdapert(this,mList);
        serviceMaintenanceLv.setAdapter(mAdapert);
        initVisiable();
    }

    private void initVisiable() {
        if (mList.size()<=0){
            tv.setVisibility(View.VISIBLE);
            serviceMaintenanceLv.setVisibility(View.GONE);
        }else{
            tv.setVisibility(View.GONE);
            serviceMaintenanceLv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_maintenance;
    }

    @Override
    protected void initView() {
        tvTitle.setText("维护统计");
        setSupportActionBar(toolbar);
        String token = WaterKnowApp.getToken(this, "token");
        mPresenter=new ServiceMaintenancePresenter();
        mPresenter.setServiceMaintenance(token);
        mPresenter.attarchView(this);
    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getServiceMaintenance(ServiceMaintenanceEntity serviceMaintenanceEntity) {
        int code = serviceMaintenanceEntity.getCode();
        switch (code){
            case 200:
                List<ServiceMaintenanceEntity.DataBean> data = serviceMaintenanceEntity.getData();
                mList.addAll(data);
                mAdapert.notifyDataSetChanged();
                initVisiable();
                break;
        }
    }
}
