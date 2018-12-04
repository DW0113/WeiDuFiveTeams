package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaShowActivity;
import com.bw.movie.adapter.CommentsAdapter;
import com.bw.movie.fragment.CinemaComment;
import com.bw.movie.model.CommentsBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaCommentPesenter extends AppDelegate{

    private String url = "/movieApi/cinema/v1/verify/cinemaCommentGreat";
    private String urlComment = "/movieApi/cinema/v1/verify/cinemaComment";
    //评论
    private XRecyclerView mXRecyclerView;
    private CommentsAdapter adapter;
    private SharedPreferences login;
    private EditText mText;
    private String id;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cinema_comment;
    }

    //获取上下文
    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        initview();
        commentsHttp();
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);

    }


    private void initview() {
        mXRecyclerView = get(R.id.xRecyclerView);
        //初始化控件
        mText = get(R.id.et_text);
        //点击评论
        get(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //评论请求数据
                commentHttp();
            }
        });

        adapter = new CommentsAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        mXRecyclerView.setLayoutManager(manager);
        mXRecyclerView.setAdapter(adapter);

        mXRecyclerView.setPullRefreshEnabled(true);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                //上拉加载

                //关闭
                mXRecyclerView.loadMoreComplete();
            }
        });


        adapter.result(new CommentsAdapter.setOnClickLisnear() {
            @Override
            public void click(int commentId) {
                praiseHttp(commentId);
            }
        });

    }
    //评论请求数据
    private void commentHttp() {
        Map<String,String> map = new HashMap<>();
        Map<String,String> mapHead = new HashMap<>();
        String userId = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        mapHead.put("sessionId", sessionId);
        mapHead.put("userId", userId);
        map.put("cinemaId", id);
        map.put("commentContent", "哈哈");

        new Utility().give(mapHead,urlComment,map).result(new HttpListener() {
            @Override
            public void success(String data) {
                Toast.makeText(context, ""+data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    //点赞
    private void praiseHttp(int commentId) {
        //Toast.makeText(context, commentId+"", Toast.LENGTH_SHORT).show();
        Map<String,String> map = new HashMap<>();
        Map<String,String> mapHead = new HashMap<>();
        String userId = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        mapHead.put("sessionId", sessionId);
        mapHead.put("userId", userId);
        map.put("commentId", commentId+"");
        new Utility().give(mapHead, url, map).result(new HttpListener() {
            @Override
            public void success(String data) {
                //Toast.makeText(context, "d" + data, Toast.LENGTH_SHORT).show();
                if (data.contains("点赞成功")){

                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private void commentsHttp() {
        Map<String, String> map = new HashMap<>();
        map.put("sessionId", "15439064564991406");
        map.put("userId", "1406");
        map.put("page", "1");
        map.put("count", "30");
        map.put("cinemaId", "1");

        new Utility().get("/movieApi/cinema/v1/findAllCinemaComment", map).result(new HttpListener() {
            @Override
            public void success(String data) {
                CommentsBean commentsBean = new Gson().fromJson(data, CommentsBean.class);
                List<CommentsBean.ResultBean> result = commentsBean.getResult();
                adapter.setList(result);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

}
