package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.MovieFragmentPresenter;

/**
 * 作者：杜威
 * 日期：2018/11/27
 */
public class MovieFragment extends BaseFragmentPresenter<MovieFragmentPresenter> {
    @Override
    public Class<MovieFragmentPresenter> getClassDelegate() {
        return MovieFragmentPresenter.class;
    }
}
