package com.bw.movie.utils;

public class Http {
    public final static String BASE_URL="http://mobile.bwstudent.com/";
    //影片详情
    public final static String MOVIE_DETAILS="movieApi/movie/v1/findMoviesDetail?";
    //热门电影
    public final static String MOVIE_HOTMOIE = "movieApi/movie/v1/findHotMovieList?userId=18&sessionId=15320748258726&count=10&page=";
    //正在热映
    public final static String MOVIE_ISHOT = "movieApi/movie/v1/findReleaseMovieList?userId=18&sessionId=15320748258726&count=10&page=";
    //即将上映
    public final static String MOVIE_COMING_SOON = "movieApi/movie/v1/findComingSoonMovieList?userId=18&sessionId=15320748258726&count=10&page=";
    //影片评论列表
    public final static String MOVIE_FILMREVIE = "movieApi/movie/v1/findAllMovieComment";
    public final static String ACTIVITY_FEEDBACK="movieApi/tool/v1/verify/recordFeedBack?";
    public final static String ACTIVITY_RENEWAL="movieApi/tool/v1/findNewVersion?userId=2260&sessionId=15435612476682260&ak=01";
    public final static String ACTIVITY_MESSAGE="movieApi/tool/v1/verify/findUnreadMessageCount?userId=2260&sessionId=15435612476682260";
    //查询电影对应的适合的影院列表
    public final static String SUITABLE_CINEMA="movieApi/movie/v1/findCinemasListByMovieId?";
    public final static String MOVIE_BUY="movieApi/movie/v1/findMovieScheduleList?";
    public final static String CINEMA_DETAILS = "movieApi/cinema1/findCinemaInfo";
    //电影详情关注
    public final static String MOVIE_ATTENTION="movieApi/movie/v1/verify/followMovie";
    //电影详情取消关注
    public final static String MOVIE_UNFOLLOW="movieApi/movie/v1/verify/cancelFollowMovie";
    //电影详情点赞
    public final static String MOVIE_GREATE="movieApi/movie/v1/verify/movieCommentGreat";
    public final static String MOVIE_REPLY="movieApi/movie/v1/findCommentReply";
    //添加电影评论
    public final static String MOVIE_REPLY_MOVIE = "movieApi/movie/v1/verify/movieComment";
    //查询评论回复列表
    public final static String MOVIE_COMMENT="movieApi/movie/v1/findCommentReply";
    //添加评论回复
    public final static String MOVIE_ADD_COMMENT_BACK = "movieApi/movie/v1/verify/commentReply";


}
