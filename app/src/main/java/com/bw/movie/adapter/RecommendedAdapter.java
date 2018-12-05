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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.CinemaShowActivity;
import com.bw.movie.model.CinemaBean;
import com.bw.movie.model.RecommendedBean;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *作者：刘进
 *日期：2018/11/28
 **/
public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MyViewHolder> {
    //上下文
    private Context context;
    private SharedPreferences login;
    private int id;

    public RecommendedAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public RecommendedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //找到layout文件
        View view = View.inflate(context, R.layout.adapter_item_recommended,null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.MyViewHolder myViewHolder, final int i) {
        //获取影院里的Id

        //赋值
        myViewHolder.cinemaName.setText(mList.get(i).getName());
        myViewHolder.cinemaAddress.setText(mList.get(i).getAddress());
        Glide.with(context).load(mList.get(i).getLogo()).into(myViewHolder.cinemaLogo);
        //影院点击事件
        myViewHolder.cinemaLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 id = mList.get(i).getId();
                Intent intent = new Intent(context,CinemaShowActivity.class);
                intent.putExtra("id",id+"");
                context.startActivity(intent);
                //context.startActivity(new Intent(context, CinemaShowActivity.class));

            }
        });

        login = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        myViewHolder.mFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cinemeId = mList.get(i).getId();
                //Toast.makeText(context, "idd===="+id1, Toast.LENGTH_SHORT).show();
                Map<String,String> maphead = new HashMap<>();
                String userId = login.getString("userld", "");
                String sessionId = login.getString("sessionId", "");
                maphead.put("sessionId", sessionId);
                maphead.put("userId", userId);
                Map<String,String> map = new HashMap<>();
                map.put("cinemaId", cinemeId+"");
                new Utility().gethead("/movieApi/cinema/v1/verify/followCinema",map,maphead).result(new HttpListener() {
                    @Override
                    public void success(String data) {
                        Toast.makeText(context, data+"", Toast.LENGTH_SHORT).show();
                        Glide.with(context).load(R.drawable.com_icon_collection_selected).into(myViewHolder.mFocus);
                    }

                    @Override
                    public void fail(String error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    private List<RecommendedBean.ResultBean> mList = new ArrayList<>();
    public void setList(List<RecommendedBean.ResultBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout comMendedItem;
        ImageView cinemaLogo,mFocus;
        TextView cinemaName,cinemaAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaName = itemView.findViewById(R.id.tv_cinema_name);
            cinemaAddress = itemView.findViewById(R.id.tv_cinema_address);
            cinemaLogo = itemView.findViewById(R.id.iv_cinema_logo);
            comMendedItem = itemView.findViewById(R.id.commended_item);
            mFocus = itemView.findViewById(R.id.focus);
        }
    }
}
