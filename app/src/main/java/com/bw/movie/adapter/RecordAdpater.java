package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.model.MyLove;
import com.bw.movie.model.RecordBean;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/12/1
 * */public class RecordAdpater extends RecyclerView.Adapter<RecordAdpater.MyViewhodel> {
    private Context context;

    public RecordAdpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewhodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           View view=View.inflate(context,R.layout.recold,null);
          MyViewhodel myViewhodel = new MyViewhodel(view);
          return myViewhodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewhodel myViewhodel, int i) {
        myViewhodel.tv_move_name.setText(listRecord.get(i).getMovieName());
        myViewhodel.tv_The_order.setText(listRecord.get(i).getOrderId());
        myViewhodel.tv_cinima.setText(listRecord.get(i).getCinemaName());
        myViewhodel.tv_screens.setText(listRecord.get(i).getScreeningHall());
        myViewhodel.tv_start_time.setText(listRecord.get(i).getBeginTime());
        myViewhodel.tv_end_time.setText(listRecord.get(i).getEndTime());
        myViewhodel.tv_num.setText(listRecord.get(i).getAmount()+"");
        myViewhodel.tv_money.setText(listRecord.get(i).getPrice()+"");
      //  myViewhodel.b_time.setText(listRecord.get(i).getCinemaName()+"");
        if(listRecord.get(i).getStatus()==1){
            myViewhodel.b_ismoney.setVisibility(View.GONE);
        }
        else{
            myViewhodel.b_ismoney.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listRecord.size();
    }
    private List<RecordBean.ResultBean> listRecord = new ArrayList<>();
    public void setList(List<RecordBean.ResultBean> list) {
        this.listRecord = list;
        notifyDataSetChanged();
    }

    public class MyViewhodel extends RecyclerView.ViewHolder {
        TextView tv_move_name,tv_The_order,tv_cinima,tv_screens,tv_start_time,tv_num,tv_money,tv_end_time;
        Button b_ismoney,b_time;
        public MyViewhodel(@NonNull View itemView) {
            super(itemView);
             tv_move_name=itemView.findViewById(R.id.tv_move_name);
             b_ismoney=itemView.findViewById(R.id.b_ismoney);
             tv_The_order=itemView.findViewById(R.id.tv_The_order);
             tv_cinima=itemView.findViewById(R.id.tv_cinmea);
             tv_screens=itemView.findViewById(R.id.tv_screens);
            tv_start_time=itemView.findViewById(R.id.tv_start_time);
            tv_end_time=itemView.findViewById(R.id.tv_end_time);
             b_time=itemView.findViewById(R.id.b_time);
             tv_num=itemView.findViewById(R.id.tv_num);
             tv_money=itemView.findViewById(R.id.tv_money);
        }
    }
}
