package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.SuitableCinemaActivity;
import com.bw.movie.adapter.SuitableCinemaAdapter;
import com.bw.movie.model.SuitableCinemaBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者：杜威
 * 时间：2018/12/03
 * 查询电影对应的适合的影院列表
 * */
public class SuitableCinemaActivityPresenter extends AppDelegate {

    private TextView tv_suitable_cinema_movie_name;
    private RecyclerView rv_suitable_cinema_movie;
    private Context context;
    private int movieId;
    private SuitableCinemaAdapter cinemaAdapter;
    private String movieName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_suitable_cinema;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //获取电影ID
        Intent intent = ((SuitableCinemaActivity) context).getIntent();
        movieId = intent.getIntExtra("movieId", 1);
        movieName = intent.getStringExtra("movieName");
        //初始化控件
        tv_suitable_cinema_movie_name = get(R.id.tv_suitable_cinema_movie_name);
        rv_suitable_cinema_movie = get(R.id.rv_suitable_cinema_movie);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_suitable_cinema_movie.setLayoutManager(linearLayoutManager);
        cinemaAdapter = new SuitableCinemaAdapter(context);
        rv_suitable_cinema_movie.setAdapter(cinemaAdapter);
        tv_suitable_cinema_movie_name.setText(movieName);
        //请求网络数据
        doHttp(movieId);
    }

    private void doHttp(int movieId) {
        new HttpHelper().get(Http.SUITABLE_CINEMA+"&movieId="+movieId).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                SuitableCinemaBean suitableCinemaBean = gson.fromJson(data, SuitableCinemaBean.class);
                List<SuitableCinemaBean.ResultBean> result = suitableCinemaBean.getResult();
                Log.i("aaa",suitableCinemaBean+"");
                cinemaAdapter.setList(result);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
