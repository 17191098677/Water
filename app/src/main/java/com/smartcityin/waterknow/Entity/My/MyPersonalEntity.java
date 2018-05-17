package com.smartcityin.waterknow.Entity.My;

/**
 * Author : Mr.老王
 * Created on 2018/5/4
 * E-mail : wkz123011@gmail.com
 */
public class MyPersonalEntity {

    /**
     * token : E6ZoeagdqdTZEte498SFqg==
     * data : {"nickname":"臻","head":"http://123.207.166.123/water/Public/Uploads/2018-05-03/201525332079.jpg","meter_id":"1","address":"未获取到当前地址","water_remain":"0.00","price":0}
     * mark : cf336c773b83f6a700dd9f5a0978cd9c
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
         * nickname : 臻
         * head : http://123.207.166.123/water/Public/Uploads/2018-05-03/201525332079.jpg
         * meter_id : 1
         * address : 未获取到当前地址
         * water_remain : 0.00
         * price : 0
         */

        private String nickname;
        private String head;
        private String meter_id;
        private String address;
        private String water_remain;
        private int price;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getMeter_id() {
            return meter_id;
        }

        public void setMeter_id(String meter_id) {
            this.meter_id = meter_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getWater_remain() {
            return water_remain;
        }

        public void setWater_remain(String water_remain) {
            this.water_remain = water_remain;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
