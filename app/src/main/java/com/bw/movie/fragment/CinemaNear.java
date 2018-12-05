package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.CinemaNearPesenter;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class CinemaNear extends BaseFragmentPresenter<CinemaNearPesenter> {
    @Override
    public Class<CinemaNearPesenter> getClassDelegate() {
        return CinemaNearPesenter.class;
    }
}
