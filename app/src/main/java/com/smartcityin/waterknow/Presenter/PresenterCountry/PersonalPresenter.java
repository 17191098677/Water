package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyPersonalEntity;
import com.smartcityin.waterknow.Entity.My.MyUserInformationEntity;
import com.smartcityin.waterknow.Entity.PersonalFunctionEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyUserInformationModel;
import com.smartcityin.waterknow.Model.ModelCountry.PersonalFunctionModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public class PersonalPresenter extends BasePresenter<Commpart.PersonalViewInf> implements Commpart.PersonalPreInf,WaterCallback.MyFunctionCallback {
    @Override
    public void successMyPersonal(MyPersonalEntity myPersonalEntity) {
        if (isViewAttarchView())
            getView().getMyPersonal(myPersonalEntity);
    }

    @Override
    public void setMyPersonal(String token) {
        PersonalFunctionModel model=new PersonalFunctionModel();
        model.getMyPersonal(this,token);
    }
}
