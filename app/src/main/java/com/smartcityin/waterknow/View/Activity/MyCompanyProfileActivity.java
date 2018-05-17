package com.smartcityin.waterknow.View.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;
import com.smartcityin.waterknow.Utils.StyleUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCompanyProfileActivity extends BaseSwipBackActivity {

    @BindView(R.id.myCompany_tv_title)
    TextView myCompany_tv_title;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String content="\t\t\t\t北京华航能信科技有限公司是一家专门从\n事地下管网信息化、数字城市、智慧城市及相\n关领域关键技术研发和产业化的高科技公司。\n公司主要依托香港科技大学，整合剑桥大学、\n悉尼大学、东南大学、上海科技大学等国内外\n著名高校科研强大团队，在技术领域实时保持\n国际的前瞻性。公司秉承“以人为本，智慧参\n与，激励创新，协同运作”的理念，以物联网\n、云计算等新一代信息技术的支撑，致力于培\n养面向知识社会的下一代创新，将智慧城市的\n概念落实到位，矢志成为中国乃至世界一流的\n智慧城市建设与运营的服务商。\n\t\t\t\t公司着重从智慧水务管网为突破，全面感\n知城市取、供、用、排水基础信息，整合和利\n" +
            "用与水务相关的各类信息，构建智慧水务大数\n据中心；通过水资源管理、水环境保护等核心\n业务的开展，成为城市应急指挥系统的重要组\n成部分，为业务主管部门、涉水企业和社会公\n众提供信息服务，促进城市公众服务体系和社\n会管理体系建设；提高水资源管理利用效率、\n加强水环境监督与保护和建设生态宜居城市，\n最达实现“人水和谐”。并且以物联网终端产\n品如无线智能物联网水表完成计量及水质自动\n监测，致力服务于居民，更将阀控联网应用于\n流体甚至高危化产品的运输监控以及排污系统\n，通过公司自主研发的智慧水务平台管理系统\n" +
            "进行实时的监控和管理，形成技术先进、功能\n齐全、安全可靠、绿色健康的超大规模智慧水\n务管网系统。填补了国内、外智慧水务管网技\n术的空白，开启了国内智慧水务新时代。\n\t\t\t\t北京华航能信科技有限公司将严格按照科\n学、高效、严谨、创新的管理要求，秉承“高\n新技术创造优质产品，周到服务孕育无限商机”\n的经营理念，建立制度化、标准化、系统化的\n运营机制和管理体系，打造成最值得信赖的智\n慧水务系统方案设计与服务供应商，为智慧城\n市的发展做出贡献。\n";
    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_company_profile;
    }

    @Override
    protected void initView() {
        String title = IntentUtils.getStringExtra(this, "title");
        myCompany_tv_title.setText(title);
        tvTitle.setText("");
        setSupportActionBar(toolbar);
        if (title.equals("服务协议")){

        }else if (title.equals("公司简介")){
            tvContent.setText(content);
        }
    }

    @Override
    protected void initData() {
        super.initData();


    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.line_finish)
    public void onViewClicked() {
        finish();
    }
}
