package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.RegisterActivityPresenter;

/*
 * 作者：秦永聪
 *日期：2018/11/28
 * */public class RegisterActivity extends BaseActivityPresenter<RegisterActivityPresenter>{
    @Override
    public Class<RegisterActivityPresenter> getClassDelegate() {
        return RegisterActivityPresenter.class;
    }
}
