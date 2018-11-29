package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.RegisterActivity;
import com.bw.movie.model.RegisteredBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/*
 * 作者：秦永聪
 *日期：2018/11/28
 * */public class RegisterActivityPresenter extends AppDelegate implements View.OnClickListener {

    private EditText et_register_name;
    private EditText et_register_birthday;
    private EditText et_register_email;
    private EditText et_register_phone;
    private EditText et_register_pwd;
    private EditText et_register_sex;
    private String et_register_email_get;
    private String et_register_birthday_get;
    private String et_register_name_get;
    private String et_register_phone_get;
    private String et_register_pwd_get;
    private String et_register_sex_get;
    private String decrypt;
    private SharedPreferences register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }
    //获取上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        et_register_name = get(R.id.et_register_name);
        et_register_birthday = get(R.id.et_register_birthday);
        et_register_email = get(R.id.et_register_email);
        et_register_phone = get(R.id.et_register_phone);
        et_register_pwd = get(R.id.et_register_pwd);
        et_register_sex = get(R.id.et_register_sex);
        RelativeLayout rl_register= get(R.id.rl_register);
        rl_register.setOnClickListener(this);
        register = context.getSharedPreferences("register", Context.MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_register:

                et_register_email_get = et_register_email.getText().toString().trim();
                et_register_birthday_get = et_register_birthday.getText().toString().trim();
                et_register_name_get = et_register_name.getText().toString().trim();
                et_register_phone_get = et_register_phone.getText().toString().trim();
                et_register_pwd_get = et_register_pwd.getText().toString().trim();
                et_register_sex_get = et_register_sex.getText().toString().trim();
                 decrypt = EncryptUtil.encrypt(et_register_pwd_get);
                //Toast.makeText(context,"成功吗？"+et_register_sex,Toast.LENGTH_LONG).show();
                dohttp();
                break;
        }
    }

        private void dohttp() {
        FormBody requestBody=new FormBody.Builder()
                .add("birthday",et_register_birthday_get)
                .add("email",et_register_email_get)
                .add("nickName",et_register_name_get)
                .add("phone",et_register_phone_get)
                .add("pwd",decrypt)
                .add("sex",et_register_sex_get+"")
                .add("pwd2",decrypt)
                .build();
               new HttpHelper().post("http://mobile.bwstudent.com/movieApi/user/v1/registerUser",requestBody).result(new HttpHelper.Httplistenner() {
                    @Override
                    public void success(String data) {

                        RegisteredBean registeredBean = new Gson().fromJson(data, RegisteredBean.class);
                        Toast.makeText(context,""+data,Toast.LENGTH_LONG).show();
                        if(registeredBean.getStatus().equals("0000")){
                            register.edit().putString("et_register_phone_get",et_register_phone_get).putString("et_register_pwd_get",et_register_pwd_get).commit();
                            context.startActivity(new Intent(context, LoginActivity.class));
                            ((RegisterActivity)context).finish();
                        }
                    }

                    @Override
                    public void error(String error) {

                    }
        });

    }
}
