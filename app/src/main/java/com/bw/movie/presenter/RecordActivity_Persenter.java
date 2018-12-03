package com.bw.movie.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.MyLoveMoveAdpater;
import com.bw.movie.adapter.RecordAdpater;
import com.bw.movie.model.RecordBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 作者：秦永聪
 *日期：2018/11/30
 * */public class RecordActivity_Persenter extends AppDelegate {
     private  List<RecordBean.ResultBean> listRecord=new ArrayList<>();
    private RecordAdpater RecordAdpater;

    @Override
    public int getLayoutId() {
        return R.layout.activity_record_pesenter;
    }
    Context context;
    @Override
    public void getContext(Context context) {
       this.context=context;
    }

    @Override
    public void initData() {
        //找控件
        RecyclerView  rv_record=get(R.id.rv_record);
        SharedPreferences login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        dohttp(userld,sessionId);
        RecordAdpater = new RecordAdpater(context, listRecord);
        rv_record.setAdapter(RecordAdpater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_record.setLayoutManager(linearLayoutManager);
    }

    private void dohttp(String userld, String sessionId) {
        Map map=new HashMap<String,String>();
        map.put("userId",userld);
        map.put("sessionId",sessionId);

        Map map1=new HashMap<String,String>();
        map1.put("page","1");
        map1.put("count","5");
              new Utility().getrecord("verify/findUserBuyTicketRecordList",map,map1).result(new HttpListener() {
                  @Override
                  public void success(String data) {
                      RecordBean recordBean = new Gson().fromJson(data, RecordBean.class);
                      List<RecordBean.ResultBean> result = recordBean.getResult();
                      if(result.size()==0&&result==null){
                          Toast.makeText(context,"亲，目前还没有购票",Toast.LENGTH_LONG).show();
                          return;
                      }
                      listRecord.addAll(result);
                      RecordAdpater.notifyDataSetChanged();
                  }

                  @Override
                  public void fail(String error) {

                  }
              });
    }
}
