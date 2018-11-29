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
import com.bw.movie.model.RecommendedBean;

import java.util.ArrayList;
import java.util.List;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MyViewHolder> {
    //上下文
    private Context context;
    public RecommendedAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public RecommendedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //找到layout文件
        View view = View.inflate(context, R.layout.adapter_item_recommended,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.MyViewHolder myViewHolder, int i) {
        //赋值
        myViewHolder.cinemaName.setText(mList.get(i).getName());
        myViewHolder.cinemaAddress.setText(mList.get(i).getAddress());
        Glide.with(context).load(mList.get(i).getLogo()).into(myViewHolder.cinemaLogo);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    private List<RecommendedBean.ResultBean> mList = new ArrayList<>();
    public void setList(List<RecommendedBean.ResultBean> list) {
        this.mList = list;
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
