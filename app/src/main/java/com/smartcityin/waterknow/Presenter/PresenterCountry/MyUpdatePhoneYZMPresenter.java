package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Base.BasePresenterInf;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyUpdatePhoneYZMModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyUpdatePhoneYZMPresenter extends BasePresenter<Commpart.MyUpdatePhoneYZMViewInf> implements Commpart.MyUpdatePhoneYZMPreInf,WaterCallback.MyUpdatePhoneYZMCallback {

    @Override
    public void successPhoneYZM(MyUpdatePhoneYZMEntity myUpdatePhoneYZM) {
        if (isViewAttarchView())
            getView().getMyUpdatePhoneYZM(myUpdatePhoneYZM);
    }

    @Override
    public void updatePhone(MyUpdatePhoneEntity entity) {
        if (isViewAttarchView())
            getView().getMyUpdatePhone(entity);
    }

    @Override
    public void setUpdatePhoneYZM(String token, String code, String msg_id) {
        MyUpdatePhoneYZMModel model=new MyUpdatePhoneYZMModel();
        model.getMyUpdatePhoneYZM(this,token,code,msg_id);
    }

    @Override
    public void setUpdatePhone(String token) {
        MyUpdatePhoneYZMModel model=new MyUpdatePhoneYZMModel();
        model.updatePhone(this,token);
    }
}
