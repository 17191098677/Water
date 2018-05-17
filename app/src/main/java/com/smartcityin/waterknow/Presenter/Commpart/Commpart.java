package com.smartcityin.waterknow.Presenter.Commpart;

import android.support.annotation.Nullable;

import com.smartcityin.waterknow.Base.BasePresenterInf;
import com.smartcityin.waterknow.Base.BaseViewInf;
import com.smartcityin.waterknow.Entity.BindingWaterMeter.BindingWaterEntity;
import com.smartcityin.waterknow.Entity.Home.HomeBannerEntity;
import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.Entity.Home.HomeMeterStateEntity;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSearchEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Entity.Login.LoginEntity;
import com.smartcityin.waterknow.Entity.My.MyAnnualQueryEntity;
import com.smartcityin.waterknow.Entity.My.MyFeedBackEntity;
import com.smartcityin.waterknow.Entity.My.MyInToolPAymentEntity;
import com.smartcityin.waterknow.Entity.My.MyInvoiceToolEntity;
import com.smartcityin.waterknow.Entity.My.MyOrderEntity;
import com.smartcityin.waterknow.Entity.My.MyPersonalEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhoneYZMEntity;
import com.smartcityin.waterknow.Entity.My.MyUpdatePhotoEntity;
import com.smartcityin.waterknow.Entity.My.MyUserInformationEntity;
import com.smartcityin.waterknow.Entity.Home.RegionEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.ForGetEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterPSWEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.Entity.ServiceGridEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceNetWorkEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceNewsCenterEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Entity.UserWater.UserWaterEntity;
import com.smartcityin.waterknow.Entity.Home.WisdomEntity;

import java.util.ArrayList;
import java.util.Map;

/**
 * Author : Mr.老王
 * Created on 2018/3/21
 * E-mail : wkz123011@gmail.com
 */

public interface Commpart {
    //智慧生活
    interface WisdomLifePreInf extends BasePresenterInf{
        void setWisdomLife(String token, @Nullable int page);
    }
    interface WisdomLifeViewInf extends BaseViewInf{
        void getData(WisdomEntity wisdomEntity);
    }
    //用水知识
    interface WaterKnowLedgePerInf extends BasePresenterInf{
        void setHomeUsetWaterKnow(String token,int page);
    }
    interface WaterKnowLedgeViewInf extends BaseViewInf{
        void getWaterKnowLedgeData(HomeWaterKnowEntity waterKnowEntity);
    }
    //首页搜索地图位置
    interface HomeRegionPreInf extends BasePresenterInf{
        void setRegion(String token);
    }
    interface HomeRegionViewInf extends BaseViewInf{
        void getHomeRegionData(RegionEntity regionEntity);
    }
    //用水界面
    interface UserWaterPreInf extends BasePresenterInf{
        void setUserWater(String token,String water_id);
    }
    interface UserWaterViewInf extends BaseViewInf{
        void getUserWaterData(UserWaterEntity userWaterEntity);
    }
    //服务的Grid列表数据
    interface ServicePreInf extends BasePresenterInf{
        void setServiceData();
    }
    interface ServiceViewInf extends BaseViewInf{
        void getServiceData(ArrayList<ServiceGridEntity> list);
    }
    //服务的服务网点数据
    interface ServiceNetWorkPreInf extends BasePresenterInf{
        void setServiceNetWork(String token);
    }
    interface ServiceNetWorkViewInf extends BaseViewInf{
        void getServiceNetWorkData(ServiceNetWorkEntity entity);
    }
    //服务的新闻中心
    interface ServiceNewsCenterPreInf extends BasePresenterInf{
        void setServiceNewsCenter(String token,int page);
    }
    interface ServiceNewsCenterViewInf extends BaseViewInf{
        void getServiceNewsCenterData(ServiceNewsCenterEntity serviceNewsCenterEntity);
    }
    //服务的用水帮助
    interface ServiceUserHelpPreInf extends BasePresenterInf{
        void setUserHelp(String token,int page);
    }
    interface ServiceUserHelpViewInf extends BaseViewInf{
        void getServiceUserHelp(ServiceUserHelpEntity serviceUserHelpEntity);
    }
    //首页轮播图数据和新闻列表数据
    interface HomePreInf extends BasePresenterInf{
        void setHomeBanner(String token);
        void setHomeWaterMeterState(String token);
        void setHomeNews(String token);
        void setHomeSwitchNumber(String token);
        void confirmNumber(String meterID,String token);
    }
    //首页
    interface HomeViewInf extends BaseViewInf{
        void getHomeBannerData(HomeBannerEntity entity);
        void getHomeWaterMeterState(HomeMeterStateEntity entity);
        void getHomeNews(HomeNewsEntity homeNews);
        void getSwitchNumber(SwitchNumberEntity entity);
        void confirmNumber(HomeSwitchNumberCommitEntity entity);
    }
    //我的页面中中间四个按钮功能
    interface PersonalPreInf extends BasePresenterInf{
        void setMyPersonal(String token);
    }
    interface PersonalViewInf extends BaseViewInf{
        void getMyPersonal(MyPersonalEntity myPersonalEntity);
    }
    //我的页面中我的订单功能
    interface MyOrderPreInf extends BasePresenterInf{
        void setMyOrder(String token,int page);
    }
    interface MyOrderViewInf extends BaseViewInf{
        void getMyOrderData(MyOrderEntity myOrderEntity);
    }
    //我的页面常见问题
    interface MyCommonProblemPreInf extends BasePresenterInf{
        void setCommonProblem(String token);
    }
    interface MyCommonViewInf extends BaseViewInf{
        void getMyCommonData(ServiceUserHelpEntity serviceUserHelpEntity);
    }
    //获取短信验证码ID
    interface RegisterSMSMsgIDPreInf extends BasePresenterInf{
        void setModelPhone(String phone,String titleID);
    }
    interface RegisterSMSMsgIDViewInf extends BaseViewInf{
        void getRegisterMsgIdInf(RegisterEntity entity);
        void getRegistererror(String error);

    }

