package com.bw.movie.presenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：马利亚
 * 日期：2018/11/28
 * 内容：
 */
public class IdeaActivityPresenter extends AppDelegate{
    private Context context;
    @Override
    public int getLayoutId() {
        return R.layout.activity_idea;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {

    }
}
