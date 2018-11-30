package com.bw.movie.presenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaDetailsPesenter extends AppDelegate {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema_details;
    }

    //获取上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {

    }
}
