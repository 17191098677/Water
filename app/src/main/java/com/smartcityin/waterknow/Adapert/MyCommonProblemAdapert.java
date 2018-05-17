package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/11
 * E-mail : wkz123011@gmail.com
 */
public class MyCommonProblemAdapert extends BaseAdapter {
    private ArrayList<ServiceUserHelpEntity.DataBean> list;
    private Context context;

    public MyCommonProblemAdapert(ArrayList<ServiceUserHelpEntity.DataBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.my_common_problem_item, parent, false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.myCommonProblemTvTitle.setText(list.get(position).getTitle());
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.myCommonProblem_tv_title)
        TextView myCommonProblemTvTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
