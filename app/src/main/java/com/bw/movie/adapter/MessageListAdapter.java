package com.bw.movie.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.model.MessageChangesTatusBean;
import com.bw.movie.model.MessageListsBean;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：马利亚
 * 日期：2018/12/4
 * 内容：
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyViewHolder>{
    private Context context;
    private List<MessageListsBean.ResultBean> list=new ArrayList<>();
    private String mMonth,mDay,mHours,mMinute;

    public MessageListAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public MessageListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.layout_messagelist, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MyViewHolder myViewHolder, int i) {
        //赋值
        myViewHolder.tv_activity_message_title.setText(list.get(i).getTitle());
        myViewHolder.tv_activity_message_content.setText(list.get(i).getContent());

        //获取getSharedPreferences里的id,userid,sessionid
        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String id = login.getString("id", "");
        String userld=login.getString("userld","");
        String sessionId=login.getString("sessionId","");
        if (list.get(i).getStatus()==0){
            myViewHolder.tv_activity_message_sum.setVisibility(View.VISIBLE);
        }else {
            myViewHolder.tv_activity_message_sum.setVisibility(View.GONE);
        }
        //点击判断是否有文件
        myViewHolder.tv_activity_message_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //把获取到的内容存入map
                Map<String,String> formmap = new HashMap<>();
                formmap.put("id",id);
                Map<String,String> maphead=new HashMap<>();
                maphead.put("userId",userld);
                maphead.put("sessionId",sessionId);
                //解析状态修改的接口
                new Utility().gethead("movieApi/tool/v1/verify/changeSysMsgStatus",formmap,maphead).result(new HttpListener() {
                    @Override
                    public void success(String data) {
                        MessageChangesTatusBean messageChangesTatusBean = new Gson().fromJson(data, MessageChangesTatusBean.class);
                        String status = messageChangesTatusBean.getStatus();
                        //如果解析成功的话，就可以让小图标消失
                        myViewHolder.tv_activity_message_sum.setVisibility(View.GONE);
                        lister.success();
                    }

                    @Override
                    public void fail(String error) {

                    }
                });


            }
        });

        Calendar calendar = Calendar.getInstance();
        /**
        * 获取日期的月
        * */
        mMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        /**
        *获取日期的天
        * */
        mDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        /**
         * 如果小时是个位数
         *
         *则在前面价格“0”
         * */
        if (calendar.get(Calendar.HOUR) < 10) {
            mHours = "0" + calendar.get(Calendar.HOUR);
        } else {
            mHours = String.valueOf(calendar.get(Calendar.HOUR));
        }
        /**
         * 如果分钟是个位数
         *
         *则在前面价格“0”
         * */
        if (calendar.get(Calendar.MINUTE) < 10) {
            mMinute = "0" + calendar.get(Calendar.MINUTE);
        } else {
            mMinute = String.valueOf(calendar.get(Calendar.MINUTE));
        }
        myViewHolder.tv_activity_message_time.setText(mHours+":"+mMinute);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<MessageListsBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_activity_message_content;
        private TextView tv_activity_message_time;
        private TextView tv_activity_message_title;
        private TextView tv_activity_message_sum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取id
            tv_activity_message_title=(TextView)itemView.findViewById(R.id.tv_activity_message_title);
            tv_activity_message_time=(TextView)itemView.findViewById(R.id.tv_activity_message_time);
            tv_activity_message_content=(TextView)itemView.findViewById(R.id.tv_activity_message_content);
            tv_activity_message_sum=(TextView)itemView.findViewById(R.id.tv_activity_message_sum);
        }
    }
    private HttpLister lister;
    public void result(HttpLister lister){
        this.lister=lister;
    }
    public interface HttpLister{
        void success();
    }
}
