package com.smartcityin.waterknow.View.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartcityin.waterknow.Adapert.InvoiceToolAdapert;
import com.smartcityin.waterknow.Base.BaseSwipBackActivity;
import com.smartcityin.waterknow.Entity.My.MyInvoiceToolEntity;
import com.smartcityin.waterknow.Entity.My.MyOrderEntity;
import com.smartcityin.waterknow.Global.WaterKnowApp;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyInvoiceToolPresenter;
import com.smartcityin.waterknow.Presenter.PresenterCountry.MyOrderPresenter;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.IntentUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyInvoiceToolActivity extends BaseSwipBackActivity<Commpart.MyInvoiceViewInf, MyInvoiceToolPresenter> implements Commpart.MyInvoiceViewInf {


    @BindView(R.id.invoice_lv)
    ListView invoiceLv;
    @BindView(R.id.invoice_tv_number)
    TextView invoiceTvNumber;
    @BindView(R.id.invoice_tv_price)
    TextView invoiceTvPrice;
    @BindView(R.id.select_img)
    ImageView selectImg;
    @BindView(R.id.invoice_select)
    LinearLayout invoiceSelect;
    @BindView(R.id.invoice_bt_Determine)
    Button invoiceBtDetermine;
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line_finish)
    LinearLayout lineFinish;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<MyInvoiceToolEntity.DataBean> mList = new ArrayList<>();
    private ArrayList<MyInvoiceToolEntity.DataBean> selece_list = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    selece_list.clear();
                    double price = 0.0;
                    for (int i = 0; i <= mList.size() - 1; i++) {
                        MyInvoiceToolEntity.DataBean myOrder = mList.get(i);
                        if (myOrder.isIsFag()) {
                            selece_list.add(myOrder);
                        } else {
                            selece_list.remove(myOrder);
                        }
                    }
                    for (int i = 0; i <= selece_list.size() - 1; i++) {
                        price += selece_list.get(i).getMoney();
                    }
                    invoiceTvNumber.setText(selece_list.size() + "");
                    invoiceTvPrice.setText(new DecimalFormat("##.##").format(price));
                    break;
            }
        }
    };
    private InvoiceToolAdapert invoiceToolAdapert;
    @Override
    protected void initAdapert() {
        invoiceToolAdapert = new InvoiceToolAdapert(mList,this);
        invoiceLv.setAdapter(invoiceToolAdapert);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_invoice_tool;
    }

    @Override
    protected void initView() {
        tvTitle.setText("发票工具");
    }

    @Override
    protected void initData() {
        super.initData();
        String token = WaterKnowApp.getToken(this, "token");
        mPresenter=new MyInvoiceToolPresenter();
        mPresenter.attarchView(this);
        mPresenter.setMyInvoiceTool(token);
    }

    @Override
    protected void initListener() {
        invoiceLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyInvoiceToolEntity.DataBean dataBean = mList.get(position);
                if (dataBean.isIsFag())
                    dataBean.setIsFag(false);
                else
                    dataBean.setIsFag(true);
                invoiceToolAdapert.notifyDataSetChanged();
                Message msg = Message.obtain();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
    }

    private boolean isFlag = false;

    @OnClick({R.id.line_finish, R.id.invoice_select, R.id.invoice_bt_Determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_finish:
                finish();
                break;
            case R.id.invoice_select:
                if (isFlag) {
                    isFlag = false;
                    selectImg.setImageResource(R.drawable.circle);
                    for (int i = 0; i <= mList.size() - 1; i++) {
                        MyInvoiceToolEntity.DataBean dataBean = mList.get(i);
                        dataBean.setIsFag(false);
                    }
                    invoiceToolAdapert.notifyDataSetChanged();
                } else {
                    isFlag = true;
                    selectImg.setImageResource(R.drawable.selectthecircle);
                    for (int i = 0; i <= mList.size() - 1; i++) {
                        MyInvoiceToolEntity.DataBean myOrderEntity = mList.get(i);
                        myOrderEntity.setIsFag(true);
                    }
                    invoiceToolAdapert.notifyDataSetChanged();
                }
                Message msg = Message.obtain();
                msg.what = 0;
                handler.sendMessage(msg);
                break;
            case R.id.invoice_bt_Determine:
                if (selece_list.size()!=0){
                    Intent intent=new Intent(this, MyInToolPaymentActivity.class);
                    intent.putExtra("price",invoiceTvPrice.getText().toString().trim());
                    ArrayList<String> orderList=new ArrayList<>();
                    for (int i = 0; i <=mList.size()-1; i++) {
                        if (mList.get(i).isIsFag()){
                            orderList.add(mList.get(i).getId());
                        }
                    }
                    String[] orders=new String[orderList.size()];
                    for (int i = 0; i <=orderList.size()-1; i++) {
                        orders[i]=orderList.get(i);
                    }
                    intent.putExtra("orders",orders);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "您好，您没有需要开发票的订单", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void getMyInvoice(MyInvoiceToolEntity myInvoiceToolEntity) {
        int code = myInvoiceToolEntity.getCode();
        switch (code){
            case 200:
                List<MyInvoiceToolEntity.DataBean> data = myInvoiceToolEntity.getData();
                mList.addAll(data);
                invoiceToolAdapert.notifyDataSetChanged();
                break;
            case 501:
                break;
            case 502:
                break;
        }
    }
}
