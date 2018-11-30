package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.CinemaCommentPesenter;
import com.bw.movie.presenter.CinemaDetailsPesenter;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class CinemaDetails extends BaseFragmentPresenter<CinemaDetailsPesenter> {
    @Override
    public Class<CinemaDetailsPesenter> getClassDelegate() {
        return CinemaDetailsPesenter.class;
    }
}
