package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.CinemaRecommendedPresenter;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class CinemaRecommended extends BaseFragmentPresenter<CinemaRecommendedPresenter> {
    @Override
    public Class<CinemaRecommendedPresenter> getClassDelegate() {
        return CinemaRecommendedPresenter.class;
    }
}
