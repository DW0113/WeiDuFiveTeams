package com.bw.movie.activity;

import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.TicketDetailsActivityPresenter;

public class TicketDetailsActivity extends BaseActivityPresenter<TicketDetailsActivityPresenter> {
    @Override
    public Class<TicketDetailsActivityPresenter> getClassDelegate() {
        return TicketDetailsActivityPresenter.class;
    }
}
