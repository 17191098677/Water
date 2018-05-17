package com.smartcityin.waterknow.Adapert;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smartcityin.waterknow.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeXBannerAdapert implements XBanner.XBannerAdapter {
    private ArrayList<String>  bannerList;
    private Activity activity;

    public HomeXBannerAdapert(ArrayList<String> bannerList, Activity activity) {
        this.bannerList = bannerList;
        this.activity = activity;
    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        ImageView img=((ImageView)view);
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);//设置缓存
       /* if (position>0){
            Glide.with(activity)
                    .load(bannerList.get(position-1))
                    .apply(options)
                    .into(img);
        }else{*/
            Glide.with(activity)
                    .load(bannerList.get(position))
                    .apply(options)
                    .into(img);
        //}

    }
}
