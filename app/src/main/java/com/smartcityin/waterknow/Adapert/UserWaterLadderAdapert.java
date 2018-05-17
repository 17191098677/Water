package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.UserWater.UserWaterEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class UserWaterLadderAdapert extends BaseAdapter {
    private ArrayList<UserWaterEntity.DataBean.HierarchicalDataBean> list;
    private Context context;

    public UserWaterLadderAdapert(ArrayList<UserWaterEntity.DataBean.HierarchicalDataBean> list, Context context) {
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
        UserWaterViewHolder holder = null;
        if (convertView==null){
            convertView=View.inflate(context,R.layout.user_water_ladder_item,null);
            holder=new UserWaterViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (UserWaterViewHolder) convertView.getTag();
        }
        holder.userWaterLadderTitle.setText(list.get(position).getTitle());
        holder.userWaterProgress.setMax(list.get(position).getMax());
        holder.userWaterProgress.setProgress(list.get(position).getWater_consumption());
        holder.userWaterTvProgress.setText(list.get(position).getWater_consumption()+"");
        if (list.get(position).getMax()>777777){
            holder.userWaterTvMax.setText("无上限");
        }else{
            holder.userWaterTvMax.setText(list.get(position).getMax()+"");
        }
        return convertView;
    }
     class UserWaterViewHolder {
        @BindView(R.id.userWater_ladder_title)
        TextView userWaterLadderTitle;
        @BindView(R.id.userWater_progress)
        ProgressBar userWaterProgress;
        @BindView(R.id.userWater_tv_progress)
        TextView userWaterTvProgress;
        @BindView(R.id.userWater_tv_max)
        TextView userWaterTvMax;

        UserWaterViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
