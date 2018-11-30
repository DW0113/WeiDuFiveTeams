package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.CinemaBean;

import java.util.ArrayList;
import java.util.List;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.MyViewHolder> {
    //上下文
    private Context context;
    public CinemaAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public CinemaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //找到layout文件
        View view = View.inflate(context, R.layout.adapter_item_cinema,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaAdapter.MyViewHolder myViewHolder, int i) {
        //赋值
        myViewHolder.cinemaName.setText(cinemaList.get(i).getName());
        myViewHolder.cinemaAddress.setText(cinemaList.get(i).getAddress());
        Glide.with(context).load(cinemaList.get(i).getLogo()).into(myViewHolder.cinemaLogo);
    }

    @Override
    public int getItemCount() {
        return cinemaList.size();
    }
    private List<CinemaBean.ResultBean.NearbyCinemaListBean> cinemaList = new ArrayList<>();
    public void setList(List<CinemaBean.ResultBean.NearbyCinemaListBean> list) {
        this.cinemaList = list;
        //刷新适配器
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaLogo;
        TextView cinemaName,cinemaAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaName = itemView.findViewById(R.id.tv_cinema_name);
            cinemaAddress = itemView.findViewById(R.id.tv_cinema_address);
            cinemaLogo = itemView.findViewById(R.id.iv_cinema_logo);
        }
    }
}
