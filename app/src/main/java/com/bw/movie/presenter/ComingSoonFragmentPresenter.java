package com.bw.movie.presenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

public class ComingSoonFragmentPresenter extends AppDelegate {
    private Context context;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coming_soon;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {

    }
}
