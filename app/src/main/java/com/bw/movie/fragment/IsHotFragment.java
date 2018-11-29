package com.bw.movie.fragment;


import android.support.v4.app.Fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.IsHotFragmentPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class IsHotFragment extends BaseFragmentPresenter<IsHotFragmentPresenter> {

    @Override
    public Class<IsHotFragmentPresenter> getClassDelegate() {
        return IsHotFragmentPresenter.class;
    }
}
