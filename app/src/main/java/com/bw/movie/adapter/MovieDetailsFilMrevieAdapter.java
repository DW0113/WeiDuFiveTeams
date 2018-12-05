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

import com.bw.movie.R;
import com.bw.movie.model.MovieAttentionBean;
import com.bw.movie.model.MovieFilmrevieBean;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.ScreenUtil;
import com.bw.movie.utils.UitlsToos;
import com.bw.movie.utils.Utility;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDetailsFilMrevieAdapter extends RecyclerView.Adapter<MovieDetailsFilMrevieAdapter.MyViewHolder> {
    private Context context;
    private List<MovieFilmrevieBean.ResultBean> list = new ArrayList<>();
    private Map<String, String> mapHead;
    private Map<String, String> map;

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


        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userld = sp.getString("userld", "");
        String sessionId = sp.getString("sessionId", "");

        mapHead = new HashMap<>();
        mapHead.put("userId",userld);
        mapHead.put("sessionId",sessionId);
        map = new HashMap<>();
        map.put("commentId",list.get(position).getCommentId()+"");

        holder.image_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doHttp(holder.image_like,holder.text_like,position);
                notifyItemChanged(position+1);
            }
        });
    }
    private void doHttp(ImageView image_like, TextView text_like, int position) {
        new Utility().postform(Http.MOVIE_GREATE,map,mapHead).result(new HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                MovieAttentionBean movieAttentionBean = gson.fromJson(data, MovieAttentionBean.class);
                Toast.makeText(context,movieAttentionBean.getMessage(),Toast.LENGTH_SHORT).show();
                if (movieAttentionBean.getMessage().equals("点赞成功")){
                    image_like.setImageResource(R.drawable.movie_details_filmrevie_like_select);
                    int i = list.get(position).getGreatNum();
                    i++;
                    text_like.setText(""+i);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
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
