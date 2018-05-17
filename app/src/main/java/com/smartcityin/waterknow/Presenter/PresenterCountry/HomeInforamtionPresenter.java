package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Base.BasePresenterInf;
import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.Model.ModelCountry.HomeInformationModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/23
 * E-mail : wkz123011@gmail.com
 */
public class HomeInforamtionPresenter extends BasePresenter<Commpart.HomeInformationViewInf> implements WaterCallback.HomeInformationCallback,Commpart.HomeInformationPreInf{

    @Override
    public void successHomeInformation(HomeInformationEntity homeInformationEntity) {
        if (isViewAttarchView())
            getView().getHomeInformation(homeInformationEntity);
    }
    @Override
    public void setHomeInfmation(String token) {
        HomeInformationModel model=new HomeInformationModel();
        model.getHomeInformation(this,token);
    }
}
