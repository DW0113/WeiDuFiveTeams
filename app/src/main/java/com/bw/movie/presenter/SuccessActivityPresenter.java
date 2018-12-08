package com.bw.movie.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.SuccessActivity;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：马利亚
 * 日期：2018/11/30
 * 内容：反馈成功页面
 */
public class SuccessActivityPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private ImageView iv_success_activity_result;

    @Override
    public int getLayoutId() {
        return R.layout.activity_success;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        /**
         * 初始化控件
         * */
        iv_success_activity_result=(ImageView)get(R.id.iv_success_activity_result);
        /**
         * 点击事件
         * */
        iv_success_activity_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_success_activity_result:
                /**
                 * 销毁
                 * */
                ((SuccessActivity)context).finish();
                break;
        }
    }
}
