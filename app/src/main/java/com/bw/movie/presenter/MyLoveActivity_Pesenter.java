package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.View;
import android.widget.Button;

import com.bw.movie.R;

import com.bw.movie.activity.MyLoveActivity;

import com.bw.movie.fragment.MyLoveCinema;
import com.bw.movie.fragment.MyLoveMovie;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class MyLoveActivity_Pesenter extends AppDelegate implements View.OnClickListener {

    private List<Fragment> cinemaList = new ArrayList<>();
    private ViewPager vp_cinema_viewpager;
    private Button tv_move_love;
    private Button tv_cinima_love;
    private Button tv_cinima_love_color;
    private Button tv_move__love_color;


    @Override
    public int getLayoutId() {
        return R.layout.activity_mylove_pesenter;
    }

    Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //找控件
        tv_move_love = get(R.id.tv_move_love);
        tv_cinima_love = get(R.id.tv_cinima_love);
        tv_cinima_love_color = get(R.id.tv_cinima_love_color);
        tv_move__love_color = get(R.id.tv_move__love_color);
        vp_cinema_viewpager = get(R.id.vp_viewpager_love);
        tv_cinima_love.setOnClickListener(this);
        tv_move_love.setOnClickListener(this);
        tv_cinima_love_color.setOnClickListener(this);
        tv_move__love_color.setOnClickListener(this);
        cinemaList.add(new MyLoveMovie());
        cinemaList.add(new MyLoveCinema());
        setClick(0);
        // showNear.setOnClickListener(this);
        MyAdpater myAdpater = new MyAdpater(((MyLoveActivity) context).getSupportFragmentManager());
        vp_cinema_viewpager.setAdapter(myAdpater);
        vp_cinema_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setClick(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_move_love:
                vp_cinema_viewpager.setCurrentItem(0);
                break;
            case R.id.tv_move__love_color:
                vp_cinema_viewpager.setCurrentItem(0);
                break;
            case R.id.tv_cinima_love:
                vp_cinema_viewpager.setCurrentItem(1);
                break;
            case R.id.tv_cinima_love_color:
                vp_cinema_viewpager.setCurrentItem(1);
                break;
        }
    }
    public void setClick(int click) {
        vp_cinema_viewpager.setCurrentItem(click);
        switch (click){
            case 0:
                tv_move__love_color.setVisibility(View.VISIBLE);
                tv_move_love.setVisibility(View.GONE);
                tv_cinima_love_color.setVisibility(View.GONE);
                tv_cinima_love.setVisibility(View.VISIBLE);

                break;
            case 1:
                tv_move__love_color.setVisibility(View.GONE);
                tv_move_love.setVisibility(View.VISIBLE);
                tv_cinima_love_color.setVisibility(View.VISIBLE);
                tv_cinima_love.setVisibility(View.GONE);


                break;


        }

    }

    class MyAdpater extends FragmentPagerAdapter {

        public MyAdpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return cinemaList.get(i);
        }

        @Override
        public int getCount() {
            return cinemaList.size();
        }
    }






}
