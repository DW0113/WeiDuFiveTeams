package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.bw.movie.R;
import com.bw.movie.activity.StartActivity;
import com.bw.movie.activity.WelcomeActivity;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：马利亚
 * 日期：2018/11/27
 * 内容：
 */
public class StartActivityPresnter extends AppDelegate{
    private Context context;
    private int time= 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                if(time>0){
                    time--;
                    handler.sendEmptyMessageDelayed(0,1000);
                }else{
                    Intent intent = new Intent(((StartActivity)context), WelcomeActivity.class);
                    context.startActivity(intent);
                    ((StartActivity) context).finish();
                }
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
