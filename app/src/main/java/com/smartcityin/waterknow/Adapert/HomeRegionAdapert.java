package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.R;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class HomeRegionAdapert extends RecyclerView.Adapter<HomeRegionAdapert.HomeRegionViewHolder>{

    private ArrayList<String> list;
    private Context context;
    private OnClicker onClicker;
    public void setOnClicker(OnClicker onClicker){
        this.onClicker=onClicker;
    }
    public HomeRegionAdapert(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public HomeRegionAdapert.HomeRegionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeRegionViewHolder(LayoutInflater.from(context).inflate(R.layout.home_region_item,parent,false));
    }

    @Override
    public void onBindViewHolder(HomeRegionAdapert.HomeRegionViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        holder.region_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClicker!=null)
                    onClicker.setOnClickerListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeRegionViewHolder extends RecyclerView.ViewHolder {

         TextView tv;
         LinearLayout region_item;

        public HomeRegionViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.region_tv_country);
            region_item=itemView.findViewById(R.id.region_item);
        }

    }
    public interface OnClicker{
        void setOnClickerListener(int position);
    }
}
