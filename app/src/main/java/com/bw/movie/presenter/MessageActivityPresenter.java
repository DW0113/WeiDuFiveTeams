package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.MessageActivity;
import com.bw.movie.model.MessagesBean;
import com.bw.movie.model.QueryMessageBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * 作者：马利亚
 * 日期：2018/11/28
 * 内容：系统通知
 */
public class MessageActivityPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView tv_activity_message_timeyear;
    private String mMonth,mDay,mHours,mMinute;
    private TextView tv_activity_message_yeardaytime;
    private TextView tv_activity_message_unread;
    private String userId;
    private String sessionId;
    private SharedPreferences login;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化控件
        init();
        //点击事件
        setOnClick(this,R.id.iv_message_activity_img);
        setOnClick(this,R.id.ll_activity_message);
        //getSharedPreferences
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userld = login.getString("userld","");
        String sessionId = login.getString("sessionId","");
        //查询是否有新消息
        doHttpMesssage(userld,sessionId);




        Calendar calendar = Calendar.getInstance();
        //获取日期的月
        mMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        //获取日期的天
        mDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        /**
         * 如果小时是个位数
         *
         *则在前面价格“0”
         * */
        if (calendar.get(Calendar.HOUR) < 10) {
            mHours = "0" + calendar.get(Calendar.HOUR);
        } else {
            mHours = String.valueOf(calendar.get(Calendar.HOUR));
        }
        /**
         * 如果分钟是个位数
         *
         *则在前面价格“0”
         * */
        if (calendar.get(Calendar.MINUTE) < 10) {
            mMinute = "0" + calendar.get(Calendar.MINUTE);
        } else {
            mMinute = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        tv_activity_message_timeyear.setText(mMonth+"-"+mDay+"  "+mHours+":"+mMinute);
        tv_activity_message_yeardaytime.setText(mMonth+"-"+mDay+"  "+mHours+":"+mMinute);




    }

    private void doHttpMesssage(String userld,String sessionId) {
        Map<String,String> map=new HashMap<>();
        map.put("userId",userld);
        map.put("sessionId",sessionId);
        new Utility().get("movieApi/tool/v1/verify/findUnreadMessageCount",map).result(new HttpListener() {
            @Override
            public void success(String data) {
                int count = new Gson().fromJson(data, QueryMessageBean.class).getCount();
                //几条新消息
                tv_activity_message_unread.setText("("+count+"条新消息)");
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private void init() {
        tv_activity_message_unread=(TextView)get(R.id.tv_activity_message_unread);
        tv_activity_message_yeardaytime=(TextView)get(R.id.tv_activity_message_yeardaytime);
        tv_activity_message_timeyear=(TextView)get(R.id.tv_activity_message_timeyear);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_message_activity_img:
                ((MessageActivity)context).finish();
                break;
            case R.id.ll_activity_message:

                break;
        }
    }
}
