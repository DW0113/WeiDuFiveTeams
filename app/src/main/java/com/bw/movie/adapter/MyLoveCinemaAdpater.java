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
import com.bw.movie.model.LoveCinemaBean;
import com.bw.movie.model.MyLove;

import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/12/1
 * */public class MyLoveCinemaAdpater extends RecyclerView.Adapter<MyLoveCinemaAdpater.MyViewhodel> {
    private Context context;
    private  List<LoveCinemaBean.ResultBean>  moveList;

    public MyLoveCinemaAdpater(Context context, List<LoveCinemaBean.ResultBean>  moveList) {
        this.context = context;
        this.moveList = moveList;
    }

    @NonNull
    @Override
    public MyViewhodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           View view=View.inflate(context,R.layout.mylovemove,null);
          MyViewhodel myViewhodel = new MyViewhodel(view);
          return myViewhodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhodel myViewhodel, int i) {
         myViewhodel.tv_cinema_address.setText(moveList.get(i).getAddress());
         myViewhodel.tv_cinema_name.setText(moveList.get(i).getName());
         Glide.with(context).load(moveList.get(i).getLogo()).into(myViewhodel.iv_cinema_logo);
    }

    @Override
    public int getItemCount() {
        return moveList.size();
    }

    public class MyViewhodel extends RecyclerView.ViewHolder {
        ImageView iv_cinema_logo;
        TextView tv_cinema_name,tv_cinema_address;
        public MyViewhodel(@NonNull View itemView) {
            super(itemView);
            iv_cinema_logo = itemView.findViewById(R.id.iv_cinema_logo);
            tv_cinema_name= itemView.findViewById(R.id.tv_cinema_name);
             tv_cinema_address= itemView.findViewById(R.id.tv_cinema_address);
        }
    }
}
