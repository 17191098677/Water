package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

public class HomeNewsAdapert extends BaseAdapter{
    private ArrayList<HomeNewsEntity.DataBean> list;
    private Context context;

    public HomeNewsAdapert(ArrayList<HomeNewsEntity.DataBean> list, Context context) {
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
        View view = View.inflate(context, R.layout.home_news_item, null);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.news_img);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL);//设置缓存
        Glide.with(context)
                .load(list.get(position).getPic())
                .apply(options)
                .into(iv_icon);
        return view;
    }
}