    //通过短信验证码ID来判断用户输入验证码的状态（忘记密码和注册）
    interface RegisterPreInf extends BasePresenterInf{
        void setRegister(String titleID,String username, String code,String pickname, String pass, String password, String msg_id);
        void setForGet(String titleID,String username, String code,String pass, String password, String msg_id);
        void resetYZM(String edPhone);
        void resetForgetYZM(String edPhone);
        void bindingPhone(Map<String,String> map);
    }
    interface RegisterViewInf extends BaseViewInf{
        void getRegister(RegisterPSWEntity entity);
        void getForget(ForGetEntity entity);
        void getResetYZM(RegisterEntity registerEntity);
        void getForgetYZM(RegisterEntity registerEntity);
        void getBindingPhone(MyInToolPAymentEntity entity);
    }
    //第一次登录获取Token
    interface LoginPreInf extends BasePresenterInf{
        void setLogin(String username,String password);
        void setQQ(String access_token,String openid);
        void QQLogin(String open_id,String nickname,String head);
    }
    interface LoginViewInf extends BasePresenterInf{
        void getLoginData(LoginEntity entity);
        void getQQData(String QQuserData);
        void getQQLogin(HomeSwitchNumberCommitEntity entity);
    }
    //绑定水表
    interface BindingWaterPreInf extends BasePresenterInf{
        void setBindingWater(String meter_id,String token);
    }
    interface BindingWaterViewInf extends BaseViewInf{
        void getBindingWaterData(BindingWaterEntity entity);
    }
    /**
     * 首页公告
     */
    interface HomeInformationPreInf extends BasePresenterInf{
        void setHomeInfmation(String token);
    }
    interface HomeInformationViewInf extends BaseViewInf{
        void getHomeInformation(HomeInformationEntity entity);
    }
    /**
     * 首页充值缴费选择户号
     */
    interface SwitchNumberPreInf extends BasePresenterInf{
        void setSwitchNumber(String token);
        void setRechargement(String meterId,String token);
        void unBindNumber(String token,String meterID);
    }
    interface SwitchNumberViewInf extends BaseViewInf{
        void getHomeSwitchNumber(SwitchNumberEntity switchNumberEntity);
        void getRechargePayment(HomeSwitchNumberCommitEntity entity);
        void unBindNumber(HomeSwitchNumberCommitEntity entity);
    }

