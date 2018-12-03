package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.MovieDetailsActivity;
import com.bw.movie.adapter.MovieBannerAdapter;
import com.bw.movie.adapter.MovieTypeAdapter;
import com.bw.movie.model.MovieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;

import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * 作者：杜威
 * 日期：2018/11/28
 */
public class MovieFragmentPresenter extends AppDelegate {
    private Context context;
    private RecyclerView rv_movie_movietype;
    private MovieTypeAdapter movieTypeAdapter;
    private RecyclerCoverFlow rc_movie_banner;
    private MovieBannerAdapter bannerAdapter;
    private RelativeLayout rl_movie_progress;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        rv_movie_movietype = get(R.id.rv_movie_movietype);
        rc_movie_banner = get(R.id.rc_movie_banner);
        rl_movie_progress = get(R.id.rl_movie_progress);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_movie_movietype.setLayoutManager(linearLayoutManager);
        //设置adapter
        movieTypeAdapter = new MovieTypeAdapter(context);
        rv_movie_movietype.setAdapter(movieTypeAdapter);
        //设置轮播adapter
        bannerAdapter = new MovieBannerAdapter(context);
        rc_movie_banner.setAdapter(bannerAdapter);
        //热门电影请求数据
        doHttpHotMovie();
        //正在热映请求数据
        doHttpIsHot();
        //即将上映请求数据
        doHttpComingSoon();
        //进入电影详情页面
        movieTypeAdapter.setOnClick(new MovieTypeAdapter.OnClick() {
            @Override
            public void Click(int id) {
                Intent intent = new Intent(((MainActivity)context), MovieDetailsActivity.class);
                intent.putExtra("movieId",id);
                context.startActivity(intent);
            }
        });
    }

    //即将上映请求数据
    private void doHttpComingSoon() {
        new HttpHelper().get(Http.MOVIE_COMING_SOON+1).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> comingSoonList = movieBean.getResult();
                movieTypeAdapter.setcomingSoonList(comingSoonList);
                rl_movie_progress.setVisibility(View.GONE);
            }

            @Override
            public void error(String error) {

            }
        });
    }

    //正在热映请求数据
    private void doHttpIsHot() {
        new HttpHelper().get(Http.MOVIE_ISHOT+1).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> isHotList = movieBean.getResult();
                movieTypeAdapter.setisHotList(isHotList);
            }

            @Override
            public void error(String error) {

            }
        });
    }

    //热门电影请求数据
    private void doHttpHotMovie() {
        new HttpHelper().get(Http.MOVIE_HOTMOIE+1).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> hotMovieList = movieBean.getResult();
                movieTypeAdapter.sethotMovieList(hotMovieList);
                bannerAdapter.setList(hotMovieList);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
