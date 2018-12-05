package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.model.TicketDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class TicketDetailsAdapter extends RecyclerView.Adapter<TicketDetailsAdapter.MyViewHolder> {
    private Context context;
    private List<TicketDetailsBean.ResultBean> list = new ArrayList<>();

    public TicketDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TicketDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.ticket_details_adapter_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        holder.image_next = view.findViewById(R.id.iv_ticket_details_item_next);
        holder.text_end =view.findViewById(R.id.tv_ticket_details_item_end);
        holder.text_money =view.findViewById(R.id.tv_ticket_details_item_money);
        holder.text_start = view.findViewById(R.id.tv_ticket_details_item_start);
        holder.text_type = view.findViewById(R.id.tv_ticket_details_item_type);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketDetailsAdapter.MyViewHolder holder, int position) {
        holder.text_type.setText(list.get(position).getScreeningHall());
        holder.text_start.setText(list.get(position).getBeginTime());
        holder.text_end.setText(list.get(position).getEndTime());
        holder.text_money.setText(list.get(position).getSeatsUseCount()+"v");
        holder.image_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<TicketDetailsBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_type,text_start,text_end,text_money;
        ImageView image_next;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void onClick(int position);
    }
}
