package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.MessageActivity;
import com.bw.movie.mvp.view.AppDelegate;

/*
 * 作者：秦永聪
 *日期：2018/11/27
 * */
    public class MyFragmentPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private ImageView iv_fragment_my_news;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化控件
        iv_fragment_my_news=(ImageView)get(R.id.iv_fragment_my_news);
        iv_fragment_my_news.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_fragment_my_news:
                Intent intent = new Intent(((MainActivity)context), MessageActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
