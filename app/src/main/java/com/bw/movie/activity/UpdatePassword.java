package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.UpdatePasswordPresenter;

/*
 * 作者：秦永聪
 *日期：2018/12/5
 * */public class UpdatePassword extends BaseActivityPresenter<UpdatePasswordPresenter> {
    @Override
    public Class<UpdatePasswordPresenter> getClassDelegate() {
        return UpdatePasswordPresenter.class;
    }
}
