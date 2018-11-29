package com.bw.movie.utils;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 作者：刘进
 *日期：2018/11/27
 * */
public class HttpHelper {
    private final int SUCCESS=1;//成功
    private final int ERROR=0;//失败

    public HttpHelper get(String url){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            doHttp(client, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void doHttp(OkHttpClient client, Request request) throws IOException {

        client.newCall(request).enqueue(new Callback() {
            final Message message =new Message();
            @Override
            public void onFailure(Call call, IOException e) {
                message.what=ERROR;
                message.obj=e.getMessage();//失败的信息
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                message.what=SUCCESS;
                message.obj=data;
                handler.sendMessage(message);
            }


        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SUCCESS:
                    String data = (String) msg.obj;
                    httplistenner.success(data);
                    break;
                case ERROR:
                    String error = (String) msg.obj;
                    httplistenner.error(error);
                    break;
            }
        }
    };


    public HttpHelper post(String url, FormBody body){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            doHttp(client,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
    //传递接口
    private Httplistenner httplistenner;
    public void result(Httplistenner httplistenner){
        this.httplistenner=httplistenner;
    }

    public interface Httplistenner {
        void success(String data);
        void error(String error);
    }
}
