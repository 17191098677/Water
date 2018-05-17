package com.smartcityin.waterknow.View.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.Register.RegisterSMSMsgPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.ValidatorUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseSwipBackActivity<Commpart.RegisterSMSMsgIDViewInf,RegisterSMSMsgPresenter> implements Commpart.RegisterSMSMsgIDViewInf{


    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.register_bt)
    Button registerBt;
    @BindView(R.id.register)
    RelativeLayout register;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String register_title;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        register_title = intent.getStringExtra("register");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        edPhone.setText(phoneNumber);
        if ("注册".equals(register_title)) {
            tvTitle.setText("注册");
            initRegister();
        } else if ("忘记密码".equals(register_title)) {
            tvTitle.setText("忘记密码");
            initForget();
        }else if("绑定手机号".equals(register_title)){
            tvTitle.setText("绑定手机号");
        }
        setSupportActionBar(toolbar);
    }
    //忘记密码页面
    private void initForget() {

    }
    //注册页面
    private void initRegister() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.line_finish, R.id.register_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.register_bt:
                if (ValidatorUtils.isChinaPhoneLegal(edPhone.getText().toString().trim())) {
                    showRegisterDialog();
                } else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showRegisterDialog() {
        final BaseDialog dialog = new BaseDialog(this);
        dialog.show();
        dialog.setLeftButton("确定", new BaseDialog.OnClickerListenerLeft() {
            private PopupWindow popWindow;
            @Override
            public void setOnClicker() {
                if ("注册".equals(register_title)){
                    initPhone();
                }else if ("忘记密码".equals(register_title)){
                   initPswPhone();
                }else if ("绑定手机号".equals(register_title)){
                    initBindingPhone();
                }
                dialog.dismiss();
            }

            /**
             * 绑定手机号
             */
            private void initBindingPhone() {
                mPresenter = new RegisterSMSMsgPresenter();
                mPresenter.setModelPhone(edPhone.getText().toString().trim(),"注册");
                initPopupWindow();
                mPresenter.attarchView(new Commpart.RegisterSMSMsgIDViewInf() {
                    @Override
                    public void getRegisterMsgIdInf(RegisterEntity entity) {
                        String msg_id = entity.getData().getMsg_id();
                        int code = entity.getCode();
                        popWindow.dismiss();
                        switch (code){
                            case 200:
                                Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                                intent.putExtra("phone_number", edPhone.getText().toString().trim());
                                intent.putExtra("titleID", "绑定手机号");
                                intent.putExtra("msg_id",msg_id);
                                startActivity(intent);
                                finish();
                                break;
                            case 300:
                                Toast.makeText(RegisterActivity.this, entity.getValue(), Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                break;
                            case 501:
                                break;

                        }
                    }
                    @Override
                    public void getRegistererror(String error) {
                        Log.e("ERROR",error);
                    }
                });
            }

            //忘记密码
            private void initPswPhone() {
                mPresenter=new RegisterSMSMsgPresenter();
                mPresenter.setModelPhone(edPhone.getText().toString().trim(),"忘记密码");
                initPopupWindow();
                mPresenter.attarchView(new Commpart.RegisterSMSMsgIDViewInf() {
                    @Override
                    public void getRegisterMsgIdInf(RegisterEntity entity) {
                        String msg_id = entity.getData().getMsg_id();
                        popWindow.dismiss();
                        if (entity.getCode()==200){
                            Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                            intent.putExtra("phone_number", edPhone.getText().toString().trim());
                            intent.putExtra("titleID","忘记密码");
                            intent.putExtra("msg_id",msg_id);
                            startActivity(intent);
                            finish();
                        }else if (entity.getCode()==300){
                            initDialog();
                        }
                    }

                    private void initDialog() {
                        popWindow.dismiss();
                        final BaseDialog dialog = new BaseDialog(RegisterActivity.this);
                        dialog.show();
                        dialog.setHintText("此账号未注册，是否跳转到注册页面");
                        dialog.setLeftButton("确定", new BaseDialog.OnClickerListenerLeft() {
                            @Override
                            public void setOnClicker() {
                                Intent intent = new Intent(RegisterActivity.this,RegisterActivity.class);
                                intent.putExtra("register","注册");
                                intent.putExtra("phoneNumber",edPhone.getText().toString().trim());
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }
                        });
                        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
                            @Override
                            public void setOnClicker() {
                                dialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void getRegistererror(String error) {

                    }
                });

            }
            //注册
            private void initPhone() {
                mPresenter = new RegisterSMSMsgPresenter();
                mPresenter.setModelPhone(edPhone.getText().toString().trim(),"注册");
                initPopupWindow();
                mPresenter.attarchView(new Commpart.RegisterSMSMsgIDViewInf() {
                    @Override
                    public void getRegisterMsgIdInf(RegisterEntity entity) {
                        String msg_id = entity.getData().getMsg_id();
                        int code = entity.getCode();
                        popWindow.dismiss();
                        switch (code){
                            case 200:
                                Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                                intent.putExtra("phone_number", edPhone.getText().toString().trim());
                                intent.putExtra("titleID", "注册");
                                intent.putExtra("msg_id",msg_id);
                                startActivity(intent);
                                finish();
                                break;
                            case 300:
                                Toast.makeText(RegisterActivity.this, entity.getValue(), Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                break;
                            case 501:
                                break;

                        }
                    }
                    @Override
                    public void getRegistererror(String error) {
                        Log.e("ERROR",error);
                    }
                });
            }

            private void initPopupWindow() {
                View popView = View.inflate(RegisterActivity.this, R.layout.popup_progress, null);
               popView.setAlpha(0.3F);
                popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                popWindow.setBackgroundDrawable(new BitmapDrawable());
                popWindow.setFocusable(false);
                popWindow.setOutsideTouchable(false);// 设置同意在外点击消失
                ColorDrawable dw = new ColorDrawable(0x30000000);
                popWindow.setBackgroundDrawable(dw);
                popWindow.showAtLocation(register, Gravity.CENTER, 0, 0);
            }

        });
        dialog.setRightButton("取消", new BaseDialog.OnClickerListenerRight() {
            @Override
            public void setOnClicker() {
                dialog.dismiss();
            }
        });
        dialog.setHintText(edPhone.getText().toString().trim());
    }


    @Override
    public void getRegisterMsgIdInf(RegisterEntity entity) {
        int code = entity.getCode();
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRegistererror(String error) {

    }
}
