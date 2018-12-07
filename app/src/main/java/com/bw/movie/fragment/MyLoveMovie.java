package com.bw.movie.fragment;

import com.bw.movie.mvp.basepresenter.BaseFragmentPresenter;
import com.bw.movie.presenter.MyLoveMovieePresenter;

/**
 * 内容：我关注的电影
 * 作者：秦永聪
 *日期：2018/11/30
 **/public class MyLoveMovie extends BaseFragmentPresenter<MyLoveMovieePresenter>{
    @Override
    public Class<MyLoveMovieePresenter> getClassDelegate() {
        return MyLoveMovieePresenter.class;
    }
}
