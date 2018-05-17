package com.smartcityin.waterknow.Model.ModelListener;

import android.support.annotation.Nullable;

import com.smartcityin.waterknow.Entity.My.MyPersonalEntity;
import com.smartcityin.waterknow.Entity.PersonalFunctionEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/4/10
 * E-mail : wkz123011@gmail.com
 */
public interface WaterKnowModelInf {

    //主页的省市地区
    interface HomeRegionInf {
        void getHomeRegion(WaterCallback.HomeRegionCallback callback,String token);
    }
    //主页的用水知识
    interface HomeWaterKnowLedgeInf {
        void getWaterKnowLedge(WaterCallback.WaterKnowCallback callback,String token,int page);
    }
    //主页的智慧生活
    interface WisdomLifeInf {
        void getWisdomLifeCallback(WaterCallback.WisdomLifeCallback wisdomLifeCallback,String token,@Nullable int page);
    }
    //服务的服务数据
    interface ServiceInf {
        void getServiceData(WaterCallback.ServiceCallback callback);
    }
    //服务的服务网点
    interface ServiceNetWorkInf {
        void getNetWork(WaterCallback.ServiceNetWorkCallback callback,String token);
    }
    //服务的新闻中心
    interface ServiceNewsCenterInf {
        void getServiceNewsCenter(WaterCallback.ServiceNewsCenterCallback callback, String token,int page);
    }
    //服务的用水帮助
    interface ServiceUserHelpInf{
        void getServiceUserHelp(WaterCallback.ServiceUserHelpCallback callback,String token,int page);
    }
    //用水界面
    interface UserWaterInf {
        void getUserWaterData(WaterCallback.UserWaterCallback callback,String token,String water_id);
    }
    //我的页面中四个按钮的功能
    interface MyFunctionInf{
        void getMyPersonal(WaterCallback.MyFunctionCallback callback,String token);
    }
    //我的我的订单列表数据
    interface MyOrderInf{
        void getMyOrderData(WaterCallback.MyOrderCallback callback,String token,int page);
    }
    /**
     * 我的常见问题数据
     */
    interface MyCommonProblemInf{
        void getMyCommonProblemData(WaterCallback.MyCommonProblemCallback callback,String token);
    }
    //注册页面获取验证码ID
    interface RegisterSMSMsgInf{
        void getRegisterMsgID(WaterCallback.RegisterSMSCallback callback,String edPhone);
    }

    /**
     * username 用户名（手机号）
     * code 验证码
     * pass 密码
     * password 重复密码
     * msg_id 验证码id
     */
    interface RegisterInf{
        void getRegister(WaterCallback.RegisterCallback callback,String username,String code,String nickname,String pass,String password,String msg_id);
        void resetGetYZM(WaterCallback.RegisterCallback callback,String edPhone);
        void resetForgetYZM(WaterCallback.RegisterCallback callback,String edPhone);
    }

