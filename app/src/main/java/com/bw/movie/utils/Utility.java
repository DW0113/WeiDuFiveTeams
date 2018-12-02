package com.bw.movie.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *作者：刘进
 *日期：2018/11/8
 **/
public class Utility {
    private  BaseService mBaseService;

    public Utility(){
        Retrofit retrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Http.BASE_URL)
                .build();
        mBaseService=retrofit.create(BaseService.class);
    }

    //get请求
    public Utility get(String url, Map<String,String> map){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return this;
    }
    public Utility getheader(String url, Map<String,String> map){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return this;
    }

    //post请求
    public Utility post(String url, Map<String,String> map){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseService.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return this;
    }
    //part
    public Utility part(String url, Map<String,String> map, MultipartBody.Part part){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseService.part(url,map,part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return this;
    }

    private Observer observer=new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data= responseBody.string();
                listener.success(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            String error= e.getMessage();
            listener.fail(error);
        }

        @Override
        public void onComplete() {

        }
    };

    private HttpListener listener;

    public void result(HttpListener listener){
        this.listener=listener;
    }
}
