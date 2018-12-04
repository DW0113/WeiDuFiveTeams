package com.bw.movie.utils;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
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
    @Headers({"ak:0110010010000","Content-Type:application/x-www-form-urlencoded"})
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map);
    @POST
    Observable<ResponseBody> postHead(@Url String url, @QueryMap Map<String, String> map, @HeaderMap Map<String, String> mapHead);
    @FormUrlEncoded
    @POST
    @Headers({"ak:0110010010000","Content-Type:application/x-www-form-urlencoded"})
    Observable<ResponseBody> postform(@Url String url, @FieldMap Map<String, String> mapfied, @HeaderMap Map<String, String> mapHead);

    @Multipart
    @POST
    Observable<ResponseBody> part(@Url String url, @HeaderMap Map<String, String> map, @Part MultipartBody.Part pay);
    @GET
    @Headers({"ak:0110010010000","Content-Type:application/x-www-form-urlencoded"})
    Observable<ResponseBody> getheader(@Url String url, @QueryMap Map<String, String> map, @HeaderMap Map<String, String> maphead);

	
	
	@FormUrlEncoded
    @POST
    @Headers({
            "ak:0110010010000",
            "Content-Type:application/x-www-form-urlencoded"
    })
    Observable<ResponseBody> give(@HeaderMap Map<String, String> m, @Url String url, @FieldMap Map<String, String> map);
	
	


}