    /**
     * 首页搜索
     */
    interface HomeSearchPreInf extends BasePresenterInf{
        void setHomeSearch(String token,String like);
    }
    interface HomeSearchViewInf extends BaseViewInf{
        void getHomeSearch(HomeSearchEntity homeSearchEntity);
    }
    /**
     * 用户信息
     */
    interface MyInformationPreInf extends BasePresenterInf{
        void setMyinformation(String token);
        void updateHeadImage(String token,String head,String foot);
        void setUpdatePhone(String token);
    }
    interface MyInformationViewInf extends BaseViewInf{
        void getMyInformation(MyUserInformationEntity userInformationEntity);
        void getUpdateHeadImage(MyUpdatePhotoEntity myUpdatePhotoEntity);
        void getMyUpdatePhone(MyUpdatePhoneEntity myUpdatePhoneEntity);
    }
    /**
     * 意见反馈
     */
    interface MyFeedBackPreInf extends BasePresenterInf{
        void setFeedBack(String token,String content,@Nullable String Email);
    }
    interface MyFeedBackViewInf extends BaseViewInf{
        void getMyFeedBack(MyFeedBackEntity myFeedBackEntity);
    }
    /**
     * 我的页面中判断验证码是否正确
     */
    interface MyUpdatePhoneYZMPreInf extends BasePresenterInf{
        void setUpdatePhoneYZM(String token,String code,String msg_id);
        void setUpdatePhone(String token);
    }
    interface MyUpdatePhoneYZMViewInf extends BaseViewInf{
        void getMyUpdatePhoneYZM(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity);
        void getMyUpdatePhone(MyUpdatePhoneEntity myUpdatePhoneEntity);
    }
    /**
     * 我的页面中验证新手机号
     */
    interface MyUpdateNewPhonePreInf extends BasePresenterInf{
        void setMyUpdateNewPhone(String token,String code,String msg_id,String newPhone);
    }
    interface MyUpdateNewPhoneViewInf extends BaseViewInf{
        void getMyUpdateNewPhone(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity);
    }

    /**
     * 我的页面中修改密码
     */
    interface MyUpdatePswPreInf extends BasePresenterInf{
        void setMyUpdatePsw(String token,String oldPsw,String newPsw);
    }
    interface MyUpdatePswViewInf extends BaseViewInf{
        void getMyUpdatePsw(MyUpdatePhoneYZMEntity myUpdatePhoneYZMEntity);
    }
    /**
     * 我的页面中我的发票订单
     */
    interface MyInvoiceToolPreInf extends BasePresenterInf{
        void setMyInvoiceTool(String token);
    }
    interface MyInvoiceViewInf extends BaseViewInf{
        void getMyInvoice(MyInvoiceToolEntity myInvoiceToolEntity);
    }
    /**
     * 我的页面中可以开发票
     */
    interface MyToolPaymentPreInf extends BasePresenterInf{
        void setElectronicsToolPayment(Map<String,String> map,String[] orders);
    }
    interface MyToolPaymentViewInf extends BaseViewInf{
        void getMyToolPayment(MyInToolPAymentEntity entity);
    }
    /**
     * 首页中我要报修界面
     */
    interface HomeWaterRepotPreInf extends BasePresenterInf{
        void setHomeWaterRepot(Map<String,String> map,String pics,String foot);
    }
    interface HomeWaterRepotViewInf extends BaseViewInf{
        void getHomeWaterRepot(MyInToolPAymentEntity entity);
    }
    /**
     * 服务页维护统计
     */
    interface ServiceMaintenancePreInf extends BasePresenterInf{
        void setServiceMaintenance(String token);
    }
    interface ServiceMaintenanceViewInf extends BaseViewInf{
        void getServiceMaintenance(ServiceMaintenanceEntity serviceMaintenanceEntity);
    }
    /**
     * 我的页面中年度查询
     */
    interface MyAnnualQueryPreInf extends BasePresenterInf{
        void setMyAnnualQuery(String token,String year,String water_id);
    }
    interface MyAnnualQueryViewInf extends BaseViewInf{
        void getMyAnnualQueryInf(MyAnnualQueryEntity myAnnualQueryEntity);
    }
}
