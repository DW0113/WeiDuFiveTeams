package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.ChooseActivity;
import com.bw.movie.activity.TicketDetailsActivity;
import com.bw.movie.adapter.TicketDetailsAdapter;
import com.bw.movie.model.MovieDetailsBean;
import com.bw.movie.model.TicketDetailsBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Http;
import com.bw.movie.utils.HttpHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.List;

public class TicketDetailsActivityPresenter extends AppDelegate {

    private TextView tv_ticket_details_cinemaname;
    private TextView tv_ticket_details_location;
    private SimpleDraweeView sdv_ticket_details_movieimage;
    private TextView tv_ticket_details_movie_name;
    private TextView tv_ticket_details_movie_director;
    private TextView tv_ticket_details_movie_duration;
    private TextView tv_ticket_details_movie_placeOrigin;
    private TextView tv_ticket_details_movie_type;
    private RecyclerView rv_ticket_details_movie;
    private Context context;
    private int cinemasId;
    private int movieId;
    private TicketDetailsAdapter ticketDetailsAdapter;
    private ImageView iv_ticket_details_movie_back;
    private List<TicketDetailsBean.ResultBean> result;
    private String movieName;
    private String location;
    private String cinemaName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ticket_details;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }
    @Override
    public void initData() {
        //初始化控件
        initView();
        //获取id
        Intent intent = ((TicketDetailsActivity) context).getIntent();
        cinemaName = intent.getStringExtra("cinemaName");
        location = intent.getStringExtra("location");
        movieName = intent.getStringExtra("movieName");
        cinemasId = intent.getIntExtra("cinemasId", 1);
        movieId = intent.getIntExtra("movieId", 1);
        SharedPreferences sp = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userId = sp.getString("userld", "");
        String sessionId = sp.getString("sessionId", "");
        tv_ticket_details_cinemaname.setText(cinemaName);
        tv_ticket_details_location.setText(location);
        //查询电影详情
        doHttpMovie(movieId, userId, sessionId);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rv_ticket_details_movie.setLayoutManager(linearLayoutManager);
        ticketDetailsAdapter = new TicketDetailsAdapter(context);
        rv_ticket_details_movie.setAdapter(ticketDetailsAdapter);
        iv_ticket_details_movie_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TicketDetailsActivity) context).finish();
            }
        });
        doHttp();
        ticketDetailsAdapter.setOnItemClick(new TicketDetailsAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(((TicketDetailsActivity)context), ChooseActivity.class);
                intent.putExtra("movieName", movieName);
                intent.putExtra("cinemaName", cinemaName);
                intent.putExtra("location", location);
                intent.putExtra("beginTime",result.get(position).getBeginTime());
                intent.putExtra("endTime",result.get(position).getEndTime());
                intent.putExtra("screeningHall",result.get(position).getScreeningHall());
                context.startActivity(intent);
            }
        });
    }

    private void doHttp() {
        new HttpHelper().get(Http.MOVIE_BUY+"cinemasId="+cinemasId+"&movieId="+movieId).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                TicketDetailsBean ticketDetailsBean = gson.fromJson(data, TicketDetailsBean.class);
                result = ticketDetailsBean.getResult();
                ticketDetailsAdapter.setList(result);
            }

            @Override
            public void error(String error) {

            }
        });
    }

    private void initView() {
        tv_ticket_details_cinemaname = get(R.id.tv_ticket_details_cinemaname);
        tv_ticket_details_location = get(R.id.tv_ticket_details_location);
        sdv_ticket_details_movieimage = get(R.id.sdv_ticket_details_movieimage);
        tv_ticket_details_movie_name = get(R.id.tv_ticket_details_movie_name);
        tv_ticket_details_movie_director = get(R.id.tv_ticket_details_movie_director);
        tv_ticket_details_movie_duration = get(R.id.tv_ticket_details_movie_duration);
        tv_ticket_details_movie_placeOrigin = get(R.id.tv_ticket_details_movie_placeOrigin);
        tv_ticket_details_movie_type = get(R.id.tv_ticket_details_movie_type);
        rv_ticket_details_movie = get(R.id.rv_ticket_details_movie);
        iv_ticket_details_movie_back = get(R.id.iv_ticket_details_movie_back);
    }
    private void doHttpMovie(int movieId, String userId, String sessionId) {
        new HttpHelper().get(Http.MOVIE_DETAILS+"userId="+userId+"&sessionId="+sessionId+"&movieId="+movieId).result(new HttpHelper.Httplistenner() {
            @Override
            public void success(String data) {
                Gson gson =new Gson();
                MovieDetailsBean movieDetailsBean = gson.fromJson(data, MovieDetailsBean.class);
                MovieDetailsBean.ResultBean result = movieDetailsBean.getResult();
                tv_ticket_details_movie_director.setText("导演："+result.getDirector());
                tv_ticket_details_movie_name.setText(result.getName());
                tv_ticket_details_movie_duration.setText("时长："+result.getDuration());
                tv_ticket_details_movie_placeOrigin.setText("产地："+result.getPlaceOrigin());
                tv_ticket_details_movie_type.setText("类型："+result.getMovieTypes());
                sdv_ticket_details_movieimage.setImageURI(result.getImageUrl());
            }

            @Override
            public void error(String error) {

            }
        });
    }
}
