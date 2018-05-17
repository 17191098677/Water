package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Entity.Service.ServiceNetWorkEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceNetWorkAdapert extends RecyclerView.Adapter<ServiceNetWorkAdapert.ServiceViewHolder> {

    private ArrayList<ServiceNetWorkEntity.DataBean> list;
    private Context context;
    private OnItemClickerListerner onItemClickerListener;
    public ServiceNetWorkAdapert(ArrayList<ServiceNetWorkEntity.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setOnItemClickerListener(OnItemClickerListerner onItemClickerListener){
        this.onItemClickerListener=onItemClickerListener;
    }
    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServiceViewHolder(LayoutInflater.from(context).inflate(R.layout.service_network_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder holder, final int position) {
        holder.serviceNetWorkTvTitle.setText(list.get(position).getName());
        holder.serviceNetWorkTvAddress.setText(list.get(position).getAddress());
        holder.serviceNetWorkTvNumberPhone.setText(list.get(position).getTel());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
       Glide.with(context).load(list.get(position).getPic()).apply(options).into(holder.serviceNetWorkImg);
       holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                if (onItemClickerListener!=null)
                    onItemClickerListener.setOnClicker(position);
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.serviceNetWork_tv_title)
        TextView serviceNetWorkTvTitle;
        @BindView(R.id.serviceNetWork_tv_address)
        TextView serviceNetWorkTvAddress;
        @BindView(R.id.serviceNetWork_tv_numberPhone)
        TextView serviceNetWorkTvNumberPhone;
        @BindView(R.id.serviceNetWork_img)
        ImageView serviceNetWorkImg;
        @BindView(R.id.service_net_work_item)
        RelativeLayout relativeLayout;
        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface OnItemClickerListerner{
        void setOnClicker(int postion);
    }
}
