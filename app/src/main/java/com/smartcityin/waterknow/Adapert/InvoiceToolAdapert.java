package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.My.MyInvoiceToolEntity;
import com.smartcityin.waterknow.Entity.My.MyOrderEntity;
import com.smartcityin.waterknow.R;
import com.smartcityin.waterknow.Utils.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/11
 * E-mail : wkz123011@gmail.com
 */
public class InvoiceToolAdapert extends BaseAdapter {
    private ArrayList<MyInvoiceToolEntity.DataBean> list;
    private Context context;


    public InvoiceToolAdapert(ArrayList<MyInvoiceToolEntity.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.invoice_tool_item, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.myOrderTvYearTime.setText(list.get(position).getTime());
        holder.myOrderPrice.setText(list.get(position).getMoney()+"元");
        holder.myOrderContent.setText(StringUtils.subPhone(list.get(position).getOrder_on()));
        if (list.get(position).isIsFag())
            holder.invoiceImg.setImageResource(R.drawable.selectthecircle);
        else
            holder.invoiceImg.setImageResource(R.drawable.circle);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.invoice_img)
        ImageView invoiceImg;
        @BindView(R.id.myOrder_tv_yearTime)
        TextView myOrderTvYearTime;
        @BindView(R.id.myOrder_content)
        TextView myOrderContent;
        @BindView(R.id.myOrder_price)
        TextView myOrderPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
