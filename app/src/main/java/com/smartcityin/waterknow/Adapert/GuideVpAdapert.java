package com.smartcityin.waterknow.Adapert;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/3/20
 * E-mail : wkz123011@gmail.com
 */

public class GuideVpAdapert extends PagerAdapter {
    private ArrayList<String> imt_url;
    private Context context;
    private ArrayList<ImageView> imgs;

    public GuideVpAdapert(Context context, ArrayList<String> img_url, ArrayList<ImageView> imgs) {
        this.imt_url = img_url;
        this.context=context;
        this.imgs=imgs;
    }
    @Override
    public int getCount() {
        return imt_url.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RequestOptions options=new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imt_url.get(position)).apply(options).into(imgs.get(position));
        container.addView(imgs.get(position));
        return imgs.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgs.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
