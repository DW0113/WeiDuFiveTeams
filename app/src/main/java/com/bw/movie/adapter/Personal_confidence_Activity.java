package com.bw.movie.adapter;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.Personal_confidence_Persenter;

/*
 * 作者：秦永聪
 *日期：2018/11/29
 * */public class Personal_confidence_Activity extends BaseActivityPresenter<Personal_confidence_Persenter> {
    @Override
    public Class<Personal_confidence_Persenter> getClassDelegate() {
        return Personal_confidence_Persenter.class;
    }
}
