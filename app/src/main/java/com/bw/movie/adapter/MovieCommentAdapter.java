package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.MovieCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MovieCommentAdapter extends RecyclerView.Adapter<MovieCommentAdapter.MyViewHolder> {

    private  Context context;
    private List<MovieCommentBean.ResultBean> list = new ArrayList<>();

    public MovieCommentAdapter(Context context) {
        this.context =context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.movie_comment_adapter_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.text_desc = view.findViewById(R.id.tv_movie_comment_content);
        holder.text_name =view.findViewById(R.id.tv_movie_comment_name);
        holder.text_time = view.findViewById(R.id.tv_movie_comment_time);
        holder.simpleDraweeView = view.findViewById(R.id.sdv_movie_comment);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(list.get(position).getReplyHeadPic());
        holder.text_time.setText(list.get(position).getCommentTime()+"");
        holder.text_name.setText(list.get(position).getReplyUserName());
        holder.text_desc.setText(list.get(position).getReplyContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MovieCommentBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_desc,text_name,text_time;
        SimpleDraweeView simpleDraweeView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
