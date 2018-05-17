package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.BindingWaterMeter.BindingWaterEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.BindingWaterPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterBindingWaterMeterActivity extends BaseSwipBackActivity<Commpart.BindingWaterViewInf, BindingWaterPresenter> implements Commpart.BindingWaterViewInf {
    @BindView(R.id.verification_finish)
    LinearLayout verificationFinish;
    @BindView(R.id.bt_binding_skip)
    TextView btBindingSkip;
    @BindView(R.id.bindingWaterMeter_tool_bar)
    Toolbar bindingWaterMeterToolBar;
    @BindView(R.id.bt_binding)
    Button btBinding;
    @BindView(R.id.ed_water_number)
    EditText edWaterNumber;
    @BindView(R.id.img_scanning)
    ImageView imgScanning;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    private String token;
    private String username;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_binding_water_meter;
    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = WaterKnowApp.getSharedPreferences(this);
        token = sharedPreferences.getString("token", "");
        username = sharedPreferences.getString("username", "");
        Log.e("QQQ", "token:" + token);
        Intent intent = getIntent();
        String personalType = intent.getStringExtra("PersonalType");
        if ("我的页面".equals(personalType)) {
            btBindingSkip.setVisibility(View.GONE);
            tvTitle.setText("绑定水表");
        } else {
            tvTitle.setText("注册");
        }
        tvNumber.setText("010-89780865");
        tvNumber.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final String phoneNumber = "010-89780865";
                    final BaseDialog dialog = new BaseDialog(RegisterBindingWaterMeterActivity.this);
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
        });
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.verification_finish, R.id.bt_binding_skip, R.id.bt_binding, R.id.img_scanning})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verification_finish:
                finish();
                break;
            case R.id.bt_binding_skip:
                Intent intent = new Intent(RegisterBindingWaterMeterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_binding:
                initBindWater();
                break;
            case R.id.img_scanning:
                IntentIntegrator integrator = new IntentIntegrator(this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
        }
    }

    //绑定水表
    private void initBindWater() {
        mPresenter = new BindingWaterPresenter();
        mPresenter.attarchView(this);
        mPresenter.setBindingWater(edWaterNumber.getText().toString().trim(), token);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            edWaterNumber.setText(result + "");
        }
    }

    @Override
    public void getBindingWaterData(BindingWaterEntity entity) {
        int code = entity.getCode();
        switch (code) {
            case 200:
                Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
                /*SharedPreferences.Editor sharedPreferencesEditor = WaterKnowApp.getUserInformationSharedPreferencesEditor(this);
                sharedPreferencesEditor.putString(username + "meterID", edWaterNumber.getText().toString().trim());
                sharedPreferencesEditor.commit();*/
                IntentUtils.startIntent(RegisterBindingWaterMeterActivity.this,HomeActivity.class);
                finish();
                break;
            case 300:
                Toast.makeText(this, "您已绑定水表，无需再次绑定", Toast.LENGTH_SHORT).show();
                break;
            case 401:
                Toast.makeText(this, entity.getValue(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
