package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.SuitableCinemaActivity;
import com.bw.movie.activity.TicketDetailsActivity;
import com.bw.movie.model.SuitableCinemaBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class SuitableCinemaAdapter extends RecyclerView.Adapter<SuitableCinemaAdapter.MyViewHolder> {
    private Context context;
    private List<SuitableCinemaBean.ResultBean> list = new ArrayList<>();

    public SuitableCinemaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.suitable_cinema_adapter_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.text_cinemaname = view.findViewById(R.id.tv_suitable_cinema_item_cinemaname);
        holder.text_distance = view.findViewById(R.id.tv_suitable_cinema_item_distance);
        holder.text_location = view.findViewById(R.id.tv_suitable_cinema_item_location);
        holder.image_like = view.findViewById(R.id.iv_suitable_cinema_item_like);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_suitable_cinema_item_logo);
        holder.relativeLayout = view.findViewById(R.id.rl_suitable_cinema_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_cinemaname.setText(list.get(position).getName());
        holder.text_location.setText(list.get(position).getAddress());
        holder.text_distance.setText(list.get(position).getDistance()+"km");
        holder.simpleDraweeView.setImageURI(list.get(position).getLogo());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(((SuitableCinemaActivity)context), TicketDetailsActivity.class);
                intent.putExtra("imageUrl",list.get(position).getLogo());
                intent.putExtra("cinemaName",list.get(position).getName());
                intent.putExtra("location",list.get(position).getAddress());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<SuitableCinemaBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_cinemaname,text_location,text_distance;
        ImageView image_like;
        SimpleDraweeView simpleDraweeView;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
