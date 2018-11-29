package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.MovieSearchActivityPresenter;

public class MovieSearchActivity extends BaseActivityPresenter<MovieSearchActivityPresenter> {
    @Override
    public Class getClassDelegate() {
        return MovieSearchActivityPresenter.class;
    }
}
