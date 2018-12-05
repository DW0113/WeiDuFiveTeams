package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.MapActivity;
import com.bw.movie.fragment.CinemaNear;
import com.bw.movie.fragment.CinemaRecommended;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：刘进
 *日期：2018/11/27
 * */
public class CinemaFragmentPresenter extends AppDelegate implements View.OnClickListener {
    private RelativeLayout show;
    private RelativeLayout hide;
    private List<Fragment> cinemaList=new ArrayList<>();
    private Button showNear,tv_cinima_color,tv_cinima_near,tv_cinima,tv_cinima_near_color;
    private ViewPager vp_cinema_viewpager;
    private ImageView cinemaMap;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
         this.context=context;
    }

    @Override
    public void initData() {

//        //搜索
        show = get(R.id.relativeLayout_show);
//
        tv_cinima_color = get(R.id.tv_cinima_color);
        tv_cinima = get(R.id.tv_cinima);
        //附近
        tv_cinima_near_color= get(R.id.tv_cinima_near_color);
        tv_cinima_near= get(R.id.tv_cinima_near);
        vp_cinema_viewpager = get(R.id.vp_cinema_viewpager);
        //获取当前位置
        cinemaMap = get(R.id.iv_cinema_position);
//        //点击事件;
        setOnClick(this,
                R.id.relativeLayout_show,
                R.id.tv_cinima_near,
                R.id.tv_cinima_color,
                R.id.tv_cinima,
                R.id.tv_cinima_near_color,
                R.id.iv_cinema_position

        );
        hide = get(R.id.relativeLayout_hide);

        cinemaList.add(new CinemaRecommended());
        cinemaList.add(new CinemaNear());
       // showNear.setOnClickListener(this);
        setClick(0);
        MyAdpater myAdpater =new MyAdpater(((MainActivity)context).getSupportFragmentManager());
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

    //改变图片

    public void setClick(int click) {
        vp_cinema_viewpager.setCurrentItem(click);
        switch (click){
            case 0:
                tv_cinima_color.setVisibility(View.VISIBLE);
                tv_cinima.setVisibility(View.GONE);
                tv_cinima_near_color.setVisibility(View.GONE);
                tv_cinima_near.setVisibility(View.VISIBLE);

                break;
            case 1:
                tv_cinima_near_color.setVisibility(View.VISIBLE);
                tv_cinima_near.setVisibility(View.GONE);
                tv_cinima_color.setVisibility(View.GONE);
                tv_cinima.setVisibility(View.VISIBLE);

                break;


        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.relativeLayout_show:
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.GONE);
                break;
            case R.id.tv_cinima_color:
                //recomMenDed.setBackground(R.drawable.shape_cinema_near);
                vp_cinema_viewpager.setCurrentItem(0);
                break;
            case R.id.tv_cinima:
                vp_cinema_viewpager.setCurrentItem(0);
                break;
            case R.id.tv_cinima_near:
                vp_cinema_viewpager.setCurrentItem(1);
                break;
            case R.id.tv_cinima_near_color:
                vp_cinema_viewpager.setCurrentItem(1);
                break;
            case R.id.iv_cinema_position:
                context.startActivity(new Intent(context, MapActivity.class));
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


