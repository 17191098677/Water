package com.smartcityin.waterknow.Global;

import android.support.annotation.Nullable;

import com.smartcityin.waterknow.Entity.BindingWaterMeter.BindingWaterEntity;
import com.smartcityin.waterknow.Entity.Home.HomeBannerEntity;
import com.smartcityin.waterknow.Entity.Home.HomeInformationEntity;
import com.smartcityin.waterknow.Entity.Home.HomeMeterStateEntity;
import com.smartcityin.waterknow.Entity.Home.HomeNewsEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSearchEntity;
import com.smartcityin.waterknow.Entity.Home.HomeSwitchNumberCommitEntity;
import com.smartcityin.waterknow.Entity.Home.HomeWaterKnowEntity;
import com.smartcityin.waterknow.Entity.Home.RegionEntity;
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
import com.smartcityin.waterknow.Entity.RegisterEntity.ForGetEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterEntity;
import com.smartcityin.waterknow.Entity.RegisterEntity.RegisterPSWEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceMaintenanceEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceNetWorkEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceNewsCenterEntity;
import com.smartcityin.waterknow.Entity.Service.ServiceUserHelpEntity;
import com.smartcityin.waterknow.Entity.SwitchNumberEntity;
import com.smartcityin.waterknow.Entity.UserWater.UserWaterEntity;
import com.smartcityin.waterknow.Entity.Home.WisdomEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author : Mr.老王
 * Created on 2018/4/17
 * E-mail : wkz123011@gmail.com
 */
