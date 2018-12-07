package com.bw.movie.activity;


import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.MessageActivityPresenter;

public class MessageActivity extends BaseActivityPresenter<MessageActivityPresenter> {

    @Override
    public Class<MessageActivityPresenter> getClassDelegate() {
        return MessageActivityPresenter.class;
    }
}
