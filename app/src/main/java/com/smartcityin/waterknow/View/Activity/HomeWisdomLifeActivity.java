package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awarmisland.android.popularrefreshlayout.RefreshLayout;
import com.awarmisland.android.popularrefreshlayout.RefreshListener;
import com.smartcityin.waterknow.Adapert.WisdomAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Home.WisdomEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.WisdomPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.View.Fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeWisdomLifeActivity extends BaseSwipBackActivity<Commpart.WisdomLifeViewInf, WisdomPresenter> implements Commpart.WisdomLifeViewInf, WisdomAdapert.OnItemClicker {

    @BindView(R.id.wisdom_reLV)
    RecyclerView wisdomReLV;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    private ArrayList<WisdomEntity.DataBean> mList = new ArrayList<>();
    private WisdomAdapert wisdomAdapert;
    private boolean isload;//false
    private LinearLayoutManager manager;
    private int page = 1;
    private String token;
    private Handler handler = new Handler();

    @Override
    protected void initAdapert() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        wisdomReLV.setLayoutManager(manager);
        wisdomAdapert = new WisdomAdapert(this, mList);
        wisdomReLV.setAdapter(wisdomAdapert);
        //添加Android自带的分割线
        wisdomReLV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_wisdom_life;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        tvTitle.setText("智慧生活");
    }

    @Override
    protected void initData() {
        super.initData();
        token = WaterKnowApp.getToken(this, "token");
        mPresenter = new WisdomPresenter();
        mPresenter.attarchView(this);
        mPresenter.setWisdomLife(token, page);
        refresh.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        refreshLayout.finishRefresh();
                        mPresenter = new WisdomPresenter();
                        page=1;
                        mPresenter.setWisdomLife(token, page);
                        mPresenter.attarchView(HomeWisdomLifeActivity.this);
                    }
                }, 1000);
            }
        });
    }
    @Override
    protected void initListener() {
        wisdomAdapert.setOnItemClickerListener(this);
        wisdomReLV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == wisdomAdapert.getItemCount() && !isload) {
                    page += 1;
                    //到达底部之后如果footView的状态不是正在加载的状态,就将 他切换成正在加载的状态
                    isload = true;
                    wisdomAdapert.changeState(wisdomAdapert.LOAD);
                    mPresenter.setWisdomLife(token, page);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.attarchView(new Commpart.WisdomLifeViewInf() {
                                @Override
                                public void getData(WisdomEntity wisdomEntity) {
                                    int code = wisdomEntity.getCode();
                                    switch (code) {
                                        case 200:
                                            List<WisdomEntity.DataBean> data = wisdomEntity.getData();
                                            if (data.size() > 0) {
                                                mList.addAll(wisdomEntity.getData());
                                                wisdomAdapert.notifyDataSetChanged();
                                            } else {
                                                wisdomAdapert.changeState(wisdomAdapert.NOLOAD);
                                                wisdomAdapert.notifyDataSetChanged();
                                            }
                                            isload = false;
                                            break;

                                    }

                                }
                            });
                            wisdomAdapert.changeState(wisdomAdapert.NOLOAD);
                        }
                    }, 3000);

                } else {
                    wisdomAdapert.changeState(wisdomAdapert.NOLOAD);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //拿到最后一个出现的item的位置
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getData(WisdomEntity wisdomEntity) {
        int code = wisdomEntity.getCode();
        switch (code) {
            case 200:
                List<WisdomEntity.DataBean> data = wisdomEntity.getData();
                mList.addAll(data);
                wisdomAdapert.notifyDataSetChanged();
                break;
            case 501:
                initDialog();
                break;
            case 502:
                initDialog();
                break;
        }
    }

    private void initDialog() {

    }

    @Override
    public void setOnClicker(int position) {
        Intent intent = new Intent(HomeWisdomLifeActivity.this, WebDetailsActivity.class);
        intent.putExtra("WebUrl", mList.get(position).getUrl());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
