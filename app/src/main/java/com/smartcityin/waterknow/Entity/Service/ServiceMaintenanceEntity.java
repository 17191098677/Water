package com.smartcityin.waterknow.Entity.Service;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/5/3
 * E-mail : wkz123011@gmail.com
 */
public class ServiceMaintenanceEntity {

    /**
     * token : E6ZoeagdqdTZEte498SFqg==
     * data : [{"fail_description":"fail_description","handle":"未解决","time":"2018-05-03 15:16:53"},{"fail_description":"fail_description","handle":"未解决","time":"2018-05-03 15:16:50"},{"fail_description":"测试","handle":"未解决","time":"2018-04-28 15:47:40"},{"fail_description":"测试","handle":"未解决","time":"2018-04-28 15:45:39"}]
     * mark : 765cac56b34209ba6dd0934a1e09dd70
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
         * fail_description : fail_description
         * handle : 未解决
         * time : 2018-05-03 15:16:53
         */

        private String fail_description;
        private String handle;
        private String time;

        public String getFail_description() {
            return fail_description;
        }

        public void setFail_description(String fail_description) {
            this.fail_description = fail_description;
        }

        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
