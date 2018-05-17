package com.smartcityin.waterknow.View.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyUpdatePswPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.ValidatorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserPSWActivity extends BaseSwipBackActivity<Commpart.MyUpdatePswViewInf, MyUpdatePswPresenter> implements Commpart.MyUpdatePswViewInf {

    @BindView(R.id.ed_Old_PSW)
    EditText edOldPSW;
    @BindView(R.id.ed_psw_new)
    EditText edPswNew;
    @BindView(R.id.user_commit)
    Button userCommit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_visable)
    ImageView imgVisable;
    @BindView(R.id.img_psw_new)
    ImageView imgPswNew;
    @BindView(R.id.ed_confirm_psw_new)
    EditText edConfirmPswNew;
    @BindView(R.id.img_confirm_psw_new)
    ImageView imgConfirmPswNew;
    private String token;
    private String password;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_psw;
    }

    @Override
    protected void initView() {
        tvTitle.setText("密码管理");
        setSupportActionBar(toolbar);
        token = WaterKnowApp.getToken(this, "token");
        password = WaterKnowApp.getToken(this, "password");
    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.line_finish, R.id.user_commit, R.id.img_visable, R.id.img_psw_new,R.id.img_confirm_psw_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.user_commit:
                String oldPsw = edOldPSW.getText().toString().trim();
                String newPsw = edPswNew.getText().toString().trim();
                if (oldPsw.isEmpty()) {
                    Toast.makeText(this, "旧密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(oldPsw)) {
                        if (ValidatorUtils.isPsw(newPsw)) {
                            if (oldPsw.equals(newPsw)) {
                                Toast.makeText(this, "旧密码和新密码不能相同", Toast.LENGTH_SHORT).show();
                            } else {
                                if (newPsw.equals(edConfirmPswNew.getText().toString().trim())){
                                    mPresenter = new MyUpdatePswPresenter();
                                    mPresenter.attarchView(this);
                                    mPresenter.setMyUpdatePsw(token, oldPsw, newPsw);
                                }else{
                                    Toast.makeText(this, "俩次新密码输入不一致", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(this, "新密码格式不正确应由6-12字母或数字组成", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "旧密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.img_visable:
                if (edOldPSW.isSelected()) {
                    edOldPSW.setSelected(false);
                    imgVisable.setImageResource(R.drawable.buxianshi);
                    edOldPSW.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见
                } else {
                    imgVisable.setImageResource(R.drawable.xianshi);
                    edOldPSW.setSelected(true);
                    edOldPSW.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见
                }
                break;
            case R.id.img_psw_new:
                if (edPswNew.isSelected()) {
                    imgPswNew.setImageResource(R.drawable.buxianshi);
                    edPswNew.setSelected(false);
                    edPswNew.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见
                } else {
                    imgPswNew.setImageResource(R.drawable.xianshi);
                    edPswNew.setSelected(true);
                    edPswNew.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见
                }
                break;
            case R.id.img_confirm_psw_new:
                if (edConfirmPswNew.isSelected()) {
                    imgConfirmPswNew.setImageResource(R.drawable.buxianshi);
                    edConfirmPswNew.setSelected(false);
                    edConfirmPswNew.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);//设置密码不可见
                } else {
                    imgConfirmPswNew.setImageResource(R.drawable.xianshi);
                    edConfirmPswNew.setSelected(true);
                    edConfirmPswNew.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置密码可见
                }
                break;
        }
    }

    @Override
    public void getMyUpdatePsw(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity) {
        int code = myUpdatePhoneYZMEntity.getCode();
        switch (code) {
            case 200:
                initDialog();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }

    private void initDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("修改成功，请重新登录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WaterKnowApp.clearSharedPreferences(UserPSWActivity.this);
                        IntentUtils.startIntent(UserPSWActivity.this, LoginActivity.class);
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UserPSWActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
        dialog.setCancelable(false);
        dialog.show();
    }

}
