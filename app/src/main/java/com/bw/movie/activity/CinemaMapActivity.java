package com.bw.movie.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.LocationSource;
import com.bw.movie.mvp.basepresenter.BaseActivityPresenter;
import com.bw.movie.presenter.CinemaMapActivityPresenter;

public class CinemaMapActivity extends BaseActivityPresenter<CinemaMapActivityPresenter> {

    @Override
    public Class getClassDelegate() {

        return CinemaMapActivityPresenter.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate( savedInstanceState);
    }
        @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState( outState);
    }
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        presenter.activate(onLocationChangedListener);
    }
    public void onLocationChanged(AMapLocation amapLocation) {
        presenter.onLocationChanged(amapLocation);
    }
}
