package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.VersionSuccessActivityPresenter;

public class VersionSuccessActivity extends BaseActivityPresenter<VersionSuccessActivityPresenter>{

    @Override
    public Class<VersionSuccessActivityPresenter> getClassDelegate() {
        return VersionSuccessActivityPresenter.class;
    }
}
