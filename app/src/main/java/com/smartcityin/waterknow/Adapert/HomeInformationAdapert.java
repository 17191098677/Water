package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/4
 * E-mail : wkz123011@gmail.com
 */
public class HomeInformationAdapert extends BaseAdapter {
    private ArrayList<HomeInformationEntity.DataBean> list;
    private Context context;

    public HomeInformationAdapert(ArrayList<HomeInformationEntity.DataBean> list, Context context) {
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
        HomeInformationViewHolder holder=null;
        if (convertView == null) {
            convertView= View.inflate(context,R.layout.home_information_item, null);

            holder = new HomeInformationViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HomeInformationViewHolder) convertView.getTag();
        }
        holder.informationActivityTvTitle.setText(list.get(position).getTitle());
        return convertView;
    }

     class HomeInformationViewHolder {
        @BindView(R.id.home_img_information)
        ImageView homeImgInformation;
        @BindView(R.id.information_activity_tv_title)
        TextView informationActivityTvTitle;

        HomeInformationViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
