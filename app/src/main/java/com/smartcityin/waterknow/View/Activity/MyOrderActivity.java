package com.smartcityin.waterknow.View.Activity;

import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.smartcityin.waterknow.Adapert.MyOrderAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Entity.My.MyOrderEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyOrderPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyOrderActivity extends BaseSwipBackActivity<Commpart.MyOrderViewInf, MyOrderPresenter> implements Commpart.MyOrderViewInf {

    @BindView(R.id.img_finish)
    ImageView imgFinish;
    @BindView(R.id.myOrder_finish)
    LinearLayout myOrderFinish;
    @BindView(R.id.myOrder_tv_invoicetool)
    TextView myOrderTvInvoicetool;
    @BindView(R.id.myOrder_tool_bar)
    Toolbar myOrderToolBar;
    @BindView(R.id.myOrder_lv)
    ListView myOrderLv;
    private String token;
    private ArrayList<MyOrderEntity.DataBean> mList=new ArrayList<>();
    private MyOrderAdapert myOrderAdapert;
    private Handler handler=new Handler();
    private boolean isload=false;
    private View footView;
    private int page=1;
    private LinearLayout loadmore;
    private LinearLayout noload;
    @Override
    protected void initAdapert() {
        myOrderAdapert = new MyOrderAdapert(mList, this);
        myOrderLv.setAdapter(myOrderAdapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initView() {
        setSupportActionBar(myOrderToolBar);
        token = WaterKnowApp.getToken(this, "token");
        footView = LayoutInflater.from(this).inflate(R.layout.waterknow_loadmore,null);
        myOrderLv.addFooterView(footView);
        loadmore = footView.findViewById(R.id.footView_load_more);
        loadmore.setVisibility(View.VISIBLE);
        noload = footView.findViewById(R.id.footView_noMore);
        noload.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        myOrderLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            int lastposition;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int count = myOrderAdapert.getCount();
                int i = count;
                if (i == lastposition && scrollState == SCROLL_STATE_IDLE && !isload) {
                    isload=true;
                    page+=1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.setMyOrder(token,page);
                            mPresenter.attarchView(MyOrderActivity.this);
                        }
                    }, 0);
                }
            }
            //滑动
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastposition = firstVisibleItem + visibleItemCount - 1;
                //有更多
                if(totalItemCount <visibleItemCount){
                    myOrderLv.addFooterView(footView);
                    //不满一屏
                }else{
                    myOrderLv.removeFooterView(footView);
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter=new MyOrderPresenter();
        mPresenter.attarchView(this);
        mPresenter.setMyOrder(token,0);
    }

    @OnClick({R.id.myOrder_finish, R.id.myOrder_tv_invoicetool})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myOrder_finish:
                finish();
                break;
            case R.id.myOrder_tv_invoicetool:
                IntentUtils.startIntent(this,MyInvoiceToolActivity.class);
                break;
        }
    }

    @Override
    public void getMyOrderData(MyOrderEntity myOrderEntity) {
        int code = myOrderEntity.getCode();
        switch (code){
            case 200:
                if (myOrderEntity.getData().size()>0) {
                    List<MyOrderEntity.DataBean> data = myOrderEntity.getData();
                    mList.addAll(data);
                    myOrderAdapert.notifyDataSetChanged();
                }else{
                    myOrderLv.removeFooterView(footView);
                }
                isload = false;
                break;
        }
    }
}
