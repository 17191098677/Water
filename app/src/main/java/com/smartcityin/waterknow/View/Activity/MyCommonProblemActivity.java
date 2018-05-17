package com.smartcityin.waterknow.View.Activity;

import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.smartcityin.waterknow.Adapert.MyCommonProblemAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyCommonProblemPresenter;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCommonProblemActivity extends BaseSwipBackActivity<Commpart.MyCommonViewInf, MyCommonProblemPresenter> implements Commpart.MyCommonViewInf {

    @BindView(R.id.myCommonProblem_lv)
    ListView myCommonProblemLv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<ServiceUserHelpEntity.DataBean> mList=new ArrayList<>();
    private MyCommonProblemAdapert adapert;

    @Override
    protected void initAdapert() {
        adapert = new MyCommonProblemAdapert(mList, this);
        myCommonProblemLv.setAdapter(adapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_common_problem;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        tvTitle.setText("常见问题");
    }

    @Override
    protected void initData() {
        super.initData();
        String token = WaterKnowApp.getToken(this, "token");
        mPresenter = new MyCommonProblemPresenter();
        mPresenter.setCommonProblem(token);
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
    public void getMyCommonData(ServiceUserHelpEntity serviceUserHelpEntity) {
        int code = serviceUserHelpEntity.getCode();
        switch (code){
            case 200:
                List<ServiceUserHelpEntity.DataBean> data = serviceUserHelpEntity.getData();
                mList.addAll(data);
                adapert.notifyDataSetChanged();
                break;
            case 501:
                break;
                case 502:
                    break;
        }
    }
}
