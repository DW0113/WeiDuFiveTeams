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
import com.bw.movie.model.CommentsBean;
import com.bw.movie.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    private Context context;
    public CommentsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.comments_adapter, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Glide.with(context).load(list.get(position).getCommentHeadPic());
        holder.mImage.setImageURI(list.get(position).getCommentHeadPic());
        holder.mName.setText(list.get(position).getCommentUserName());
        long commentTime = list.get(position).getCommentTime();
        String format = DateUtils.format(commentTime, "MM-dd HH:mm");
        holder.mData.setText(format);
        holder.mComments.setText(list.get(position).getCommentContent());

        holder.mPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int commentId = list.get(position).getCommentId();
                lisnear.click(commentId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private List<CommentsBean.ResultBean> list = new ArrayList<>();
    public void setList(List<CommentsBean.ResultBean> list) {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mImage;
        TextView mName,mComments,mData;
        ImageView mPraise;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.iv_image);
            mName = itemView.findViewById(R.id.tv_name);
            mComments = itemView.findViewById(R.id.tv_comments);
            mData = itemView.findViewById(R.id.tv_data);
            mPraise = itemView.findViewById(R.id.iv_praise);
        }
    }
    private setOnClickLisnear lisnear;
    public void result(setOnClickLisnear lisnear){
        this.lisnear=lisnear;
    }

    public interface setOnClickLisnear{
        void click(int commentId);
    }
}
