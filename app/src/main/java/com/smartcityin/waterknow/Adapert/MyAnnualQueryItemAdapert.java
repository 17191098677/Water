package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.My.MyAnnualQueryEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/5/7
 * E-mail : wkz123011@gmail.com
 */
public class MyAnnualQueryItemAdapert extends BaseAdapter {
    private Context context;
    private ArrayList<MyAnnualQueryEntity.DataBean.HierarchicalDataBean> list;

    public MyAnnualQueryItemAdapert(Context context, ArrayList<MyAnnualQueryEntity.DataBean.HierarchicalDataBean> list) {
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
        if (convertView==null) {
            convertView = View.inflate(context, R.layout.annual_query_item, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.userWaterLadderTitle.setText(list.get(position).getTitle());
        holder.userWaterProgress.setMax(list.get(position).getMax());
        holder.userWaterProgress.setProgress((int) list.get(position).getWater_consumption());
        holder.tvWater.setText("年度"+list.get(position).getTitle()+"用水量 : "+list.get(position).getWater_consumption()+"m³");
        holder.userWaterTvProgress.setText("年度"+list.get(position).getTitle()+"金额 : "+list.get(position).getPrice()+"元");
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.userWater_ladder_title)
        TextView userWaterLadderTitle;
        @BindView(R.id.userWater_progress)
        ProgressBar userWaterProgress;
        @BindView(R.id.tv_water)
        TextView tvWater;
        @BindView(R.id.userWater_tv_progress)
        TextView userWaterTvProgress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
