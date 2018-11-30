package com.bw.movie.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.CinemaMapActivityPresenter;

public class CinemaMapActivity extends BaseActivityPresenter<CinemaMapActivityPresenter> {

    @Override
    public Class getClassDelegate() {
        return CinemaMapActivityPresenter.class;
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        presenter.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.onDestroy();
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        presenter.onResume();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        presenter.onSaveInstanceState();
//    }

}
