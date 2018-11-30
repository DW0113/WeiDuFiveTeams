package com.bw.movie.presenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.CinemaShowActivity;
import com.bw.movie.model.CinemaItemBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/*
 *作者：刘进
 *日期：2018/11/29
 **/
public class CinemaShowActivityPresenter extends AppDelegate{
    private RecyclerView mRecyclerView;
    private SharedPreferences login;
    private String id;
    private ImageView mImage;
    private TextView mName,mAddress;
    private RelativeLayout details_show;
    private ImageView test;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cinema_show;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        details_show = get(R.id.layout1);
        test = get(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintShopCar();
            }
        });
        //初始化控件
        mImage = get(R.id.iv_cinema_logo);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShopCar();
            }
        });
        mName = get(R.id.tv_cinema_name);
        mAddress = get(R.id.tv_cinema_address);

        //Intent
        Intent intent = ((CinemaShowActivity) context).getIntent();
         id = intent.getStringExtra("id");
        //Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
        //取数据
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userId = login.getString("userId", "");
        String sessionId = login.getString("sessionId", "");
        Http();
    }
    //网络请求数据
    private void Http() {
        Map<String,String> map = new HashMap<>();
        map.put("cinemaId",id);
        map.put("userId","userId");
        map.put("sessionId","sessionId");
        new Utility().get("http://172.17.8.100/movieApi/cinema/v1/findCinemaInfo",map).result(new HttpListener() {
            @Override
            public void success(String data) {
                //Toast.makeText(context, ""+data, Toast.LENGTH_SHORT).show();
                CinemaItemBean cinemaItemBean = new Gson().fromJson(data, CinemaItemBean.class);
                CinemaItemBean.ResultBean result = cinemaItemBean.getResult();
                //mImage.setImageURI(result.getLogo()+"");
                mName.setText(result.getName());
                mAddress.setText(result.getAddress());
                Glide.with(context).load(result.getLogo()).into(mImage);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private void showShopCar() {
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(details_show, "translationY", heightPixels, 0);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        details_show.setVisibility(View.VISIBLE);
    }

    //关闭
    private void hintShopCar() {
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(details_show, "translationY", 0, heightPixels);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                details_show.setVisibility(View.GONE);
            }
        }, 1000);

    }

}
