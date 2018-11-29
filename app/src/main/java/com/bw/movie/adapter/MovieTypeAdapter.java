package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.MovieBean;

import java.util.ArrayList;
import java.util.List;

public class MovieTypeAdapter extends RecyclerView.Adapter<MovieTypeAdapter.MyViewHolder> {

    private List<MovieBean.ResultBean> hotMovieList =  new ArrayList<>();
    private List<MovieBean.ResultBean> isHotList =  new ArrayList<>();
    private List<MovieBean.ResultBean> comingSoonList =  new ArrayList<>();
    private String [] movieType = {
            "热门电影",
            "正在热映",
            "即将上映"
    };
    private Context context;
    private MovieTypeChildeAdapter adapter;

    public MovieTypeAdapter(Context context) {
        this.context = context;
    }

    public void sethotMovieList(List<MovieBean.ResultBean> hotMovieList) {
        this.hotMovieList = hotMovieList;
        notifyDataSetChanged();
    }

    public void setisHotList(List<MovieBean.ResultBean> isHotList) {
        this.isHotList = isHotList;
    }

    public void setcomingSoonList(List<MovieBean.ResultBean> comingSoonList) {
        this.comingSoonList = comingSoonList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_adapter_item_movietype, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.movie_type = view.findViewById(R.id.tv_movie_item_type);
        holder.recyclerView = view.findViewById(R.id.rv_movie_item_movietype);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movie_type.setText(movieType[position]);
        setMovieChildAdapter(holder.recyclerView,position,context);
    }

    private void setMovieChildAdapter(RecyclerView recyclerView, int position, Context context) {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MovieTypeChildeAdapter(context);
        recyclerView.setAdapter(adapter);
        switch (position){
            case 0:
                adapter.setList(hotMovieList);
                break;
            case 1:
                adapter.setList(isHotList);
                break;
            case 2:
                adapter.setList(comingSoonList);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movieType.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movie_type;
        RecyclerView recyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}