package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.MovieDetailsBean;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class MovieDetailsForemShowAdapter extends RecyclerView.Adapter<MovieDetailsForemShowAdapter.MyViewHolder> {
    private Context context;
    private MovieDetailsBean movieDetailsBean;

    public MovieDetailsForemShowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_details_forem_show_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.videoplayer = view.findViewById(R.id.videoplayer);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.videoplayer.setUp(movieDetailsBean.getResult()
                .getShortFilmList()
                .get(position)
                .getVideoUrl(),
                movieDetailsBean
                .getResult()
                .getName(),
                Jzvd.SCREEN_WINDOW_NORMAL);
       Glide.with(context).load(movieDetailsBean
       .getResult()
       .getShortFilmList()
       .get(position)
       .getImageUrl()).into(holder.videoplayer.thumbImageView);
       holder.videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return movieDetailsBean.getResult().getShortFilmList().size();
    }

    public void setList(MovieDetailsBean movieDetailsBean) {
        this.movieDetailsBean = movieDetailsBean;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        JzvdStd videoplayer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
