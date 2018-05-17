package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.ServiceGridEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceGridAdapert extends BaseAdapter {

    private ArrayList<ServiceGridEntity> list;
    private Context context;

    public ServiceGridAdapert(ArrayList<ServiceGridEntity> list, Context context) {
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
        ServiceViewHolder holder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.service_grid_item,parent,false);
            holder=new ServiceViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ServiceViewHolder) convertView.getTag();
        }
        holder.serviceTvTitle.setText(list.get(position).getTitle());
        holder.serviceImgBack.setImageResource(list.get(position).getImg());
        return convertView;
    }

    public class ServiceViewHolder  {
        @BindView(R.id.service_img_back)
        ImageView serviceImgBack;
        @BindView(R.id.service_tv_title)
        TextView serviceTvTitle;
        public ServiceViewHolder(View itemView) {
            ButterKnife.bind(this,itemView);
        }
    }
}
