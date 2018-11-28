package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.LoginActivityPresnter;
import com.bw.movie.presenter.MainActivityPresenter;

public class LoginActivity extends BaseActivityPresenter<LoginActivityPresnter> {

    @Override
    public Class getClassDelegate() {
        return LoginActivityPresnter.class;
    }
}
