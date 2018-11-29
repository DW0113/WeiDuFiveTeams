package com.bw.movie.fragment;


import android.support.v4.app.Fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.HotMovieFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotMovieFragment extends BaseFragmentPresenter<HotMovieFragmentPresenter> {


    @Override
    public Class getClassDelegate() {
        return HotMovieFragmentPresenter.class;
    }
}
