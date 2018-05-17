package com.smartcityin.waterknow.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.smartcityin.waterknow.Base.BaseActivity;
import com.smartcityin.waterknow.Base.BaseEditText;
import com.smartcityin.waterknow.Dao.UserEntityDao;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.Login.LoginEntity;
import com.smartcityin.waterknow.Entity.UserEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.LoginPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.DaoUtils;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.ValidatorUtils;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;


import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<Commpart.LoginViewInf,LoginPresenter> implements BaseEditText.OnFocusChangedListener,IUiListener,Commpart.LoginViewInf {

    @BindView(R.id.login_img)
    ImageView loginImg;
    @BindView(R.id.tv_small_userName)
    TextView tvSmallUserName;
    @BindView(R.id.edit_userName)
    BaseEditText editUserName;
    @BindView(R.id.tv_small_psw)
    TextView tvSmallPsw;
    @BindView(R.id.edit_passWord)
    BaseEditText editPsw;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_forget_psw)
    TextView tvForgetPsw;
    @BindView(R.id.login_qq)
    ImageView loginQq;
    @BindView(R.id.login_weChat)
    ImageView loginWeChat;
    @BindView(R.id.bt_register)
    TextView btRegister;
    private Tencent mTencent;
    private SharedPreferences sharedPreferences;
    private String openid;

    @Override
    protected void initAdapert() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //QQ注册
        mTencent = Tencent.createInstance("1106804472", this.getApplicationContext());
        //免登录
        initCancelLogin();
    }

    private void initCancelLogin() {
        //免登录
        sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.e("QQQ",token);
        if (!token.equals("")){
            IntentUtils.startIntent(this,HomeActivity.class);
            finish();
        }
    }

    @Override
    protected void initListener() {
        initEditOnFouceListener();
    }
    private TextWatcher psw_textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tvSmallPsw.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editPsw.getText().equals("") || editPsw.getText().toString().length() <= 0) {
                tvSmallPsw.setVisibility(View.INVISIBLE);
                editPsw.setHint("密码");
            } else {
                tvSmallPsw.setVisibility(View.VISIBLE);
            }
        }
    };
    private TextWatcher userNameTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tvSmallUserName.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editUserName.getText().equals("") || editUserName.getText().toString().length() <= 0) {
                tvSmallUserName.setVisibility(View.INVISIBLE);
            } else {
                tvSmallUserName.setVisibility(View.VISIBLE);
            }
        }
    };
    //通过输入框的输入状态来监听输入框的效果
    private void initEditOnFouceListener() {
        editUserName.addTextChangedListener(userNameTextWatcher);
        editPsw.addTextChangedListener(psw_textWatcher);
        editUserName.setOnListener(this);
        editPsw.setOnListener(this);
    }

    @OnClick({R.id.login_qq, R.id.login_weChat, R.id.tv_forget_psw, R.id.bt_register,R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_qq:
                login();
                break;
            case R.id.login_weChat:
                break;
            case R.id.tv_forget_psw:
                IntentUtils.putExtra(LoginActivity.this, RegisterActivity.class,"register", "忘记密码");
                break;
            case R.id.bt_register:
                IntentUtils.putExtra(LoginActivity.this, RegisterActivity.class,"register", "注册");
                break;
            case R.id.bt_login:
                initLogin();
                break;
        }
    }

    private void initLogin() {
        if (ValidatorUtils.isChinaPhoneLegal(editUserName.getText().toString().trim())){
            if(ValidatorUtils.isPsw(editPsw.getText().toString().trim())){
                mPresenter=new LoginPresenter();
                mPresenter.attarchView(this);
                mPresenter.setLogin(editUserName.getText().toString().trim(),editPsw.getText().toString().trim());
            }else{
                Toast.makeText(this, "密码格式不正确应由6-12字母或数字组成", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "用户名格式不正确", Toast.LENGTH_SHORT).show();
        }
    }


    //开启QQ登录
    public void login() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all",this);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR)
            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void getLoginData(LoginEntity entity) {
        int code = entity.getCode();
        Log.e("QQQ","token"+entity.getToken());
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("username",editUserName.getText().toString().trim());
        edit.putString("password",editPsw.getText().toString().trim());
        edit.putString("token",entity.getToken());
        edit.commit();
        switch (code){
            case 200:
                IntentUtils.startIntent(LoginActivity.this, HomeActivity.class);
                finish();
                break;
            case 301:
                Toast.makeText(this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                break;
            case 300:
                Toast.makeText(this, "此账号没有注册，请前往注册页面注册", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void getQQData(final String QQuserData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject=new JSONObject(QQuserData);
                    String nickname = jsonObject.getString("nickname");
                    String figureurl = jsonObject.getString("figureurl_2");
                    mPresenter.QQLogin(openid,nickname,figureurl);
                    mPresenter.attarchView(LoginActivity.this);
                    Log.e("QQQ",openid);
                    Log.e("QQQ",nickname);
                    Log.e("QQQ",figureurl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void getQQLogin(HomeSwitchNumberCommitEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                SharedPreferences.Editor editor = WaterKnowApp.getSharedPreferencesEditor(this);
                editor.putString("username",openid);
                editor.putString("token",entity.getToken());
                editor.commit();
                IntentUtils.startIntent(LoginActivity.this, HomeActivity.class);
                finish();
                break;
            case 201:
                SharedPreferences.Editor edit = WaterKnowApp.getSharedPreferencesEditor(this);
                edit.putString("username",openid);
                edit.putString("token",entity.getToken());
                edit.commit();
                IntentUtils.startIntent(LoginActivity.this, RegisterBindingWaterMeterActivity.class);
                finish();
                break;
            case 300:
                Toast.makeText(this, ""+entity.getValue(), Toast.LENGTH_SHORT).show();
                break;
                
        }
    }

    @Override
    public void onComplete(Object o) {
        JSONObject jsonObject= (JSONObject) o;
        try {
            //获取到QQ用户的Openid，用户登录的唯一标识
            openid = (String) jsonObject.get("openid");
            String access_token = jsonObject.getString("access_token");
            mTencent.setOpenId(openid);
            mPresenter=new LoginPresenter();
            mPresenter.setQQ(access_token, openid);
            mPresenter.attarchView(LoginActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onFocusChanged(View v, boolean hasFoucs) {
        switch (v.getId()){
            case R.id.edit_userName:
                if (!hasFoucs) {
                    if (editUserName.getText().equals("") || editUserName.getText().toString().length() <= 0) {
                        tvSmallUserName.setVisibility(View.INVISIBLE);
                    } else {
                        tvSmallUserName.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.edit_passWord:
                if (!hasFoucs) {
                    if (editPsw.getText().equals("") || editPsw.getText().toString().length() <= 0) {
                        tvSmallPsw.setVisibility(View.INVISIBLE);
                    } else {
                        tvSmallPsw.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }
}
