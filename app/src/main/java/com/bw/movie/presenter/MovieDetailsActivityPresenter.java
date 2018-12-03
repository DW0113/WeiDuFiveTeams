package com.bw.movie.presenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieDetailsActivity;
import com.bw.movie.activity.SuitableCinemaActivity;
import com.bw.movie.adapter.MovieDetailsFilMrevieAdapter;
import com.bw.movie.adapter.MovieDetailsForemShowAdapter;
import com.bw.movie.adapter.MovieDetailsStageShowAdapter;
import com.bw.movie.model.MovieDetailsBean;
import com.bw.movie.model.MovieFilmrevieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.SimpDrawViewUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;

public class MovieDetailsActivityPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SimpleDraweeView sdv_movie_details_image;
    private Button btn_movie_details_details;
    private Button btn_movie_details_filmrevie;
    private Button btn_movie_details_foreshow;
    private Button btn_movie_details_stageshow;
    private ImageView iv_movie_details_back;
    private ImageView iv_movie_details_buy;
    private TextView tv_movie_details_moviename;
    private SimpleDraweeView sdv_movie_details_details_image;
    private TextView tv_movie_details_details_actor_name;
    private TextView tv_movie_details_details_movie_desc;
    private TextView tv_movie_details_details_movie_director;
    private TextView tv_movie_details_details_movie_duration;
    private TextView tv_movie_details_details_movie_placeOrigin;
    private RelativeLayout rl_movie_details_details;
    private TextView tv_movie_details_details_movie_type;
    private ImageView iv_movie_details_foreshow_down;
    private RecyclerView rv_movie_details_foreshow;
    private RelativeLayout rl_movie_details_foreshow;
    private RelativeLayout rl_movie_details_stageshow;
    private ImageView iv_movie_details_stageshow_down;
    private RecyclerView rv_movie_details_stageshow;
    private MovieDetailsForemShowAdapter foremShowAdapter;
    private MovieDetailsStageShowAdapter stageShowAdapter;
    private SimpleDraweeView sdv_movie_details_background;
    private ImageView iv_movie_details_filmrevie_down;
    private XRecyclerView xrv_movie_details_filmrevie;
    private RelativeLayout rl_movie_details_filmrevie;
    private SharedPreferences sp;
    private int movieId;
    private String userld;
    private String sessionId;
    private int page = 1;
    private String movieName;
    private MovieDetailsFilMrevieAdapter filMrevieAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.acitivity_movie_details;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        initView();
        //获取电影Id
        Intent intent = ((MovieDetailsActivity) context).getIntent();
        movieId = intent.getIntExtra("movieId", 1);
        //获取用户信息
        sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        userld = sp.getString("userld", "");
        sessionId = sp.getString("sessionId", "");
        //请求电影详情数据
        doHttpDetails(movieId);
        //返回点击事件
        setOnClick(this,R.id.btn_movie_details_details);
        setOnClick(this,R.id.btn_movie_details_filmrevie);
        setOnClick(this,R.id.btn_movie_details_foreshow);
        setOnClick(this,R.id.btn_movie_details_stageshow);
        setOnClick(this,R.id.iv_movie_details_back);
        setOnClick(this,R.id.iv_movie_details_filmrevie_down);
        setOnClick(this,R.id.iv_movie_details_details_down);
        setOnClick(this,R.id.iv_movie_details_foreshow_down);
        setOnClick(this,R.id.iv_movie_details_stageshow_down);
        setOnClick(this,R.id.iv_movie_details_buy);
        //设置预告片布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_movie_details_foreshow.setLayoutManager(linearLayoutManager);
        foremShowAdapter = new MovieDetailsForemShowAdapter(context);
        rv_movie_details_foreshow.setAdapter(foremShowAdapter);
        //设置剧照布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv_movie_details_stageshow.setLayoutManager(staggeredGridLayoutManager);
        stageShowAdapter = new MovieDetailsStageShowAdapter(context);
        rv_movie_details_stageshow.setAdapter(stageShowAdapter);
        //设置影评布局管理器
        LinearLayoutManager linearLayoutManager_ilmrevie = new LinearLayoutManager(context);
        xrv_movie_details_filmrevie.setLayoutManager(linearLayoutManager_ilmrevie);
        //影片评论列表请求网络数据
        doHttpFilMrevie(1);
        filMrevieAdapter = new MovieDetailsFilMrevieAdapter(context);
        xrv_movie_details_filmrevie.setAdapter(filMrevieAdapter);
        xrv_movie_details_filmrevie.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                doHttpFilMrevie(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                doHttpFilMrevie(page);
            }
        });
    }
    //影片评论列表请求网络数据
    private void doHttpFilMrevie(int page) {
        new HttpHelper().get(Http.MOVIE_FILMREVIE+"&userld="+userld+"&sessionId="+sessionId+"&movieId="+movieId+"&page="+page).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieFilmrevieBean movieFilmrevieBean = gson.fromJson(data, MovieFilmrevieBean.class);
                List<MovieFilmrevieBean.ResultBean> resultBeanList = new ArrayList<>();
                if (page == 1){
                    resultBeanList.clear();
                }
                if (movieFilmrevieBean.getMessage().equals("查询成功")){
                    resultBeanList.addAll(movieFilmrevieBean.getResult());
                }
                filMrevieAdapter.setList(resultBeanList);
                xrv_movie_details_filmrevie.refreshComplete();
                xrv_movie_details_filmrevie.loadMoreComplete();
            }

            @Override
            public void error(String error) {

            }
        });
    }
    //初始化控件
    private void initView() {
        sdv_movie_details_background = get(R.id.sdv_movie_details_background);
        rl_movie_details_details = get(R.id.rl_movie_details_details);
        tv_movie_details_details_actor_name = get(R.id.tv_movie_details_details_actor_name);
        tv_movie_details_details_movie_desc = get(R.id.tv_movie_details_details_movie_desc);
        tv_movie_details_details_movie_director = get(R.id.tv_movie_details_details_movie_director);
        tv_movie_details_details_movie_duration = get(R.id.tv_movie_details_details_movie_duration);
        tv_movie_details_details_movie_placeOrigin = get(R.id.tv_movie_details_details_movie_placeOrigin);
        sdv_movie_details_details_image = get(R.id.sdv_movie_details_details_image);
        tv_movie_details_moviename = get(R.id.tv_movie_details_moviename);
        sdv_movie_details_image = get(R.id.sdv_movie_details_image);
        iv_movie_details_back = get(R.id.iv_movie_details_back);
        iv_movie_details_buy = get(R.id.iv_movie_details_buy);
        tv_movie_details_details_movie_type = get(R.id.tv_movie_details_details_movie_type);
        //预告
        btn_movie_details_foreshow = get(R.id.btn_movie_details_foreshow);
        iv_movie_details_foreshow_down = get(R.id.iv_movie_details_foreshow_down);
        rv_movie_details_foreshow = get(R.id.rv_movie_details_foreshow);
        rl_movie_details_foreshow = get(R.id.rl_movie_details_foreshow);
        //剧照
        btn_movie_details_stageshow = get(R.id.btn_movie_details_stageshow);
        rl_movie_details_stageshow = get(R.id.rl_movie_details_stageshow);
        iv_movie_details_stageshow_down = get(R.id.iv_movie_details_stageshow_down);
        rv_movie_details_stageshow = get(R.id.rv_movie_details_stageshow);
        //影评
        btn_movie_details_filmrevie = get(R.id.btn_movie_details_filmrevie);
        iv_movie_details_filmrevie_down = get(R.id.iv_movie_details_filmrevie_down);
        xrv_movie_details_filmrevie = get(R.id.xrv_movie_details_filmrevie);
        rl_movie_details_filmrevie = get(R.id.rl_movie_details_filmrevie);
    }

    //请求电影详情数据
    private void doHttpDetails(int id) {
        new HttpHelper().get(Http.MOVIE_DETAILS+id).result(new HttpHelper.Httplistenner() {

            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieDetailsBean movieDetailsBean = gson.fromJson(data, MovieDetailsBean.class);
                SimpDrawViewUtils.showUrlBlur(sdv_movie_details_background,movieDetailsBean.getResult().getImageUrl(),5,5);
                //sdv_movie_details_background.setImageURI(movieDetailsBean.getResult().getImageUrl());
                sdv_movie_details_image.setImageURI(movieDetailsBean.getResult().getImageUrl());
                sdv_movie_details_details_image.setImageURI(movieDetailsBean.getResult().getImageUrl());
                tv_movie_details_moviename.setText(movieDetailsBean.getResult().getName());
                tv_movie_details_details_movie_type.setText("类型："+movieDetailsBean.getResult().getMovieTypes());
                tv_movie_details_details_actor_name.setText(movieDetailsBean.getResult().getStarring());
                tv_movie_details_details_movie_desc.setText(movieDetailsBean.getResult().getSummary());
                tv_movie_details_details_movie_director.setText("导演："+movieDetailsBean.getResult().getDirector());
                tv_movie_details_details_movie_duration.setText("时长："+movieDetailsBean.getResult().getDuration());
                tv_movie_details_details_movie_placeOrigin.setText("产地："+movieDetailsBean.getResult().getPlaceOrigin());
                foremShowAdapter.setList(movieDetailsBean);
                stageShowAdapter.setList(movieDetailsBean.getResult().getPosterList());
                movieName = movieDetailsBean.getResult().getName();
            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_movie_details_back:
                ((MovieDetailsActivity)context).finish();
            break;
            case R.id.iv_movie_details_details_down:
                ObjectAnimator objectAnimator_details = ObjectAnimator.ofFloat(rl_movie_details_details,"translationY",0,1800);
                objectAnimator_details.setDuration(1000);
                objectAnimator_details.start();
                break;
            case R.id.btn_movie_details_details:
                rl_movie_details_details.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_details_select = ObjectAnimator.ofFloat(rl_movie_details_details,"translationY",1800,0);
                objectAnimator_details_select.setDuration(1000);
                objectAnimator_details_select.start();
                break;
            case R.id.iv_movie_details_filmrevie_down:
                ObjectAnimator objectAnimator_filmrevie = ObjectAnimator.ofFloat(rl_movie_details_filmrevie,"translationY",0,1800);
                objectAnimator_filmrevie.setDuration(1000);
                objectAnimator_filmrevie.start();
                break;
            case R.id.btn_movie_details_filmrevie:
                rl_movie_details_filmrevie.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_filmrevie_select = ObjectAnimator.ofFloat(rl_movie_details_filmrevie,"translationY",1800,0);
                objectAnimator_filmrevie_select.setDuration(1000);
                objectAnimator_filmrevie_select.start();
                break;
            case R.id.iv_movie_details_foreshow_down:
                ObjectAnimator objectAnimator_foreshow = ObjectAnimator.ofFloat(rl_movie_details_foreshow,"translationY",0,1800);
                objectAnimator_foreshow.setDuration(1000);
                objectAnimator_foreshow.start();
                break;
            case R.id.btn_movie_details_foreshow:
                rl_movie_details_foreshow.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_foreshow_select = ObjectAnimator.ofFloat(rl_movie_details_foreshow,"translationY",1800,0);
                objectAnimator_foreshow_select.setDuration(1000);
                objectAnimator_foreshow_select.start();
                break;
            case R.id.iv_movie_details_stageshow_down:
                ObjectAnimator objectAnimator_stageshow = ObjectAnimator.ofFloat(rl_movie_details_stageshow,"translationY",0,1800);
                objectAnimator_stageshow.setDuration(1000);
                objectAnimator_stageshow.start();
                break;
            case R.id.btn_movie_details_stageshow:
                rl_movie_details_stageshow.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_stageshow_select = ObjectAnimator.ofFloat(rl_movie_details_stageshow,"translationY",1800,0);
                objectAnimator_stageshow_select.setDuration(1000);
                objectAnimator_stageshow_select.start();
                break;
            case R.id.iv_movie_details_buy:
                Intent intent = new Intent(((MovieDetailsActivity)context), SuitableCinemaActivity.class);
                intent.putExtra("movieId",movieId);
                intent.putExtra("movieName",movieName);
                context.startActivity(intent);
                break;
        }
    }
    public void setVieo() {
        Jzvd.releaseAllVideos();
    }
}

