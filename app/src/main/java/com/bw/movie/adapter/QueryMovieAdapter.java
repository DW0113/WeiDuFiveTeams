package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.ChooseActivity;
import com.bw.movie.model.MovieAndCinemaBean;

import java.util.ArrayList;
import java.util.List;

public class QueryMovieAdapter extends RecyclerView.Adapter<QueryMovieAdapter.MyViewHolder> {

    private Context context;

    public QueryMovieAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.adapter_query, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stopTime.setText(list.get(position).getEndTime());
        holder.startTime.setText(list.get(position).getBeginTime());

        holder.price.setText(30 + position + "");


        holder.num.setText(list.get(position).getScreeningHall());
        SharedPreferences value = context.getSharedPreferences("value", Context.MODE_PRIVATE);

        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beginTime = list.get(position).getBeginTime();
                String endTime = list.get(position).getEndTime();
                //时间
                String duration = list.get(position).getDuration();
                String screeningHall = list.get(position).getScreeningHall();
                int id = list.get(position).getId();

                value.edit().putString("beginTime", beginTime)
                        .putString("endTime", endTime)
                        .putString("duration", duration)
                        .putString("screeningHall", screeningHall)
                        .putInt("id", id)
                        .commit();
                context.startActivity(new Intent(context, ChooseActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private List<MovieAndCinemaBean.ResultBean> list = new ArrayList<>();

    public void setList(List<MovieAndCinemaBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView jump;
        TextView startTime, stopTime, price, num;
        RelativeLayout rela;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            startTime = itemView.findViewById(R.id.starttime);
            stopTime = itemView.findViewById(R.id.stoptime);
            price = itemView.findViewById(R.id.tv_price);
            num = itemView.findViewById(R.id.activity_time);
            jump = itemView.findViewById(R.id.iv_jump);
            rela = itemView.findViewById(R.id.rela);

        }
    }
}
