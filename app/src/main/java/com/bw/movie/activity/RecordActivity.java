package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.RecordActivity_Persenter;

/*
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class RecordActivity extends BaseActivityPresenter<RecordActivity_Persenter>{
    @Override
    public Class<RecordActivity_Persenter> getClassDelegate() {
        return RecordActivity_Persenter.class;
    }
}
