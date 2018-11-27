package com.bw.movie.mvp.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *作者：刘进
 *日期：2018/10/30
 */
public abstract class AppDelegate implements Delegate {
    private View rootView;

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
    }


    @Override
    public View rootView() {
        return rootView;
    }

    //获取控件ID
    SparseArray<View> views = new SparseArray<>();
    public <T extends View> T get(int id) {
        View view = views.get(id);
        if (view == null){
            view  = rootView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    //点击事件
    public void setOnClick(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public abstract int getLayoutId();
}
