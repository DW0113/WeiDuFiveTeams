package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieSearchActivity;
import com.bw.movie.adapter.IsHotAdapter;
import com.bw.movie.model.MovieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IsHotFragmentPresenter extends AppDelegate {
    private Context context;
    private ImageView iv_ishot_back;
    private XRecyclerView rv_ishot_recyclerview;
    private IsHotAdapter isHotAdapter;
    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_is_hot;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        iv_ishot_back = get(R.id.iv_ishot_back);
        rv_ishot_recyclerview = get(R.id.rv_ishot_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_ishot_recyclerview.setLayoutManager(linearLayoutManager);
        isHotAdapter = new IsHotAdapter(context);
        rv_ishot_recyclerview.setAdapter(isHotAdapter);
        //设置可上拉加载下拉刷新
        rv_ishot_recyclerview.setPullRefreshEnabled(true);
        rv_ishot_recyclerview.setLoadingMoreEnabled(true);
        rv_ishot_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                doHttpIsHot(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                doHttpIsHot(page);
            }
        });
        //返回点击事件
        iv_ishot_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MovieSearchActivity)context).finish();
            }
        });
        doHttpIsHot(1);
    }
    //正在热映请求数据
    private void doHttpIsHot(final int page) {
        new HttpHelper().get(Http.MOVIE_ISHOT+1).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> isHotList = new ArrayList<>();
                if (page == 1 ){
                    isHotList.clear();
                }
                isHotList.addAll(movieBean.getResult());
                isHotAdapter.setisHotList(isHotList);
                rv_ishot_recyclerview.loadMoreComplete();
                rv_ishot_recyclerview.refreshComplete();
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
