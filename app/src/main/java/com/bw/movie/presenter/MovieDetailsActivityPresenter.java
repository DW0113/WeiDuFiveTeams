package com.bw.movie.presenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.MovieDetailsActivity;
import com.bw.movie.activity.SuitableCinemaActivity;
import com.bw.movie.adapter.MovieDetailsFilMrevieAdapter;
import com.bw.movie.adapter.MovieDetailsForemShowAdapter;
import com.bw.movie.adapter.MovieDetailsStageShowAdapter;
import com.bw.movie.model.MovieAttentionBean;
import com.bw.movie.model.MovieDetailsBean;
import com.bw.movie.model.MovieFilmrevieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.SimpDrawViewUtils;
import com.bw.movie.utils.Utility;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String movieName;
    private MovieDetailsFilMrevieAdapter filMrevieAdapter;
    private ImageView iv_movie_details_like;
    private MovieDetailsBean movieDetailsBean;
    private EditText et_movie_details_filmrevie;
    private TextView tv_movie_details_filmrevie;
    private Map<String, String> mapHead;
    private Map<String, String> map;
    private int count=5;

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
        movieId = intent.getIntExtra("movieId", 1);//电影ID

        //获取用户信息
        sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        userld = sp.getString("userld", "");//用户ID
        sessionId = sp.getString("sessionId", "");//用户sesssionID
        setOnClick(this,R.id.btn_movie_details_details);//详情
        setOnClick(this,R.id.btn_movie_details_filmrevie);//影评
        setOnClick(this,R.id.btn_movie_details_foreshow);//预告
        setOnClick(this,R.id.btn_movie_details_stageshow);//剧照
        setOnClick(this,R.id.iv_movie_details_back);//返回
        setOnClick(this,R.id.iv_movie_details_filmrevie_down);//影评隐藏
        setOnClick(this,R.id.iv_movie_details_details_down);//详情隐藏
        setOnClick(this,R.id.iv_movie_details_foreshow_down);//预告隐藏
        setOnClick(this,R.id.iv_movie_details_stageshow_down);//剧照隐藏
        setOnClick(this,R.id.iv_movie_details_buy);//购买
        setOnClick(this,R.id.iv_movie_details_like);//关注

        //设置预告片布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_movie_details_foreshow.setLayoutManager(linearLayoutManager);
        //设置预告片适配器
        foremShowAdapter = new MovieDetailsForemShowAdapter(context);
        rv_movie_details_foreshow.setAdapter(foremShowAdapter);

        //设置剧照布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv_movie_details_stageshow.setLayoutManager(staggeredGridLayoutManager);
        //设置剧照适配器
        stageShowAdapter = new MovieDetailsStageShowAdapter(context);
        rv_movie_details_stageshow.setAdapter(stageShowAdapter);

        //设置影评布局管理器
        LinearLayoutManager linearLayoutManager_ilmrevie = new LinearLayoutManager(context);
        xrv_movie_details_filmrevie.setLayoutManager(linearLayoutManager_ilmrevie);
        //设置影评adapter
        filMrevieAdapter = new MovieDetailsFilMrevieAdapter(context);
        xrv_movie_details_filmrevie.setAdapter(filMrevieAdapter);
        //头部信息
        mapHead = new HashMap<>();
        mapHead.put("userId",userld);
        mapHead.put("sessionId",sessionId);
        //影片评论列表请求网络数据
        doHttpFilMrevie(count);
        //请求电影详情数据
        doHttpDetails();
        //影评刷新加载事件
        xrv_movie_details_filmrevie.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 5;
                doHttpFilMrevie(count);
            }
            @Override
            public void onLoadMore() {
                count+=5;
                doHttpFilMrevie(count);
            }
        });

    }
    //影片评论列表请求网络数据
    private void doHttpFilMrevie(int count) {
        map = new HashMap<>();
        map.put("movieId",movieId+"");
        map.put("count",count+"");
        map.put("page",1+"");
        new Utility().gethead(Http.MOVIE_FILMREVIE,map,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieFilmrevieBean movieFilmrevieBean = gson.fromJson(data, MovieFilmrevieBean.class);
                List<MovieFilmrevieBean.ResultBean> resultBeanList = movieFilmrevieBean.getResult();
                filMrevieAdapter.setList(resultBeanList);
                xrv_movie_details_filmrevie.refreshComplete();
                xrv_movie_details_filmrevie.loadMoreComplete();
            }

            @Override
            public void fail(String error) {

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
        iv_movie_details_like = get(R.id.iv_movie_details_like);
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

        et_movie_details_filmrevie = get(R.id.et_movie_details_filmrevie);
        tv_movie_details_filmrevie = get(R.id.tv_movie_details_filmrevie);
    }

    //请求电影详情数据
    private void doHttpDetails(){
        Map<String,String> map = new HashMap<>();
        map.put("movieId",movieId+"");
        new Utility().gethead(Http.MOVIE_DETAILS,map,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                movieDetailsBean = gson.fromJson(data, MovieDetailsBean.class);
                setData();
                foremShowAdapter.setList(movieDetailsBean);//预告
                stageShowAdapter.setList(movieDetailsBean.getResult().getPosterList());//剧照
                movieName = movieDetailsBean.getResult().getName();
                if (movieDetailsBean.getResult().isFollowMovie()){//未关注
                    iv_movie_details_like.setImageResource(R.drawable.like);
                }else{//关注
                    iv_movie_details_like.setImageResource(R.drawable.movie_search_like_select);
                }
            }
            @Override
            public void fail(String error) {

            }
        });
    }
    //各页面赋值
    private void setData() {
        //高斯模糊
        SimpDrawViewUtils.showUrlBlur(sdv_movie_details_background, movieDetailsBean.getResult().getImageUrl(),5,5);
        sdv_movie_details_image.setImageURI(movieDetailsBean.getResult().getImageUrl());
        sdv_movie_details_details_image.setImageURI(movieDetailsBean.getResult().getImageUrl());
        tv_movie_details_moviename.setText(movieDetailsBean.getResult().getName());
        tv_movie_details_details_movie_type.setText("类型："+ movieDetailsBean.getResult().getMovieTypes());
        tv_movie_details_details_actor_name.setText(movieDetailsBean.getResult().getStarring());
        tv_movie_details_details_movie_desc.setText(movieDetailsBean.getResult().getSummary());
        tv_movie_details_details_movie_director.setText("导演："+ movieDetailsBean.getResult().getDirector());
        tv_movie_details_details_movie_duration.setText("时长："+ movieDetailsBean.getResult().getDuration());
        tv_movie_details_details_movie_placeOrigin.setText("产地："+ movieDetailsBean.getResult().getPlaceOrigin());
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回
            case R.id.iv_movie_details_back:
                ((MovieDetailsActivity)context).finish();
            break;
            //详情隐藏
            case R.id.iv_movie_details_details_down:
                ObjectAnimator objectAnimator_details = ObjectAnimator.ofFloat(rl_movie_details_details,"translationY",0,1800);
                objectAnimator_details.setDuration(1000);
                objectAnimator_details.start();
                break;
                //详情出现
            case R.id.btn_movie_details_details:
                rl_movie_details_details.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_details_select = ObjectAnimator.ofFloat(rl_movie_details_details,"translationY",1800,0);
                objectAnimator_details_select.setDuration(1000);
                objectAnimator_details_select.start();
                break;
                //影评隐藏
            case R.id.iv_movie_details_filmrevie_down:
                ObjectAnimator objectAnimator_filmrevie = ObjectAnimator.ofFloat(rl_movie_details_filmrevie,"translationY",0,1800);
                objectAnimator_filmrevie.setDuration(1000);
                objectAnimator_filmrevie.start();
                break;
                //影评出现
            case R.id.btn_movie_details_filmrevie:
                rl_movie_details_filmrevie.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_filmrevie_select = ObjectAnimator.ofFloat(rl_movie_details_filmrevie,"translationY",1800,0);
                objectAnimator_filmrevie_select.setDuration(1000);
                objectAnimator_filmrevie_select.start();
                break;
                //预告隐藏
            case R.id.iv_movie_details_foreshow_down:
                ObjectAnimator objectAnimator_foreshow = ObjectAnimator.ofFloat(rl_movie_details_foreshow,"translationY",0,1800);
                objectAnimator_foreshow.setDuration(1000);
                objectAnimator_foreshow.start();
                break;
                //预告出现
            case R.id.btn_movie_details_foreshow:
                rl_movie_details_foreshow.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_foreshow_select = ObjectAnimator.ofFloat(rl_movie_details_foreshow,"translationY",1800,0);
                objectAnimator_foreshow_select.setDuration(1000);
                objectAnimator_foreshow_select.start();
                break;
                //剧照隐藏
            case R.id.iv_movie_details_stageshow_down:
                ObjectAnimator objectAnimator_stageshow = ObjectAnimator.ofFloat(rl_movie_details_stageshow,"translationY",0,1800);
                objectAnimator_stageshow.setDuration(1000);
                objectAnimator_stageshow.start();
                break;
                //剧照点击
            case R.id.btn_movie_details_stageshow:
                rl_movie_details_stageshow.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator_stageshow_select = ObjectAnimator.ofFloat(rl_movie_details_stageshow,"translationY",1800,0);
                objectAnimator_stageshow_select.setDuration(1000);
                objectAnimator_stageshow_select.start();
                break;
                //点击购买
            case R.id.iv_movie_details_buy:
                Intent intent = new Intent(((MovieDetailsActivity)context), SuitableCinemaActivity.class);
                intent.putExtra("movieId",movieId);
                intent.putExtra("movieName",movieName);
                context.startActivity(intent);
                break;
                //关注点击
            case R.id.iv_movie_details_like:
                if (movieDetailsBean.getResult().isFollowMovie()){
                    doHttpAttention();
                    movieDetailsBean.getResult().setFollowMovie(false);
                    iv_movie_details_like.setImageResource(R.drawable.movie_search_like_select);
                }else{
                    doHttpUnFollow();
                    movieDetailsBean.getResult().setFollowMovie(true);
                    iv_movie_details_like.setImageResource(R.drawable.like);
                }
                break;

        }
    }

    //关注电影
    private void doHttpAttention() {
        Map <String,String> contentMap = new HashMap<>();
        contentMap.put("movieId",movieId+"");
        new Utility().gethead(Http.MOVIE_ATTENTION,contentMap,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieAttentionBean movieAttentionBean = gson.fromJson(data, MovieAttentionBean.class);
                Toast.makeText(context,movieAttentionBean.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String error) {

            }
        });
    }
    //取消关注
    private void doHttpUnFollow(){
        Map <String,String> contentMap = new HashMap<>();
        contentMap.put("movieId",movieId+"");
        new Utility().gethead(Http.MOVIE_UNFOLLOW,contentMap,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieAttentionBean movieAttentionBean = gson.fromJson(data, MovieAttentionBean.class);
                Toast.makeText(context,movieAttentionBean.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String error) {

            }
        });
    }
    public void setVieo() {
        Jzvd.releaseAllVideos();
    }
}

