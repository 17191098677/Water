package com.smartcityin.waterknow.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awarmisland.android.popularrefreshlayout.RefreshLayout;
import com.awarmisland.android.popularrefreshlayout.RefreshListener;
import com.smartcityin.waterknow.Adapert.UserWaterLadderAdapert;
import com.smartcityin.waterknow.Base.BaseFragment;
import com.smartcityin.waterknow.Base.ListViewForScrollView;
import com.smartcityin.waterknow.Entity.UserWater.UserWaterEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.UserWaterPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.View.Activity.HomeActivity;
import com.smartcityin.waterknow.View.Activity.RegisterBindingWaterMeterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UseWaterFragment extends BaseFragment<Commpart.UserWaterViewInf, UserWaterPresenter> implements Commpart.UserWaterViewInf {
    //设置头标题
    @BindView(R.id.wisdom_tv_title)
    TextView wisdomTvTitle;
    @BindView(R.id.userWater_tool_bar)
    Toolbar userWaterToolBar;
    //警告栏提示水费不多
    @BindView(R.id.notice_waterBalance)
    LinearLayout noticeWaterBalance;
    //头像
    @BindView(R.id.useWater_header_img)
    CircleImageView useWaterHeaderImg;
    //绑定水表号
    @BindView(R.id.userWater_bingWater)
    Button userWaterBingWater;
    //月用水量
    @BindView(R.id.userWater_tv_waterMonthBalance)
    TextView userWaterTvWaterMonthBalance;
    //月用水额度
    @BindView(R.id.userWater_tv_waterMonthRmb)
    TextView userWaterTvWaterMonthRmb;
    //年用水量
    @BindView(R.id.userWater_tv_waterYearBalance)
    TextView userWaterTvWaterYearBalance;
    //年用水额度
    @BindView(R.id.userWater_tv_waterYearRmb)
    TextView userWaterTvWaterYearRmb;
    //总用水量
    @BindView(R.id.userWater_tv_TotalBalance)
    TextView userWaterTvTotalBalance;

    //阶梯水价显示
    @BindView(R.id.userWater_lv)
    ListViewForScrollView userWaterLv;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    @BindView(R.id.tv_water_id)
    TextView tvWaterId;
    Unbinder unbinder;
    private HomeActivity activity;
    private ArrayList<UserWaterEntity.DataBean.HierarchicalDataBean> mList = new ArrayList<>();
    private UserWaterLadderAdapert userWaterLadderAdapert;
    private Handler handler = new Handler();
    private String meterID;

    @Override
    protected UserWaterPresenter createPresenter() {
        return new UserWaterPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_use_water;
    }

    @Override
    protected void initAdapert() {
        userWaterLadderAdapert = new UserWaterLadderAdapert(mList, activity);
        userWaterLv.setAdapter(userWaterLadderAdapert);
    }

    @Override
    protected void initListener() {
        refresh.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        refresh.finishRefresh();
                        initData();
                        mPresenter.attarchView(UseWaterFragment.this);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        String username = WaterKnowApp.getToken(activity, "username");
        meterID = WaterKnowApp.getUserInformationSharePreferences(activity).getString(username + "meterID", null);
        if (meterID==null){
            userWaterBingWater.setVisibility(View.VISIBLE);
            tvWaterId.setVisibility(View.GONE);
            userWaterBingWater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, RegisterBindingWaterMeterActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            userWaterBingWater.setVisibility(View.GONE);
            tvWaterId.setVisibility(View.VISIBLE);
            tvWaterId.setText("水表号 ："+meterID);
        }
    }

    @Override
    protected void initView() {
        activity = (HomeActivity) getActivity();
        activity.setSupportActionBar(userWaterToolBar);

    }

    @Override
    public void initData() {
        mPresenter = new UserWaterPresenter();
        String username = WaterKnowApp.getToken(activity, "username");
        meterID = WaterKnowApp.getUserInformationSharePreferences(activity).getString(username + "meterID", null);
        tvWaterId.setText("水表号 ："+meterID);
        String token = WaterKnowApp.getToken(activity, "token");
        mPresenter.setUserWater(token, meterID);
        mPresenter.attarchView(this);
    }

    @Override
    public void getUserWaterData(UserWaterEntity userWaterEntity) {
        int code = userWaterEntity.getCode();
        switch (code) {
            case 200:
                UserWaterEntity.DataBean data = userWaterEntity.getData();
                userWaterTvWaterMonthBalance.setText(data.getMonthly_water_consumption() + "");
                userWaterTvWaterMonthRmb.setText(data.getMonthly_water_rate() + "");
                userWaterTvWaterYearBalance.setText(data.getYearly_water_consumption() + "");
                userWaterTvWaterYearRmb.setText(data.getYearly_water_rate() + "");
                userWaterTvTotalBalance.setText(data.getWater_meter_reading() + "");
                List<UserWaterEntity.DataBean.HierarchicalDataBean> hierarchical_data = data.getHierarchical_data();
                mList.addAll(hierarchical_data);
                userWaterLadderAdapert.notifyDataSetChanged();
                break;
            case 501:
                WaterKnowApp.reNewLogin(activity);
                break;
            case 502:
                WaterKnowApp.reNewLogin(activity);
                break;
        }
    }
}
