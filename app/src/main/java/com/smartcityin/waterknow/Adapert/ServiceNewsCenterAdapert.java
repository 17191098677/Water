package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Entity.Service.ServiceNewsCenterEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceNewsCenterAdapert extends BaseAdapter {
    private ArrayList<ServiceNewsCenterEntity.DataBean> list;
    private Context context;

    public ServiceNewsCenterAdapert(ArrayList<ServiceNewsCenterEntity.DataBean> list, Context context) {
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
        ServiceNewsCenterHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.service_newscenter_item, null);
            holder = new ServiceNewsCenterHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ServiceNewsCenterHolder) convertView.getTag();
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(list.get(position).getPic()).apply(options).into(holder.serviceNewsCenterImg);
        return convertView;
    }

    class ServiceNewsCenterHolder {
        @BindView(R.id.service_newsCenter_img)
        ImageView serviceNewsCenterImg;
        public ServiceNewsCenterHolder(View convertView) {
            ButterKnife.bind(this, convertView);

        }
    }

}
