package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.model.CinemaBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaCommentPesenter extends AppDelegate {



    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema_comment;
    }

    //获取上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {

    }
}
