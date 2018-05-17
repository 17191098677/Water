package com.smartcityin.waterknow.View.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awarmisland.android.popularrefreshlayout.RefreshLayout;
import com.awarmisland.android.popularrefreshlayout.RefreshListener;
import com.smartcityin.waterknow.Adapert.HomeNavigationAdapert;
import com.smartcityin.waterknow.Adapert.HomeNewsAdapert;
import com.smartcityin.waterknow.Adapert.HomeSwitchNumberAdapert;
import com.smartcityin.waterknow.Adapert.HomeXBannerAdapert;
import com.smartcityin.waterknow.Base.BaseFragment;
import com.smartcityin.waterknow.Base.ListViewForScrollView;
import com.smartcityin.waterknow.Base.WaterView;
import com.smartcityin.waterknow.Entity.Home.HomeBannerEntity;
import com.smartcityin.waterknow.Entity.Home.HomeMeterStateEntity;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.HomeNavigation;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.HomePresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.View.Activity.HomeActivity;
import com.smartcityin.waterknow.View.Activity.HomeInformationActivity;
import com.smartcityin.waterknow.View.Activity.HomePaymentActivity;
import com.smartcityin.waterknow.View.Activity.HomeRegionActivity;
import com.smartcityin.waterknow.View.Activity.HomeSearchActivity;
import com.smartcityin.waterknow.View.Activity.HomeWaterKnowLedgeActivity;
import com.smartcityin.waterknow.View.Activity.HomeWaterRepotActivity;
import com.smartcityin.waterknow.View.Activity.HomeWisdomLifeActivity;
import com.smartcityin.waterknow.View.Activity.WebDetailsActivity;
import com.stx.xhb.xbanner.XBanner;

