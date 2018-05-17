package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Home.HomeBannerEntity;
import com.smartcityin.waterknow.Entity.Home.HomeMeterStateEntity;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Model.ModelCountry.HomeModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class HomePresenter extends BasePresenter<Commpart.HomeViewInf> implements Commpart.HomePreInf,WaterCallback.HomeCallback{
    HomeModel model=new HomeModel();

    @Override
    public void setHomeBanner(String token) {
        model.getHomeBanner(this,token);
    }

    @Override
    public void setHomeWaterMeterState(String token) {
        model.getHomeWaterMeterState(this,token);
    }

    @Override
    public void setHomeNews(String token) {
        model.getHomeNews(this,token);
    }

    @Override
    public void setHomeSwitchNumber(String token) {
        model.getHomeSwitchNumber(this,token);
    }

    @Override
    public void confirmNumber(String meterID, String token) {
        model.confirmNumber(this,meterID,token);
    }

    @Override
    public void successHomeBanner(HomeBannerEntity homeBanner) {
        if (isViewAttarchView())
            getView().getHomeBannerData(homeBanner);
    }

    @Override
    public void successHomeWaterMeterState(HomeMeterStateEntity homeMeterStateEntity) {
        if (isViewAttarchView())
            getView().getHomeWaterMeterState(homeMeterStateEntity);
    }

    @Override
    public void successHomeNewsEntity(HomeNewsEntity entity) {
        if (isViewAttarchView())
            getView().getHomeNews(entity);
    }

    @Override
    public void getHomeSwitchNumberEntity(SwitchNumberEntity switchNumberEntity) {
        if (isViewAttarchView())
            getView().getSwitchNumber(switchNumberEntity);
    }

    @Override
    public void confirmNumberEntity(HomeSwitchNumberCommitEntity entity) {
        if (isViewAttarchView())
            getView().confirmNumber(entity);

    }

}
