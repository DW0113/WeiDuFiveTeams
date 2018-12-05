package com.bw.movie.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.CommentsBean;
import com.bw.movie.utils.DateUtils;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    int num = 0;
    private Context context;
    private SharedPreferences login;
    private String url = "/movieApi/cinema/v1/verify/cinemaCommentGreat";
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
        holder.mNum.setText(list.get(position).getGreatNum()+"");
        long commentTime = list.get(position).getCommentTime();
        String format = DateUtils.format(commentTime, "MM-dd HH:mm");
        holder.mData.setText(format);
        holder.mComments.setText(list.get(position).getCommentContent());

        holder.mPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int commentId = list.get(position).getCommentId();
                //lisnear.click(commentId);
                //praiseHttp(commentId);
                Map<String,String> map = new HashMap<>();
                Map<String,String> mapHead = new HashMap<>();
                String userId = login.getString("userld", "");
                String sessionId = login.getString("sessionId", "");
                mapHead.put("sessionId", sessionId);
                mapHead.put("userId", userId);
                map.put("commentId", commentId+"");

                new Utility().give(mapHead, url, map).result(new HttpListener() {
                    @Override
                    public void success(String data) {
                        //Toast.makeText(context, "d" + data, Toast.LENGTH_SHORT).show();
                        if (data.contains("点赞成功")){
                            num++;
                            Glide.with(context).load(R.drawable.com_icon_praise_selected).into(holder.mPraise);
                            holder.mNum.setText(num+"");
                        }else{
                            Toast.makeText(context, ""+data, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void fail(String error) {

                    }
                });
            }
        });

        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);

    }

    //点赞
    /**private void praiseHttp(int commentId) {
        //Toast.makeText(context, commentId+"", Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }
    private List<CommentsBean.ResultBean> list = new ArrayList<>();
    public void setList(List<CommentsBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mImage;
        TextView mName,mComments,mData,mNum;
        ImageView mPraise;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.iv_image);
            mName = itemView.findViewById(R.id.tv_name);
            mComments = itemView.findViewById(R.id.tv_comments);
            mData = itemView.findViewById(R.id.tv_data);
            mNum = itemView.findViewById(R.id.tv_num);
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