import org.bouncycastle.jce.provider.JCEIESCipher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<Commpart.HomeViewInf, HomePresenter> implements Commpart.HomeViewInf {
    //获取地理位置按钮
    @BindView(R.id.line_orientation)
    LinearLayout lineOrientation;
    //搜索按钮
    @BindView(R.id.home_search)
    LinearLayout homeSearch;
    //消息图片按钮
    @BindView(R.id.home_img_information)
    ImageView homeImgInformation;
    //toolbar
    @BindView(R.id.home_title)
    Toolbar homeTitle;
    //中部四个功能按钮
    @BindView(R.id.home_grid)
    GridView homeGrid;
    //轮播图
    @BindView(R.id.home_xbanner)
    XBanner homeXbanner;
    //水费余额
    @BindView(R.id.tv_water_balance)
    TextView tvWaterBalance;
    //电池电量
    @BindView(R.id.home_tv_battery_level)
    TextView homeTvBatteryLevel;
    //设备状态
    @BindView(R.id.home_tv_signal_intensity)
    TextView homeTvSignalIntensity;
    //新闻列表
    @BindView(R.id.home_news)
    ListViewForScrollView homeNews;
    //自定义360加速球效果
    @BindView(R.id.home_Water)
    WaterView homeWater;
    //刷新按钮
    @BindView(R.id.home_swipeRefresh)
    RefreshLayout homeSwipeRefresh;
    @BindView(R.id.tv_region)
    TextView tvRegion;
    @BindView(R.id.water_Id)
    TextView waterId;
    @BindView(R.id.home_lv_waterID)
    ListViewForScrollView homeLvWaterID;
    @BindView(R.id.bt_waterID)
    RelativeLayout btWaterID;
    private HomeActivity activity;
    private ArrayList<HomeNavigation> navigationsList = new ArrayList<>();
    private Handler handler = new Handler();
    private SharedPreferences sharedPreferences;
    private String token;
    private ArrayList<HomeNewsEntity.DataBean> mList = new ArrayList<>();
    private HomeNewsAdapert homeNewsadapert;
    private String username;
    private int water_remain;
    private ArrayList<SwitchNumberEntity.DataBean> switchNumberList=new ArrayList<>();
    private HomeSwitchNumberAdapert homeSwitchNumberAdapert;
    private boolean isFlag;
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }
    @Override
    public int getLayoutId() {
        activity = (HomeActivity) getActivity();
        return R.layout.fragment_home;
    }


    @Override
    protected void initAdapert() {
        homeGrid.setAdapter(new HomeNavigationAdapert(navigationsList, activity));
        homeNewsadapert = new HomeNewsAdapert(mList, activity);
        homeNews.setAdapter(homeNewsadapert);
        homeSwitchNumberAdapert = new HomeSwitchNumberAdapert(switchNumberList,activity);
        homeLvWaterID.setAdapter(homeSwitchNumberAdapert);
    }

    @Override
    protected void initListener() {
        homeWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeWater.change(250);
            }
        });
        homeSwipeRefresh.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        homeSwipeRefresh.finishRefresh();
                        sharedPreferences = WaterKnowApp.getSharedPreferences(activity);
                        token = sharedPreferences.getString("token", null);
                        username = sharedPreferences.getString("username", null);
                        mPresenter = new HomePresenter();
                        mPresenter.attarchView(HomeFragment.this);
                        initHomeBanner();
                        initHomeWaterMeter();
                        initHomeNews();
                        initHomeSwitchNumber();
                    }
                }, 1000);
            }
        });
        homeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (navigationsList.get(position).getName()) {
                    case "充值缴费":
                        IntentUtils.startIntent(activity, HomePaymentActivity.class);
                        break;
                    case "智慧生活":
                        IntentUtils.startIntent(activity, HomeWisdomLifeActivity.class);
                        break;
                    case "用水知识":
                        IntentUtils.startIntent(activity, HomeWaterKnowLedgeActivity.class);
                        break;
                    case "我要报修":
                        IntentUtils.startIntent(activity, HomeWaterRepotActivity.class);
                        break;
                }
            }
        });
        homeNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, WebDetailsActivity.class);
                intent.putExtra("WebUrl", mList.get(position).getUrl());
                startActivity(intent);
            }
        });
        homeLvWaterID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switchNumberList.get(position).setFlag(true);
                waterId.setText(switchNumberList.get(position).getMeter_id());
                SharedPreferences.Editor editor = WaterKnowApp.getUserInformationSharedPreferencesEditor(activity);
                editor.putString(username+"meterID",switchNumberList.get(position).getMeter_id()+"");
                editor.commit();
                homeLvWaterID.setVisibility(View.GONE);
                isFlag=false;
                mPresenter.confirmNumber(switchNumberList.get(position).getMeter_id(),token);
                mPresenter.attarchView(HomeFragment.this);
                initHomeWaterMeter();
            }
        });
    }

    @Override
    protected void initView() {
        sharedPreferences = WaterKnowApp.getSharedPreferences(activity);
        token = sharedPreferences.getString("token", null);
        username = sharedPreferences.getString("username", null);
        initUserInformation();


    }

    /**
     * 获取用户状态信息
     */
    private void initUserInformation() {
        SharedPreferences sharedPreferences = WaterKnowApp.getUserInformationSharePreferences(activity);
        String region = sharedPreferences.getString(username + "region", null);
        if (region == null)
            tvRegion.setText("北京");
        else
            tvRegion.setText(region);

    }

    /**
     * 请求首页新闻
     */
    private void initHomeNews() {
        mPresenter.setHomeNews(token);
    }

    /**
     * 加载水表状态
     */
    private void initHomeWaterMeter() {
        mPresenter.setHomeWaterMeterState(token);
    }

    /**
     * 加载首页轮播图
     */
    private void initHomeBanner() {
        mPresenter.setHomeBanner(token);
    }

    @Override
    public void initData() {
        //中部功能
        navigationsList.add(new HomeNavigation("充值缴费", R.drawable.home_recharge));
        navigationsList.add(new HomeNavigation("智慧生活", R.drawable.home_wit));
        navigationsList.add(new HomeNavigation("用水知识", R.drawable.home_water));
        navigationsList.add(new HomeNavigation("我要报修", R.drawable.home_repair));

        mPresenter = new HomePresenter();
        mPresenter.attarchView(this);
        initHomeBanner();
        initHomeWaterMeter();
        initHomeNews();
        initHomeSwitchNumber();
    }

    private void initHomeSwitchNumber() {
        mPresenter.setHomeSwitchNumber(token);
    }

    @OnClick({R.id.line_orientation, R.id.home_search, R.id.home_img_information,R.id.bt_waterID})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_orientation:
                Intent intent = new Intent(activity, HomeRegionActivity.class);
                activity.startActivityForResult(intent, 0);
                break;
            case R.id.home_search:
                IntentUtils.startIntent(activity, HomeSearchActivity.class);
                break;
            case R.id.home_img_information:
                IntentUtils.startIntent(activity, HomeInformationActivity.class);
                break;
            case R.id.bt_waterID:
                if (!isFlag){
                    homeLvWaterID.setVisibility(View.VISIBLE);
                    isFlag=true;
                }else{
                    homeLvWaterID.setVisibility(View.GONE);
                    isFlag=false;
                }
                break;
        }
    }

    /**
     * 获取首页轮播图
     *
     * @param entity
     */
    private ArrayList<String> imgList = new ArrayList<>();

    @Override
    public void getHomeBannerData(HomeBannerEntity entity) {
        int code = entity.getCode();
        switch (code) {
            case 200:
                imgList.clear();
                for (int i = 0; i < entity.getData().size(); i++) {
                    imgList.add(entity.getData().get(i).getPic());
                }
                homeXbanner.setData(imgList);
                homeXbanner.setmAdapter(new HomeXBannerAdapert(imgList, activity));
                initBannerListener(entity.getData());
                break;
            case 501:
                WaterKnowApp.reNewLogin(activity);
                break;
            case 502:
                WaterKnowApp.reNewLogin(activity);
                break;
        }
    }

    private void initBannerListener(final List<HomeBannerEntity.DataBean> data) {
        homeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Intent intent = new Intent(activity, WebDetailsActivity.class);
                intent.putExtra("WebUrl", data.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    /**
     * 获取水表状态
     *
     * @param entity
     */
    @Override
    public void getHomeWaterMeterState(HomeMeterStateEntity entity) {
        int code = entity.getCode();
        switch (code) {
            case 200:
                String price = entity.getData().getPrice();
                String pri = price.substring(0, price.length() - 1);
                Double parseDouble = Double.parseDouble(pri);
                if (parseDouble >= 9999.99) {
                    tvWaterBalance.setText(9999.99 + "元");
                } else {
                    tvWaterBalance.setText(parseDouble + "元");
                }
                homeTvSignalIntensity.setText(entity.getData().getStatus());
                water_remain = entity.getData().getWater_remain();
                waterId.setText(entity.getData().getMeter_id());
                if (water_remain > 0) {
                    homeWater.change(water_remain);
                }
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    /**
     * 获取首页新闻
     *
     * @param homeNews
     */
    @Override
    public void getHomeNews(HomeNewsEntity homeNews) {
        int code = homeNews.getCode();
        switch (code) {
            case 200:
                List<HomeNewsEntity.DataBean> data = homeNews.getData();
                mList.addAll(data);
                homeNewsadapert.notifyDataSetChanged();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    @Override
    public void getSwitchNumber(SwitchNumberEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                switchNumberList.clear();
                List<SwitchNumberEntity.DataBean> data = entity.getData();
                switchNumberList.addAll(data);
                homeSwitchNumberAdapert.notifyDataSetChanged();
                break;
            case 502:
                break;
            case 501:
                break;
        }
    }

    @Override
    public void confirmNumber(HomeSwitchNumberCommitEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                Toast.makeText(activity, "切换水表号成功", Toast.LENGTH_SHORT).show();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == -1) {
            String region = data.getStringExtra("region");
            tvRegion.setText(region);
            SharedPreferences.Editor editor = WaterKnowApp.getUserInformationSharedPreferencesEditor(activity);
            editor.putString(username + "region", region);
            editor.commit();
        }
    }

}
