package com.bw.movie.mvp.basepresenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.mvp.view.AppDelegate;


/**
 *作者：刘进
 *日期：2018/10/30
 */
public abstract class BaseFragmentPresenter<T extends AppDelegate> extends Fragment {
    protected T presenter;

    public abstract Class<T> getClassDelegate();

    public BaseFragmentPresenter() {
        try {
            presenter = getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.create(inflater, container, savedInstanceState);
        return presenter.rootView();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getContext(getActivity());
        presenter.initData();
    }
}
