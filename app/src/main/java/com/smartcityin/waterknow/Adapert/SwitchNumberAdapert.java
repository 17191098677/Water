package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : Mr.老王
 * Created on 2018/4/20
 * E-mail : wkz123011@gmail.com
 */
public class SwitchNumberAdapert extends BaseAdapter {

    private Context context;
    private ArrayList<SwitchNumberEntity.DataBean> list;

    public SwitchNumberAdapert(Context context, ArrayList<SwitchNumberEntity.DataBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder holder = null;
        if (holder == null) {
            convertView = View.inflate(context, R.layout.my_switch_number_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).isFlag())
            holder.checkboxSwitch.setChecked(true);
        else
            holder.checkboxSwitch.setChecked(false);

        holder.checkboxSwitch.setText(list.get(position).getMeter_id());
        holder.tvContent.setText(list.get(position).getWater_value());
        return convertView;
    }
    public void refresh(){
        notifyDataSetChanged();
    }
     class ViewHolder {
        @BindView(R.id.checkbox_switch)
        CheckBox checkboxSwitch;
        @BindView(R.id.tv_content)
        TextView tvContent;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
