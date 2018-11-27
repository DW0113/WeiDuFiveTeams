package com.bw.movie.presenter;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：马利亚
 * 日期：2018/11/27
 * 内容：
 */
public class StartActivityPresnter extends AppDelegate{
    private Context context;
    private ViewPager vp_start_viewpager;
    private List<Integer> list=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化控件
        vp_start_viewpager=(ViewPager)get(R.id.vp_start_viewpager);
        //创建存储图片的集合
        list=new ArrayList<>();
        //把图片添加到集合
        list.add(R.drawable.welcome_one_hdpi);
        list.add(R.drawable.welcome_two_hdpi);
        list.add(R.drawable.welcome_three_hdpi);
        list.add(R.drawable.welcome_four_hdpi);
        //创建适配器

    }
}
