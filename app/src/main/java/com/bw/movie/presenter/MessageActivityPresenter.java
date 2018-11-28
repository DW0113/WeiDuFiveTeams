package com.bw.movie.presenter;

import android.content.Context;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

import java.util.Calendar;

/**
 * 作者：马利亚
 * 日期：2018/11/28
 * 内容：系统通知
 */
public class MessageActivityPresenter extends AppDelegate{
    private Context context;
    private TextView tv_activity_message_timeyear;
    private String mMonth,mDay,mHours,mMinute;
    private TextView tv_activity_message_yeardaytime;

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

    private void init() {
        tv_activity_message_yeardaytime=(TextView)get(R.id.tv_activity_message_yeardaytime);
        tv_activity_message_timeyear=(TextView)get(R.id.tv_activity_message_timeyear);
    }
}
