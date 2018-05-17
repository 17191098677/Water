package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseEditText;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.Register.RegisterSMSMsgPresenter;
import com.smartcityin.waterknow.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePhonePswActivity extends BaseSwipBackActivity<Commpart.RegisterSMSMsgIDViewInf, RegisterSMSMsgPresenter> implements Commpart.RegisterSMSMsgIDViewInf, BaseEditText.OnFocusChangedListener {


    @BindView(R.id.updatePhone_ed_newPhone)
    EditText updatePhoneEdNewPhone;
    @BindView(R.id.updatePhone_newPhone_bt_commit)
    Button updatePhoneNewPhoneBtCommit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.updatePhone_psw)
    LinearLayout updatePhonePsw;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_phone_psw;
    }

    @Override
    protected void initView() {
        tvTitle.setText("修改绑定手机");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onFocusChanged(View v, boolean hasFoucs) {

    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @OnClick({R.id.line_finish, R.id.updatePhone_newPhone_bt_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.updatePhone_newPhone_bt_commit:
                if (isChinaPhoneLegal(updatePhoneEdNewPhone.getText().toString().trim())) {
                    mPresenter = new RegisterSMSMsgPresenter();
                    mPresenter.setModelPhone(updatePhoneEdNewPhone.getText().toString().trim(), "注册");
                    initPopupWindow();
                    mPresenter.attarchView(this);
                } else {
                    Toast.makeText(this, "手机号不可为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private PopupWindow popWindow;

    private void initPopupWindow() {
        View popView = View.inflate(UpdatePhonePswActivity.this, R.layout.popup_progress, null);
        popView.setAlpha(0.3F);
        popWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setFocusable(false);
        popWindow.setOutsideTouchable(false);// 设置同意在外点击消失
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(updatePhonePsw, Gravity.CENTER, 0, 0);
    }

    @Override
    public void getRegisterMsgIdInf(RegisterEntity entity) {
        popWindow.dismiss();
        int code = entity.getCode();
        switch (code) {
            case 200:
                Intent intent = new Intent(this, UpdeterPhoneNewPhoneActivity.class);
                intent.putExtra("newPhone", updatePhoneEdNewPhone.getText().toString().trim());
                intent.putExtra("msg_id", entity.getData().getMsg_id());
                startActivity(intent);
                finish();
                break;
            case 300:
                Toast.makeText(this, "此手机号已经注册是否登录", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void getRegistererror(String error) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
