package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.MessageActivity;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.adapter.Personal_confidence_Activity;
import com.bw.movie.mvp.view.AppDelegate;
import com.makeramen.roundedimageview.RoundedImageView;

/*
 * 作者：秦永聪
 *日期：2018/11/27
 * */
    public class MyFragmentPresenter extends AppDelegate implements View.OnClickListener {
    private ImageView iv_fragment_my_news;
    private RoundedImageView tv_fragment_my_login;
    private TextView tv_fragment_my_text;
    private String username;
    private LinearLayout ll_fragment_my_information;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }
   public Context context;
    @Override
    public void getContext(Context context) {

        this.context=context;

    }

    @Override
    public void initData() {
        //初始化控件
        iv_fragment_my_news=(ImageView)get(R.id.iv_fragment_my_news);
        iv_fragment_my_news.setOnClickListener(this);
        ll_fragment_my_information = get(R.id.ll_fragment_my_information);
        ll_fragment_my_information.setOnClickListener(this);
          //找控件
        tv_fragment_my_login = get(R.id.tv_fragment_my_login);
        tv_fragment_my_login.setOnClickListener(this);
        tv_fragment_my_text = get(R.id.tv_fragment_my_text);
        //获取登录用户名
        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        username = login.getString("nickName", "");
        //判断是否登录，来改变字体
        if(TextUtils.isEmpty(username)) {
            tv_fragment_my_text.setText("未登录");
        }
        else{
            tv_fragment_my_text.setText(username+"");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击登录
            case R.id.tv_fragment_my_login:
                Toast.makeText(context,"姓名："+username,Toast.LENGTH_LONG).show();
                //判断是否登录
                if(TextUtils.isEmpty(username)){

                    context.startActivity(new Intent(context, LoginActivity.class));
                    return;
                }

                break;
                //
            case R.id.iv_fragment_my_news:
                Intent intent = new Intent(((MainActivity)context), MessageActivity.class);
                context.startActivity(intent);
                break;
            case R.id.ll_fragment_my_information:

                context.startActivity(new Intent(context, Personal_confidence_Activity.class));
                break;
        }
    }

//返回的一个方法，用来刷新的
    public void onResume() {
        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        username = login.getString("nickName", "");
        if(TextUtils.isEmpty(username)) {
            tv_fragment_my_text.setText("未登录");
        }
        else{
            tv_fragment_my_text.setText(username+"");
        }
    }
}
