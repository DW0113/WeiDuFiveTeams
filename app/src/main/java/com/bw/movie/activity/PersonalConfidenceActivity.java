package com.bw.movie.activity;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.PersonalconfidencePersenter;

/*
 * 作者：秦永聪
 *日期：2018/11/29
 * */public class PersonalConfidenceActivity extends BaseActivityPresenter<PersonalconfidencePersenter> {
    @Override
    public Class<PersonalconfidencePersenter> getClassDelegate() {
        return PersonalconfidencePersenter.class;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.setData(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
}
