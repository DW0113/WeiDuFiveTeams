package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.Cinema_Recommended_Presenter;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class Cinema_Recommended extends BaseFragmentPresenter<Cinema_Recommended_Presenter> {
    @Override
    public Class<Cinema_Recommended_Presenter> getClassDelegate() {
        return Cinema_Recommended_Presenter.class;
    }
}
