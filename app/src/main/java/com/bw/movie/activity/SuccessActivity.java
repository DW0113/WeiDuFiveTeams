package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.SuccessActivityPresenter;

public class SuccessActivity extends BaseActivityPresenter<SuccessActivityPresenter> {

    @Override
    public Class<SuccessActivityPresenter> getClassDelegate() {
        return SuccessActivityPresenter.class;
    }
}
