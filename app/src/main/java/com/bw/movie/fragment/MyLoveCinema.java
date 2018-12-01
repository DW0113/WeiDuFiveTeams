package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.MyLoveCinemaPresenter;

/*
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class MyLoveCinema extends BaseFragmentPresenter<MyLoveCinemaPresenter> {
    @Override
    public Class<MyLoveCinemaPresenter> getClassDelegate() {
        return MyLoveCinemaPresenter.class;
    }
}
