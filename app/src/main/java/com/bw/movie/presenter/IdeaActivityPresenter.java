package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.IdeaActivity;
import com.bw.movie.activity.SuccessActivity;
import com.bw.movie.model.IdeaBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;


/**
 * 作者：马利亚
 * 日期：2018/11/28
 * 内容：
 */
public class IdeaActivityPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private EditText ed_activity_idea_edtxt;
    private Button bt_idea_activity_btn;
    private SharedPreferences login;


    @Override
    public int getLayoutId() {
        return R.layout.activity_idea;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化
        init();
        //点击事件
        setOnClick(this,R.id.bt_idea_activity_btn);
        setOnClick(this,R.id.iv_idea_activity_img);

    }


    private void doHttpIdea(String userld, String sessionId,String content){
        Map<String,String> formmap = new HashMap<>();
        formmap.put("content",content);
        Map<String,String> maphead = new HashMap<>();
        maphead.put("userId",userld);
        maphead.put("sessionId",sessionId);

        new Utility().postform("movieApi/tool/v1/verify/recordFeedBack",formmap,maphead).result(new HttpListener() {
            @Override
            public void success(String data) {
                IdeaBean ideaBean = new Gson().fromJson(data, IdeaBean.class);
                String message = ideaBean.getStatus();
                if (message.equals("0000")){
                    Intent intent = new Intent(((IdeaActivity)context), SuccessActivity.class);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context,message+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void fail(String error) {
                Toast.makeText(context,error,Toast.LENGTH_LONG).show();


            }
        });

    }

    private void init() {
        ed_activity_idea_edtxt=(EditText)get(R.id.ed_activity_idea_edtxt);
        bt_idea_activity_btn=(Button)get(R.id.bt_idea_activity_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_idea_activity_img:
                ((IdeaActivity)context).finish();
                break;
            case R.id.bt_idea_activity_btn:
                //获取输入框内的字
                String content= ed_activity_idea_edtxt.getText().toString().trim();
                //判断是否为空
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(context,"您还没有输入哦",Toast.LENGTH_LONG).show();

                }else {
                    login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
                    String userld = login.getString("userld", "");
                    String sessionId = login.getString("sessionId", "");
                    doHttpIdea(userld, sessionId, content);
                }
                break;
        }
    }
}
