package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.WelcomeActivityPresenter;

public class WelcomeActivity extends BaseActivityPresenter<WelcomeActivityPresenter>{
    @Override
    public Class getClassDelegate() {
        return WelcomeActivityPresenter.class;
    }
}
