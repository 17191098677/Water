package com.smartcityin.waterknow.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.HomeSearchAdapert;
import com.smartcityin.waterknow.Base.BaseDialog;
import com.smartcityin.waterknow.Base.BaseEditText;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.Home.HomeSearchEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.HomeSearchPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.StyleUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSearchActivity extends BaseSwipBackActivity<Commpart.HomeSearchViewInf,HomeSearchPresenter> implements Commpart.HomeSearchViewInf {

    @BindView(R.id.img_cancel)
    ImageView imgCancel;
    @BindView(R.id.ed_Search)
    BaseEditText edSearch;
    @BindView(R.id.home_search_line)
    LinearLayout homeSearchLine;
    @BindView(R.id.search_Determine)
    TextView searchDetermine;
    @BindView(R.id.home_search_toolBar)
    Toolbar homeSearchToolBar;
    @BindView(R.id.search_lv)
    ListView searchLv;
    @BindView(R.id.tv_Probably_search)
    LinearLayout tvProbablySearch;
    private ArrayList<HomeSearchEntity.DataBean> search_list=new ArrayList<>();
    private String token;
    private HomeSearchAdapert homeSearchAdapert;

    @Override
    protected void initAdapert() {
        if (search_list.size()>=1){
            searchLv.setVisibility(View.VISIBLE);
            tvProbablySearch.setVisibility(View.GONE);
            homeSearchAdapert = new HomeSearchAdapert(search_list,this);
            searchLv.setAdapter(homeSearchAdapert);
        }else{
            tvProbablySearch.setVisibility(View.VISIBLE);
            searchLv.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        token = WaterKnowApp.getToken(this, "token");
    }

    @Override
    protected void initListener() {
        edSearch.setOnListener(new BaseEditText.OnFocusChangedListener() {
            @Override
            public void onFocusChanged(View v, boolean hasFoucs) {

            }
        });
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter=new HomeSearchPresenter();
                mPresenter.attarchView(HomeSearchActivity.this);
                if (edSearch.getText().toString().trim().equals("")) {
                    searchLv.setVisibility(View.GONE);
                    tvProbablySearch.setVisibility(View.VISIBLE);
                }else {
                    mPresenter.setHomeSearch(token, edSearch.getText().toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (search_list.get(position).getType()){
                    case 0:
                        Intent intent=new Intent(HomeSearchActivity.this, WebDetailsActivity.class);
                        intent.putExtra("WebUrl",search_list.get(position).getValue());
                        startActivity(intent);
                        break;
                    case 1:
                        initDialog(search_list.get(position).getValue());
                        break;
                }
            }
        });
    }

    @OnClick({R.id.img_cancel, R.id.search_Determine, R.id.home_search_toolBar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                finish();
                break;
            case R.id.search_Determine:
                mPresenter=new HomeSearchPresenter();
                mPresenter.attarchView(this);
                if (edSearch.getText().toString()==null)
                    mPresenter.setHomeSearch(token,"");
                else
                    mPresenter.setHomeSearch(token,edSearch.getText().toString().trim());
                break;
        }
    }

    @Override
    public void getHomeSearch(HomeSearchEntity homeSearchEntity) {
        int code = homeSearchEntity.getCode();
        Log.e("QQQ",homeSearchEntity.getCode()+"----"+homeSearchEntity.getValue());
        switch (code){
            case 200:
                search_list.clear();
                List<HomeSearchEntity.DataBean> data = homeSearchEntity.getData();
                if (data.size()==0){
                    searchLv.setVisibility(View.GONE);
                    tvProbablySearch.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "暂无该搜索界面", Toast.LENGTH_SHORT).show();
                }else{
                    search_list.addAll(data);
                    initAdapert();
                    homeSearchAdapert.notifyDataSetChanged();
                }
                break;
            case 501:
                WaterKnowApp.reNewLogin(this);
                break;
            case 502:
                WaterKnowApp.reNewLogin(this);
                break;
        }
    }
    private void initDialog(final String phoneNumber) {
        final BaseDialog dialog = new BaseDialog(this);
        dialog.show();
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
