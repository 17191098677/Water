package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.ServiceUserHelpAdapert;
import com.smartcityin.waterknow.Base.BaseEditText;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.ServiceUserHelpPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.StyleUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceWaterHelpActivity extends BaseSwipBackActivity<Commpart.ServiceUserHelpViewInf,ServiceUserHelpPresenter> implements Commpart.ServiceUserHelpViewInf{


    @BindView(R.id.ed_Search)
    BaseEditText edSearch;
    @BindView(R.id.home_search_line)
    LinearLayout homeSearchLine;
    @BindView(R.id.service_newsCenter_lv)
    ListView serviceNewsCenterLv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<ServiceUserHelpEntity.DataBean> mList=new ArrayList();
    private ServiceUserHelpAdapert serviceUserHelpAdapert;

    @Override
    protected void initAdapert() {
        serviceUserHelpAdapert = new ServiceUserHelpAdapert(mList,this);
        serviceNewsCenterLv.setAdapter(serviceUserHelpAdapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_water_help;
    }

    @Override
    protected void initView() {
        tvTitle.setText("用水帮助");
    }

    @Override
    protected void initListener() {
        edSearch.setOnListener(new BaseEditText.OnFocusChangedListener() {
            @Override
            public void onFocusChanged(View v, boolean hasFoucs) {

            }
        });
        serviceNewsCenterLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ServiceWaterHelpActivity.this, WebDetailsActivity.class);
                intent.putExtra("WebUrl",mList.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        String token = WaterKnowApp.getToken(this, "token");
         mPresenter=new ServiceUserHelpPresenter();
        mPresenter.attarchView(this);
         mPresenter.setUserHelp(token,0);
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getServiceUserHelp(ServiceUserHelpEntity serviceUserHelpEntity) {
        int code = serviceUserHelpEntity.getCode();
        switch (code){
            case 200:
                List<ServiceUserHelpEntity.DataBean> data = serviceUserHelpEntity.getData();
                mList.addAll(data);
                serviceUserHelpAdapert.notifyDataSetChanged();
                break;
        }
    }
}
