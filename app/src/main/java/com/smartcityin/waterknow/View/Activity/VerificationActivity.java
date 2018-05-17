package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.ForGetEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterPSWEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.Register.RegisterPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.ValidatorUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用来接收验证码页面
 */
public class VerificationActivity extends BaseSwipBackActivity<Commpart.RegisterViewInf, RegisterPresenter> implements Commpart.RegisterViewInf {
    //手机号
    @BindView(R.id.tv_verification_phone)
    TextView tvVerificationPhone;
    //结束页面
    @BindView(R.id.verification_finish)
    LinearLayout verificationFinish;
    //输入验证码的输入框
    @BindView(R.id.ed_verification)
    EditText edVerification;
    //获取验证码的按钮
    @BindView(R.id.bt_again_verification)
    Button btAgainVerification;
    //输入昵称
    @BindView(R.id.verification_ed_name)
    EditText verificationEdName;
    //输入密码
    @BindView(R.id.verification_ed_psw)
    EditText verificationEdPsw;
    //验证按钮
    @BindView(R.id.bt_verification)
    Button btVerification;
    @BindView(R.id.verification_tool_bar)
    Toolbar verificationToolBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_finish)
    ImageView imgFinish;
    //确认密码
    @BindView(R.id.ed_psw_confirm)
    EditText edPswConfirm;
    private String openid;
    private Handler handler = new Handler();
    //按钮的倒计时
    private int time = 60;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time > 0) {
                btAgainVerification.setText(time + "S秒后重新获取验证码");
                handler.postDelayed(runnable, 1000);
            } else {
                btAgainVerification.setText("重新获取验证码");
            }
        }
    };
    private String msg_id;
    private String phone_number;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verification;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        phone_number = intent.getStringExtra("phone_number");
        msg_id = intent.getStringExtra("msg_id");
        openid = intent.getStringExtra("titleID");
        if ("注册".equals(openid)) {
            tvTitle.setText("注册");
            imgFinish.setVisibility(View.VISIBLE);
            verificationEdName.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            verificationEdName.setHint("昵称(1~12位,由数字,字母,中文组成)");
            verificationEdPsw.setHint("密码(6~16位,由数字和字母组成)");
            edPswConfirm.setVisibility(View.VISIBLE);
        } else if ("忘记密码".equals(openid)) {
            tvTitle.setText("忘记密码");
            imgFinish.setVisibility(View.INVISIBLE);
            verificationEdName.setTransformationMethod(PasswordTransformationMethod.getInstance());
            verificationEdName.setHint("新密码(6~16位,由数字和字母组成)");
            verificationEdPsw.setHint("确认密码(6~16位,由数字和字母组成)");
            edPswConfirm.setVisibility(View.GONE);
        }else if ("绑定手机号".equals(openid)){
            tvTitle.setText("绑定手机号");
            verificationEdName.setVisibility(View.GONE);
            edPswConfirm.setVisibility(View.VISIBLE);
        }
        handler.postDelayed(runnable, 0);
        tvVerificationPhone.setText(phone_number);
        setSupportActionBar(verificationToolBar);
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.verification_finish, R.id.bt_again_verification, R.id.bt_verification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.verification_finish:
                finish();
                break;
            case R.id.bt_again_verification:
                if (openid.equals("注册")) {
                    initRegister();
                } else if (openid.equals("忘记密码")) {
                    initForgetPsw();
                }else if (openid.equals("绑定手机号")){
                    initRegister();
                }
                break;
            case R.id.bt_verification:
                if ("注册".equals(openid)) {
                    initUpLoad();
                } else if ("忘记密码".equals(openid)) {
                    initForgetPSW();
                }else if ("绑定手机号".equals(openid)){
                    initBindingPhone();
                }
                break;
        }
    }

    /**
     * 绑定手机号
     */
    private  void initBindingPhone(){
        mPresenter = new RegisterPresenter();
        if (ValidatorUtils.isPsw(verificationEdPsw.getText().toString().trim())) {
            if (verificationEdPsw.getText().toString().trim().equals(edPswConfirm.getText().toString().trim())){
                String token = WaterKnowApp.getToken(this, "token");
                Map<String,String> map=new HashMap<>();
                map.put("token",token);
                map.put("code",edVerification.getText().toString().trim());
                map.put("msg_id",msg_id);
                map.put("phone",phone_number);
                map.put("password",verificationEdPsw.getText().toString().trim());
                mPresenter.attarchView(this);
                mPresenter.bindingPhone(map);
            }else{
                Toast.makeText(this, "密码和确认密码请输入一致", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
        }
    }
    private void initForgetPsw() {
        mPresenter = new RegisterPresenter();
        mPresenter.resetForgetYZM(phone_number);
        mPresenter.attarchView(this);
        time = 60;
        handler.postDelayed(runnable, 0);
    }

    private void initRegister() {
        mPresenter = new RegisterPresenter();
        mPresenter.resetYZM(phone_number);
        mPresenter.attarchView(this);
        time = 60;
        handler.postDelayed(runnable, 0);
    }

    /**
     * 忘记密码
     */
    private void initForgetPSW() {
        mPresenter = new RegisterPresenter();
        //验证码
        String verification = edVerification.getText().toString().trim();
        //密码
        String pass = verificationEdName.getText().toString().trim();
        //确认密码
        String password = verificationEdPsw.getText().toString().trim();
        if (!verification.isEmpty()) {
            if (!pass.isEmpty() && ValidatorUtils.isPsw(pass)) {
                if (!password.isEmpty() && ValidatorUtils.isPsw(password) && pass.equals(password)) {
                    mPresenter.setForGet("忘记密码 ", phone_number, verification, pass, password, msg_id);
                    mPresenter.attarchView(this);
                } else {
                    Toast.makeText(this, "密码与确认密码必须一样", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "验证码不可为空", Toast.LENGTH_SHORT).show();
        }
    }

    //和后台对接用户输入的验证码的状态
    private void initUpLoad() {
        mPresenter = new RegisterPresenter();
        if (!edVerification.getText().toString().isEmpty()) {
            if (ValidatorUtils.isPsw(verificationEdPsw.getText().toString().trim())) {
                if (verificationEdPsw.getText().toString().trim().equals(edPswConfirm.getText().toString().trim())){
                    mPresenter.attarchView(this);
                    mPresenter.setRegister("注册", phone_number, edVerification.getText().toString().trim(), verificationEdName.getText().toString().trim(), verificationEdPsw.getText().toString().trim(), edPswConfirm.getText().toString().trim(), msg_id);
                }else{
                    Toast.makeText(this, "密码和确认密码请输入一致", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getRegister(RegisterPSWEntity entity) {
        String value = entity.getValue();
        int code = entity.getCode();
        switch (code) {
            case 200:
                SharedPreferences.Editor editor = WaterKnowApp.getSharedPreferencesEditor(this);
                editor.putString("username", phone_number);
                editor.putString("password", verificationEdPsw.getText().toString().trim());
                editor.putString("token", entity.getToken());
                editor.commit();
                IntentUtils.startIntent(VerificationActivity.this, RegisterBindingWaterMeterActivity.class);
                finish();
                break;
            case 300:
                break;

        }
        Log.e("QQQ", "value:" + value + "code" + entity.getCode());
    }

    @Override
    public void getForget(ForGetEntity entity) {
        String value = entity.getValue();
        if (entity.getCode() == 200) {
            Toast.makeText(this, "修改成功，请重新登录", Toast.LENGTH_SHORT).show();
            IntentUtils.startIntent(VerificationActivity.this, LoginActivity.class);
            finish();
        } else if (entity.getCode() == 300) {
            Toast.makeText(this, "验证码错误，请重新输入验证码", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getResetYZM(RegisterEntity registerEntity) {
        int code = registerEntity.getCode();
        switch (code) {
            case 200:
                msg_id = registerEntity.getData().getMsg_id();
                break;
            case 300:
                break;
        }
    }

    @Override
    public void getForgetYZM(RegisterEntity registerEntity) {
        int code = registerEntity.getCode();
        switch (code) {
            case 200:
                msg_id = registerEntity.getData().getMsg_id();
                break;
            case 300:
                break;
        }
    }

    @Override
    public void getBindingPhone(MyInToolPAymentEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = WaterKnowApp.getSharedPreferencesEditor(this);
                editor.putString("username",phone_number);
                editor.commit();
                finish();
                break;
            case 300:
                Toast.makeText(this, "验证码有误，请重新填写", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
