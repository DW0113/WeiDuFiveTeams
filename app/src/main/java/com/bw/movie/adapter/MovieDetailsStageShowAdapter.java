package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.utils.ScreenUtil;
import com.bw.movie.utils.UitlsToos;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.android.tpush.horse.Tools;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsStageShowAdapter extends RecyclerView.Adapter<MovieDetailsStageShowAdapter.MyViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();

    public MovieDetailsStageShowAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_details_stage_show_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_movie_stage_show_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.simpleDraweeView.setImageURI(list.get(position));
        UitlsToos.setControllerListener(holder.simpleDraweeView,list.get(position),800);
        ScreenUtil.getScreenWidth(context);     }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
