package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitSuccessActivity extends BaseSwipBackActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_commit_success)
    TextView tvCommitSuccess;
    @BindView(R.id.bt_success)
    Button btSuccess;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commit_success;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        tvTitle.setText(title);
        tvCommitSuccess.setText(content);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.line_finish,R.id.bt_success})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.bt_success:
                finish();
                break;
        }
    }
}
