package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.MovieDetailsActivityPresenter;

public class MovieDetailsActivity extends BaseActivityPresenter<MovieDetailsActivityPresenter> {
    @Override
    public Class<MovieDetailsActivityPresenter> getClassDelegate() {
        return MovieDetailsActivityPresenter.class;
    }
}
