package com.smartcityin.waterknow.Presenter.PresenterCountry;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Model.ModelCountry.MyUpdateNewPhoneModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyUpdateNewPhonePresenter extends BasePresenter<Commpart.MyUpdateNewPhoneViewInf> implements Commpart.MyUpdateNewPhonePreInf,WaterCallback.MyUpdateNewPhoneCallback{

    @Override
    public void successnewPhone(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity) {
        if (isViewAttarchView())
            getView().getMyUpdateNewPhone(myUpdatePhoneYZMEntity);
    }

    @Override
    public void setMyUpdateNewPhone(String token, String code, String msg_id, String newPhone) {
        MyUpdateNewPhoneModel model=new MyUpdateNewPhoneModel();
        model.getMyUpdateNewPhone(this,token,code,msg_id,newPhone);
    }
}
