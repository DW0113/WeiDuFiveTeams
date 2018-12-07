package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.MovieDetailsActivityPresenter;

import cn.jzvd.Jzvd;

public class MovieDetailsActivity extends BaseActivityPresenter<MovieDetailsActivityPresenter> {
    @Override
    public Class<MovieDetailsActivityPresenter> getClassDelegate() {
        return MovieDetailsActivityPresenter.class;
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
