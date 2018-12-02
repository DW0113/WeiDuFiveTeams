package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.model.CinemaBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class Cinema_Near_Pesenter extends AppDelegate {

    private XRecyclerView mRecyclerView;
    private String url = "movieApi/cinema/v1/findRecommendCinemas";
    private CinemaAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.cinema_near_fragment;
    }

    //获取上下文
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //获取控件
        mRecyclerView = get(R.id.recyclerView);
        //请求数据
        doHttp();
        //适配器
        adapter = new CinemaAdapter(context);
        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        //XrecyclerView刷新
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.loadMoreComplete();
            }
        });
    }

    //请求数据
    private void doHttp() {
        new HttpHelper().get(url+"?page="+1+"&count="+10).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                //Toast.makeText(context, ""+data, Toast.LENGTH_SHORT).show();
                CinemaBean cinemaBean = new Gson().fromJson(data, CinemaBean.class);
                CinemaBean.ResultBean result = cinemaBean.getResult();
                List<CinemaBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = result.getNearbyCinemaList();
                //通过适配器把集合传过去
                adapter.setList(nearbyCinemaList);

            }

            @Override
            public void error(String error) {

            }
        });
    }
}
