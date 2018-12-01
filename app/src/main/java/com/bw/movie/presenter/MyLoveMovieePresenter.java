package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
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

/*
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class MyLoveMovieePresenter extends AppDelegate {
     private    List<MyLove.ResultBean.MovieListBean> moveList=new ArrayList<>();
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
         RecyclerView  rv_love_move=get(R.id.rv_love_move);

        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userld = login.getString("userld", "");
        Toast.makeText(context,""+userld,Toast.LENGTH_LONG).show();
        String sessionId = login.getString("sessionId", "");
       if(TextUtils.isEmpty(userld)){
           Toast.makeText(context,"请先登录",Toast.LENGTH_LONG).show();
           return;
       }
       else{
           dohttp(userld,sessionId);
       }

    }

    private void dohttp(String userld, String sessionId) {
        Map map = new HashMap<>();
        map.put("userId",userld);
        map.put("sessionId",sessionId);
        new Utility().get("verify/findUserHomeInfo",map).result(new HttpListener() {
               @Override
               public void success(String data) {
                   MyLove myLove = new Gson().fromJson(data, MyLove.class);
                   List<MyLove.ResultBean.MovieListBean> moveListt = myLove.getResult().getMovieList();
                    moveList.addAll(moveListt);

               }

               @Override
               public void fail(String error) {

               }
           });
    }
}
