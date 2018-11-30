package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CinemaBannerAdapter extends RecyclerView.Adapter<CinemaBannerAdapter.MyViewHolder> {
    private Context context;
    public CinemaBannerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.ciname_adapter_banner, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.textView = view.findViewById(R.id.tv_movie_banner_imagename);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_movie_banner_image);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private List<MovieBean.ResultBean> list = new ArrayList<>();
    public void setList(List<MovieBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
