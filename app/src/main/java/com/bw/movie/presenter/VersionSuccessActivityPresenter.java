package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.VersionSuccessActivity;
import com.bw.movie.mvp.view.AppDelegate;


/**
 * 作者：马利亚
 * 日期：2018/12/3
 * 内容：
 */
public class VersionSuccessActivityPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SharedPreferences login;
    private TextView wv_activity_successVersion_web;
    private WebView wv_activity_successVersion_web2;
    private ImageView iv_successversion_activity_img;


    @Override
    public int getLayoutId() {
        return R.layout.activity_version_success;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化控件
        wv_activity_successVersion_web=(TextView)get(R.id.wv_activity_successVersion_web);
        iv_successversion_activity_img=(ImageView) get(R.id.iv_successversion_activity_img);
        //获取保存的数据
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String url = login.getString("url", "");
        wv_activity_successVersion_web.setText(url);

        //点击事件
        iv_successversion_activity_img.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_successversion_activity_img:
                ((VersionSuccessActivity)context).finish();
                break;
        }
    }
}
