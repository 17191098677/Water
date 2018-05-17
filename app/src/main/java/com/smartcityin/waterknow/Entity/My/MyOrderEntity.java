package com.smartcityin.waterknow.Entity.My;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class MyOrderEntity {

    /**
     * token : BSyr8W0XfwNLfwsyTVQURQ==
     * data : [{"id":"1","order_on":"2330201804270233","money":"5.00","water_id":"16","uid":"20","time":"2018-04-27 13:15:53","trade_state":"1","invoice":"0"}]
     * mark : 4456496853718fb4f8f34a80e6897b58
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
         * order_on : 2330201804270233
         * money : 5.00
         * water_id : 16
         * uid : 20
         * time : 2018-04-27 13:15:53
         * trade_state : 1
         * invoice : 0
         */

        private String id;
        private String order_on;
        private String money;
        private String water_id;
        private String uid;
        private String time;
        private String trade_state;
        private String invoice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_on() {
            return order_on;
        }

        public void setOrder_on(String order_on) {
            this.order_on = order_on;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getWater_id() {
            return water_id;
        }

        public void setWater_id(String water_id) {
            this.water_id = water_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTrade_state() {
            return trade_state;
        }

        public void setTrade_state(String trade_state) {
            this.trade_state = trade_state;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }
    }
}
