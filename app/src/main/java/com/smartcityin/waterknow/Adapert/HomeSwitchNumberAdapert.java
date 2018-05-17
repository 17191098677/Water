package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/5/14
 * E-mail : wkz123011@gmail.com
 */
public class HomeSwitchNumberAdapert extends BaseAdapter {
    private ArrayList<SwitchNumberEntity.DataBean> mList;
    private Context context;

    public HomeSwitchNumberAdapert(ArrayList<SwitchNumberEntity.DataBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (holder==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.home_switch_number_item, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_homeWaterID.setText("水表号 ："+mList.get(position).getMeter_id());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_homeWaterID)
        TextView tv_homeWaterID;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
