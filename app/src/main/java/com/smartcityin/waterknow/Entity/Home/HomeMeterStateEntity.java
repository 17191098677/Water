package com.smartcityin.waterknow.Entity.Home;

/**
 * Author : Mr.老王
 * Created on 2018/4/23
 * E-mail : wkz123011@gmail.com
 */

/**
 * 水表状态
 */
public class HomeMeterStateEntity {

    /**
     * token : AlXhnynLxygG8S7uQ02BkQ==
     * data : {"meter_id":"16","status":"开启","water_remain":259,"standard":"1","price":"0.00"}
     * mark : fe0dc0bba57b42f209bbd26e975d6e36
     * code : 200
     * value : OK
     */

    private String token;
    private DataBean data;
    private String mark;
    private int code;
    private String value;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * meter_id : 16
         * status : 开启
         * water_remain : 259
         * standard : 1
         * price : 0.00
         */

        private String meter_id;
        private String status;
        private int water_remain;
        private String standard;
        private String price;

        public String getMeter_id() {
            return meter_id;
        }

        public void setMeter_id(String meter_id) {
            this.meter_id = meter_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getWater_remain() {
            return water_remain;
        }

        public void setWater_remain(int water_remain) {
            this.water_remain = water_remain;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
