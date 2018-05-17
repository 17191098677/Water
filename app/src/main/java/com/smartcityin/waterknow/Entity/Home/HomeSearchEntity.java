package com.smartcityin.waterknow.Entity.Home;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/26
 * E-mail : wkz123011@gmail.com
 */
public class HomeSearchEntity {

    /**
     * token : DnLfZK2nJOdgn0CwuL59Qw==
     * data : [{"title":"火车上的水怎么来的，你造吗？","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/1.html"},{"title":"关于智慧生活，人大代表说了啥？","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/2.html"},{"title":"你不知道的用水小常识！","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/3.html"},{"title":"用水小常识","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/4.html"},{"title":"数字中国建设成果展览会 智慧生活什么样","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/5.html"},{"title":"窗帘电机有必要做成智能的吗?","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/7.html"},{"title":"智慧生活已经到来？从中国到全球它正在蔓延","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/10.html"},{"title":"生活中饮用水安全知识","type":0,"value":"http://123.207.166.123/water/index.php/Personal/News/index/type/help/id/1.html"},{"title":"测试公共服务网点","type":1,"value":"40000000000"},{"title":"北京测试网点","type":1,"value":"4001000000"}]
     * mark : 3728f4795c7d975c3a24e6a5d5c06247
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
         * title : 火车上的水怎么来的，你造吗？
         * type : 0
         * value : http://123.207.166.123/water/index.php/Personal/News/index/type/news/id/1.html
         */

        private String title;
        private int type;
        private String value;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
