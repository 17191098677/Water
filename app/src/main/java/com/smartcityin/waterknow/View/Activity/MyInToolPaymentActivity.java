package com.smartcityin.waterknow.View.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyToolPaymentPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.ValidatorUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyInToolPaymentActivity extends BaseSwipBackActivity<Commpart.MyToolPaymentViewInf, MyToolPaymentPresenter> implements RadioGroup.OnCheckedChangeListener, Commpart.MyToolPaymentViewInf {

    //纸质发票和电子发票
    @BindView(R.id.invoice_Electronics_RadioBt)
    RadioButton invoiceElectronicsRadioBt;
    @BindView(R.id.invoice_Paper_RadioBt)
    RadioButton invoicePaperRadioBt;
    @BindView(R.id.invoice_rg_type)
    RadioGroup invoiceRgType;
    //抬头类型
    @BindView(R.id.in_toolPayment)
    TextView inToolPayment;
    //企业单位
    @BindView(R.id.rb_bt_enterprise)
    RadioButton rbBtEnterprise;
    //个人单位
    @BindView(R.id.rb_bt_personal)
    RadioButton rbBtPersonal;
    //单位选择的
    @BindView(R.id.in_danwei_rg)
    RadioGroup inDanweiRg;
    //发票抬头
    @BindView(R.id.in_tool_ed)
    EditText inToolEd;
    //发票内容
    @BindView(R.id.in_tool_ed_content)
    EditText inToolEdContent;
    //发票价格
    @BindView(R.id.in_tool_tv_price)
    TextView inToolTvPrice;
    //更多信息
    @BindView(R.id.in_tool_bt_information)
    LinearLayout inToolBtInformation;
    @BindView(R.id.tv_address_type)
    TextView tvAddressType;
    //输入电子邮箱
    @BindView(R.id.electronics_ed_address)
    EditText electronicsEdAddress;
    //电子发票微信
    @BindView(R.id.electronics_weChat)
    LinearLayout electronicsWeChat;
    //电子发票支付宝
    @BindView(R.id.electronics_ZFB)
    LinearLayout electronicsZFB;
    //纸质发票填写收件人
    @BindView(R.id.paper_ed_Addressee)
    EditText paperEdAddressee;
    //纸质发票手机号
    @BindView(R.id.paper_ed_Phone)
    EditText paperEdPhone;
    //填写详细地址
    @BindView(R.id.paper_ed_detailedAddress)
    EditText paperEdDetailedAddress;
    //微信支付
    @BindView(R.id.paper_payment_weChat)
    RelativeLayout paperPaymentWeChat;
    //支付宝支付
    @BindView(R.id.paper_payment_ZFB)
    RelativeLayout paperPaymentZFB;
    //货到付款
    @BindView(R.id.paper_payment_Delivery)
    RelativeLayout paperPaymentDelivery;
    //提交
    @BindView(R.id.in_tool_bt_commit)
    Button inToolBtCommit;
    @BindView(R.id.invoice_Electronics_layout)
    LinearLayout invoiceElectronics;
    @BindView(R.id.invoice_paper_layout)
    LinearLayout invoicePaper;
    //纳税人识别号
    @BindView(R.id.in_paragraph_ed)
    EditText inParagraphEd;
    @BindView(R.id.line_paragraph_bt)
    LinearLayout lineParagraphBt;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.paper_payment_weChat_CheckBox)
    CheckBox paperPaymentWeChatCheckBox;
    @BindView(R.id.paper_payment_ZFB_CheckBox)
    CheckBox paperPaymentZFBCheckBox;
    @BindView(R.id.paper_payment_Delivery_CheckBox)
    CheckBox paperPaymentDeliveryCheckBox;
    @BindView(R.id.in_tool_ed_information)
    EditText inToolEdInformation;
    private String token;
    private String[] orders;
    private CityPicker cityPicker;

    @Override
    protected void initAdapert() {

    }

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_my_in_tool_payment;
    }

    @Override
    protected void initView() {
        RadioButton rb = (RadioButton) invoiceRgType.getChildAt(0);
        rb.setChecked(true);
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        orders = intent.getStringArrayExtra("orders");
        inToolTvPrice.setText(price);
        RadioButton danweiBt = (RadioButton) inDanweiRg.getChildAt(0);
        danweiBt.setChecked(true);
        lineParagraphBt.setVisibility(View.VISIBLE);
        inDanweiRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_bt_enterprise:
                        lineParagraphBt.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_bt_personal:
                        lineParagraphBt.setVisibility(View.GONE);
                        break;
                }
            }
        });
        tvTitle.setText("发票工具");
        setSupportActionBar(toolbar);
        token = WaterKnowApp.getToken(this, "token");
    }

    @Override
    protected void initListener() {
        invoiceRgType.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.invoice_Electronics_RadioBt:
                invoiceElectronics.setVisibility(View.VISIBLE);
                invoicePaper.setVisibility(View.GONE);
                break;
            case R.id.invoice_Paper_RadioBt:
                invoiceElectronics.setVisibility(View.GONE);
                invoicePaper.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.line_finish, R.id.in_tool_bt_commit, R.id.tv_address, R.id.paper_payment_weChat, R.id.paper_payment_ZFB, R.id.paper_payment_Delivery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.in_tool_bt_commit:
                initCommit();
                break;
            case R.id.tv_address:
                selectAddress();
                break;
            case R.id.paper_payment_weChat:
                initCheckBox();
                paperPaymentWeChatCheckBox.setChecked(true);
                break;
            case R.id.paper_payment_ZFB:
                initCheckBox();
                paperPaymentZFBCheckBox.setChecked(true);
                break;
            case R.id.paper_payment_Delivery:
                initCheckBox();
                paperPaymentDeliveryCheckBox.setChecked(true);
                break;
        }
    }

    private void initCommit() {
        mPresenter = new MyToolPaymentPresenter();
        int checkedRadioButtonId = invoiceRgType.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            //电子
            case R.id.invoice_Electronics_RadioBt:
                int inDanweiRgCheckedRadioButtonId = inDanweiRg.getCheckedRadioButtonId();
                switch (inDanweiRgCheckedRadioButtonId) {
                    //企业
                    case R.id.rb_bt_enterprise:
                       initEnterprise();
                        break;
                    //个人
                    case R.id.rb_bt_personal:
                       initPersonal();
                        break;
                }
                break;
            //纸质
            case R.id.invoice_Paper_RadioBt:
                int inDanweiRgCheckedRadioButtonId2 = inDanweiRg.getCheckedRadioButtonId();
                switch (inDanweiRgCheckedRadioButtonId2) {
                    //企业
                    case R.id.rb_bt_enterprise:
                       initPaperEnterprise();
                        break;
                    //个人
                    case R.id.rb_bt_personal:
                        initPaperPersonal();
                        break;
                }
                break;
        }
    }

    /**
     * 纸质个人
     */
    private void initPaperPersonal() {
        Map<String, String> enterpriseMap = new HashMap<>();
        enterpriseMap.put("token", token);
        enterpriseMap.put("invoice_type",1 + "");
        enterpriseMap.put("title_type", 0 + "");
        enterpriseMap.put("further_information", inToolEdInformation.getText().toString().trim()+"");
        if (inToolEd.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "发票抬头不能为空", Toast.LENGTH_SHORT).show();
        } else {
            enterpriseMap.put("invoice_title", inToolEd.getText().toString().trim());
                if (inToolEdContent.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "发票内容不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    enterpriseMap.put("invoice_content", inToolEdContent.getText().toString().trim());
                    if (paperEdAddressee.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "收件人不能为空", Toast.LENGTH_SHORT).show();
                    }else {
                        enterpriseMap.put("addressee",paperEdAddressee.getText().toString().trim());
                        if (ValidatorUtils.isMobile(paperEdPhone.getText().toString().trim())){
                            enterpriseMap.put("contact_number",paperEdPhone.getText().toString().trim());
                            String[] split = tvAddress.getText().toString().split("-");
                                enterpriseMap.put("province",split[0]);
                                enterpriseMap.put("city",split[1]);
                                enterpriseMap.put("area",split[2]);
                                if (paperEdDetailedAddress.getText().toString().trim().isEmpty()){
                                    Toast.makeText(this, "详细信息地址不可为空", Toast.LENGTH_SHORT).show();
                                }else{
                                    enterpriseMap.put("address",paperEdDetailedAddress.getText().toString().trim());
                                    mPresenter.setElectronicsToolPayment(enterpriseMap,orders);
                                    mPresenter.attarchView(this);
                                }
                        }else{
                            Toast.makeText(this, "手机号格式不正确，请填写正确格式的手机号", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        }
    }

    //纸质企业
    private void initPaperEnterprise() {
        Map<String, String> enterpriseMap = new HashMap<>();
        enterpriseMap.put("token", token);
        enterpriseMap.put("invoice_type",1 + "");
        enterpriseMap.put("title_type", 1 + "");
        enterpriseMap.put("further_information", inToolEdInformation.getText().toString().trim()+"");
        if (inToolEd.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "发票抬头不能为空", Toast.LENGTH_SHORT).show();
        } else {
            enterpriseMap.put("invoice_title", inToolEd.getText().toString().trim());
            if (inParagraphEd.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "您好，企业单位申请发票，税号不能为空", Toast.LENGTH_SHORT).show();
            } else {
                enterpriseMap.put("duty_paragraph", inParagraphEd.getText().toString().trim());
                if (inToolEdContent.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "发票内容不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    enterpriseMap.put("invoice_content", inToolEdContent.getText().toString().trim());
                    if (paperEdAddressee.getText().toString().trim().isEmpty()){
                        Toast.makeText(this, "收件人不能为空", Toast.LENGTH_SHORT).show();
                    }else {
                        enterpriseMap.put("addressee",paperEdAddressee.getText().toString().trim());
                        if (ValidatorUtils.isMobile(paperEdPhone.getText().toString().trim())){
                            enterpriseMap.put("contact_number",paperEdPhone.getText().toString().trim());
                            String[] split = tvAddress.getText().toString().split("-");
                            for (int i = 0; i < split.length; i++) {
                                Log.e("QQQ",split[i]);
                            }
                            enterpriseMap.put("province",split[0]);
                                enterpriseMap.put("city",split[1]);
                                enterpriseMap.put("area",split[2]);
                                if (paperEdDetailedAddress.getText().toString().trim().isEmpty()){
                                    Toast.makeText(this, "详细信息地址不可为空", Toast.LENGTH_SHORT).show();
                                }else{
                                    enterpriseMap.put("address",paperEdDetailedAddress.getText().toString().trim());
                                    mPresenter.setElectronicsToolPayment(enterpriseMap,orders);
                                    mPresenter.attarchView(this);
                                }
                        }else{
                            Toast.makeText(this, "手机号格式不正确，请填写正确格式的手机号", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    //电子个人
    private void initPersonal() {
        Map<String, String> personalMap = new HashMap<>();
        personalMap.put("token",token);
        personalMap.put("invoice_type", 0 + "");
        personalMap.put("title_type", 0 + "");
        personalMap.put("further_information", inToolEdInformation.getText().toString().trim()+"");
        if (inToolEd.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "发票抬头不能为空", Toast.LENGTH_SHORT).show();
        } else {
            personalMap.put("invoice_title", inToolEd.getText().toString().trim());
            if (inToolEdContent.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "发票内容不可为空", Toast.LENGTH_SHORT).show();
            } else {
                personalMap.put("invoice_content", inToolEdContent.getText().toString().trim());
                if (ValidatorUtils.isEmail(electronicsEdAddress.getText().toString().trim())){
                    personalMap.put("email",electronicsEdAddress.getText().toString());
                    mPresenter.setElectronicsToolPayment(personalMap,orders);
                    mPresenter.attarchView(this);
                }else{
                    Toast.makeText(this, "电子邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //电子企业
    private void initEnterprise() {
        Map<String, String> enterpriseMap = new HashMap<>();
        enterpriseMap.put("token", token);
        enterpriseMap.put("invoice_type", 0 + "");
        enterpriseMap.put("title_type", 1 + "");
        enterpriseMap.put("further_information", inToolEdInformation.getText().toString().trim()+"");
        if (inToolEd.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "发票抬头不能为空", Toast.LENGTH_SHORT).show();
        } else {
            enterpriseMap.put("invoice_title", inToolEd.getText().toString().trim());
            if (inParagraphEd.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "您好，企业单位申请发票，税号不能为空", Toast.LENGTH_SHORT).show();
            } else {
                enterpriseMap.put("duty_paragraph", inParagraphEd.getText().toString().trim());
                if (inToolEdContent.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "发票内容不可为空", Toast.LENGTH_SHORT).show();
                } else {
                    enterpriseMap.put("invoice_content", inToolEdContent.getText().toString().trim());
                    if (ValidatorUtils.isEmail(electronicsEdAddress.getText().toString().trim())){
                        enterpriseMap.put("email",electronicsEdAddress.getText().toString());
                        mPresenter.setElectronicsToolPayment(enterpriseMap,orders);
                        mPresenter.attarchView(this);
                    }else{
                        Toast.makeText(this, "电子邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void initCheckBox() {
        paperPaymentDeliveryCheckBox.setEnabled(false);
        paperPaymentDeliveryCheckBox.setChecked(false);
        paperPaymentZFBCheckBox.setEnabled(false);
        paperPaymentZFBCheckBox.setChecked(false);
        paperPaymentWeChatCheckBox.setEnabled(false);
        paperPaymentWeChatCheckBox.setChecked(false);
    }

    private void selectAddress() {
        cityPicker = new CityPicker.Builder(MyInToolPaymentActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        setCity();
    }
    private void setCity(){
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                tvAddress.setText(province.trim()+ "-" +city.trim() + "-" +district.trim() );
            }
        });
    }

    @Override
    public void getMyToolPayment(MyInToolPAymentEntity entity) {
        int code = entity.getCode();
        Log.e("QQQ",entity.getCode()+"::::"+entity.getValue());
        switch (code){
            case 200:
                Intent intent=new Intent(MyInToolPaymentActivity.this,CommitSuccessActivity.class);
                intent.putExtra("title","发票工具");
                intent.putExtra("content","开取发票成功");
                startActivity(intent);
                finish();
                break;
        }
    }

}
