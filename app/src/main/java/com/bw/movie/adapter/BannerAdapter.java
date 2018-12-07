package com.bw.movie.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.BannerBean;
import com.bw.movie.model.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {
    private Context context;
    private SharedPreferences preferences;

    public BannerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.adapter_banner, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.textView = view.findViewById(R.id.tv_movie_banner_imagename);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_movie_banner_image);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.simpleDraweeView.setImageURI(list.get(position).getImageUrl());
        holder.textView.setText(list.get(position).getName());

         preferences = context.getSharedPreferences("item", Context.MODE_PRIVATE);
        int movieId = list.get(position).getId();
        lisnear.getClick(movieId);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private List<BannerBean.ResultBean> list = new ArrayList<>();
    public void setList(List<BannerBean.ResultBean> list) {
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

    private setOnClickLisnear lisnear;
    public void click(setOnClickLisnear lisnear){
        this.lisnear=lisnear;
    }

    public interface setOnClickLisnear{
        void getClick(int movieId);
    }
}
