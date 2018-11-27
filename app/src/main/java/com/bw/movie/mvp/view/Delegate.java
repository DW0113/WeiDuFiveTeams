package com.bw.movie.mvp.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：刘进
 * 日期：2018/10/30
 */
public interface Delegate {

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    void getContext(Context context);

    void initData();

    View rootView();

}
