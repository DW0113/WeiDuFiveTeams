package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.CinemaFragmentPresenter;

/*
 * 作者：秦永聪
 *日期：2018/11/27
 * */public class CinemaFragment extends BaseFragmentPresenter<CinemaFragmentPresenter> {
    @Override
    public Class<CinemaFragmentPresenter> getClassDelegate() {
        return CinemaFragmentPresenter.class;
    }

}
