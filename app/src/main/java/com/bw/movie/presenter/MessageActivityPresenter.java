package com.bw.movie.presenter;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.MessageActivity;
import com.bw.movie.adapter.MessageListAdapter;
import com.bw.movie.model.MessageListsBean;
import com.bw.movie.model.PutTokenBean;
import com.bw.movie.model.QueryMessageBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：马利亚
 * 日期：2018/11/28
 * 内容：系统通知
 */
public class MessageActivityPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView tv_activity_message_timeyear;
    private String mMonth, mDay, mHours, mMinute;
    private TextView tv_activity_message_yeardaytime;
    private TextView tv_activity_message_unread;
    private String userId;
    private String sessionId;
    private SharedPreferences login;
    private RecyclerView rv_activity_message_recycle;
    private MessageListAdapter messageListAdapter;
    private String id;
    private String status;
    private List<MessageListsBean.ResultBean> result;
    private String userld;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化控件
        init();
        //点击事件

        setOnClick(this, R.id.iv_message_activity_img);
        setOnClick(this, R.id.ll_activity_message);

        //获取getSharedPreferences
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        id = login.getString("id", "");
        userld = login.getString("userld", "");
        sessionId = login.getString("sessionId", "");



        //查询系统消息列表
        messageListAdapter = new MessageListAdapter(context);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_activity_message_recycle.setLayoutManager(linearLayoutManager);
        rv_activity_message_recycle.setAdapter(messageListAdapter);
        doHttpMesssageLists(userld, sessionId);


        messageListAdapter.result(new MessageListAdapter.HttpLister() {
            @Override
            public void success() {
                //查询是否有新消息
                doHttpMesssage(userld, sessionId);
            }
        });
    }
    //查询系统消息列表
    private void doHttpMesssageLists(String userld, String sessionId) {
        //把入参存入map集合
        Map<String, String> formmap = new HashMap<>();
        formmap.put("page", "1");
        formmap.put("count", "5");

        Map<String, String> maphead = new HashMap<>();
        maphead.put("userId", userld);
        maphead.put("sessionId", sessionId);
        //解析系统列表数据
        new Utility().gethead("movieApi/tool/v1/verify/findAllSysMsgList", formmap, maphead).result(new HttpListener() {
            @Override
            public void success(String data) {
                List<MessageListsBean.ResultBean> result = new Gson().fromJson(data, MessageListsBean.class).getResult();
                messageListAdapter.setList(result);
            }

            @Override
            public void fail(String error) {

            }
        });

    }

    // 查询未读消息数量
    private void doHttpMesssage(String userld, String sessionId) {
        //把入参存入map集合
        Map<String, String> map = new HashMap<>();
        map.put("userId", userld);
        map.put("sessionId", sessionId);
        //解析消息消息数量
        new Utility().get("movieApi/tool/v1/verify/findUnreadMessageCount", map).result(new HttpListener() {
            @Override
            public void success(String data) {
                int count = new Gson().fromJson(data, QueryMessageBean.class).getCount();
                //查询几条新消息
                tv_activity_message_unread.setText("(" + count + "条新消息)");
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private void init() {
        tv_activity_message_unread = (TextView) get(R.id.tv_activity_message_unread);
        rv_activity_message_recycle = (RecyclerView) get(R.id.rv_activity_message_recycle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_message_activity_img:
                //返回按钮
                ((MessageActivity) context).finish();
                break;
        }
    }


}
