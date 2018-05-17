package com.smartcityin.waterknow.View.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.smartcityin.waterknow.Base.BaseActivity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyUpdateNewPhonePresenter;
import com.smartcityin.waterknow.Presenter.PresenterCountry.Register.RegisterSMSMsgPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.StringUtils;
import com.smartcityin.waterknow.Utils.StyleUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdeterPhoneNewPhoneActivity extends BaseActivity<Commpart.MyUpdateNewPhoneViewInf,MyUpdateNewPhonePresenter> implements Commpart.MyUpdateNewPhoneViewInf{

    @BindView(R.id.updatePhone_finish)
    LinearLayout updatePhoneFinish;
    @BindView(R.id.updatePhone_Tool_Bar)
    Toolbar updatePhoneToolBar;
    @BindView(R.id.phoneNumber)
    TextView phoneNumber;
    @BindView(R.id.ed_YZM)
    EditText edYZM;
    @BindView(R.id.bt_getYZM)
    Button btGetYZM;
    @BindView(R.id.updatePhone_bt_commit)
    Button updatePhoneBtCommit;
    private String newPhone;
    private String msg_id;
    private String token;
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
    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_updeter_phone_new_phone;
    }

    @Override
    protected void initView() {
        handler.postDelayed(runnable,0);
        Intent intent=getIntent();
        token = WaterKnowApp.getToken(this, "token");
        Log.e("QQQQ",token);
        newPhone =intent.getStringExtra("newPhone");
        String subPhone = StringUtils.subPhone(newPhone);
        msg_id = intent.getStringExtra("msg_id");
        setSupportActionBar(updatePhoneToolBar);
        phoneNumber.setText(subPhone);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        super.initData();


    }

    @OnClick({R.id.updatePhone_finish, R.id.bt_getYZM, R.id.updatePhone_bt_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updatePhone_finish:
                finish();
                break;
            case R.id.bt_getYZM:
                handler.postDelayed(runnable,0);
                break;
            case R.id.updatePhone_bt_commit:
                if (edYZM.getText().toString().trim().isEmpty()){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    mPresenter=new MyUpdateNewPhonePresenter();
                    mPresenter.attarchView(this);
                    Log.e("QQQQ","msg_id"+msg_id);
                    mPresenter.setMyUpdateNewPhone(token,edYZM.getText().toString().trim(),msg_id,newPhone);
                }
                break;
        }
    }

    @Override
    public void getMyUpdateNewPhone(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity) {
        int code = myUpdatePhoneYZMEntity.getCode();
        switch (code){
            case 200:
                token=myUpdatePhoneYZMEntity.getToken();
                initDialog("修改成功是否登录");
                break;
            case 300:
                Toast.makeText(this, "验证码有误", Toast.LENGTH_SHORT).show();
                break;
            case 501:
                initDialog("登录信息过期，是否重新登陆");
                break;
            case 502:
                initDialog("登录信息已过期，是否重新登陆");
                break;
        }
    }

    private void initDialog(String title) {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WaterKnowApp.clearSharedPreferences(UpdeterPhoneNewPhoneActivity.this);
                        IntentUtils.startIntent(UpdeterPhoneNewPhoneActivity.this, LoginActivity.class);
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = WaterKnowApp.getSharedPreferencesEditor(UpdeterPhoneNewPhoneActivity.this);
                        editor.putString("token",token);
                        editor.commit();
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
        dialog.setCancelable(false);
        dialog.show();
    }
}
