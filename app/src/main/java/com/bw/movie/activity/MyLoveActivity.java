package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.MyLoveActivity_Pesenter;

/*
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class MyLoveActivity extends BaseActivityPresenter<MyLoveActivity_Pesenter>{
    @Override
    public Class<MyLoveActivity_Pesenter> getClassDelegate() {
        return MyLoveActivity_Pesenter.class;
    }
}
