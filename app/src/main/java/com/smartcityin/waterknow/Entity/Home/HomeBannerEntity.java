package com.smartcityin.waterknow.Entity.Home;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/18
 * E-mail : wkz123011@gmail.com
 */
public class HomeBannerEntity {

    /**
     * token : 3sl1bCieegiJtYsqjN5t9w==
     * data : [{"id":"1","url":"https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_15415313745980484042%22%7D&n_type=0&p_from=1","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524461046920&di=29439dee6cb426c6e0d0fd815bf08c69&imgtype=0&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F55%2F48%2F16pic_5548763_b.jpg","val":""},{"id":"2","url":"https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9790640236386569335%22%7D&n_type=0&p_from=1","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524461046920&di=dfaebc1e77932f9a433ea1dbb4413a39&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F121128%2F235094-12112Q62J830.jpg","val":""},{"id":"3","url":"https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_2913228409594400741%22%7D&n_type=0&p_from=1","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524461046917&di=7d0404037ab397572b7fe1fd964d5c12&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dpixel_huitu%252C0%252C0%252C294%252C40%2Fsign%3Dbde3241a78f08202399f997f2283","val":""}]
     * mark : e73d0ca97b85ef0740cc4b1e285997d7
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
         * url : https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_15415313745980484042%22%7D&n_type=0&p_from=1
         * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524461046920&di=29439dee6cb426c6e0d0fd815bf08c69&imgtype=0&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F55%2F48%2F16pic_5548763_b.jpg
         * val :
         */

        private String id;
        private String url;
        private String pic;
        private String val;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
