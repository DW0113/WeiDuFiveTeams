package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.MyFragmentPresenter;

/*
 * 作者：秦永聪
 *日期：2018/11/27
 * */public class MyFragment extends BaseFragmentPresenter<MyFragmentPresenter> {
    @Override
    public Class<MyFragmentPresenter> getClassDelegate() {
        return MyFragmentPresenter.class;
    }


}
