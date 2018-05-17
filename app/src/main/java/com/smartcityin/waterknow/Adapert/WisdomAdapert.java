package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.Entity.Home.WisdomEntity;
import com.smartcityin.waterknow.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class WisdomAdapert extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<WisdomEntity.DataBean> list;
    private final int IMAGEONEVIEW = 0;
    private final int IMAGETWOVIEW = 1;
    private OnItemClicker onItemClickerListener;

    //脚布局
    public static final int TYPE_FOOTER = 2;

    public WisdomAdapert(Context context, ArrayList<WisdomEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        //如果position加1正好等于所有item的总和,说明是最后一个item,将它设置为脚布局
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return list.get(position).getType();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case IMAGEONEVIEW:
                holder = new WisdomViewONEHolder(LayoutInflater.from(context).inflate(R.layout.wisdom_item_one_img, parent, false));
                break;
            case IMAGETWOVIEW:
                holder = new WisdomViewTWOHolder(LayoutInflater.from(context).inflate(R.layout.wisdom_item_two_img, parent, false));
                break;
            case TYPE_FOOTER:
                View view=LayoutInflater.from(context).inflate(R.layout.waterknow_loadmore, parent, false);
                holder=new FootViewHolder(view);
                break;
        }
        return holder;
    }
    //加载
    public static final int LOAD = 0;
    //不能加载
    public static final int NOLOAD = 1;

    //脚布局当前的状态,默认为没有更多
    int footer_state = 1;
    /**
     * 改变脚布局的状态的方法,在activity根据请求数据的状态来改变这个状态
     *
     * @param state
     */
    public void changeState(int state) {
        this.footer_state = state;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case IMAGEONEVIEW:
                final WisdomViewONEHolder oneHolder = (WisdomViewONEHolder) holder;
                oneHolder.wisdomTvTitle.setText(list.get(position).getTitle());
                RequestOptions options = new RequestOptions();
                options.placeholder(R.drawable.zhanwei)
                        .error(R.drawable.errorjpg)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(context)
                        .load(list.get(position).getPic().get(0))
                        .apply(options)
                        .into(oneHolder.wisdomImg);
                    oneHolder.wisdomItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (onItemClickerListener!=null)
                                onItemClickerListener.setOnClicker(position);
                        }
                    });
                break;
            case IMAGETWOVIEW:
                WisdomViewTWOHolder twoHolder = (WisdomViewTWOHolder) holder;
                twoHolder.wisdomTvTitle.setText(list.get(position).getTitle());
                RequestOptions option = new RequestOptions();
                option.placeholder(R.drawable.zhanwei)
                        .error(R.drawable.errorjpg)
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(context)
                        .load(list.get(position).getPic().get(0))
                        .apply(option)
                        .into(twoHolder.wisdomOneImg);
                if (list.get(position).getPic().size() >= 2) {
                    Glide.with(context)
                            .load(list.get(position).getPic().get(1))
                            .apply(option)
                            .into(twoHolder.wisdomTwoImg);
                }
                if (list.get(position).getPic().size() >= 3) {
                    Glide.with(context)
                            .load(list.get(position).getPic().get(2))
                            .apply(option)
                            .into(twoHolder.wisdomThreeImg);
                }
                twoHolder.wisdomItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickerListener!=null)
                            onItemClickerListener.setOnClicker(position);
                    }
                });
                break;
            case TYPE_FOOTER:
                FootViewHolder footViewHolder = (FootViewHolder) holder;
                if (footer_state==LOAD) {//如果第一个就是脚布局,,那就让他隐藏
                    footViewHolder.footView.setVisibility(View.VISIBLE);
                    footViewHolder.noLoadMore.setVisibility(View.GONE);
                }else if (footer_state==NOLOAD){
                    footViewHolder.noLoadMore.setVisibility(View.VISIBLE);
                    footViewHolder.footView.setVisibility(View.GONE);
                }
                break;
        }

    }
    public void setOnItemClickerListener(OnItemClicker onItemClickerListener){
        this.onItemClickerListener=onItemClickerListener;
    }
    @Override
    public int getItemCount() {
         return list != null ? list.size() + 1 : 0;
    }
    public class FootViewHolder extends RecyclerView.ViewHolder {
        LinearLayout footView;
        LinearLayout noLoadMore;
        public FootViewHolder(View itemView) {
            super(itemView);
            footView=itemView.findViewById(R.id.footView_load_more);
            noLoadMore=itemView.findViewById(R.id.footView_noMore);
        }
    }
    public class WisdomViewONEHolder extends RecyclerView.ViewHolder {
        TextView wisdomTvTitle;
        ImageView wisdomImg;
        TextView wisdomTvTimer;
        LinearLayout wisdomItem;
        public WisdomViewONEHolder(View itemView) {
            super(itemView);
            wisdomTvTitle = itemView.findViewById(R.id.wisdom_tv_title);
            wisdomImg = itemView.findViewById(R.id.wisdom_img);
            wisdomTvTimer = itemView.findViewById(R.id.wisdom_tv_timer);
            wisdomItem=itemView.findViewById(R.id.wisdomItem);
        }
    }

    public class WisdomViewTWOHolder extends RecyclerView.ViewHolder {
        TextView wisdomTvTitle;
        ImageView wisdomOneImg;
        ImageView wisdomTwoImg;
        ImageView wisdomThreeImg;
        TextView wisdomTvTimer;
        LinearLayout wisdomItem;
        public WisdomViewTWOHolder(View itemView) {
            super(itemView);
            wisdomTvTitle = itemView.findViewById(R.id.wisdom_tv_title);
            wisdomTvTimer = itemView.findViewById(R.id.wisdom_tv_timer);
            wisdomOneImg = itemView.findViewById(R.id.wisdom_one_img);
            wisdomTwoImg = itemView.findViewById(R.id.wisdom_two_img);
            wisdomThreeImg = itemView.findViewById(R.id.wisdom_three_img);
            wisdomItem=itemView.findViewById(R.id.wisdom_item);
        }
    }
   public interface OnItemClicker{
        void setOnClicker(int position);
    }
}
