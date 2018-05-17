package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.HomeNavigation;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeNavigationAdapert extends BaseAdapter {
    private ArrayList<HomeNavigation> list;
    private Context context;

    public HomeNavigationAdapert(ArrayList<HomeNavigation> list, Context context) {
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
        HomeNavigationViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.home_navigation_item, null);
            holder = new HomeNavigationViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HomeNavigationViewHolder) convertView.getTag();
        }

        holder.homeNavigationImg.setImageResource(list.get(position).getImg());
        holder.homeNavigationTv.setText(list.get(position).getName());
        return convertView;
    }


     class HomeNavigationViewHolder {
        @BindView(R.id.home_navigation_img)
        ImageView homeNavigationImg;
        @BindView(R.id.home_navigation_tv)
        TextView homeNavigationTv;

        HomeNavigationViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
