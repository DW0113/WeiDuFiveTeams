package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.MessageActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.mvp.view.AppDelegate;
import com.makeramen.roundedimageview.RoundedImageView;

/*
 * 作者：秦永聪
 *日期：2018/11/27
 * */
    public class MyFragmentPresenter extends AppDelegate implements View.OnClickListener {
    private ImageView iv_fragment_my_news;
    private RoundedImageView tv_fragment_my_login;

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

          //找控件
        tv_fragment_my_login = get(R.id.tv_fragment_my_login);
        tv_fragment_my_login.setOnClickListener(this);
        TextView tv_fragment_my_text= get(R.id.tv_fragment_my_text);
        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = login.getString("username", "");
        Toast.makeText(context,"姓名："+username,Toast.LENGTH_LONG).show();
        if(username.equals("")&&username==null){
            tv_fragment_my_text.setText("未登录");
            return;
        }
      else{

            tv_fragment_my_text.setText(username+"");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_fragment_my_login:
                   context.startActivity(new Intent(context, LoginActivity.class));
                break;
            case R.id.iv_fragment_my_news:
                Intent intent = new Intent(((MainActivity)context), MessageActivity.class);
                context.startActivity(intent);
                break;
        }
    }


}
