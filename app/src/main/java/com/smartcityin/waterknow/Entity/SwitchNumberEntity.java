package com.smartcityin.waterknow.Entity;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/20
 * E-mail : wkz123011@gmail.com
 */
public class SwitchNumberEntity {

    /**
     * token : Eep8tLTGzBs3oNbm1SVU/w==
     * data : [{"meter_id":"16","water_value":"0.00"},{"meter_id":"1","water_value":"0.00"}]
     * mark : b738644f7eeed8215dbad06ed1179d11
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
         * meter_id : 16
         * water_value : 0.00
         */

        private String meter_id;
        private String water_value;
        private boolean isFlag;

        public boolean isFlag() {
            return isFlag;
        }

        public void setFlag(boolean flag) {
            isFlag = flag;
        }

        public String getMeter_id() {
            return meter_id;
        }

        public void setMeter_id(String meter_id) {
            this.meter_id = meter_id;
        }

        public String getWater_value() {
            return water_value;
        }

        public void setWater_value(String water_value) {
            this.water_value = water_value;
        }
    }
}
