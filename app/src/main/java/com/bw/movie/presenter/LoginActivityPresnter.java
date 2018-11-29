package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.RegisterActivity;
import com.bw.movie.activity.StartActivity;
import com.bw.movie.activity.WelcomeActivity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.HttpHelper;

import okhttp3.FormBody;
import okhttp3.RequestBody;


/**
 * 作者：秦永聪
 * 日期：2018/11/27
 * 内容：
 */
public class LoginActivityPresnter extends AppDelegate implements View.OnClickListener {

    private EditText login_phone;
    private EditText login_pwd;
    private String login_phone_get;
    private String decrypt;
   private boolean isChecked=true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
  private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //获取输入的东西和点击事件
        TextView tv_login_register= get(R.id.tv_login_register);
        tv_login_register.setOnClickListener(this);
        RelativeLayout tv_login= get(R.id.tv_login);
        tv_login.setOnClickListener(this);
        login_phone = get(R.id.login_phone);
        login_pwd = get(R.id.login_pwd);
        ImageView im_login_eye= get(R.id.im_login_eye);
        im_login_eye.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login_register:
                //点击注册，然后就跳转到注册页面
                context.startActivity(new Intent(context, RegisterActivity.class));
                break;
             case R.id.tv_login:
                 //点击登录的时候，
                 login_phone_get = login_phone.getText().toString().trim();
                 String login_pwd_get = login_pwd.getText().toString().trim();
                  decrypt = EncryptUtil.encrypt(login_pwd_get+"");
                  dohttp();
                break;
            case R.id.im_login_eye:
                if(isChecked){
                    //如果选中，显示密码
                    login_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }


                break;
        }
    }

    private void dohttp() {
        FormBody requestBody=new FormBody.Builder()
                .add("phone",login_phone_get)
                .add("pwd",decrypt)
                .build();
        new HttpHelper().post("http://mobile.bwstudent.com/movieApi/user/v1/login",requestBody).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Toast.makeText(context,"成功吗？"+data,Toast.LENGTH_LONG).show();

            }

            @Override
            public void error(String error) {

            }
        });


    }
}