    /**
     * 登录
     * username用户名
     * password密码
     */
    interface LoginInf{
        void getLogin(WaterCallback.LoginCallback callback,String username,String password);
        void QQLogin(WaterCallback.LoginCallback callback,String openid,String nickname,String head);
    }
    /**
     * 忘记密码
     */
    interface ForGetInf{
        void getForget(WaterCallback.ForgetCallback callback,String username,String code,String msg_id,String pass,String password);
    }
    /**
     * 绑定水表号
     */
    interface BindingWaterInf{
        void getBindingWater(WaterCallback.BindingWaterCallback callback,String meter_id,String token);
    }
    /**
     * 获取QQ的用户信息
     */
    interface QQInf{
        void getQQEntity(WaterCallback.QQCallback callback, String access_token, String openid);
    }
    /**
     * 主页Banner和新闻列表
     */
    interface HomeInf {
        void getHomeWaterMeterState(WaterCallback.HomeCallback callback,String token);
        void getHomeBanner(WaterCallback.HomeCallback callback,String token);
        void getHomeNews(WaterCallback.HomeCallback callback,String token);
        void getHomeSwitchNumber(WaterCallback.HomeCallback callback,String token);
        void confirmNumber(WaterCallback.HomeCallback callback,String meterId, String token);
    }
    /**
     * 主页消息
     */
    interface HomeInformationInf{
        void getHomeInformation(WaterCallback.HomeInformationCallback callback,String token);
    }
    /**
     * 主页充值缴费时切换户号
     */
    interface HomeSwitchNumberInf{
        void getHomeSwitchNumber(WaterCallback.HomeSwitchNumberCallback callback,String token);
        void getHomeRecharge(WaterCallback.HomeSwitchNumberCallback callback,String meterID,String token);
        void deleteSwitchNumber(WaterCallback.HomeSwitchNumberCallback callback,String meterID,String token);
    }
    /**
     * 主页的搜索
     */
    interface HomeSearchInf{
        void getHomeSearch(WaterCallback.HomeSearchCallback callback,String token,String like);
    }
    /**
     * 我的页面中用户信息
     */
    interface MyUserInformationInf {
        void getMyUserInformation(WaterCallback.MyUserInformationCallback callback,String token);
        void getUpdateHeadImage(WaterCallback.MyUserInformationCallback callback,String token,String head,String foot);
    }
    /**
     * 我的页面中意见反馈
     */
    interface MyFeedBackInf{
        void getMyFeedBack(WaterCallback.MyFeedBakcCallback callback,String token,String content,@Nullable String Email);
    }
    /**
     * 我的页面中修改手机号
     */
    interface MyUpdatePhoneInf {
        void getMyupdatePhone(WaterCallback.MyUpdatePhoneCallback callback,String token);
    }
    /**
     * 我的页面中修改手机号验证码是否正确
     */
    interface MyUpdatePhoneYZMInf{
        void getMyUpdatePhoneYZM(WaterCallback.MyUpdatePhoneYZMCallback callback,String token,String code,String msg_id);
        void updatePhone(WaterCallback.MyUpdatePhoneYZMCallback callback,String token);
    }
    /**
     * 我的页面中验证新手机号
     */
    interface MyUpdateNewPhoneInf{
        void getMyUpdateNewPhone(WaterCallback.MyUpdateNewPhoneCallback callback,String token,String code,String msg_id,String newPhone);
    }
    /**
     * 修改我的密码
     */
    interface MyUpdatePswInf{
        void getMyPsw(WaterCallback.MyUpdatePSWCallback callback,String token,String oldPsw,String newPsw);
    }
    /**
     * 我的订单中可以开发票的订单
     */
    interface MyInvoiceToolInf{
        void getMyInvoiceTool(WaterCallback.MyInvoiceToolCallback callback,String token);
    }
    /**
     * 我的订单中可以开发票
     */
    interface MyToolPaymentInf{
        void getMyToolPayment(WaterCallback.MyInToolPaymentCallback callback, Map<String,String> map,String[] orders);
    }
    /**
     * 首页中我要报修
     */
    interface HomeWaterRepotInf {
        void getHomeWaterRepot(WaterCallback.HomeWaterRepotCallback callback,Map<String,String> map,String pics,String foots);
    }
    /**
     * 服务页中维护统计页面
     */
    interface ServiceMaintenanceInf{
        void getServiceMaintenance(WaterCallback.ServiceMaintenceCallback callback,String token);
    }
    /**
     * 我的页面中年度查询
     */
    interface MyAnnualQueryInf{
        void getMyAnnualQuery(WaterCallback.AnnualQueryCallback callback,String token,String year,String water_id);
    }
    /**
     * 绑定手机号
     */
    interface BindingPhoneInf{
        void bindingPhone(WaterCallback.BindingPhoneCallback callback, Map<String,String> map);
    }
}
