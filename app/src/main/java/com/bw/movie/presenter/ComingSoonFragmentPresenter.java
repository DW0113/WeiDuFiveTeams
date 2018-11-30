package com.bw.movie.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieSearchActivity;
import com.bw.movie.adapter.ComingSoonAdapter;
import com.bw.movie.model.MovieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComingSoonFragmentPresenter extends AppDelegate {
    private Context context;
    private ImageView iv_comingsoon_back;
    private XRecyclerView rv_comingsoon_recyclerview;
    private ComingSoonAdapter comingSoonAdapter;
    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_coming_soon;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
//初始化控件
        iv_comingsoon_back = get(R.id.iv_comingsoon_back);
        rv_comingsoon_recyclerview = get(R.id.rv_comingsoon_recyclerview);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_comingsoon_recyclerview.setLayoutManager(linearLayoutManager);
        //设置适配器
        comingSoonAdapter = new ComingSoonAdapter(context);
        rv_comingsoon_recyclerview.setAdapter(comingSoonAdapter);
        //设置可上拉加载下拉刷新
        rv_comingsoon_recyclerview.setPullRefreshEnabled(true);
        rv_comingsoon_recyclerview.setLoadingMoreEnabled(true);
        rv_comingsoon_recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                doHttpComingSoon(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                doHttpComingSoon(page);
            }
        });
        iv_comingsoon_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MovieSearchActivity) context).finish();
            }
        });
        doHttpComingSoon(1);
    }

    //即将上映请求数据
    private void doHttpComingSoon(final int page) {
        new HttpHelper().get(Http.MOVIE_COMING_SOON+page).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieBean movieBean = gson.fromJson(data, MovieBean.class);
                List<MovieBean.ResultBean> comingSoonList = new ArrayList<>();
                if (page == 1){
                    comingSoonList.clear();
                }
                if (movieBean.getResult().isEmpty()){
                    comingSoonList.addAll(movieBean.getResult());
                    comingSoonAdapter.setcomingSoonList(comingSoonList);
                }
                rv_comingsoon_recyclerview.loadMoreComplete();
                rv_comingsoon_recyclerview.refreshComplete();
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
