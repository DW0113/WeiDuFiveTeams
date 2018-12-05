package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.RegisterActivity;
import com.bw.movie.activity.StartActivity;
import com.bw.movie.activity.WelcomeActivity;
import com.bw.movie.model.LoginBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;

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
   private boolean isCheckedd=true;
    private SharedPreferences register;
    private SharedPreferences login;
    private CheckBox cb_remember_the_password;
    private CheckBox cb_automatic_login;
    private String login_pwd_get;
    private String login_pwd_get1;
    private String phone;
    private boolean remember_password;
    private boolean automatic_login;
    private String login_pwd_get2;
    private String phone1;

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
        cb_remember_the_password = get(R.id.cb_remember_the_password);
        cb_automatic_login = get(R.id.cb_Automatic_login);
        cb_remember_the_password.setOnClickListener(this);
        cb_automatic_login.setOnClickListener(this);
        im_login_eye.setOnClickListener(this);
        //获得注册得到手机号与密码，直接展示
        register = context.getSharedPreferences("register", Context.MODE_PRIVATE);
        String et_register_pwd_get = register.getString("et_register_pwd_get", "");
        String et_register_phone_get = register.getString("et_register_phone_get", "");
        login_phone.setText(et_register_phone_get);
        login_pwd.setText(et_register_pwd_get);
        //存登录的值
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        remember_password = login.getBoolean("remember_password", false);
        automatic_login = login.getBoolean("automatic_login", false);
        login_pwd_get2 = login.getString("login_pwd_get", "");

        phone1 = login.getString("phone", "");
        if(remember_password){
            login_phone.setText(phone1);
            login_pwd.setText(login_pwd_get2);
            cb_remember_the_password.setChecked(true);
        }else{
            login_phone.setText(phone1);
        }
        if(TextUtils.isEmpty(login_pwd_get2)){
            Toast.makeText(context,"哈哈哈，请先登录",Toast.LENGTH_LONG).show();
            return;
        }else{
            if(automatic_login){
                cb_automatic_login.setChecked(true);
                context.startActivity(new Intent(context,MainActivity.class));
                ((LoginActivity)context).finish();

            }
        }

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
                  login_pwd_get = login_pwd.getText().toString().trim();
                  decrypt = EncryptUtil.encrypt(login_pwd_get +"");
                  dohttp();
                break;
            case R.id.im_login_eye:
                if(isCheckedd){
                    //如果选中，显示密码
                    login_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isCheckedd=false;
                }else{
                    //否则隐藏密码
                    login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isCheckedd=true;
                }
                break;

        }
    }
   //网络请求
    private void dohttp() {
        FormBody requestBody=new FormBody.Builder()
                .add("phone",login_phone_get)
                .add("pwd",decrypt)
                .build();
        new HttpHelper().post("movieApi/user/v1/login",requestBody).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Toast.makeText(context,""+data,Toast.LENGTH_LONG).show();
                LoginBean loginBean = new Gson().fromJson(data, LoginBean.class);
                if(loginBean.getStatus().equals("0000")){
                    LoginBean.ResultBean.UserInfoBean userInfo = loginBean.getResult().getUserInfo();
                   Toast.makeText(context,""+userInfo.getNickName()+""+userInfo.getPhone()+""+userInfo.getBirthday(),Toast.LENGTH_LONG).show();
                    login.edit().putString("nickName",userInfo.getNickName())
                            .putString("phone",userInfo.getPhone())
                            .putString("sex",userInfo.getSex()+"")
                            .putString("birthday",userInfo.getBirthday()+"")
                            .putString("headpic",userInfo.getHeadPic())
                            .putString("userld",loginBean.getResult().getUserId()+"")
                            .putString("sessionId",loginBean.getResult().getSessionId())
                            .putString("login_pwd_get",login_pwd_get)
                            .commit();
                    //记住密码
                       if(cb_remember_the_password.isChecked()){
                           login.edit().putBoolean("remember_password",true).commit();
                       }
                       else{
                           login.edit().putBoolean("remember_password",false).commit();
                       }
                       //自动登录
                    if(cb_automatic_login.isChecked()){
                           login.edit().putBoolean("automatic_login",true).commit();
                    }
                    else{
                        login.edit().putBoolean("automatic_login",false).commit();
                    }
                    context.startActivity(new Intent(context,MainActivity.class));
                    ((LoginActivity)context).finish();
                }
            }

            @Override
            public void error(String error) {

            }
        });


    }

    public void onResume() {
//        remember_password = login.getBoolean("remember_password", false);
//        automatic_login = login.getBoolean("automatic_login", false);
//        login_pwd_get2 = login.getString("login_pwd_get", "");
//
//        phone1 = login.getString("phone", "");
//        if(remember_password){
//            login_phone.setText(phone1);
//            login_pwd.setText(login_pwd_get2);
//            cb_remember_the_password.setChecked(true);
//        }else{
//            login_phone.setText(phone1);
//        }
//        if(automatic_login){
//            cb_automatic_login.setChecked(true);
//            context.startActivity(new Intent(context,MainActivity.class));
//            ((LoginActivity)context).finish();
//
//        }

    }
}
