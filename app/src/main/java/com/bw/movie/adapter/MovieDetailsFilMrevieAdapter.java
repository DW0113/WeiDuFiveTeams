package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.MovieFilmrevieBean;
import com.bw.movie.utils.ScreenUtil;
import com.bw.movie.utils.UitlsToos;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsFilMrevieAdapter extends RecyclerView.Adapter<MovieDetailsFilMrevieAdapter.MyViewHolder> {
    private Context context;
    private List<MovieFilmrevieBean.ResultBean> list = new ArrayList<>();

    public MovieDetailsFilMrevieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_details_fil_mrevie_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.image_comment = view.findViewById(R.id.iv_movie_fil_mrevie_item_comments);
        holder.image_like = view.findViewById(R.id.iv_movie_fil_mrevie_item_like);
        holder.text_comment = view.findViewById(R.id.tv_movie_fil_mrevie_item_comments);
        holder.text_desc = view.findViewById(R.id.tv_movie_fil_mrevie_item_desc);
        holder.text_like = view.findViewById(R.id.tv_movie_fil_mrevie_item_like);
        holder.text_time = view.findViewById(R.id.tv_movie_fil_mrevie_item_time);
        holder.text_username = view.findViewById(R.id.tv_movie_fil_mrevie_item_username);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_movie_fil_mrevie_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(list.get(position).getCommentHeadPic());
        holder.text_username.setText(list.get(position).getCommentUserName());
        holder.text_time.setText(list.get(position).getCommentTime()+"");
        holder.text_desc.setText(list.get(position).getCommentContent());
        holder.text_comment.setText(list.get(position).getHotComment()+"");
        holder.text_like.setText(list.get(position).getGreatNum()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MovieFilmrevieBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView text_username,text_desc,text_time,text_like,text_comment;
        ImageView image_like,image_comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
