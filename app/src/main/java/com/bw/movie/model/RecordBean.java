package com.bw.movie.model;

import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/12/1
 * */public class RecordBean {

    /**
     * result : [{"amount":1,"beginTime":"20:00:00","cinemaName":"CGV影城（清河店）","createTime":1544183934000,"endTime":"21:48:00","id":4726,"movieName":"碟中谍6：全面瓦解","orderId":"20181207195854134","price":0.2,"screeningHall":"7号厅","status":1,"userId":1390},{"amount":1,"beginTime":"20:00:00","cinemaName":"CGV影城（清河店）","createTime":1544172762000,"endTime":"21:48:00","id":4723,"movieName":"碟中谍6：全面瓦解","orderId":"20181207165242680","price":0.2,"screeningHall":"7号厅","status":1,"userId":1390},{"amount":1,"beginTime":"20:00:00","cinemaName":"东融国际影城西直河店","createTime":1544172449000,"endTime":"21:48:00","id":4722,"movieName":"碟中谍6：全面瓦解","orderId":"20181207164729981","price":0.2,"screeningHall":"8号厅","status":1,"userId":1390}]
     * message : 请求成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * amount : 1
         * beginTime : 20:00:00
         * cinemaName : CGV影城（清河店）
         * createTime : 1544183934000
         * endTime : 21:48:00
         * id : 4726
         * movieName : 碟中谍6：全面瓦解
         * orderId : 20181207195854134
         * price : 0.2
         * screeningHall : 7号厅
         * status : 1
         * userId : 1390
         */

        private int amount;
        private String beginTime;
        private String cinemaName;
        private long createTime;
        private String endTime;
        private int id;
        private String movieName;
        private String orderId;
        private double price;
        private String screeningHall;
        private int status;
        private int userId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
