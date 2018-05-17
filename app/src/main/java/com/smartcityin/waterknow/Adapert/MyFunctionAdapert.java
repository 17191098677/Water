package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.PersonalFunctionEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class MyFunctionAdapert extends BaseAdapter {
    private ArrayList<PersonalFunctionEntity> list;
    private Context context;

    public MyFunctionAdapert(ArrayList<PersonalFunctionEntity> list, Context context) {
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
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.personal_water_meter_information, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.myFunctionImg.setImageResource(list.get(position).getImg());
        holder.myFunctionTv.setText(list.get(position).getName());
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.myFunction_img)
        ImageView myFunctionImg;
        @BindView(R.id.myFunction_tv)
        TextView myFunctionTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
