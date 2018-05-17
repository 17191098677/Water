package com.smartcityin.waterknow.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartcityin.waterknow.Adapert.ServiceGridAdapert;
import com.smartcityin.waterknow.Base.BaseFragment;
import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.ServiceGridEntity;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.ServicePresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.FragmentUtils;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.View.Activity.HomeActivity;
import com.smartcityin.waterknow.View.Activity.ServiceMaintenanceActivity;
import com.smartcityin.waterknow.View.Activity.ServiceNetWorkActivity;
import com.smartcityin.waterknow.View.Activity.ServiceNewsCenterActivity;
import com.smartcityin.waterknow.View.Activity.ServiceWaterHelpActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceFragment extends BaseFragment<Commpart.ServiceViewInf,ServicePresenter> implements Commpart.ServiceViewInf, AdapterView.OnItemClickListener {

    @BindView(R.id.userWater_tool_bar)
    Toolbar userWaterToolBar;
    @BindView(R.id.service_titleImg)
    ImageView serviceTitleImg;
    @BindView(R.id.service_reLV)
    GridView serviceReLV;
    private HomeActivity activity;
    private ArrayList<ServiceGridEntity> mList=new ArrayList<>();

    @Override
    protected ServicePresenter createPresenter() {
        return new ServicePresenter();
    }

    @Override
    public int getLayoutId() {
        activity = (HomeActivity) getActivity();
        return R.layout.fragment_service;
    }

    @Override
    protected void initAdapert() {

    }

    @Override
    protected void initListener() {
        serviceReLV.setOnItemClickListener(this);
    }

    @Override
    protected void initView() {
        activity.setSupportActionBar(userWaterToolBar);
    }

    @Override
    public void initData() {
        mList.add(new ServiceGridEntity("服务网点", R.drawable.service_a));
        mList.add(new ServiceGridEntity("新闻中心", R.drawable.service_b));
        mList.add(new ServiceGridEntity("用水帮助", R.drawable.service_c));
        mList.add(new ServiceGridEntity("维护统计", R.drawable.service_d));
        ServiceGridAdapert adapert=new ServiceGridAdapert(mList,activity);
        serviceReLV.setAdapter(adapert);
    }

    @Override
    public void getServiceData(ArrayList<ServiceGridEntity> list) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (mList.get(position).getTitle()) {
            case "服务网点":
                IntentUtils.startIntent(activity, ServiceNetWorkActivity.class);
                break;
            case "新闻中心":
                IntentUtils.startIntent(activity, ServiceNewsCenterActivity.class);
                break;
            case "用水帮助":
                IntentUtils.startIntent(activity, ServiceWaterHelpActivity.class);
                break;
            case "维护统计":
                IntentUtils.startIntent(activity, ServiceMaintenanceActivity.class);
                break;
        }
    }

}
