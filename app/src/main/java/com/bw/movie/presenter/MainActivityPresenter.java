package com.bw.movie.presenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

public class MainActivityPresenter  extends AppDelegate{
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void getContext(Context context) {

    }

    @Override
    public void initData() {
           //秦永聪
    }
}
