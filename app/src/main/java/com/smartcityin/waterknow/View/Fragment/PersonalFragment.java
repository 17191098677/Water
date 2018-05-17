package com.smartcityin.waterknow.View.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awarmisland.android.popularrefreshlayout.RefreshLayout;
import com.awarmisland.android.popularrefreshlayout.RefreshListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Adapert.MyFunctionAdapert;
import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseFragment;
import com.smartcityin.waterknow.Base.GlideCircleTransform;
import com.smartcityin.waterknow.Entity.My.MyPersonalEntity;
import com.smartcityin.waterknow.Entity.PersonalFunctionEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.PersonalPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.View.Activity.HomeActivity;
import com.smartcityin.waterknow.View.Activity.HomeInformationActivity;
import com.smartcityin.waterknow.View.Activity.LoginActivity;
import com.smartcityin.waterknow.View.Activity.MyAnnualQueryActivity;
import com.smartcityin.waterknow.View.Activity.MyCommonProblemActivity;
import com.smartcityin.waterknow.View.Activity.MyFeedBackActivity;
import com.smartcityin.waterknow.View.Activity.MyOrderActivity;
import com.smartcityin.waterknow.View.Activity.MySetActivity;
import com.smartcityin.waterknow.View.Activity.MySwitchNumberActivity;
import com.smartcityin.waterknow.View.Activity.RegisterBindingWaterMeterActivity;
import com.smartcityin.waterknow.View.Activity.UserInformationActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment<Commpart.PersonalViewInf, PersonalPresenter> implements Commpart.PersonalViewInf {
    //标题
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    //toolbar
    @BindView(R.id.fragment_home_toolbar)
    Toolbar fragmentHomeToolbar;
    //用户头像
    @BindView(R.id.use_head_img)
    ImageView useHeadImg;
    //用户名字
    @BindView(R.id.tv_login_user)
    TextView tvLoginUser;
    //用户地址
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.bt_start_details)
    ImageView btStartDetails;
    @BindView(R.id.myNews_bt)
    LinearLayout myNewsBt;
    @BindView(R.id.commonProblem_bt)
    LinearLayout commonProblemBt;
    @BindView(R.id.feedback_bt)
    LinearLayout feedbackBt;
    @BindView(R.id.setup_bt)
    LinearLayout setupBt;
    //水表号
    @BindView(R.id.personal_tv_waterNumber)
    TextView personalTvWaterNumber;
    @BindView(R.id.personal_function_grid)
    GridView personalFunctionGrid;
    @BindView(R.id.order_bt)
    LinearLayout orderBt;
    @BindView(R.id.personal_information_bt)
    RelativeLayout personalInformationBt;
    @BindView(R.id.myPersonal_price)
    TextView myPersonalPrice;
    @BindView(R.id.myPersonal_Water)
    TextView myPersonalWater;
    @BindView(R.id.refresh)
    RefreshLayout refresh;
    private HomeActivity activity;
    private Handler handler=new Handler();
    private ArrayList<PersonalFunctionEntity> mList = new ArrayList<>();
    private String token;

    @Override
    protected PersonalPresenter createPresenter() {
        return new PersonalPresenter();
    }

    @Override
    public int getLayoutId() {
        activity = (HomeActivity) getActivity();
        return R.layout.fragment_personal;
    }

    @Override
    protected void initAdapert() {
        MyFunctionAdapert adapert = new MyFunctionAdapert(mList, activity);
        personalFunctionGrid.setAdapter(adapert);
    }

    @Override
    protected void initListener() {
        personalFunctionGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (mList.get(position).getName()) {
                    case "绑定水表":
                        IntentUtils.putExtra(activity, RegisterBindingWaterMeterActivity.class, "PersonalType", "我的页面");
                        break;
                    case "切换户号":
                        IntentUtils.startIntent(activity, MySwitchNumberActivity.class);
                        break;
                    case "年度查询":
                        IntentUtils.startIntent(activity, MyAnnualQueryActivity.class);
                        break;
                    case "客服电话":
                        initDialog();
                        break;
                }
            }
        });
        refresh.setRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.finishRefresh();
                        mPresenter.setMyPersonal(token);
                        mPresenter.attarchView(PersonalFragment.this);
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void initView() {
        activity.setSupportActionBar(fragmentHomeToolbar);
    }
    @Override
    public void initData() {
        mList.add(new PersonalFunctionEntity(R.drawable.binding, "绑定水表"));
        mList.add(new PersonalFunctionEntity(R.drawable.trigger, "切换户号"));
        mList.add(new PersonalFunctionEntity(R.drawable.theannualquery, "年度查询"));
        mList.add(new PersonalFunctionEntity(R.drawable.servicetel, "客服电话"));

    }

    @Override
    public void onStart() {
        super.onStart();
        token = WaterKnowApp.getToken(activity, "token");
        if (!token.equals("")){
            mPresenter = new PersonalPresenter();
            mPresenter.setMyPersonal(token);
            mPresenter.attarchView(this);
        }else{
            /**
             * 退出登录后返回到当前Fragment
             * 退出登录是清空sp文件
             * 当sp文件清空的时候获取到的token值就为空
             * 当sp文件为空的时候就让他跳转到登录界面
             */
            Intent intent=new Intent(activity,LoginActivity.class);
            startActivity(intent);
            activity.finish();
        }
    }

    @OnClick({R.id.order_bt, R.id.myNews_bt, R.id.commonProblem_bt, R.id.feedback_bt, R.id.setup_bt, R.id.personal_information_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_bt:
                IntentUtils.startIntent(activity, MyOrderActivity.class);
                break;
            case R.id.myNews_bt:
                IntentUtils.startIntent(activity, HomeInformationActivity.class);
                break;
            case R.id.commonProblem_bt:
                IntentUtils.startIntent(activity, MyCommonProblemActivity.class);
                break;
            case R.id.feedback_bt:
                IntentUtils.startIntent(activity, MyFeedBackActivity.class);
                break;
            case R.id.setup_bt:
                Intent intent=new Intent(activity,MySetActivity.class);
                startActivity(intent);
                break;
            case R.id.personal_information_bt:
                IntentUtils.startIntent(activity, UserInformationActivity.class);
                break;
        }
    }

    @Override
    public void getMyPersonal(MyPersonalEntity myPersonalEntity) {
        int code = myPersonalEntity.getCode();
        switch (code) {
            case 200:
                MyPersonalEntity.DataBean data = myPersonalEntity.getData();
                //裁剪用户头像
                RequestOptions options = new RequestOptions();
                options.placeholder(R.drawable.user_276)
                        .error(R.drawable.user_276)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(activity)); //设置缓存
                Glide.with(this)
                        .load(data.getHead())
                        .apply(options)
                        .into(useHeadImg);
                tvLoginUser.setText(data.getNickname());
                tvAddress.setText(data.getAddress());
                personalTvWaterNumber.setText(data.getMeter_id());
                myPersonalPrice.setText(data.getPrice() + "");
                myPersonalWater.setText(data.getWater_remain() + "");
                break;
            case 501:
                WaterKnowApp.reNewLogin(activity);
                break;
            case 502:
                WaterKnowApp.reNewLogin(activity);
                break;
        }
    }

    private void initDialog() {
        final String phoneNumber = "010-89780865";
        final BaseDialog dialog = new BaseDialog(activity);
        dialog.show();
        dialog.setTvContent("是否呼叫");
        dialog.setHintText(phoneNumber);
        dialog.setLeftButton("呼叫", new BaseDialog.OnClickerListenerLeft() {
            @Override
            public void setOnClicker() {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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

}
