package com.bw.movie.activity;

import android.widget.Toast;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.LoginActivityPresnter;
import com.bw.movie.presenter.MainActivityPresenter;

public class LoginActivity extends BaseActivityPresenter<LoginActivityPresnter> {

    @Override
    public Class getClassDelegate() {
        return LoginActivityPresnter.class;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(LoginActivity.this,"走了吗",Toast.LENGTH_LONG).show();

    }
}
