package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.Personal_confidence_Activity;
import com.bw.movie.mvp.view.AppDelegate;

/*
 * 作者：秦永聪
 *日期：2018/11/29
 * */public class Personal_confidence_Persenter extends AppDelegate {
    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_confidence;
    }
    Context context;
    @Override
    public void getContext(Context context) {
    this.context=context;
    }

    @Override
    public void initData() {
        //存值
        final SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
      //找控件
        RelativeLayout tv_login_exit=get(R.id.tv_login_exit);
        //点击事件
        tv_login_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 login.edit().putString("phone","")
                         .putString("pwd","")
                         .putString("sex","")
                         .putString("email","")
                         .putString("birthday","")
                         .putString("nickName","").commit();
                Toast.makeText(context, "注销成功", Toast.LENGTH_SHORT).show();
                ((Personal_confidence_Activity)context).finish();

            }
        });
    }
}
