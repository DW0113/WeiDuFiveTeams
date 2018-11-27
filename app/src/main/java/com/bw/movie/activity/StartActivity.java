package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.StartActivityPresnter;

public class StartActivity extends BaseActivityPresenter<StartActivityPresnter>{

    @Override
    public Class<StartActivityPresnter> getClassDelegate() {
        return StartActivityPresnter.class;
    }
}
