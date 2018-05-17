package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awarmisland.android.popularrefreshlayout.RefreshLayout;
import com.awarmisland.android.popularrefreshlayout.RefreshListener;
import com.smartcityin.waterknow.Adapert.ServiceNewsCenterAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.PushListView;
import com.smartcityin.waterknow.Entity.Service.ServiceNewsCenterEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.ServiceNewsCenterPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.StyleUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceNewsCenterActivity extends BaseSwipBackActivity<Commpart.ServiceNewsCenterViewInf, ServiceNewsCenterPresenter> implements Commpart.ServiceNewsCenterViewInf,AdapterView.OnItemClickListener {

    @BindView(R.id.service_newsCenter_lv)
    PushListView serviceNewsCenterLv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    private LinearLayout loadmore;
    private LinearLayout noload;
    private ArrayList<ServiceNewsCenterEntity.DataBean> mList = new ArrayList<>();
    private ServiceNewsCenterAdapert serviceNewsCenterAdapert;
    private View footView;
    private String token;
    private int page = 1;
    private Handler handler = new Handler();
    private boolean isload = false;
    @Override
    protected void initAdapert() {
        serviceNewsCenterAdapert = new ServiceNewsCenterAdapert(mList, this);
        serviceNewsCenterLv.setAdapter(serviceNewsCenterAdapert);
    }

    @Override
    protected int getLayoutId() {
        StyleUtils.setStatusBarMode(this, true);
        return R.layout.activity_service_news;
    }

    @Override
    protected void initView() {
        tvTitle.setText("新闻中心");
        setSupportActionBar(toolbar);
        footView = LayoutInflater.from(this).inflate(R.layout.loadmore_view, null);
        serviceNewsCenterLv.addFooterView(footView);
        loadmore = footView.findViewById(R.id.footView_load_more);
        loadmore.setVisibility(View.VISIBLE);
        noload = footView.findViewById(R.id.footView_noMore);
        noload.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        serviceNewsCenterLv.setOnItemClickListener(this);
        refresh.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        mList.clear();
                        loadmore.setVisibility(View.VISIBLE);
                        noload.setVisibility(View.GONE);
                        mPresenter.setServiceNewsCenter(token, page);
                        mPresenter.attarchView(ServiceNewsCenterActivity.this);
                        refresh.finishRefresh();
                    }
                }, 1000);
            }
        });
        //        滑动监听
        serviceNewsCenterLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            int lastposition;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int count = serviceNewsCenterAdapert.getCount();
                int i = count + 1 - 1;
                if (i == lastposition && scrollState == SCROLL_STATE_IDLE && !isload) {
                    isload = true;
                    page += 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.setServiceNewsCenter(token, page);
                            mPresenter.attarchView(ServiceNewsCenterActivity.this);
                        }
                    }, 0);
                }
            }

            //            滑动
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastposition = firstVisibleItem + visibleItemCount - 1;
            }
        });
    }



    @Override
    protected void initData() {
        super.initData();
        token = WaterKnowApp.getToken(this, "token");
        mPresenter = new ServiceNewsCenterPresenter();
        mPresenter.setServiceNewsCenter(token, page);
        mPresenter.attarchView(this);
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getServiceNewsCenterData(ServiceNewsCenterEntity serviceNewsCenterEntity) {
        int code = serviceNewsCenterEntity.getCode();
        switch (code) {
            case 200:
                if (serviceNewsCenterEntity.getData().size() > 0) {
                    List<ServiceNewsCenterEntity.DataBean> data = serviceNewsCenterEntity.getData();
                    mList.addAll(data);
                    serviceNewsCenterAdapert.notifyDataSetChanged();
                } else {
                    loadmore.setVisibility(View.GONE);
                    noload.setVisibility(View.VISIBLE);
                }
                isload = false;

                break;
            case 501:
                break;
            case 502:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ServiceNewsCenterActivity.this, WebDetailsActivity.class);
        intent.putExtra("WebUrl", mList.get(position).getUrl());
        startActivity(intent);
    }
}
