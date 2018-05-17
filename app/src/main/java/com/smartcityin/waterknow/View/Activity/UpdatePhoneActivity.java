package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyUpdatePhoneYZMPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.StyleUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePhoneActivity extends BaseSwipBackActivity<Commpart.MyUpdatePhoneYZMViewInf,MyUpdatePhoneYZMPresenter> implements Commpart.MyUpdatePhoneYZMViewInf {

    @BindView(R.id.phoneNumber)
    TextView phoneNumber;
    @BindView(R.id.ed_YZM)
    EditText edYZM;
    @BindView(R.id.bt_getYZM)
    Button btGetYZM;
    @BindView(R.id.updatePhone_bt_commit)
    Button updatePhoneBtCommit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private int timer=60;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            timer--;
            if (timer>=0){
                btGetYZM.setText(timer+"S后重新获取验证码");
                handler.postDelayed(runnable,1000);
            }else{
                btGetYZM.setText("重新获取验证码");
            }
        }
    };
    private String token;
    private String msg_id;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update;
    }

    @Override
    protected void initView() {
        tvTitle.setText("修改绑定手机");
        setSupportActionBar(toolbar);
        token = WaterKnowApp.getToken(this, "token");
        Intent intent=getIntent();
        String phone = intent.getStringExtra("phone");
        msg_id = intent.getStringExtra("msg_id");
        phoneNumber.setText(phone);
        handler.postDelayed(runnable,1000);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.line_finish, R.id.phoneNumber, R.id.bt_getYZM, R.id.updatePhone_bt_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.phoneNumber:
                break;
            case R.id.bt_getYZM:
                token = WaterKnowApp.getToken(this, "token");
                mPresenter=new MyUpdatePhoneYZMPresenter();
                mPresenter.setUpdatePhone(token);
                timer=60;
                handler.postDelayed(runnable,1000);
                mPresenter.attarchView(this);
                break;
            case R.id.updatePhone_bt_commit:
                if (!edYZM.getText().toString().trim().isEmpty()){
                    mPresenter=new MyUpdatePhoneYZMPresenter();
                    mPresenter.attarchView(this);
                    mPresenter.setUpdatePhoneYZM(token,edYZM.getText().toString().trim(),msg_id);
                }else{
                    Toast.makeText(this, "验证码不可为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void getMyUpdatePhoneYZM(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity) {
        int code = myUpdatePhoneYZMEntity.getCode();
        switch (code){
            case 200:
                IntentUtils.startIntent(this, UpdatePhonePswActivity.class);
                finish();
                break;
            case 300:
                Toast.makeText(this, "验证码有误", Toast.LENGTH_SHORT).show();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    @Override
    public void getMyUpdatePhone(MyUpdatePhoneEntity myUpdatePhoneEntity) {
        int code = myUpdatePhoneEntity.getCode();
        switch (code){
            case 200:
                msg_id=myUpdatePhoneEntity.getData().getMsg_id();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }
}
