package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter  extends AppDelegate implements View.OnClickListener{
    //全局控件
    private ViewPager vp_main_viewpager;
    private List<Fragment> fragmentlist=new ArrayList<>();
    private ImageView im_move_fragment;
    private ImageView im_cinema_fragment;
    private ImageView im_my_fragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
     private Context context;
    @Override
    public void getContext(Context context) {
  this.context=context;
    }

    @Override
    public void initData() {
        //找控件
        vp_main_viewpager=(ViewPager)get(R.id.vp_main_viewpager);
        im_move_fragment = (ImageView) get(R.id.im_move_fragment);
        im_cinema_fragment = (ImageView) get(R.id.im_cinema_fragment);
        im_my_fragment = (ImageView) get(R.id.im_my_fragment);
        //把fragment放到集合里面
        fragmentlist.add(new MovieFragment());
        fragmentlist.add(new CinemaFragment());
        fragmentlist.add(new MyFragment());
        //适配器
        MyAdpater myAdpater = new MyAdpater(((MainActivity)context).getSupportFragmentManager());
        vp_main_viewpager.setAdapter(myAdpater);
        //点击事件
        im_my_fragment.setOnClickListener(this);
        im_cinema_fragment.setOnClickListener(this);
        im_move_fragment.setOnClickListener(this);
        //让他默认是0
        setClick(0);
        //滑动的时候，跟着变
        vp_main_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_move_fragment:
                setClick(0);
                break;
            case R.id.im_cinema_fragment:
                setClick(1);
                break;
            case R.id.im_my_fragment:
                setClick(2);
                break;

        }
    }
  //改变图片
    public void setClick(int click) {
        vp_main_viewpager.setCurrentItem(click);
        switch (click){
            case 0:
                im_move_fragment.setImageResource(R.drawable.fragment_movie_selected);
                im_cinema_fragment.setImageResource(R.drawable.fragment_cinema);
                im_my_fragment.setImageResource(R.drawable.fragment_my);
                break;
            case 1:
                im_move_fragment.setImageResource(R.drawable.fragment_movie);
                im_cinema_fragment.setImageResource(R.drawable.fragment_cinema_selected);
                im_my_fragment.setImageResource(R.drawable.fragment_my);
                break;
            case 2:
                im_move_fragment.setImageResource(R.drawable.fragment_movie);
                im_cinema_fragment.setImageResource(R.drawable.fragment_cinema);
                im_my_fragment.setImageResource(R.drawable.fragment_my_selected);

                break;
        }

    }

//适配器
    class MyAdpater extends FragmentPagerAdapter {

        public MyAdpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentlist.get(i);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }

}
