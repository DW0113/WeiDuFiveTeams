package com.bw.movie.presenter;

import android.content.Context;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

/*
 *作者：刘进
 *日期：2018/11/28
 **/public class CinemaMapActivityPresenter extends AppDelegate {
    private MapView mMapView;
    private AMap aMap;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cinema_map;
    }
    //获取上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        mMapView = get(R.id.map);
    }

//    public void onPause() {
//
//    }
//
//    public void onDestroy() {
//
//    }
//
//    public void onResume() {
//
//    }
//
//    public void onSaveInstanceState() {
//    }
}
