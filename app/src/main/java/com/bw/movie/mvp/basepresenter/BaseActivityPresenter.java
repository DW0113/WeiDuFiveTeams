package com.bw.movie.mvp.basepresenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.UltimateBar;


/**
 * 作者：刘进
 * 日期：2018/10/30
 */
public abstract class BaseActivityPresenter<T extends AppDelegate> extends AppCompatActivity {
    protected T presenter;

    public abstract Class<T> getClassDelegate();

    public BaseActivityPresenter() {
        try {
            presenter = getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.create(getLayoutInflater(), null, savedInstanceState);
        UltimateBar.newImmersionBuilder().applyNav(false).build(this).apply();
        setContentView(presenter.rootView());
        //initViews();
        presenter.getContext(this);
        presenter.initData();
    }

}
