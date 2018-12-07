package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MyLoveMoveAdpater;
import com.bw.movie.model.LoveMoveBean;
import com.bw.movie.model.MyLove;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容：我关注的电影
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class MyLoveMovieePresenter extends AppDelegate {
     private    List<LoveMoveBean.ResultBean> moveList=new ArrayList<>();
    private MyLoveMoveAdpater myLoveMoveAdpater;
    private String userld;
    private String sessionId;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mylove_move;
    }
    Context context;
    @Override
    public void getContext(Context context) {
  this.context=context;
    }

    @Override
    public void initData() {
        //找控件
         RecyclerView  rv_love_move=get(R.id.rv_love_move);
         //获取值
        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        userld = login.getString("userld", "");
        sessionId = login.getString("sessionId", "");
        //判断有没有登陆
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
       if(TextUtils.isEmpty(userld)){
           Toast.makeText(context,"请先登录",Toast.LENGTH_LONG).show();
           return;
       }
       else{
           dohttp(userld,sessionId);
       }

           myLoveMoveAdpater = new MyLoveMoveAdpater(context, moveList);
           rv_love_move.setAdapter(myLoveMoveAdpater);
           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
           rv_love_move.setLayoutManager(linearLayoutManager);


    }
//网络请求
    private void dohttp(String userld, String sessionId) {
        Map map = new HashMap<>();
        Map m = new HashMap<>();
        m.put("userId",userld);
        m.put("sessionId",sessionId);
        map.put("page",1);
        map.put("count",10);
        new Utility().getcinema(m,"movieApi/movie/v1/verify/findMoviePageList",map).result(new HttpListener() {
               @Override
               public void success(String data) {
                   Toast.makeText(context,"电影："+data,Toast.LENGTH_LONG).show();
                   LoveMoveBean loveMoveBean = new Gson().fromJson(data, LoveMoveBean.class);
                   if(loveMoveBean.getStatus().equals("0000")){
                       List<LoveMoveBean.ResultBean> result = loveMoveBean.getResult();
                       if(result==null){
                           Toast.makeText(context,"目前还没有关注的",Toast.LENGTH_LONG).show();
                           return;
                       }
                       moveList.addAll(result);
                       myLoveMoveAdpater.notifyDataSetChanged();
                   }
                   else{
                       Toast.makeText(context,""+data,Toast.LENGTH_LONG).show();
                   }
               }

               @Override
               public void fail(String error) {

               }
           });
    }
}
