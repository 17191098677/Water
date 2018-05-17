package com.smartcityin.waterknow.Presenter.PresenterCountry;

import android.util.Log;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Model.ModelCountry.HomeWaterRepotModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/5/3
 * E-mail : wkz123011@gmail.com
 */
public class HomeWaterRepotPresenter extends BasePresenter<Commpart.HomeWaterRepotViewInf> implements Commpart.HomeWaterRepotPreInf,WaterCallback.HomeWaterRepotCallback{

    @Override
    public void successWaterRepot(MyInToolPAymentEntity myInToolPAymentEntity) {
        if (isViewAttarchView())
            getView().getHomeWaterRepot(myInToolPAymentEntity);
    }

    @Override
    public void setHomeWaterRepot(Map<String, String> map, String pics, String foot) {
        HomeWaterRepotModel model=new HomeWaterRepotModel();
        model.getHomeWaterRepot(this,map,pics,foot);
    }
}
