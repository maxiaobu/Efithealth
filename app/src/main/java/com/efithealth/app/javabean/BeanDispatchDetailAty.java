package com.efithealth.app.javabean;

import java.util.List;

/**
 * Created by 马小布 on 2016/8/29.
 */
public class BeanDispatchDetailAty {

    /**
     * msgFlag : 1
     * msgContent : 配餐配送详情列表
     * list : [{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周一","deliverydate":{"date":29,"day":1,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1472400000000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-08-29 2","deliveryid":"D000369","deliverytime":"2","delvystr":"2016-08-29","dlvtimestr":"午餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周一","deliverydate":{"date":29,"day":1,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1472400000000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-08-29 3","deliveryid":"D000370","deliverytime":"3","delvystr":"2016-08-29","dlvtimestr":"晚餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周二","deliverydate":{"date":30,"day":2,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1472486400000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-08-30 3","deliveryid":"D000371","deliverytime":"3","delvystr":"2016-08-30","dlvtimestr":"晚餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周三","deliverydate":{"date":31,"day":3,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1472572800000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-08-31 2","deliveryid":"D000372","deliverytime":"2","delvystr":"2016-08-31","dlvtimestr":"午餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周三","deliverydate":{"date":31,"day":3,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1472572800000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-08-31 3","deliveryid":"D000373","deliverytime":"3","delvystr":"2016-08-31","dlvtimestr":"晚餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周四","deliverydate":{"date":1,"day":4,"hours":0,"minutes":0,"month":8,"nanos":0,"seconds":0,"time":1472659200000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-09-01 3","deliveryid":"D000374","deliverytime":"3","delvystr":"2016-09-01","dlvtimestr":"晚餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周五","deliverydate":{"date":2,"day":5,"hours":0,"minutes":0,"month":8,"nanos":0,"seconds":0,"time":1472745600000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-09-02 2","deliveryid":"D000375","deliverytime":"2","delvystr":"2016-09-02","dlvtimestr":"午餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"},{"ISLAST":"","begindate":"","buynum":1,"createtime":{"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116},"createuser":"","dayOfWeek":"周五","deliverydate":{"date":2,"day":5,"hours":0,"minutes":0,"month":8,"nanos":0,"seconds":0,"time":1472745600000,"timezoneOffset":-480,"year":116},"deliverydatestr":"2016-09-02 3","deliveryid":"D000376","deliverytime":"3","delvystr":"2016-09-02","dlvtimestr":"晚餐","enddate":"","foodmerid":"M000019","foodmerprice":300,"foodmertype":"","foodname":"壹想食健身周双餐","forderid":"FO-20160827-558","modifytime":null,"modifyuser":"","ordamt":300,"recaddress":"和平区马达加斯加奥斯特洛夫斯基","recname":"赵子龙","recphone":"13897900910","region":"和平区","remark":"","status":"1","statusname":"有效"}]
     */

    private String msgFlag;
    private String msgContent;
    /**
     * ISLAST :
     * begindate :
     * buynum : 1
     * createtime : {"date":27,"day":6,"hours":15,"minutes":30,"month":7,"nanos":0,"seconds":34,"time":1472283034000,"timezoneOffset":-480,"year":116}
     * createuser :
     * dayOfWeek : 周一
     * deliverydate : {"date":29,"day":1,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1472400000000,"timezoneOffset":-480,"year":116}
     * deliverydatestr : 2016-08-29 2
     * deliveryid : D000369
     * deliverytime : 2
     * delvystr : 2016-08-29
     * dlvtimestr : 午餐
     * enddate :
     * foodmerid : M000019
     * foodmerprice : 300
     * foodmertype :
     * foodname : 壹想食健身周双餐
     * forderid : FO-20160827-558
     * modifytime : null
     * modifyuser :
     * ordamt : 300
     * recaddress : 和平区马达加斯加奥斯特洛夫斯基
     * recname : 赵子龙
     * recphone : 13897900910
     * region : 和平区
     * remark :
     * status : 1
     * statusname : 有效
     */

    private List<ListBean> list;

