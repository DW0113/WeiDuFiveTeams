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

import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/12/1
 * */public class RecordAdpater extends RecyclerView.Adapter<RecordAdpater.MyViewhodel> {
    private Context context;
    private List<RecordBean.ResultBean> listRecord ;

    public RecordAdpater(Context context, List<RecordBean.ResultBean> listRecord) {
        this.context = context;
        this.listRecord = listRecord;
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
        myViewhodel.tv_time.setText(listRecord.get(i).getBeginTime());
        myViewhodel.tv_num.setText(listRecord.get(i).getAmount());
        myViewhodel.tv_money.setText(listRecord.get(i).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return listRecord.size();
    }

    public class MyViewhodel extends RecyclerView.ViewHolder {
        TextView tv_move_name,tv_The_order,tv_cinima,tv_screens,tv_time,tv_num,tv_money;
        Button b_ismoney;
        public MyViewhodel(@NonNull View itemView) {
            super(itemView);
             tv_move_name=itemView.findViewById(R.id.tv_move_name);
             b_ismoney=itemView.findViewById(R.id.b_ismoney);
             tv_The_order=itemView.findViewById(R.id.tv_The_order);
             tv_cinima=itemView.findViewById(R.id.tv_cinima);
             tv_screens=itemView.findViewById(R.id.tv_screens);
             tv_time=itemView.findViewById(R.id.tv_time);
             tv_num=itemView.findViewById(R.id.tv_num);
             tv_money=itemView.findViewById(R.id.tv_money);
        }
    }
}
