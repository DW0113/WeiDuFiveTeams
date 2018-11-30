package com.bw.movie.model;

/**
 * 作者：马利亚
 * 日期：2018/11/29
 * 内容：
 */
public class MessagesBean {

    /**
     * count : 0
     * message : 查询成功
     * status : 0000
     */

    private int count;
    private String message;
    private String status;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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
}