    public String getMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(String msgFlag) {
        this.msgFlag = msgFlag;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String ISLAST;
        private String begindate;
        private int buynum;
        /**
         * date : 27
         * day : 6
         * hours : 15
         * minutes : 30
         * month : 7
         * nanos : 0
         * seconds : 34
         * time : 1472283034000
         * timezoneOffset : -480
         * year : 116
         */

        private CreatetimeBean createtime;
        private String createuser;
        private String dayOfWeek;
        /**
         * date : 29
         * day : 1
         * hours : 0
         * minutes : 0
         * month : 7
         * nanos : 0
         * seconds : 0
         * time : 1472400000000
         * timezoneOffset : -480
         * year : 116
         */

        private DeliverydateBean deliverydate;
        private String deliverydatestr;
        private String deliveryid;
        private String deliverytime;
        private String delvystr;
        private String dlvtimestr;
        private String enddate;
        private String foodmerid;
        private int foodmerprice;
        private String foodmertype;
        private String foodname;
        private String forderid;
        private Object modifytime;
        private String modifyuser;
        private int ordamt;
        private String recaddress;
        private String recname;
        private String recphone;
        private String region;
        private String remark;
        private String status;
        private String statusname;

        public String getISLAST() {
            return ISLAST;
        }

        public void setISLAST(String ISLAST) {
            this.ISLAST = ISLAST;
        }

        public String getBegindate() {
            return begindate;
        }

        public void setBegindate(String begindate) {
            this.begindate = begindate;
        }

        public int getBuynum() {
            return buynum;
        }

        public void setBuynum(int buynum) {
            this.buynum = buynum;
        }

        public CreatetimeBean getCreatetime() {
            return createtime;
        }

        public void setCreatetime(CreatetimeBean createtime) {
            this.createtime = createtime;
        }

        public String getCreateuser() {
            return createuser;
        }

        public void setCreateuser(String createuser) {
            this.createuser = createuser;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public DeliverydateBean getDeliverydate() {
            return deliverydate;
        }

        public void setDeliverydate(DeliverydateBean deliverydate) {
            this.deliverydate = deliverydate;
        }

        public String getDeliverydatestr() {
            return deliverydatestr;
        }

        public void setDeliverydatestr(String deliverydatestr) {
            this.deliverydatestr = deliverydatestr;
        }

        public String getDeliveryid() {
            return deliveryid;
        }

        public void setDeliveryid(String deliveryid) {
            this.deliveryid = deliveryid;
        }

        public String getDeliverytime() {
            return deliverytime;
        }

        public void setDeliverytime(String deliverytime) {
            this.deliverytime = deliverytime;
        }

        public String getDelvystr() {
            return delvystr;
        }

        public void setDelvystr(String delvystr) {
            this.delvystr = delvystr;
        }

        public String getDlvtimestr() {
            return dlvtimestr;
        }

        public void setDlvtimestr(String dlvtimestr) {
            this.dlvtimestr = dlvtimestr;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getFoodmerid() {
            return foodmerid;
        }

        public void setFoodmerid(String foodmerid) {
            this.foodmerid = foodmerid;
        }

        public int getFoodmerprice() {
            return foodmerprice;
        }

        public void setFoodmerprice(int foodmerprice) {
            this.foodmerprice = foodmerprice;
        }

        public String getFoodmertype() {
            return foodmertype;
        }

        public void setFoodmertype(String foodmertype) {
            this.foodmertype = foodmertype;
        }

        public String getFoodname() {
            return foodname;
        }

        public void setFoodname(String foodname) {
            this.foodname = foodname;
        }

        public String getForderid() {
            return forderid;
        }

        public void setForderid(String forderid) {
            this.forderid = forderid;
        }

        public Object getModifytime() {
            return modifytime;
        }

        public void setModifytime(Object modifytime) {
            this.modifytime = modifytime;
        }

        public String getModifyuser() {
            return modifyuser;
        }

        public void setModifyuser(String modifyuser) {
            this.modifyuser = modifyuser;
        }

        public int getOrdamt() {
            return ordamt;
        }

        public void setOrdamt(int ordamt) {
            this.ordamt = ordamt;
        }

        public String getRecaddress() {
            return recaddress;
        }

        public void setRecaddress(String recaddress) {
            this.recaddress = recaddress;
        }

        public String getRecname() {
            return recname;
        }

        public void setRecname(String recname) {
            this.recname = recname;
        }

        public String getRecphone() {
            return recphone;
        }

        public void setRecphone(String recphone) {
            this.recphone = recphone;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusname() {
            return statusname;
        }

        public void setStatusname(String statusname) {
            this.statusname = statusname;
        }

        public static class CreatetimeBean {
            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class DeliverydateBean {
            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
