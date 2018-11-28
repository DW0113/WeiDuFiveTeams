package com.bw.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.IdeaActivityPresenter;

public class IdeaActivity extends BaseActivityPresenter<IdeaActivityPresenter> {

    @Override
    public Class<IdeaActivityPresenter> getClassDelegate() {
        return IdeaActivityPresenter.class;
    }
}
