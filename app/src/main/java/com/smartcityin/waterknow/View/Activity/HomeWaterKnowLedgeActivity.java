package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.awarmisland.android.popularrefreshlayout.RefreshLayout;
import com.awarmisland.android.popularrefreshlayout.RefreshListener;
import com.smartcityin.waterknow.Adapert.WaterKnowLedgeAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.PushListView;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.WaterKnowLedgePresenter;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeWaterKnowLedgeActivity extends BaseSwipBackActivity<Commpart.WaterKnowLedgeViewInf, WaterKnowLedgePresenter> implements Commpart.WaterKnowLedgeViewInf,AdapterView.OnItemClickListener{


    @BindView(R.id.fragment_waterKnow_lv)
    PushListView fragmentWaterKnowLv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_swipeRefresh)
    RefreshLayout homeSwipeRefresh;
    private ArrayList<HomeWaterKnowEntity.DataBean> mList = new ArrayList<>();
    private WaterKnowLedgeAdapert waterKnowLedgeAdapert;
    private Handler handler = new Handler();
    private boolean isload = false;
    private View footView;
    private String token;
    private int page = 1;
    private LinearLayout loadmore;
    private LinearLayout noload;
    @Override
    protected void initAdapert() {
        waterKnowLedgeAdapert = new WaterKnowLedgeAdapert(mList, this);
        fragmentWaterKnowLv.setAdapter(waterKnowLedgeAdapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_water_know_ledge;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        tvTitle.setText("用水知识");
        footView = LayoutInflater.from(this).inflate(R.layout.waterknow_loadmore, null);
        fragmentWaterKnowLv.addFooterView(footView);
        loadmore = footView.findViewById(R.id.footView_load_more);
        loadmore.setVisibility(View.VISIBLE);
        noload = footView.findViewById(R.id.footView_noMore);
        noload.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        token = WaterKnowApp.getToken(this, "token");
        mPresenter = new WaterKnowLedgePresenter();
        mPresenter.attarchView(this);
        mPresenter.setHomeUsetWaterKnow(token, page);
    }

    @Override
    protected void initListener() {
        fragmentWaterKnowLv.setOnItemClickListener(this);

        homeSwipeRefresh.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        mList.clear();
                        loadmore.setVisibility(View.VISIBLE);
                        noload.setVisibility(View.GONE);
                        mPresenter.setHomeUsetWaterKnow(token, page);
                        mPresenter.attarchView(HomeWaterKnowLedgeActivity.this);
                        homeSwipeRefresh.finishRefresh();
                    }
                }, 1000);
            }
        });
        fragmentWaterKnowLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            int lastposition;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int count = waterKnowLedgeAdapert.getCount();
                int i = count;
                if (i == lastposition && scrollState == SCROLL_STATE_IDLE && !isload) {
                    isload = true;
                    page += 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.setHomeUsetWaterKnow(token, page);
                            mPresenter.attarchView(HomeWaterKnowLedgeActivity.this);
                        }
                    }, 0);
                }
            }

            //            滑动
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastposition = firstVisibleItem + visibleItemCount - 1;
                //有更多
                if (totalItemCount < visibleItemCount) {
                    fragmentWaterKnowLv.removeFooterView(footView);
                }
            }
        });
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getWaterKnowLedgeData(HomeWaterKnowEntity waterKnowEntity) {
        switch (waterKnowEntity.getCode()) {
            case 200:
                if (waterKnowEntity.getData().size() > 0) {
                    List<HomeWaterKnowEntity.DataBean> data = waterKnowEntity.getData();
                    mList.addAll(data);
                    waterKnowLedgeAdapert.notifyDataSetChanged();
                } else {
                    loadmore.setVisibility(View.GONE);
                    noload.setVisibility(View.VISIBLE);
                }
                isload = false;
                break;
            case 501:
                WaterKnowApp.reNewLogin(this);
                break;
            case 502:
                WaterKnowApp.reNewLogin(this);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(HomeWaterKnowLedgeActivity.this, WebDetailsActivity.class);
        intent.putExtra("WebUrl", mList.get(position).getUrl());
        startActivity(intent);
    }

}
