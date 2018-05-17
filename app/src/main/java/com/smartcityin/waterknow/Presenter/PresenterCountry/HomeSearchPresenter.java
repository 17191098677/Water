package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Base.BasePresenterInf;
import com.smartcityin.waterknow.Entity.Home.HomeSearchEntity;
import com.smartcityin.waterknow.Model.ModelCountry.HomeSearchModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/26
 * E-mail : wkz123011@gmail.com
 */
public class HomeSearchPresenter extends BasePresenter<Commpart.HomeSearchViewInf> implements Commpart.HomeSearchPreInf,WaterCallback.HomeSearchCallback{

    @Override
    public void successHomeSearch(HomeSearchEntity homeSearchEntity) {
        if (isViewAttarchView())
            getView().getHomeSearch(homeSearchEntity);
    }

    @Override
    public void setHomeSearch(String token, String like) {
        HomeSearchModel model=new HomeSearchModel();
        model.getHomeSearch(this,token,like);
    }
}
