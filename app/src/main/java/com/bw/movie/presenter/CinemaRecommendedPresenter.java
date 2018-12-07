package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;


import com.bw.movie.R;
import com.bw.movie.adapter.RecommendedAdapter;
import com.bw.movie.model.RecommendedBean;
import com.bw.movie.mvp.view.AppDelegate;

import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaRecommendedPresenter extends AppDelegate {

    private XRecyclerView recommendedRecyclerView;
    private RecommendedAdapter adapter;
    private String id;
    private SharedPreferences login;
    private SharedPreferences distance;

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
        distance = context.getSharedPreferences("distance", MODE_PRIVATE);
        login = context.getSharedPreferences("login", MODE_PRIVATE);
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
        String latitude = distance.getString("latitude", "");
        String altitude = distance.getString("altitude", "");
        //Toast.makeText(context, "altitude="+altitude, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, "latitude="+latitude, Toast.LENGTH_SHORT).show();
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        Map<String,String> mapHead = new HashMap<>();
        mapHead.put("userId",userld);
        mapHead.put("sessionId",sessionId);
        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("count","30");
        map.put("longitude",latitude);
        map.put("latitude",altitude);
        new Utility().gethead("movieApi/cinema/v1/findAllCinemas",map,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                RecommendedBean recommendedBean = new Gson().fromJson(data, RecommendedBean.class);
                List<RecommendedBean.ResultBean> result = recommendedBean.getResult();
                adapter.setList(result);
            }

            @Override
            public void fail(String error) {

            }
        });

    }
}
