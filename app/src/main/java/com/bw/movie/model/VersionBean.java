package com.bw.movie.model;

/**
 * 作者：马利亚
 * 日期：2018/12/3
 * 内容：
 */
public class VersionBean {

    /**
     * flag : 1
     * downloadUrl : http://172.17.8.100/media/movie.apk
     * message : 查询成功
     * status : 0000
     */

    private int flag;
    private String downloadUrl;
    private String message;
    private String status;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
