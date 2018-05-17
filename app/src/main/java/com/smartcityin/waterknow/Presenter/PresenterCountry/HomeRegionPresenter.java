package com.smartcityin.waterknow.Presenter.PresenterCountry;


import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.Home.RegionEntity;
import com.smartcityin.waterknow.Model.ModelCountry.HomeRegionModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;


/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class HomeRegionPresenter extends BasePresenter<Commpart.HomeRegionViewInf> implements WaterCallback.HomeRegionCallback<RegionEntity>,Commpart.HomeRegionPreInf{


    @Override
    public void successHomeRegion(RegionEntity region) {
        if (isViewAttarchView())
            getView().getHomeRegionData(region);
    }

    @Override
    public void errorRegion(String error) {

    }

    @Override
    public void setRegion(String token) {
        HomeRegionModel model=new HomeRegionModel();
        model.getHomeRegion(this,token);
    }
}
