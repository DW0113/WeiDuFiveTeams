package com.bw.movie.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchActivityPresenter extends AppDelegate {

    private ViewPager vp_moviesearch_viewpager;
    private Button btn_moviesearch_comingsoon;
    private Button btn_moviesearch_hotmovie;
    private Button btn_moviesearch_ishot;
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_moviesearch;
    }

    @Override
    public void getContext(Context context) {
        //初始化控件
        vp_moviesearch_viewpager = get(R.id.vp_moviesearch_viewpager);
        btn_moviesearch_comingsoon = get(R.id.btn_moviesearch_comingsoon);
        btn_moviesearch_ishot = get(R.id.btn_moviesearch_ishot);
        btn_moviesearch_hotmovie = get(R.id.btn_moviesearch_hotmovie);
    }

    @Override
    public void initData() {

    }
}
