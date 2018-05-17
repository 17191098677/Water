package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyUpdatePswModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyUpdatePswPresenter extends BasePresenter<Commpart.MyUpdatePswViewInf> implements Commpart.MyUpdatePswPreInf,WaterCallback.MyUpdatePSWCallback{

    @Override
    public void successUpdatePsw(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity) {
        if (isViewAttarchView())
            getView().getMyUpdatePsw(myUpdatePhoneYZMEntity);
    }

    @Override
    public void setMyUpdatePsw(String token, String oldPsw, String newPsw) {
        MyUpdatePswModel model=new MyUpdatePswModel();
        model.getMyPsw(this,token,oldPsw,newPsw);
    }
}
