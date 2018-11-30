package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.CinemaCommentPesenter;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class CinemaComment extends BaseFragmentPresenter<CinemaCommentPesenter> {
    @Override
    public Class<CinemaCommentPesenter> getClassDelegate() {
        return CinemaCommentPesenter.class;
    }
}
