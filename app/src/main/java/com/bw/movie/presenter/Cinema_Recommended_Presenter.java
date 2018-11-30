package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;


import com.bw.movie.R;
import com.bw.movie.adapter.RecommendedAdapter;
import com.bw.movie.model.RecommendedBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpHelper;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class Cinema_Recommended_Presenter extends AppDelegate {

    private XRecyclerView recommendedRecyclerView;
    private RecommendedAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.cinema_recommended_fragment;
    }
    //上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //获取控件
        recommendedRecyclerView = get(R.id.recommendedRecyclerView);
        //推荐影院请求数据
        recommendedHttp();
        //适配器
         adapter = new RecommendedAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recommendedRecyclerView.setLayoutManager(manager);
        recommendedRecyclerView.setAdapter(adapter);
        //XrecyclerView刷新
        recommendedRecyclerView.setPullRefreshEnabled(true);
        recommendedRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recommendedRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                recommendedRecyclerView.loadMoreComplete();
            }
        });

    }
    //请求数据
    private void recommendedHttp() {
        new HttpHelper().get("http://172.17.8.100/movieApi/cinema/v1/findAllCinemas?page="+1+"&count="+30).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {

                RecommendedBean recommendedBean = new Gson().fromJson(data, RecommendedBean.class);
                List<RecommendedBean.ResultBean> result = recommendedBean.getResult();
                //Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
                adapter.setList(result);
            }

            @Override
            public void error(String error) {

            }
        });
    }



}
