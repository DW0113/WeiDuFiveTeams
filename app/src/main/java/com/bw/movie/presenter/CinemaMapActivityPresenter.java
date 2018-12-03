package com.bw.movie.presenter;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.bw.movie.R;
import com.bw.movie.activity.CinemaMapActivity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.PermissionUtils;

/**
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaMapActivityPresenter extends AppDelegate {
    private MapView mMapView;
    private AMap aMap;
    private LocationSource.OnLocationChangedListener mListener;

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


    }
    public void onCreate(Bundle savedInstanceState) {
        mMapView = get(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.showMyLocation(true);//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        // 设置定位监听
        PermissionUtils.permission(context, new PermissionUtils.PermissionListener() {
            @Override
            public void success() {
                /**
                aMap.setLocationSource(context);*/
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
                aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种

            }
        });
    }
    public void onDestroy() {
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();

    }

    public void onResume() {
        mMapView.onResume();
    }
    public void onPause() {
        mMapView.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);

    }
    /**
    LocationSource.OnLocationChangedListener mListener;*/
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;

        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(context);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            /**
            mlocationClient.setLocationListener(context);*/
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }

    }

    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null
                &&amapLocation.getErrorCode() == 0) {
            mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            //获取城市
            String city = amapLocation.getCity();
            //获取纬度
            double latitude = amapLocation.getLatitude();
            //获取经度
            double altitude = amapLocation.getAltitude();
            //获取城市编号
            String adCode = amapLocation.getAdCode();
        } else {
            String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
            Log.e("AmapErr",errText);
        }
    }


}
