package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.UpdatePassword;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;

import java.util.HashMap;
import java.util.Map;

/*
 * 作者：秦永聪
 *日期：2018/12/5
 * */public class UpdatePasswordPresenter extends AppDelegate implements View.OnClickListener {

    private EditText et_updatepassword_olderpassword;
    private EditText et_updatepassword_newerpassword;
    private EditText et_updatepassword_confirmpassword;
    private SharedPreferences login;
    private String userId;
    private String sessionId;
    private String et_updatepassword_olderpassword_get_encryption;
    private String et_updatepassword_newerpassword_get_encryption;
    private String et_updatepassword_confirmpassword_get_encryption;

    @Override
    public int getLayoutId() {
        return R.layout.updatepassword;
    }
    Context context;
    @Override
    public void getContext(Context context) {
            this.context=context;
    }

    @Override
    public void initData() {
        //找控件
        et_updatepassword_olderpassword = get(R.id.et_updatepassword_olderpassword);
        et_updatepassword_newerpassword = get(R.id.et_updatepassword_newerpassword);
        et_updatepassword_confirmpassword = get(R.id.et_updatepassword_confirmpassword);
       Button  b_updatepassword_submit=get(R.id.b_updatepassword_submit);
       //点击事件
        b_updatepassword_submit.setOnClickListener(this);
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = login.getString("userld", "");
        sessionId = login.getString("sessionId", "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b_updatepassword_submit:
                String  et_updatepassword_olderpassword_get = et_updatepassword_olderpassword.getText().toString().trim();
                String  et_updatepassword_newerpassword_get = et_updatepassword_newerpassword.getText().toString().trim();
                String   et_updatepassword_confirmpassword_get = et_updatepassword_confirmpassword.getText().toString().trim();
                et_updatepassword_olderpassword_get_encryption = EncryptUtil.encrypt(et_updatepassword_olderpassword_get);
                et_updatepassword_newerpassword_get_encryption = EncryptUtil.encrypt(et_updatepassword_newerpassword_get);
                et_updatepassword_confirmpassword_get_encryption = EncryptUtil.encrypt(et_updatepassword_confirmpassword_get);
                dohttp();
                break;
        }
    }

    private void dohttp() {
        Map map= new HashMap<>();
        Map m= new HashMap<>();
        m.put("userId",userId);
        m.put("sessionId",sessionId);
        map.put("oldPwd",et_updatepassword_olderpassword_get_encryption);
        map.put("newPwd",et_updatepassword_newerpassword_get_encryption);
        map.put("newPwd2",et_updatepassword_confirmpassword_get_encryption);
        new Utility().postupdate(m,"/movieApi/user/v1/verify/modifyUserPwd",map).result(new HttpListener() {
            @Override
            public void success(String data) {
                Toast.makeText(context,"修改成功",Toast.LENGTH_LONG).show();
                context.startActivity(new Intent(context, LoginActivity.class));
                ((UpdatePassword)context).finish();
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
