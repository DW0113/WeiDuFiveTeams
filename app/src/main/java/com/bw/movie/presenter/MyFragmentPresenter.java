package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.IdeaActivity;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.MessageActivity;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MyLoveActivity;
import com.bw.movie.activity.RecordActivity;
import com.bw.movie.activity.Sign_in_Activity;
import com.bw.movie.activity.VersionSuccessActivity;
import com.bw.movie.activity.Personal_confidence_Activity;
import com.bw.movie.model.VersionBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.Map;

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
    private LinearLayout ll_fragment_my_version;
    private LinearLayout ll_fragment_my_love;
    private LinearLayout ll_fragment_my_recordd;
    private SharedPreferences login;

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
        setOnClick(this,R.id.ll_fragment_my_record);
        iv_fragment_my_news=(ImageView)get(R.id.iv_fragment_my_news);
        ll_fragment_my_version = get(R.id.ll_fragment_my_version);
        ll_fragment_my_information = get(R.id.ll_fragment_my_information);
        tv_fragment_my_login = get(R.id.tv_fragment_my_login);
        tv_fragment_my_text = get(R.id.tv_fragment_my_text);
        ll_fragment_my_love = get(R.id.ll_fragment_my_love);
        ll_fragment_my_recordd = get(R.id.ll_fragment_my_recordd);
        ll_fragment_my_information.setOnClickListener(this);
        iv_fragment_my_news.setOnClickListener(this);
        tv_fragment_my_login.setOnClickListener(this);
        ll_fragment_my_love.setOnClickListener(this);
        ll_fragment_my_recordd.setOnClickListener(this);
        ImageView iv_fragment_my_Sign_in= get(R.id.iv_fragment_my_Sign_in);
        iv_fragment_my_Sign_in.setOnClickListener(this);
        setOnClick(this,R.id.ll_fragment_my_version);
        //获取登录用户名
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
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
            case R.id.iv_fragment_my_Sign_in:
                context.startActivity(new Intent(context, Sign_in_Activity.class));
                break;
            //点击登录
            case R.id.tv_fragment_my_login:
                //判断是否登录
                if(TextUtils.isEmpty(username)){
                    context.startActivity(new Intent(context, LoginActivity.class));
                    return;
                }
                break;
            case R.id.iv_fragment_my_news:
                Intent intent = new Intent(((MainActivity)context), MessageActivity.class);
                context.startActivity(intent);
                break;
            //我的信息
            case R.id.ll_fragment_my_information:
                if(TextUtils.isEmpty(username)) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
                else{
                    context.startActivity(new Intent(context, Personal_confidence_Activity.class));
                }

                break;
            //意见反馈
            case R.id.ll_fragment_my_record:
                Intent intent1 = new Intent(((MainActivity)context), IdeaActivity.class);
                context.startActivity(intent1);
                break;
            //购票记录
            case R.id.ll_fragment_my_recordd:
                 context.startActivity(new Intent(context, RecordActivity.class));
                break;
            //我的关注
            case R.id.ll_fragment_my_love:
                context.startActivity(new Intent(context, MyLoveActivity.class));
                break;
            //查询新版本
            case R.id.ll_fragment_my_version:
                login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                String userld = login.getString("userld", "");
                String sessionId = login.getString("sessionId", "");
                doHttpVersion(userld,sessionId);
                break;
        }
    }
    //查询新版本
    private void doHttpVersion(String userld,String sessionId) {
        //创建一个map
        Map<String,String> map=new HashMap<>();
        //保存信息
        map.put("userId",userld);
        map.put("sessionId",sessionId);
        map.put("ak","0110010010000");
        //解析数据
        new Utility().get("movieApi/tool/v1/findNewVersion", map).result(new HttpListener() {
            @Override
            public void success(String data) {
                //获取对象
                VersionBean versionBean = new Gson().fromJson(data, VersionBean.class);
                //获取flag
                int flag = versionBean.getFlag();
                //保存url
                 login.edit().putString("url",versionBean.getDownloadUrl()).commit();
                //判断flag是否==1，等于则跳转页面
                if (flag == 1) {
                    Toast.makeText(context, flag+"", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(context, VersionSuccessActivity.class);
                    context.startActivity(intent2);
                } else {
                    Toast.makeText(context, "暂时没有新版本", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void fail(String error) {

            }
        });


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
