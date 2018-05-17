package com.smartcityin.waterknow.Entity.My;

/**
 * Author : Mr.老王
 * Created on 2018/4/26
 * E-mail : wkz123011@gmail.com
 */
public class MyUpdatePhotoEntity {
    /**
     * token : uPMObi90633AjJ/yInyvuQ==
     * data : {"username":"17801094126","head":null,"nickname":"臻"}
     * mark : 9a2003d2ce2fdcda46412c00278c7e9c
     * code : 200
     * value : OK
     */

    private String token;
    private MyUserInformationEntity.DataBean data;
    private String mark;
    private int code;
    private String value;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MyUserInformationEntity.DataBean getData() {
        return data;
    }

    public void setData(MyUserInformationEntity.DataBean data) {
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
        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
