package com.bw.movie.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.activity.MovieSearchActivity;
import com.bw.movie.adapter.MovieSearchPagerAdapter;
import com.bw.movie.fragment.ComingSoonFragment;
import com.bw.movie.fragment.HotMovieFragment;
import com.bw.movie.fragment.IsHotFragment;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchActivityPresenter extends AppDelegate implements View.OnClickListener {

    private ViewPager vp_moviesearch_viewpager;
    private Button btn_moviesearch_comingsoon;
    private Button btn_moviesearch_hotmovie;
    private Button btn_moviesearch_ishot;
    private List<Fragment> fragmentList = new ArrayList<>();
    private Context context;

    @Override
    public int getLayoutId() {
        return R.layout.activity_moviesearch;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        vp_moviesearch_viewpager = get(R.id.vp_moviesearch_viewpager);
        btn_moviesearch_comingsoon = get(R.id.btn_moviesearch_comingsoon);
        btn_moviesearch_ishot = get(R.id.btn_moviesearch_ishot);
        btn_moviesearch_hotmovie = get(R.id.btn_moviesearch_hotmovie);
        //点击事件
        setOnClick(this,R.id.btn_moviesearch_comingsoon);
        setOnClick(this,R.id.btn_moviesearch_ishot);
        setOnClick(this,R.id.btn_moviesearch_hotmovie);
        //添加fragment
        fragmentList.add(new HotMovieFragment());
        fragmentList.add(new IsHotFragment());
        fragmentList.add(new ComingSoonFragment());
        MovieSearchPagerAdapter searchPagerAdapter =
                new MovieSearchPagerAdapter(((MovieSearchActivity)context).getSupportFragmentManager(),fragmentList);
        vp_moviesearch_viewpager.setAdapter(searchPagerAdapter);
        vp_moviesearch_viewpager.setOffscreenPageLimit(3);
        vp_moviesearch_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setClickBackGround(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void setClickBackGround(int i) {
        switch (i){
            case 0:
                btn_moviesearch_hotmovie.setTextColor(R.color.color_fragment_movie_FFFFFF);
                btn_moviesearch_ishot.setTextColor(R.color.color_fragment_movie_000000);
                btn_moviesearch_comingsoon.setTextColor(R.color.color_fragment_movie_000000);
                btn_moviesearch_hotmovie.setBackgroundResource(R.drawable.shape_moviesearch_button_background);
                btn_moviesearch_ishot.setBackgroundResource(R.drawable.shape_moviesearch_button_background_noselect);
                btn_moviesearch_comingsoon.setBackgroundResource(R.drawable.shape_moviesearch_button_background_noselect);
                break;
            case 1:
                btn_moviesearch_ishot.setTextColor(R.color.color_fragment_movie_FFFFFF);
                btn_moviesearch_comingsoon.setTextColor(R.color.color_fragment_movie_000000);
                btn_moviesearch_hotmovie.setTextColor(R.color.color_fragment_movie_000000);
                btn_moviesearch_ishot.setBackgroundResource(R.drawable.shape_moviesearch_button_background);
                btn_moviesearch_comingsoon.setBackgroundResource(R.drawable.shape_moviesearch_button_background_noselect);
                btn_moviesearch_hotmovie.setBackgroundResource(R.drawable.shape_moviesearch_button_background_noselect);
                break;
            case 2:
                btn_moviesearch_comingsoon.setTextColor(R.color.color_fragment_movie_FFFFFF);
                btn_moviesearch_ishot.setTextColor(R.color.color_fragment_movie_000000);
                btn_moviesearch_hotmovie.setTextColor(R.color.color_fragment_movie_000000);
                btn_moviesearch_comingsoon.setBackgroundResource(R.drawable.shape_moviesearch_button_background);
                btn_moviesearch_ishot.setBackgroundResource(R.drawable.shape_moviesearch_button_background_noselect);
                btn_moviesearch_hotmovie.setBackgroundResource(R.drawable.shape_moviesearch_button_background_noselect);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_moviesearch_comingsoon:
                vp_moviesearch_viewpager.setCurrentItem(2);
                setClickBackGround(2);
                break;
            case R.id.btn_moviesearch_ishot:
                vp_moviesearch_viewpager.setCurrentItem(1);
                setClickBackGround(1);
                break;
            case R.id.btn_moviesearch_hotmovie:
                vp_moviesearch_viewpager.setCurrentItem(0);
                setClickBackGround(0);
                break;
        }
    }
}
