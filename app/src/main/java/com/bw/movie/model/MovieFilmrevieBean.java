package com.bw.movie.model;

import java.util.List;

public class MovieFilmrevieBean {
    /**
     * result : [{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-11-21/20181121094725.jpg","commentId":1256,"commentTime":1542591930000,"commentUserId":480,"commentUserName":"头哥嫌你头小","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-11-21/20181121094725.jpg","commentId":1255,"commentTime":1542591890000,"commentUserId":480,"commentUserName":"头哥嫌你头小","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1253,"commentTime":1542426872000,"commentUserId":1873,"commentUserName":"sunmeng","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"haha","commentHeadPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/cH5v6IHFYe0DtVTlCjHvInNP7j057o4a2gbmoTyF6Hoib0kbbxXqnzwkZm8DiabK5waOc3TpOdpBmbfhnPjhJF8g/132","commentId":1031,"commentTime":1539865106000,"commentUserId":897,"commentUserName":"安_YgC","greatNum":0,"hotComment":0,"isGreat":0,"replyNum":0},{"commentContent":"啊啊啊","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2018-09-21/20180921094633.png","commentId":879,"commentTime":1537490168000,"commentUserId":553,"commentUserName":"幻幻陵_Aku","greatNum":2,"hotComment":0,"isGreat":0,"replyNum":0}]
     * message : 查询成功
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
         * commentContent : 电影好看
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2018-11-21/20181121094725.jpg
         * commentId : 1256
         * commentTime : 1542591930000
         * commentUserId : 480
         * commentUserName : 头哥嫌你头小
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         * replyNum : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;
        private int replyNum;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }
    }
}
