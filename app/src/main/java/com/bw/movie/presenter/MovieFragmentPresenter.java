package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieTypeAdapter;
import com.bw.movie.model.MovieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者：杜威
 * 日期：2018/11/28
 */
public class MovieFragmentPresenter extends AppDelegate {
    private Context context;
    private RecyclerView rv_movie_movietype;
    private MovieTypeAdapter movieTypeAdapter;

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
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_movie_movietype.setLayoutManager(linearLayoutManager);
        movieTypeAdapter = new MovieTypeAdapter(context);
        rv_movie_movietype.setAdapter(movieTypeAdapter);
        doHttpHotMovie();
        doHttpIsHot();
        doHttpComingSoon();
    }

    //即将上映请求数据
    private void doHttpComingSoon() {
        new HttpHelper().get(Http.MOVIE_COMING_SOON).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> comingSoonList = movieBean.getResult();
                movieTypeAdapter.setcomingSoonList(comingSoonList);
            }

            @Override
            public void error(String error) {

            }
        });
    }

    //正在热映请求数据
    private void doHttpIsHot() {
        new HttpHelper().get(Http.MOVIE_ISHOT).result(new HttpHelper.Httplistenner() {
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
        new HttpHelper().get(Http.MOVIE_HOTMOIE).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> hotMovieList = movieBean.getResult();
                movieTypeAdapter.sethotMovieList(hotMovieList);
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
