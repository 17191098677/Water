package com.smartcityin.waterknow.Presenter.PresenterCountry.Register;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Model.ModelCountry.ForgetPswModel;
import com.smartcityin.waterknow.Model.ModelCountry.RegisterSMSMsgIDModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

/**
 * Author : Mr.老王
 * Created on 2018/4/17
 * E-mail : wkz123011@gmail.com
 */
public class RegisterSMSMsgPresenter extends BasePresenter<Commpart.RegisterSMSMsgIDViewInf> implements WaterCallback.RegisterSMSCallback<RegisterEntity> ,Commpart.RegisterSMSMsgIDPreInf{

    @Override
    public void successRegisterSMSData(RegisterEntity registerEntity) {
        if (isViewAttarchView())
            getView().getRegisterMsgIdInf(registerEntity);
    }

    @Override
    public void errorRegisterSMS(String error) {
        if (isViewAttarchView())
            getView().getRegistererror(error);
    }

    @Override
    public void setModelPhone(String phone,String titleID) {
        if (titleID.equals("注册")){
            RegisterSMSMsgIDModel model=new RegisterSMSMsgIDModel();
            model.getRegisterMsgID(this,phone);
        }else if(titleID.equals("忘记密码")){
            ForgetPswModel model=new ForgetPswModel();
            model.getRegisterMsgID(this,phone);
        }
    }

}
