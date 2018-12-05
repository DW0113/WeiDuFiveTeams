package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.ChooseActivityPresenter;

/**
 *作者：刘进
 *日期：2018/12/1
 */public class ChooseActivity extends BaseActivityPresenter<ChooseActivityPresenter> {
    @Override
    public Class<ChooseActivityPresenter> getClassDelegate() {
        return ChooseActivityPresenter.class;
    }
}
