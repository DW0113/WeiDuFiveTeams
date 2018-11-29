package com.bw.movie.fragment;


import android.support.v4.app.Fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.ComingSoonFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComingSoonFragment extends BaseFragmentPresenter<ComingSoonFragmentPresenter> {


    @Override
    public Class<ComingSoonFragmentPresenter> getClassDelegate() {
        return ComingSoonFragmentPresenter.class;
    }
}
