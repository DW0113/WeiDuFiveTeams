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
import com.bw.movie.model.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MovieTypeChildeAdapter extends RecyclerView.Adapter<MovieTypeChildeAdapter.MyViewHolder>{

    private Context context;
    private List<MovieBean.ResultBean> list = new ArrayList<>();

    public MovieTypeChildeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_adapter_child_movietype, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.imageView = view.findViewById(R.id.iv_movie_child_movieimage);
        holder.textView =view.findViewById(R.id.tv_movie_child_moviename);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.textView.setText(list.get(i).getName());
        holder.imageView.setImageURI(list.get(i).getImageUrl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.Click(list.get(i).getId(),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MovieBean.ResultBean> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick{
        void Click(int id ,int position);
    }
}
