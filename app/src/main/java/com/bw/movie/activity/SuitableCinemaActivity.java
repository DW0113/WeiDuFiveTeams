package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.SuitableCinemaActivityPresenter;

public class SuitableCinemaActivity extends BaseActivityPresenter<SuitableCinemaActivityPresenter> {
    @Override
    public Class<SuitableCinemaActivityPresenter> getClassDelegate() {
        return SuitableCinemaActivityPresenter.class;
    }
}
