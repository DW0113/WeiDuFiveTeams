package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaShowActivity;
import com.bw.movie.model.DetailsBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaDetailsPesenter extends AppDelegate {

    private TextView mPhone,mDAddress,mCar;
    private String id;
    private TextView carRout;
    private SharedPreferences login;
    //private int id1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema_details;
    }

    //获取上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        Intent intent = ((CinemaShowActivity) context).getIntent();
        id = intent.getStringExtra("id");
        mPhone = get(R.id.tv_phone);
        mDAddress = get(R.id.tv_address);
        mCar = get(R.id.tv_car);
        carRout = get(R.id.car_route);
        detailsHttp(id);


        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);

    }

    private void detailsHttp(String id) {


        Map<String,String> mapHead = new HashMap<>();
        //String userId = login.getString("userId", "");
        //String sessionId = login.getString("sessionId", "");

        mapHead.put("userId","1406");
        mapHead.put("sessionId","15439882211661406");
        Map<String,String> map = new HashMap<>();
        map.put("cinemaId",id);
        new Utility().gethead("/movieApi/cinema/v1/findCinemaInfo",map,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                DetailsBean detailsBean = new Gson().fromJson(data, DetailsBean.class);
                //mCar.setText(detailsBean.getResult().getVehicleRoute());
                DetailsBean.ResultBean result = detailsBean.getResult();
                mDAddress.setText(result.getName());
                mPhone.setText(result.getPhone());
                carRout.setText(result.getVehicleRoute());
                //id1 = result.getId();
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
