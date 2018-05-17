package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/25
 * E-mail : wkz123011@gmail.com
 */
public class ServiceMaintenanceAdapert extends BaseAdapter {
    private Context context;
    private ArrayList<ServiceMaintenanceEntity.DataBean> list;

    public ServiceMaintenanceAdapert(Context context, ArrayList<ServiceMaintenanceEntity.DataBean> list) {
        this.context = context;
        this.list = list;
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
        if (holder==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.my_order_item, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.myOrderTvYearTime.setText(list.get(position).getTime());
        holder.myOrderContent.setText(list.get(position).getFail_description());
        holder.myOrderPrice.setText(list.get(position).getHandle());
        return convertView;
    }

    static class ViewHolder {
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
