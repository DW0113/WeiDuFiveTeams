package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.CinemaShowActivityPresenter;

public class CinemaShowActivity extends BaseActivityPresenter<CinemaShowActivityPresenter> {

    @Override
    public Class getClassDelegate() {
        return CinemaShowActivityPresenter.class;
    }

}
