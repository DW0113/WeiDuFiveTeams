package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieSearchActivity;
import com.bw.movie.adapter.HotMovieAdapter;
import com.bw.movie.model.MovieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HotMovieFragmentPresenter extends AppDelegate {
    private Context context;
    private XRecyclerView rv_hotmovie_recyclerview;
    private ImageView iv_hotmovie_back;
    private HotMovieAdapter hotMovieAdapter;
    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot_movie;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        iv_hotmovie_back = get(R.id.iv_hotmovie_back);
        rv_hotmovie_recyclerview = get(R.id.rv_hotmovie_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_hotmovie_recyclerview.setLayoutManager(linearLayoutManager);
        hotMovieAdapter = new HotMovieAdapter(context);
        rv_hotmovie_recyclerview.setAdapter(hotMovieAdapter);
        //设置可上拉加载下拉刷新
        rv_hotmovie_recyclerview.setPullRefreshEnabled(true);
        rv_hotmovie_recyclerview.setLoadingMoreEnabled(true);
        rv_hotmovie_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                doHttpHotMovie(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                doHttpHotMovie(page);
            }
        });
       //返回点击事件
        iv_hotmovie_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MovieSearchActivity)context).finish();
            }
        });
        doHttpHotMovie(1);
    }

    //热门电影请求数据
    private void doHttpHotMovie(final int page) {
        new HttpHelper().get(Http.MOVIE_HOTMOIE+page).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> hotMovieList = new ArrayList<>();
                if (page == 1){
                    hotMovieList.clear();
                }
                if(!movieBean.getResult().isEmpty()){
                    hotMovieList.addAll(movieBean.getResult());
                    hotMovieAdapter.sethotMovieList(hotMovieList);
                }
                rv_hotmovie_recyclerview.refreshComplete();
                rv_hotmovie_recyclerview.loadMoreComplete();
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
