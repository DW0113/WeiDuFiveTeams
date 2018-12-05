package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieDetailsActivity;
import com.bw.movie.activity.MovieSearchActivity;
import com.bw.movie.model.MovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class IsHotAdapter extends RecyclerView.Adapter<IsHotAdapter.MyViewHolder> {
    private Context context;
    private List<MovieBean.ResultBean> list  = new ArrayList<>();

    public IsHotAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.ishot_adapter_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.text_desc = view.findViewById(R.id.tv_ishot_moviedesc);
        holder.text_name = view.findViewById(R.id.tv_ishot_moviename);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_ishot_movieimage);
        holder.image_like = view.findViewById(R.id.iv_ishot_like);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        holder.text_name.setText(list.get(i).getName());
        holder.text_desc.setText(list.get(i).getSummary());
        holder.simpleDraweeView.setImageURI(list.get(i).getImageUrl());
        if (list.get(i).isSelect()){
            holder.image_like.setImageResource(R.drawable.movie_search_like_select);
        }else{
            holder.image_like.setImageResource(R.drawable.movie_search_like);
        }
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(((MovieSearchActivity)context), MovieDetailsActivity.class);
                intent.putExtra("movieId",list.get(i).getId());
                context.startActivity(intent);
            }
        });
        holder.image_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isSelect()){
                    list.get(i).setSelect(false);
                }else{
                    list.get(i).setSelect(true);
                }
                notifyItemChanged(i+1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void sethotMovieList(List<MovieBean.ResultBean> hotMovieList) {
        this.list = hotMovieList;
        notifyDataSetChanged();
    }

    public void setisHotList(List<MovieBean.ResultBean> isHotList) {
        this.list = isHotList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_name,text_desc;
        SimpleDraweeView simpleDraweeView;
        ImageView image_like;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
