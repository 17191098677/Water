package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.SwitchNumberAdapert;
import com.smartcityin.waterknow.Base.BaseActivity;
import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Base.swipemenulistview.SwipeMenu;
import com.smartcityin.waterknow.Base.swipemenulistview.SwipeMenuCreator;
import com.smartcityin.waterknow.Base.swipemenulistview.SwipeMenuItem;
import com.smartcityin.waterknow.Base.swipemenulistview.SwipeMenuListView;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.SwitchNumberPresenter;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySwitchNumberActivity extends BaseActivity<Commpart.SwitchNumberViewInf, SwitchNumberPresenter> implements Commpart.SwitchNumberViewInf {

    @BindView(R.id.mySwitchNumber_tv)
    TextView mySwitchNumberTv;
    @BindView(R.id.mySwitchNumber)
    SwipeMenuListView mySwitchNumber;
    @BindView(R.id.mySwitchNumber_commit)
    Button mySwitchNumberCommit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<SwitchNumberEntity.DataBean> mList = new ArrayList<>();
    private SwitchNumberAdapert adapert;
    private String token;
    private String meter_id;
    private String username;
    private String meterID;

    @Override
    protected void initAdapert() {
        if (mList.size() != 0) {
            mySwitchNumberTv.setVisibility(View.GONE);
            mySwitchNumber.setVisibility(View.VISIBLE);
        } else {
            mySwitchNumberTv.setVisibility(View.VISIBLE);
            mySwitchNumber.setVisibility(View.GONE);
        }
        adapert = new SwitchNumberAdapert(this, mList);
        mySwitchNumber.setAdapter(adapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_switch_number;
    }

    @Override
    protected void initData() {
        super.initData();
        SharedPreferences sharedPreferences = WaterKnowApp.getSharedPreferences(this);
        token = sharedPreferences.getString("token", "");
        username = sharedPreferences.getString("username", "");
        mPresenter = new SwitchNumberPresenter();
        mPresenter.attarchView(this);
        mPresenter.setSwitchNumber(token);
    }

    @Override
    protected void initView() {
        tvTitle.setText("切换户号");
        setSupportActionBar(toolbar);
        String username = WaterKnowApp.getToken(this, "username");
        SharedPreferences sp = WaterKnowApp.getUserInformationSharePreferences(this);
        meterID = sp.getString(username + "meterID", "");

    }

    @Override
    protected void initListener() {
        mySwitchNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i <= mList.size() - 1; i++) {
                    if (position == i) {
                        SwitchNumberEntity.DataBean switchNumberEntity = mList.get(i);
                        switchNumberEntity.setFlag(true);
                    } else {
                        SwitchNumberEntity.DataBean switchNumberEntity = mList.get(i);
                        switchNumberEntity.setFlag(false);
                    }
                    initAdapert();
                    adapert.notifyDataSetChanged();
                }
            }
        });
        /**
         * 设置侧滑菜单
         */
        initSwipeMenu();
    }

    private void initSwipeMenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                menu.addMenuItem(deleteItem);
            }
        };
        mySwitchNumber.setMenuCreator(creator);
        mySwitchNumber.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        initDialog(mList.get(position).getMeter_id());
                        break;
                }
                return false;
            }

            private void initDialog(final String meter_id) {
                final BaseDialog dialog = new BaseDialog(MySwitchNumberActivity.this);
                dialog.show();
                dialog.setTitle("确认户号");
                dialog.setTvContent("是否删除这个户号");
                dialog.setHintText(meter_id);
                dialog.setLeftButton("确认", new BaseDialog.OnClickerListenerLeft() {
                    @Override
                    public void setOnClicker() {
                        mPresenter.unBindNumber(token,meter_id);
                        mPresenter.attarchView(MySwitchNumberActivity.this);
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

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @OnClick({R.id.line_finish, R.id.mySwitchNumber_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.mySwitchNumber_commit:
                mPresenter=new SwitchNumberPresenter();
                mPresenter.attarchView(this);
                for (int i = 0; i <=mList.size()-1; i++) {
                    if (mList.get(i).isFlag()){
                        meter_id = mList.get(i).getMeter_id();
                        SharedPreferences.Editor editor = WaterKnowApp.getUserInformationSharedPreferencesEditor(this);
                        editor.putString(username+"meterID",meter_id);
                        editor.commit();
                        mPresenter.setRechargement(meter_id,token);
                    }
                }
                break;
        }
    }
    @Override
    public void getHomeSwitchNumber(SwitchNumberEntity switchNumberEntity) {
        int code = switchNumberEntity.getCode();
        switch (code){
            case 200:
                mList.clear();
                List<SwitchNumberEntity.DataBean> data = switchNumberEntity.getData();
                mList.addAll(data);
                initAdapert();
                for (int i = 0; i < mList.size(); i++) {
                    String meter_id = mList.get(i).getMeter_id();
                    if (meter_id.equals(meterID)){
                        mList.get(i).setFlag(true);
                    }
                }
                adapert.notifyDataSetChanged();
                break;
            case 501:
                break;
            case 502:
                break;

        }
    }
    @Override
    public void getRechargePayment(HomeSwitchNumberCommitEntity entity) {
        if (entity.getCode()==200){
            Intent intent=getIntent();
            SharedPreferences.Editor sharedPreferencesEditor = WaterKnowApp.getUserInformationSharedPreferencesEditor(this);
             sharedPreferencesEditor.putString(username+"meterID", meter_id);
            sharedPreferencesEditor.commit();
            intent.putExtra("meterID",meter_id);
            setResult(-2,intent);
            finish();
        }
        finish();
    }

    @Override
    public void unBindNumber(HomeSwitchNumberCommitEntity entity) {
        int code = entity.getCode();
        switch (code){
            case 200:
                Toast.makeText(this, "解绑成功", Toast.LENGTH_SHORT).show();
                mPresenter.setSwitchNumber(token);
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }
}
