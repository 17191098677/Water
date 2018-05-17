package com.smartcityin.waterknow.Entity.Home;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/8
 * E-mail : wkz123011@gmail.com
 */
public class RegionEntity {

    /**
     * token : Eep8tLTGzBs3oNbm1SVU/w==
     * data : [{"id":"1","name":"北京","firstletter":"B","wordfirstletter":"BJS","fullletter":"BeiJingShi"},{"id":"2","name":"天津","firstletter":"T","wordfirstletter":"TJS","fullletter":"TianJinShi"},{"id":"3","name":"河北省","firstletter":"H","wordfirstletter":"HBS","fullletter":"HeBeiSheng"},{"id":"4","name":"山西省","firstletter":"S","wordfirstletter":"SXS","fullletter":"ShanXiSheng"},{"id":"5","name":"内蒙古自治区","firstletter":"N","wordfirstletter":"NMGZZQ","fullletter":"NeiMengGuZiZhiQu"},{"id":"6","name":"辽宁省","firstletter":"L","wordfirstletter":"LNS","fullletter":"LiaoNingSheng"},{"id":"7","name":"吉林省","firstletter":"J","wordfirstletter":"JLS","fullletter":"JiLinSheng"},{"id":"8","name":"黑龙江省","firstletter":"H","wordfirstletter":"HLJS","fullletter":"HeiLongJiangSheng"},{"id":"9","name":"上海","firstletter":"S","wordfirstletter":"SHS","fullletter":"ShangHaiShi"},{"id":"10","name":"江苏省","firstletter":"J","wordfirstletter":"JSS","fullletter":"JiangSuSheng"},{"id":"11","name":"浙江省","firstletter":"Z","wordfirstletter":"ZJS","fullletter":"ZheJiangSheng"},{"id":"12","name":"安徽省","firstletter":"A","wordfirstletter":"AHS","fullletter":"AnHuiSheng"},{"id":"13","name":"福建省","firstletter":"F","wordfirstletter":"FJS","fullletter":"FuJianSheng"},{"id":"14","name":"江西省","firstletter":"J","wordfirstletter":"JXS","fullletter":"JiangXiSheng"},{"id":"15","name":"山东省","firstletter":"S","wordfirstletter":"SDS","fullletter":"ShanDongSheng"},{"id":"16","name":"河南省","firstletter":"H","wordfirstletter":"HNS","fullletter":"HeNanSheng"},{"id":"17","name":"湖北省","firstletter":"H","wordfirstletter":"HBS","fullletter":"HuBeiSheng"},{"id":"18","name":"湖南省","firstletter":"H","wordfirstletter":"HNS","fullletter":"HuNanSheng"},{"id":"19","name":"广东省","firstletter":"G","wordfirstletter":"GDS","fullletter":"GuangDongSheng"},{"id":"20","name":"广西壮族自治区","firstletter":"G","wordfirstletter":"GXZZZZQ","fullletter":"GuangXiZhuangZuZiZhiQu"},{"id":"21","name":"海南省","firstletter":"H","wordfirstletter":"HNS","fullletter":"HaiNanSheng"},{"id":"22","name":"重庆","firstletter":"C","wordfirstletter":"CQS","fullletter":"ZhongQingShi"},{"id":"23","name":"四川省","firstletter":"S","wordfirstletter":"SCS","fullletter":"SiChuanSheng"},{"id":"24","name":"贵州省","firstletter":"G","wordfirstletter":"GZS","fullletter":"GuiZhouSheng"},{"id":"25","name":"云南省","firstletter":"Y","wordfirstletter":"YNS","fullletter":"YunNanSheng"},{"id":"26","name":"西藏自治区","firstletter":"X","wordfirstletter":"XCZZQ","fullletter":"XiCangZiZhiQu"},{"id":"27","name":"陕西省","firstletter":"S","wordfirstletter":"SXS","fullletter":"ShanXiSheng"},{"id":"28","name":"甘肃省","firstletter":"G","wordfirstletter":"GSS","fullletter":"GanSuSheng"},{"id":"29","name":"青海省","firstletter":"Q","wordfirstletter":"QHS","fullletter":"QingHaiSheng"},{"id":"30","name":"宁夏回族自治区","firstletter":"N","wordfirstletter":"NXHZZZQ","fullletter":"NingXiaHuiZuZiZhiQu"},{"id":"31","name":"新疆维吾尔自治区","firstletter":"X","wordfirstletter":"XJWWEZZQ","fullletter":"XinJiangWeiWuErZiZhiQu"},{"id":"32","name":"台湾省","firstletter":"T","wordfirstletter":"TWS","fullletter":"TaiWanSheng"},{"id":"33","name":"香港特别行政区","firstletter":"X","wordfirstletter":"XGTBXZQ","fullletter":"XiangGangTeBieXingZhengQu"},{"id":"34","name":"澳门特别行政区","firstletter":"A","wordfirstletter":"AMTBXZQ","fullletter":"AoMenTeBieXingZhengQu"},{"id":"3552","name":"11","firstletter":"f","wordfirstletter":"ff","fullletter":"dfsd"},{"id":"3553","name":"11","firstletter":"f","wordfirstletter":"ff","fullletter":"dfsd"}]
     * mark : 67519f562e5b77acd704f460fbed76cf
     * code : 200
     * value : OK
     */

    private String token;
    private String mark;
    private int code;
    private String value;
    private List<DataBean> data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 北京
         * firstletter : B
         * wordfirstletter : BJS
         * fullletter : BeiJingShi
         */

        private String id;
        private String name;
        private String firstletter;
        private String wordfirstletter;
        private String fullletter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirstletter() {
            return firstletter;
        }

        public void setFirstletter(String firstletter) {
            this.firstletter = firstletter;
        }

        public String getWordfirstletter() {
            return wordfirstletter;
        }

        public void setWordfirstletter(String wordfirstletter) {
            this.wordfirstletter = wordfirstletter;
        }

        public String getFullletter() {
            return fullletter;
        }

        public void setFullletter(String fullletter) {
            this.fullletter = fullletter;
        }
    }
}
