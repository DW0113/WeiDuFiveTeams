package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.ChooseActivity;
import com.bw.movie.model.OrderBean;
import com.bw.movie.model.PayBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.SeatTable;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：刘进
 * 日期：2018/12/1
 **/
public class ChooseActivityPresenter extends AppDelegate implements View.OnClickListener {

    public SeatTable seatTableView;
    private TextView tv_choose_cinema_location;
    private TextView tv_choose_cinema_name;
    private TextView tv_choose_endtime;
    private TextView tv_choose_starttime;
    private TextView tv_choose_screeningHall;
    private TextView tv_choose_movie_name;
    private ImageView iv_choose_back;
    private SharedPreferences va;
    private SharedPreferences value;
    private int num;
    private SharedPreferences login;
    private IWXAPI api;

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        api = WXAPIFactory.createWXAPI(context, "wxb3852e6a6b7d9516");
        //getSharedPreferences
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        va = context.getSharedPreferences("va", Context.MODE_PRIVATE);
        value = context.getSharedPreferences("value", Context.MODE_PRIVATE);
        String mBeginTime1 = value.getString("beginTime", "");
        String mEndTime = value.getString("endTime", "");
        String mDuration = value.getString("duration", "");
        String mScreeningHall = value.getString("screeningHall", "");
        String name = va.getString("name", "");
        String address = va.getString("address", "");
        //初始化控件

        tv_choose_cinema_location = get(R.id.tv_choose_cinema_location); //影院地址
        tv_choose_cinema_name = get(R.id.tv_choose_cinema_name);//影院名字
        tv_choose_movie_name = get(R.id.tv_choose_movie_name);//电影名字
        tv_choose_endtime = get(R.id.tv_choose_endtime);//结束时间
        tv_choose_starttime = get(R.id.tv_choose_starttime);//开始时间
        tv_choose_screeningHall = get(R.id.tv_choose_screeningHall);//播放大厅
        seatTableView = (SeatTable) get(R.id.seatView);
        get(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num == 0) {
                    Toast.makeText(context, "请先选座", Toast.LENGTH_SHORT).show();
                } else {
                    doHttp();
                }

            }
        });
        //返回键点击事件
        //setOnClick(this,R.id.iv_choose_back);
        //影片详情获取数据
        Intent intent = ((ChooseActivity) context).getIntent();
        String movieName = intent.getStringExtra("movieName");
        String cinemaName = intent.getStringExtra("cinemaName");
        String location = intent.getStringExtra("location");
        String beginTime = intent.getStringExtra("beginTime");
        String endTime = intent.getStringExtra("endTime");
        String screeningHall = intent.getStringExtra("screeningHall");
        //影院详情获取数据

        //影片详情赋值
        tv_choose_cinema_location.setText(location);
        tv_choose_cinema_name.setText(cinemaName);
        tv_choose_movie_name.setText(movieName);
        tv_choose_endtime.setText(endTime);
        tv_choose_starttime.setText(beginTime);
        tv_choose_screeningHall.setText(screeningHall);
        //影院详情赋值
        tv_choose_starttime.setText(mBeginTime1);
        tv_choose_endtime.setText(mEndTime);
        tv_choose_screeningHall.setText(mScreeningHall);
        tv_choose_cinema_name.setText(name);
        tv_choose_cinema_location.setText(address);


        seatTableView.setScreenName(mScreeningHall + "荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(10);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                num++;
            }

            @Override
            public void unCheck(int row, int column) {
                num--;
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10, 15);
    }

    private void doHttp() {
        ///movieApi/movie/v1/verify/buyMovieTicket
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        int id = value.getInt("id", 0);
        String trim = userld.trim();
        String sign = trim + id + num + "movie";
        String s = stringToMD5(sign);
        Log.d("ChooseActivity", userld + "\n" + sessionId + "\n" + id + "\n" + sign + "\n" + num);
        Map map = new HashMap();
        Map mapHead = new HashMap();
        map.put("scheduleId", id);
        map.put("amount", num);
        map.put("sign", s);
        mapHead.put("userId", userld);
        mapHead.put("sessionId", sessionId);
        new Utility().postform("/movieApi/movie/v1/verify/buyMovieTicket", map, mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
//                Log.i("ChooseActivity", data);
                OrderBean orderBean = new Gson().fromJson(data, OrderBean.class);
                String message = orderBean.getMessage();
                if (message.equals("下单成功")) {
                    String orderId = orderBean.getOrderId();
                    //记得弹出选择支付方式
                    //点击微信支付时调用doHttpPay(orderId);
                    //点击支付宝是暂未开通此功能
                    doHttpPay(orderId, userld, sessionId);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private void doHttpPay(String orderId, String userld, String sessionId) {
        Map map = new HashMap();
        Map mapHead = new HashMap();
        map.put("payType", 1);
        map.put("orderId", orderId);
        mapHead.put("userId", userld);
        mapHead.put("sessionId", sessionId);
        new Utility().postform("/movieApi/movie/v1/verify/pay", map, mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                Log.i("ChooseActivity", data);
                PayBean payBean = new Gson().fromJson(data, PayBean.class);
                String message = payBean.getMessage();
                if (message.equals("支付成功")) {
                    PayReq request = new PayReq();
                    request.appId = payBean.getAppId();
                    request.partnerId = payBean.getPartnerId();
                    request.prepayId = payBean.getPrepayId();
                    request.packageValue = payBean.getPackageValue();
                    request.nonceStr = payBean.getNonceStr();
                    request.timeStamp = payBean.getTimeStamp();
                    request.sign = payBean.getSign();
                    api.sendReq(request);

                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_choose_back:
//                ((ChooseActivity)context).finish();
//                break;
        }
    }

    public String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
