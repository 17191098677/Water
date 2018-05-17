package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyFeedBackEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyFeedBackPresenter;
import com.smartcityin.waterknow.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFeedBackActivity extends BaseSwipBackActivity<Commpart.MyFeedBackViewInf, MyFeedBackPresenter> implements Commpart.MyFeedBackViewInf {

    @BindView(R.id.my_feed_back_ed)
    EditText myFeedBackEd;
    @BindView(R.id.myFeed_tv_number)
    TextView myFeedTvNumber;
    @BindView(R.id.my_feed_back_eMail)
    EditText myFeedBackEMail;
    @BindView(R.id.my_feed_back_bt_commit)
    Button myFeedBackBtCommit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String token;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return R.layout.activity_my_feed_back;
    }

    @Override
    protected void initView() {
        tvTitle.setText("意见反馈");
        setSupportActionBar(toolbar);
        token = WaterKnowApp.getToken(this, "token");
    }

    @Override
    protected void initListener() {
        myFeedBackEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                myFeedTvNumber.setText(myFeedBackEd.length() + "/200");
            }
        });
    }

    @OnClick({R.id.line_finish, R.id.my_feed_back_bt_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.my_feed_back_bt_commit:
                if (myFeedBackEd.getText().toString().trim().isEmpty()){
                    Toast.makeText(this, "意见反馈不可为空", Toast.LENGTH_SHORT).show();
                }else{
                    mPresenter=new MyFeedBackPresenter();
                    mPresenter.attarchView(this);
                    mPresenter.setFeedBack(token,myFeedBackEd.getText().toString().trim(),myFeedBackEMail.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void getMyFeedBack(MyFeedBackEntity myFeedBackEntity) {
        int code = myFeedBackEntity.getCode();
        switch (code){
            case 200:
                Intent intent=new Intent(MyFeedBackActivity.this,CommitSuccessActivity.class);
                intent.putExtra("title","意见反馈");
                intent.putExtra("content","提交成功");
                startActivity(intent);
                finish();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }
}
