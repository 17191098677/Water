package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//充值缴费
public class HomePaymentActivity extends BaseSwipBackActivity {
    //水表号码
    @BindView(R.id.tv_recharge_payment_number)
    TextView tvRechargePaymentNumber;
    //账号绑定号码
    @BindView(R.id.tv_recahrge_payment_binding)
    TextView tvRecahrgePaymentBinding;
    //跳转到切换户号的按钮
    @BindView(R.id.bt_binding_number)
    RelativeLayout btBindingNumber;
    //充值金额输入框
    @BindView(R.id.ed_recharge)
    EditText edRecharge;
    //确定按钮
    @BindView(R.id.recharge_payment_bt)
    Button rechargePaymentBt;
    //标题
    @BindView(R.id.tv_title)
    TextView tvTitle;
    //结束页面
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    //toolBar
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String meterID;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_payment;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        tvTitle.setText("充值缴费");
        String username = WaterKnowApp.getToken(this, "username");
        meterID = WaterKnowApp.getUserInformationSharePreferences(this).getString(username + "meterID", "");
        tvRechargePaymentNumber.setText(meterID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvRechargePaymentNumber.setText(meterID);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.line_finish, R.id.recharge_payment_bt,R.id.bt_binding_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.recharge_payment_bt:
                break;
            case R.id.bt_binding_number:
                Intent intent=new Intent(this,MySwitchNumberActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==-2){
            String meterID = data.getStringExtra("meterID");
            tvRechargePaymentNumber.setText(meterID);
        }
    }
}
