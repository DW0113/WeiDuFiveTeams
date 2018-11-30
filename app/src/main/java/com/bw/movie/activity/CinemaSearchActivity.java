package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.CinemaSearchActivityPresenter;
import com.bw.movie.presenter.LoginActivityPresnter;

public class CinemaSearchActivity extends BaseActivityPresenter<CinemaSearchActivityPresenter> {

    @Override
    public Class getClassDelegate() {
        return CinemaSearchActivityPresenter.class;
    }
}
