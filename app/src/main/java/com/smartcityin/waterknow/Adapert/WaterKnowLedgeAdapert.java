package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/4
 * E-mail : wkz123011@gmail.com
 */
public class WaterKnowLedgeAdapert extends BaseAdapter {
    private ArrayList<HomeWaterKnowEntity.DataBean> list;
    private Context context;

    public WaterKnowLedgeAdapert(ArrayList<HomeWaterKnowEntity.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.water_know_item, parent, false);

        TextView tv_name=view.findViewById(R.id.water_know_tv_title);
        TextView tv_time=view.findViewById(R.id.water_know_tv_time);
        ImageView img=view.findViewById(R.id.water_know_img);
        tv_name.setText(list.get(position).getTitle());
        tv_time.setText(list.get(position).getTime());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(list.get(position).getPic())
                .apply(options)
                .into(img);
        return view;
    }
}
