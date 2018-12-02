package com.bw.movie.utils;

public class Http {
    public final static String BASE_URL="http://mobile.bwstudent.com/";
    public final static String MOVIE_DETAILS="movieApi/movie/v1/findMoviesDetail?userId=18&sessionId=15320748258726&movieId=";
    public final static String MOVIE_HOTMOIE = "movieApi/movie/v1/findHotMovieList?userId=18&sessionId=15320748258726&count=10&page=";
    public final static String MOVIE_ISHOT = "movieApi/movie/v1/findReleaseMovieList?userId=18&sessionId=15320748258726&count=10&page=";
    public final static String MOVIE_COMING_SOON = "movieApi/movie/v1/findComingSoonMovieList?userId=18&sessionId=15320748258726&count=10&page=";
    public final static String ACTIVITY_FEEDBACK="movieApi/tool/v1/verify/recordFeedBack?";
    public final static String ACTIVITY_RENEWAL="movieApi/tool/v1/findNewVersion?userId=2260&sessionId=15435612476682260&ak=01";
    public final static String ACTIVITY_MESSAGE="movieApi/tool/v1/verify/findUnreadMessageCount?userId=2260&sessionId=15435612476682260";
    public final static String MOVIE_FILMREVIE = "movieApi/movie/v1/findAllMovieComment?userId=6&sessionId=15320748258726&movieId=1&count=5&page=";
}
