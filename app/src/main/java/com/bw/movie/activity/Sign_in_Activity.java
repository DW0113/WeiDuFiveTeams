package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.Sign_in_Pesent;

/*
 * 作者：秦永聪
 *日期：2018/12/3
 * */public class Sign_in_Activity extends BaseActivityPresenter<Sign_in_Pesent> {
    @Override
    public Class<Sign_in_Pesent> getClassDelegate() {
        return Sign_in_Pesent.class;
    }
}
