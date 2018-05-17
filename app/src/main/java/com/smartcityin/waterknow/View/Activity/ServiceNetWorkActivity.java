package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.ServiceNetWorkAdapert;
import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Service.ServiceNetWorkEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.ServiceNetWorkPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.StyleUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.internal.queue.MpscLinkedQueue;

public class ServiceNetWorkActivity extends BaseSwipBackActivity<Commpart.ServiceNetWorkViewInf, ServiceNetWorkPresenter> implements Commpart.ServiceNetWorkViewInf,ServiceNetWorkAdapert.OnItemClickerListerner {

    @BindView(R.id.serviceNetWork_Relv)
    RecyclerView serviceNetWorkRelv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<ServiceNetWorkEntity.DataBean> mList=new ArrayList();
    private ServiceNetWorkAdapert serviceNetWorkAdapert;

    @Override
    protected void initAdapert() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        serviceNetWorkRelv.setLayoutManager(manager);
        serviceNetWorkAdapert = new ServiceNetWorkAdapert(mList, this);
        serviceNetWorkRelv.setAdapter(serviceNetWorkAdapert);
        serviceNetWorkRelv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected int getLayoutId() {
        StyleUtils.setStatusBarMode(this, true);
        return R.layout.activity_service_net_work;
    }

    @Override
    protected void initData() {
        super.initData();
        String token = WaterKnowApp.getToken(this, "token");
        mPresenter = new ServiceNetWorkPresenter();
        mPresenter.attarchView(this);
        mPresenter.setServiceNetWork(token);
    }

    @Override
    protected void initView() {
        tvTitle.setText("服务网点");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {
        serviceNetWorkAdapert.setOnItemClickerListener(this);
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getServiceNetWorkData(ServiceNetWorkEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                List<ServiceNetWorkEntity.DataBean> data = entity.getData();
                mList.addAll(data);
                serviceNetWorkAdapert.notifyDataSetChanged();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    @Override
    public void setOnClicker(int postion) {
        String tel = mList.get(postion).getTel();
        initDialog(tel);
    }

    private void initDialog(final String phoneNumber) {
        final BaseDialog dialog = new BaseDialog(this);
        dialog.show();
        dialog.setHintText(phoneNumber);
        dialog.setLeftButton("呼叫", new BaseDialog.OnClickerListenerLeft() {
            @Override
            public void setOnClicker() {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
            @Override
            public void setOnClicker() {
                dialog.dismiss();
            }
        });
    }
}
