package com.smartcityin.waterknow.Entity.My;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/5/4
 * E-mail : wkz123011@gmail.com
 */
public class MyAnnualQueryEntity {

    /**
     * token : H387YX1MChaf1b68wG0qGg==
     * data : {"water_consumption":56.94,"water_rate":284.7,"constitute_of_water_rate":[{"key":"水费","var":117.8658,"percent":0.414},{"key":"水资源费","var":89.3958,"percent":0.314},{"key":"污水处理费","var":77.4384,"percent":0.272}],"water_meter_reading":56.94,"hierarchical_data":[{"water_consumption":56.94,"max":180,"price":5,"water_rent":2.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"一阶"},{"water_consumption":0,"max":260,"price":7,"water_rent":4.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"二阶"},{"title":"三阶","water_consumption":0,"max":2147483647,"price":9,"water_rent":6.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}]}]}
     * mark : 54ff799272e5ffa074e123f4c6b86ca4
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
         * water_consumption : 56.94
         * water_rate : 284.7
         * constitute_of_water_rate : [{"key":"水费","var":117.8658,"percent":0.414},{"key":"水资源费","var":89.3958,"percent":0.314},{"key":"污水处理费","var":77.4384,"percent":0.272}]
         * water_meter_reading : 56.94
         * hierarchical_data : [{"water_consumption":56.94,"max":180,"price":5,"water_rent":2.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"一阶"},{"water_consumption":0,"max":260,"price":7,"water_rent":4.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"二阶"},{"title":"三阶","water_consumption":0,"max":2147483647,"price":9,"water_rent":6.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}]}]
         */

        private double water_consumption;
        private double water_rate;
        private double water_meter_reading;
        private List<ConstituteOfWaterRateBean> constitute_of_water_rate;
        private List<HierarchicalDataBean> hierarchical_data;

        public double getWater_consumption() {
            return water_consumption;
        }

        public void setWater_consumption(double water_consumption) {
            this.water_consumption = water_consumption;
        }

        public double getWater_rate() {
            return water_rate;
        }

        public void setWater_rate(double water_rate) {
            this.water_rate = water_rate;
        }

        public double getWater_meter_reading() {
            return water_meter_reading;
        }

        public void setWater_meter_reading(double water_meter_reading) {
            this.water_meter_reading = water_meter_reading;
        }

        public List<ConstituteOfWaterRateBean> getConstitute_of_water_rate() {
            return constitute_of_water_rate;
        }

        public void setConstitute_of_water_rate(List<ConstituteOfWaterRateBean> constitute_of_water_rate) {
            this.constitute_of_water_rate = constitute_of_water_rate;
        }

        public List<HierarchicalDataBean> getHierarchical_data() {
            return hierarchical_data;
        }

        public void setHierarchical_data(List<HierarchicalDataBean> hierarchical_data) {
            this.hierarchical_data = hierarchical_data;
        }

        public static class ConstituteOfWaterRateBean {
            /**
             * key : 水费
             * var : 117.8658
             * percent : 0.414
             */

            private String key;
            private double vars;
            private double percent;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public double getVar() {
                return vars;
            }

            public void setVar(double var) {
                this.vars = var;
            }

            public double getPercent() {
                return percent;
            }

            public void setPercent(double percent) {
                this.percent = percent;
            }
        }

        public static class HierarchicalDataBean {
            /**
             * water_consumption : 56.94
             * max : 180
             * price : 5
             * water_rent : 2.07
             * other_rent : [{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}]
             * title : 一阶
             */

            private double water_consumption;
            private int max;
            private int price;
            private double water_rent;
            private String title;
            private List<OtherRentBean> other_rent;

            public double getWater_consumption() {
                return water_consumption;
            }

            public void setWater_consumption(double water_consumption) {
                this.water_consumption = water_consumption;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public double getWater_rent() {
                return water_rent;
            }

            public void setWater_rent(double water_rent) {
                this.water_rent = water_rent;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<OtherRentBean> getOther_rent() {
                return other_rent;
            }

            public void setOther_rent(List<OtherRentBean> other_rent) {
                this.other_rent = other_rent;
            }

            public static class OtherRentBean {
                /**
                 * name : 水资源费
                 * val : 1.57
                 */

                private String name;
                private double val;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public double getVal() {
                    return val;
                }

                public void setVal(double val) {
                    this.val = val;
                }
            }
        }
    }
}
