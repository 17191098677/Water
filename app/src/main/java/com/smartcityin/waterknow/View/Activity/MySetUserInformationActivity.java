package com.smartcityin.waterknow.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.StyleUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySetUserInformationActivity extends BaseSwipBackActivity {


    @BindView(R.id.tv_Prompt)
    TextView tvPrompt;
    @BindView(R.id.checkbox_getInformation)
    CheckBox checkboxGetInformation;
    @BindView(R.id.checkbox_update)
    CheckBox checkboxUpdate;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_set_user_information;
    }

    @Override
    protected void initView() {
        tvTitle.setText("消息设置");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {
        checkboxGetInformation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvPrompt.setVisibility(View.VISIBLE);
                } else {
                    tvPrompt.setVisibility(View.GONE);
                }
            }
        });
        checkboxUpdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }
}
