package com.smartcityin.waterknow.Entity.Service;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class ServiceNetWorkEntity {

    /**
     * token : t+vUzLmzXriu67HQLqY4Cg==
     * data : [{"id":"1","station_id":null,"name":"测试公共服务网点","address":"测试地址","tel":"40000000000","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524646568082&di=d69d3345cfc7fbb8f753efa95d3b2291&imgtype=0&src=http%3A%2F%2Fwww.cjr.org.cn%2Fupload%2Fimages%2F2013%2F12%2F3011344937.jpg"},{"id":"2","station_id":"1","name":"北京测试网点","address":"测试地址","tel":"4001000000","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525242581&di=b8caca6008ba247e3b200a062ef4525c&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.ccerdao.gov.cn%2Fweb%2FUploadFile%2F2016-11%2F20161101065756361.jpg"}]
     * mark : b7ba19e36e53f62f791597e67bcc658e
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
         * station_id : null
         * name : 测试公共服务网点
         * address : 测试地址
         * tel : 40000000000
         * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524646568082&di=d69d3345cfc7fbb8f753efa95d3b2291&imgtype=0&src=http%3A%2F%2Fwww.cjr.org.cn%2Fupload%2Fimages%2F2013%2F12%2F3011344937.jpg
         */

        private String id;
        private Object station_id;
        private String name;
        private String address;
        private String tel;
        private String pic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getStation_id() {
            return station_id;
        }

        public void setStation_id(Object station_id) {
            this.station_id = station_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