public interface RetrofitService {
    String BASE_URL="http://123.207.166.123/water/index.php/Personal/";
    //注册通过手机号获取验证码ID
    @FormUrlEncoded
    @POST("User/register_SMS")
    Observable<RegisterEntity> getRegisterEntity(@FieldMap Map<String,String> map);
    //通过已得到的验证码ID与后台进行对接
    @FormUrlEncoded
    @POST("User/register")
    Observable<RegisterPSWEntity> getRegister(@FieldMap Map<String,String> map);
    //登录
    @FormUrlEncoded
    @POST("User/login")
    Observable<LoginEntity> getLogin(@FieldMap Map<String,String> map);
    //忘记密码
    @FormUrlEncoded
    @POST("User/SMS")
    Observable<RegisterEntity> getForGetPSWEntity(@FieldMap Map<String,String> map);
    //修改密码
    @FormUrlEncoded
    @POST("User/forgot_password")
    Observable<ForGetEntity> getForGetPSW(@FieldMap Map<String,String> map);
    //绑定水表好
    @FormUrlEncoded
    @POST("User/binding_water")
    Observable<BindingWaterEntity> getBindingWater(@FieldMap Map<String,String> map);
    //获取首页轮播图
    @FormUrlEncoded
    @POST("Index/banner")
    Observable<HomeBannerEntity> getHomeBannerEntity(@FieldMap Map<String,String> map);
    //获取首页水表状态
    @FormUrlEncoded
    @POST("Index/water_data")
    Observable<HomeMeterStateEntity> getHomeWaterMeterState(@FieldMap Map<String,String> map);
    //获取首页新闻
    @FormUrlEncoded
    @POST("Index/index_news")
    Observable<HomeNewsEntity> getHomeNews(@FieldMap Map<String,String> map);
    //获取首页公告
    @FormUrlEncoded
    @POST("Index/notice")
    Observable<HomeInformationEntity> getHomeInformation(@FieldMap Map<String,String> map);
    //获取首页充值缴费时的户号列表
    @FormUrlEncoded
    @POST("Index/water_list")
    Observable<SwitchNumberEntity> getSwitchNumber(@FieldMap Map<String,String> map);
    //切换户号时返回状态
    @FormUrlEncoded
    @POST("Index/transposing_water")
    Observable<HomeSwitchNumberCommitEntity> getHomeRechargePayment(@Field("meter_id")String meter_id, @Field("token")String token);
    //首页省市区三级联动
    @FormUrlEncoded
    @POST("Index/city_three_linkage")
    Observable<RegionEntity> getHomeRegion(@Field("token") String token);
    //首页用水知识
    @FormUrlEncoded
    @POST("Index/water_knowledge")
    Observable<HomeWaterKnowEntity> getHomeUserWater(@Field("token")String token, @Nullable @Field("page") int page);
    //获取智慧生活
    @FormUrlEncoded
    @POST("Index/wisdom_life")
    Observable<WisdomEntity> getHomeWisdomLife(@Field("token")String token, @Nullable @Field("page")int page);
    //用水详情页
    @FormUrlEncoded
    @POST("Water/water_date")
    Observable<UserWaterEntity> getUserWater(@Field("token")String token, @Field("water_id")String water_id);
    //服务网点
    @FormUrlEncoded
    @POST("Service/service_bases")
    Observable<ServiceNetWorkEntity> getServiceNetWork(@Field("token")String token);
    //新闻中心
    @FormUrlEncoded
    @POST("Service/news")
    Observable<ServiceNewsCenterEntity> getServiceNews(@Field("token")String token,@Nullable @Field("page") int page);
    //用水帮助
    @FormUrlEncoded
    @POST("Service/help")
    Observable<ServiceUserHelpEntity> getServiceUserHelp(@Field("token")String token,@Field("page")int page);
    //首页综合搜索
    @FormUrlEncoded
    @POST("Index/search")
    Observable<HomeSearchEntity> getHomeSearchEntity(@Field("token")String token,@Field("like") String like);
    //我的页面中用户信息
    @FormUrlEncoded
    @POST("My/personal")
    Observable<MyUserInformationEntity> getUserInformationEntity(@Field("token")String token);
    //个人信息页面上传头像
    @FormUrlEncoded
    @POST("My/upload")
    Observable<MyUpdatePhotoEntity> getUpdateImage(@Field("token")String token,@Field("head")String head,@Field("foot")String foot);
    //我的页面中意见反馈
    @FormUrlEncoded
    @POST("My/feedback")
    Observable<MyFeedBackEntity> getMyFeedBackEntity(@Field("token")String token,@Field("content")String content,@Nullable @Field("email")String Email);
    //我的页面中修改手机号界面
    @FormUrlEncoded
    @POST("My/original_SMS")
    Observable<MyUpdatePhoneEntity>  getMyUpdatePhoneEntity(@Field("token")String token);
    //我的页面中修改手机号判断验证码是否正确
    @FormUrlEncoded
    @POST("My/verify_code")
    Observable<MyUpdatePhoneYZMEntity> getMyUpdatePhoneYZMEntity(@Field("token")String token,@Field("code")String code,@Field("msg_id")String msg_id);
    //我的页面中更换手机号验证新手机号
    @FormUrlEncoded
    @POST("My/change_tel")
    Observable<MyUpdatePhoneYZMEntity> getMyUpdateNewPhoneEntity(@Field("token")String token,@Field("code")String code,@Field("msg_id")String msg_id,@Field("new_phone")String phone);
    //我的页面中我的订单
    @FormUrlEncoded
    @POST("My/my_order")
    Observable<MyOrderEntity> getMyOrderEntity(@Field("token")String token,@Field("page")String page);
    //我的页面中密码管理
    @FormUrlEncoded
    @POST("My/alter_password")
    Observable<MyUpdatePhoneYZMEntity> getMyUpdatePsw(@Field("token")String token,@Field("old_password")String oldPsw,@Field("new_password")String newPSW);
    //我的订单中可以开发票的订单
    @FormUrlEncoded
    @POST("My/invoice_order")
    Observable<MyInvoiceToolEntity> getMyInvoiceTool(@Field("token")String token);
    //申请开电子发票
    @FormUrlEncoded
    @POST("My/draw_a_bill")
    Observable<MyInToolPAymentEntity> getElectronicsToolPayment(@FieldMap Map<String,String> map,@Field("orders") String[] orders);
    //我要报修
    @FormUrlEncoded
    @POST("Index/repair")
    Observable<MyInToolPAymentEntity> getWaterRepot(@FieldMap Map<String,String> map,@Field("pic")String pics,@Field("foot")String foots);
    //服务页面的维护统计
    @FormUrlEncoded
    @POST("Service/maintenance_statistics")
    Observable<ServiceMaintenanceEntity> getServiceMaintenance(@Field("token")String token);
    //我的页面中的个人信息
    @FormUrlEncoded
    @POST("My/my_data")
    Observable<MyPersonalEntity> getMyPersonalEntity(@Field("token")String token);
    /**
     * 我的页面中常见问题
     */
    @FormUrlEncoded
    @POST("My/FAQ")
    Observable<ServiceUserHelpEntity> getMyCommonProblem(@Field("token")String token);
    /**
     * 年度查询
     */
    @FormUrlEncoded
    @POST("My/annual_query")
    Observable<MyAnnualQueryEntity> getMyAnnualEntity(@Field("token")String token,@Field("year")String year,@Field("water_id")String water_id);
    /**
     * 解绑户号
     */
    @FormUrlEncoded
    @POST("My/untie_water")
    Observable<HomeSwitchNumberCommitEntity>  unBindingWaterID(@Field("token")String token,@Field("meter_id") String meterID);
    /**
     * 第三方登录
     */
    @FormUrlEncoded
    @POST("User/third_party_logins")
    Observable<HomeSwitchNumberCommitEntity> QQLogin(@Field("QQopenid")String openid,@Field("nickname")String nickname,@Field("head")String head);
    /**
     * 第三方登录绑定手机号
     */
    @FormUrlEncoded
    @POST("My/add_information")
    Observable<MyInToolPAymentEntity> bindingPhone(@FieldMap Map<String,String> map);
}
