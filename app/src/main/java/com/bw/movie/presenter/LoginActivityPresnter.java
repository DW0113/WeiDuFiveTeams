package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.bw.movie.R;
import com.bw.movie.activity.StartActivity;
import com.bw.movie.activity.WelcomeActivity;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：秦永聪
 * 日期：2018/11/27
 * 内容：
 */
public class LoginActivityPresnter extends AppDelegate{

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
  private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
    }
}
