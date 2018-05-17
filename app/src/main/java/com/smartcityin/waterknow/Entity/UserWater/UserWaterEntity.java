package com.smartcityin.waterknow.Entity.UserWater;

import java.util.List;

/**
 * Author : Mr.老王
 * Created on 2018/4/9
 * E-mail : wkz123011@gmail.com
 */
public class UserWaterEntity {

    /**
     * token : 8GLoWgiHLKUlqpylnRR1/w==
     * data : {"yearly_water_consumption":44556,"yearly_water_rate":400124,"monthly_water_consumption":0,"monthly_water_rate":-274453.84,"water_meter_reading":44556,"water_surplus_water":789,"hierarchical_data":[{"water_consumption":180,"max":180,"price":5,"water_rent":2.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"一阶"},{"water_consumption":260,"max":260,"price":7,"water_rent":4.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"二阶"},{"water_consumption":44296,"max":2147483647,"price":9,"water_rent":6.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"三阶"}]}
     * mark : f589dce91346274ad5f0a497a67ff875
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
         * yearly_water_consumption : 44556
         * yearly_water_rate : 400124
         * monthly_water_consumption : 0
         * monthly_water_rate : -274453.84
         * water_meter_reading : 44556
         * water_surplus_water : 789
         * hierarchical_data : [{"water_consumption":180,"max":180,"price":5,"water_rent":2.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"一阶"},{"water_consumption":260,"max":260,"price":7,"water_rent":4.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"二阶"},{"water_consumption":44296,"max":2147483647,"price":9,"water_rent":6.07,"other_rent":[{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}],"title":"三阶"}]
         */

        private int yearly_water_consumption;
        private int yearly_water_rate;
        private double monthly_water_consumption;
        private double monthly_water_rate;
        private int water_meter_reading;
        private int water_surplus_water;
        private List<HierarchicalDataBean> hierarchical_data;

        public int getYearly_water_consumption() {
            return yearly_water_consumption;
        }

        public void setYearly_water_consumption(int yearly_water_consumption) {
            this.yearly_water_consumption = yearly_water_consumption;
        }

        public int getYearly_water_rate() {
            return yearly_water_rate;
        }

        public void setYearly_water_rate(int yearly_water_rate) {
            this.yearly_water_rate = yearly_water_rate;
        }

        public double getMonthly_water_consumption() {
            return monthly_water_consumption;
        }

        public void setMonthly_water_consumption(double monthly_water_consumption) {
            this.monthly_water_consumption = monthly_water_consumption;
        }

        public double getMonthly_water_rate() {
            return monthly_water_rate;
        }

        public void setMonthly_water_rate(double monthly_water_rate) {
            this.monthly_water_rate = monthly_water_rate;
        }

        public int getWater_meter_reading() {
            return water_meter_reading;
        }

        public void setWater_meter_reading(int water_meter_reading) {
            this.water_meter_reading = water_meter_reading;
        }

        public int getWater_surplus_water() {
            return water_surplus_water;
        }

        public void setWater_surplus_water(int water_surplus_water) {
            this.water_surplus_water = water_surplus_water;
        }

        public List<HierarchicalDataBean> getHierarchical_data() {
            return hierarchical_data;
        }

        public void setHierarchical_data(List<HierarchicalDataBean> hierarchical_data) {
            this.hierarchical_data = hierarchical_data;
        }

        public static class HierarchicalDataBean {
            /**
             * water_consumption : 180
             * max : 180
             * price : 5
             * water_rent : 2.07
             * other_rent : [{"name":"水资源费","val":1.57},{"name":"污水处理费","val":1.36}]
             * title : 一阶
             */

            private int water_consumption;
            private int max;
            private int price;
            private double water_rent;
            private String title;
            private List<OtherRentBean> other_rent;

            public int getWater_consumption() {
                return water_consumption;
            }

            public void setWater_consumption(int water_consumption) {
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
