package com.bw.movie.utils;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/*
 *作者：刘进
 *日期：2018/11/8
 **/
public interface BaseService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map);
    @Multipart
    @POST
    Observable<ResponseBody> part(@Url String url, @QueryMap Map<String, String> map, @Part MultipartBody.Part pay);
}
