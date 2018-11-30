package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.Cinema_Near_Pesenter;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class Cinema_Near extends BaseFragmentPresenter<Cinema_Near_Pesenter> {
    @Override
    public Class<Cinema_Near_Pesenter> getClassDelegate() {
        return Cinema_Near_Pesenter.class;
    }
}
