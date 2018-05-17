package com.smartcityin.waterknow.Model.ModelListener;

import com.smartcityin.waterknow.Entity.Home.HomeBannerEntity;
import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.Entity.Home.HomeMeterStateEntity;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSearchEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Entity.My.MyAnnualQueryEntity;
import com.smartcityin.waterknow.Entity.My.MyFeedBackEntity;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Entity.My.MyInvoiceToolEntity;
import com.smartcityin.waterknow.Entity.My.MyPersonalEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhotoEntity;
import com.smartcityin.waterknow.Entity.My.MyUserInformationEntity;
import com.smartcityin.waterknow.Entity.PersonalFunctionEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;

import java.util.ArrayList;

/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */

public interface WaterCallback {
    //智慧生活
    interface WisdomLifeCallback<T> extends WaterCallback{
        void successWisdomLife(T wisdomEntity);
        void errorWisdomLife(String error);
    }
    //首页用水知识
    interface WaterKnowCallback<T> extends WaterCallback{
        void successWaterKnow(T waterKnow);
        void errorWaterKnow(String error);
    }
    //省市选择
    interface HomeRegionCallback<T> extends WaterCallback{
        void successHomeRegion(T region);
        void errorRegion(String error);
    }
    //用水
    interface UserWaterCallback<T> extends WaterCallback{
        void successUserWater(T userWater);
        void errorUserWater(String error);
    }
    //服务
    interface ServiceCallback<T> extends WaterCallback{
        void successService(T service);
        void errorService(String error);
    }
    //服务服务网点
    interface ServiceNetWorkCallback<T> extends WaterCallback{
        void successServiceNetWork(T service);
        void errorServiceNetWork(String error);
    }
    //服务  新闻中心
    interface ServiceNewsCenterCallback<T> extends WaterCallback{
        void successNewsCenter(T serviceNewsCenter);
        void errorNewsCenter(String error);
    }
    //服务  用水帮助
    interface ServiceUserHelpCallback<T> extends WaterCallback{
        void successUserHelp(T serviceUserHelp);
        void errorUserHelp(String error);
    }

    /**
     * 主页中我的页面返回
     */
    interface MyFunctionCallback extends WaterCallback{
        void successMyPersonal(MyPersonalEntity myPersonalEntity);
    }


    //我的页面中我的订单
    interface MyOrderCallback<T> extends WaterCallback{
        void successMyOrder(T myOrder);
        void errorMyOrder(String error);
    }
    /**
     * 我的页面中的常见问题
     */
    interface MyCommonProblemCallback<T> extends WaterCallback{
        void successMyCommonData(T myCommonProblem);
        void errorMyCommon(String error);
    }
    //获取验证码ID
    interface RegisterSMSCallback<T> extends WaterCallback{
        void successRegisterSMSData(T registerEntity);
        void errorRegisterSMS(String error);
    }
    //返回的是用户所输入验证码的状态
    interface RegisterCallback<T> extends WaterCallback{
        void successRegisterData(T registerEntity);
        void getYZM(RegisterEntity registertity);
        void getForgetYZM(RegisterEntity registerEntity);
    }
    //登录状态
    interface LoginCallback<T> extends WaterCallback{
        void successLoginData(T loginEntity);
        void getQQEntity(HomeSwitchNumberCommitEntity homeSwitchNumberCommitEntity);
    }
    /**
     *忘记密码状态
     */
    interface ForgetCallback<T> extends WaterCallback{
        void successForgetCallback(T forGetEntity);
    }
    /**
     * 注册页面绑定水表号
     */
    interface BindingWaterCallback<T> extends WaterCallback{
        void successBindingWater(T BindingWaterEntity);
    }
    /**
     * 获取QQ的用户信息
     */
    interface QQCallback<T> extends WaterCallback{
        void getQQEntity(T qqEntity);
    }
    /**
     * 获取首页banner、新闻、水表状态
     */
    interface HomeCallback extends WaterCallback{
        void successHomeBanner(HomeBannerEntity homeBanner);
        void successHomeWaterMeterState(HomeMeterStateEntity homeMeterStateEntity);
        void successHomeNewsEntity(HomeNewsEntity entity);
        void getHomeSwitchNumberEntity(SwitchNumberEntity switchNumberEntity);
        void confirmNumberEntity(HomeSwitchNumberCommitEntity entity);
    }
    /**
     * 首页公告
     */
    interface HomeInformationCallback extends WaterCallback{
        void successHomeInformation(HomeInformationEntity homeInformationEntity);
    }
    /**
     * 首页充值缴费中切换户号
     */
    interface HomeSwitchNumberCallback extends WaterCallback{
        void successHomeSwitchNumber(SwitchNumberEntity switchNumberEntity);
        void successHomeRechargePayment(HomeSwitchNumberCommitEntity homeRechargePaymentEntity);
        void deleteSwitchNumber(HomeSwitchNumberCommitEntity homeSwitchNumberCommitEntity);
    }
    /**
     * 首页搜索
     */
    interface HomeSearchCallback extends WaterCallback{
        void successHomeSearch(HomeSearchEntity homeSearchEntity);
    }
    /**
     * 我的页面中用户信息
     */
    interface MyUserInformationCallback extends WaterCallback{
        void successMyUserInformation(MyUserInformationEntity myUserInformationEntity);
        void updateImageHead(MyUpdatePhotoEntity myUpdatePhotoEntity);
    }
    /**
     * 我的页面意见反馈
     */
    interface MyFeedBakcCallback extends WaterCallback{
        void successMyFeedBack(MyFeedBackEntity myFeedBackEntity);
    }

    /**
     * 我的页面中修改手机号
     */
    interface MyUpdatePhoneCallback extends WaterCallback{
        void successUpdatePhone(MyUpdatePhoneEntity myUpdatePhoneEntity);
    }

    /**
     * 我的页面中修改手机号判断验证码是否正确
     */
    interface MyUpdatePhoneYZMCallback extends WaterCallback{
        void successPhoneYZM(MyUpdatePhoneYZMEntity myUpdatePhoneYZM);
        void updatePhone(MyUpdatePhoneEntity entity);
    }

    /**
     * 我的页面中修改手机号验证新手机号
     */
    interface MyUpdateNewPhoneCallback extends WaterCallback{
        void successnewPhone(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity);
    }

    /**
     * 我的页面中修改密码
     */
    interface MyUpdatePSWCallback extends WaterCallback{
        void successUpdatePsw(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity);
    }

    /**
     * 我的订单中可以开发票的订单
     */
    interface MyInvoiceToolCallback extends WaterCallback{
        void successMyInvoiceTool(MyInvoiceToolEntity myInvoiceToolEntity);
    }

    /**
     * 申请开发票
     */
    interface MyInToolPaymentCallback extends WaterCallback{
        void successElectronicsToolPayment(MyInToolPAymentEntity my);
    }

    /**
     * 首页我要报修
     */
    interface HomeWaterRepotCallback extends WaterCallback{
        void successWaterRepot(MyInToolPAymentEntity myInToolPAymentEntity);
    }

    /**
     * 服务中的维护统计页面
     */
    interface ServiceMaintenceCallback extends WaterCallback{
        void serviceMaintence(ServiceMaintenanceEntity serviceMaintenanceEntity);
    }
    /**
     * 年度查询
     */
    interface AnnualQueryCallback extends WaterCallback{
        void getAnnualEntity(MyAnnualQueryEntity myAnnualQueryEntity);
    }
    /**
     * 绑定手机号
     */
    interface BindingPhoneCallback extends WaterCallback{
        void bindingPhone(MyInToolPAymentEntity entity);
    }
}
