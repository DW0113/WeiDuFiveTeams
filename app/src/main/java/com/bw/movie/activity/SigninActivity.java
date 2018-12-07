package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.SigninPersenter;

/*
 * 作者：秦永聪
 *日期：2018/12/3
 * */public class SigninActivity extends BaseActivityPresenter<SigninPersenter> {
    @Override
    public Class<SigninPersenter> getClassDelegate() {
        return SigninPersenter.class;
    }
}
