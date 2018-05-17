package com.smartcityin.waterknow.Presenter.PresenterCountry.Register;

import com.smartcityin.waterknow.Base.BasePresenter;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.ForGetEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterPSWEntity;
import com.smartcityin.waterknow.Model.ModelCountry.BindingPhoneModel;
import com.smartcityin.waterknow.Model.ModelCountry.ForGetModel;
import com.smartcityin.waterknow.Model.ModelCountry.ForgetPswModel;
import com.smartcityin.waterknow.Model.ModelCountry.RegisterModel;
import com.smartcityin.waterknow.Model.ModelListener.WaterCallback;
import com.smartcityin.waterknow.Presenter.Commpart.Commpart;

import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/18
 * E-mail : wkz123011@gmail.com
 */
public class RegisterPresenter extends BasePresenter<Commpart.RegisterViewInf> implements Commpart.RegisterPreInf,WaterCallback.RegisterCallback,WaterCallback.ForgetCallback,WaterCallback.BindingPhoneCallback{

    @Override
    public void successRegisterData(Object registerEntity) {
        if (isViewAttarchView())
            getView().getRegister((RegisterPSWEntity) registerEntity);
    }

    @Override
    public void getYZM(RegisterEntity registertity) {
        if (isViewAttarchView())
            getView().getResetYZM(registertity);
    }

    @Override
    public void getForgetYZM(RegisterEntity registerEntity) {
        if (isViewAttarchView())
            getView().getForgetYZM(registerEntity);

    }

    @Override
    public void setRegister(String titleID,String username, String code,String pickname, String pass, String password, String msg_id) {
        if ("注册".equals(titleID)){
            RegisterModel model=new RegisterModel();
            model.getRegister(this,username,code,pickname,pass,password,msg_id);
        }
    }

    @Override
    public void setForGet(String titleID, String username, String code, String pass, String password, String msg_id) {
            ForGetModel model=new ForGetModel();
            model.getForget(this,username,code,msg_id,pass,password);
    }

    @Override
    public void resetYZM(String edPhone) {
        RegisterModel registerModel=new RegisterModel();
        registerModel.resetGetYZM(this,edPhone);
    }

    @Override
    public void resetForgetYZM(String edPhone) {
        RegisterModel registerModel=new RegisterModel();
        registerModel.resetForgetYZM(this,edPhone);
    }

    @Override
    public void bindingPhone(Map<String, String> map) {
        BindingPhoneModel model=new BindingPhoneModel();
        model.bindingPhone(this,map);
    }

    @Override
    public void successForgetCallback(Object forGetEntity) {
        if (isViewAttarchView())
            getView().getForget((ForGetEntity) forGetEntity);
    }

    @Override
    public void bindingPhone(MyInToolPAymentEntity entity) {
        if (isViewAttarchView())
            getView().getBindingPhone(entity);
    }
}
