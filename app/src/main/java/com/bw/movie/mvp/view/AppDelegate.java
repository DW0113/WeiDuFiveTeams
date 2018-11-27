package com.bw.movie.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 *作者：刘进
 *日期：2018/10/30
 **/public abstract class AppDelegate implements Delegate {
    private View view;

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        view = inflater.inflate(getLayoutId(),viewGroup,false);
    }


    @Override
    public View rootView() {
        return view;
    }
    public <T extends View> T get(int id){
        return view.findViewById(id);
    }
    public void setOnclick(View.OnClickListener listener,int...ids){
        if (ids==null){
            return;
        }
        for (int id:ids){
            get(id).setOnClickListener(listener);
        }
    }
    public abstract int getLayoutId();
}
