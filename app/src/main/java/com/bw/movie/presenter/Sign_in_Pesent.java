package com.bw.movie.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.SignCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/12/3
 * */public class Sign_in_Pesent extends AppDelegate {
    private SignCalendar calendar;
    private String date;
    private int years;
    private String months;
    private Button btn_sign;
    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_in;
    }
   public Context context;
    @Override
    public void getContext(Context context) {
     this.context=context;
    }

    @Override
    public void initData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        date = formatter.format(curDate);
        //  calendar = (SignCalendar) findViewById(R.id.sc_main);
        calendar = get(R.id.sc_main);
        btn_sign = (Button) get(R.id.btn_sign);
        btn_sign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 List<String> list = new ArrayList<String>();
                 list.add("2018-12-3");
                 list.add(date);
                 calendar.setCalendarDaysBgColor(list, R.drawable.welcome_two);

                calendar.addMarks(list, 0);
            }
        });
    }
}
