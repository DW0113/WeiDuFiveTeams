package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.model.MyLove;

import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/12/1
 * */public class MyLoveMoveAdpater extends RecyclerView.Adapter<MyLoveMoveAdpater.MyViewhodel> {
    private Context context;
    private List<MyLove.ResultBean.MovieListBean> moveList;

    public MyLoveMoveAdpater(Context context, List<MyLove.ResultBean.MovieListBean> moveList) {
        this.context = context;
        this.moveList = moveList;
    }

    @NonNull
    @Override
    public MyViewhodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhodel myViewhodel, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewhodel extends RecyclerView.ViewHolder {
        public MyViewhodel(@NonNull View itemView) {
            super(itemView);
        }
    }
}
