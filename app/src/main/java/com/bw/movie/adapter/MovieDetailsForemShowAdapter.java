package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.model.MovieDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsForemShowAdapter extends RecyclerView.Adapter<MovieDetailsForemShowAdapter.MyViewHolder> {
    private Context context;
    private List<MovieDetailsBean.ResultBean.ShortFilmListBean> list = new ArrayList<>();

    public MovieDetailsForemShowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_details_forem_show_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.videoView = view.findViewById(R.id.vv_movie_details_adapter_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.videoView.setVideoPath(list.get(position).getVideoUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MovieDetailsBean.ResultBean.ShortFilmListBean> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
