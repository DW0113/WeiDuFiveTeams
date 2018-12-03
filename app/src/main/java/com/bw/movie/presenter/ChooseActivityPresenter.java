package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.ChooseActivity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.SeatTable;

/**
 *作者：刘进
 *日期：2018/12/1
 **/public class ChooseActivityPresenter extends AppDelegate implements View.OnClickListener{

    public SeatTable seatTableView;
    private TextView tv_choose_cinema_location;
    private TextView tv_choose_cinema_name;
    private TextView tv_choose_endtime;
    private TextView tv_choose_starttime;
    private TextView tv_choose_screeningHall;
    private TextView tv_choose_movie_name;
    private ImageView iv_choose_back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose;
    }
    private Context context;
    @Override
    public void getContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        //初始化控件
        tv_choose_cinema_location = get(R.id.tv_choose_cinema_location); //影院地址
        tv_choose_cinema_name = get(R.id.tv_choose_cinema_name);//影院名字
        tv_choose_movie_name = get(R.id.tv_choose_movie_name);//电影名字
        tv_choose_endtime = get(R.id.tv_choose_endtime);//结束时间
        tv_choose_starttime = get(R.id.tv_choose_starttime);//开始时间
        tv_choose_screeningHall = get(R.id.tv_choose_screeningHall);//播放大厅
        seatTableView = (SeatTable) get(R.id.seatView);
        //返回键点击事件
        setOnClick(this,R.id.iv_choose_back);
        //影片详情获取数据
        Intent intent = ((ChooseActivity) context).getIntent();
        String movieName = intent.getStringExtra("movieName");
        String cinemaName = intent.getStringExtra("cinemaName");
        String location = intent.getStringExtra("location");
        String beginTime = intent.getStringExtra("beginTime");
        String endTime = intent.getStringExtra("endTime");
        String screeningHall = intent.getStringExtra("screeningHall");
        //影院详情获取数据

        //影片详情赋值
        tv_choose_cinema_location.setText(location);
        tv_choose_cinema_name.setText(cinemaName);
        tv_choose_movie_name.setText(movieName);
        tv_choose_endtime.setText(endTime);
        tv_choose_starttime.setText(beginTime);
        tv_choose_screeningHall.setText(screeningHall);
        //影院详情赋值


        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10,15);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_choose_back:
                ((ChooseActivity)context).finish();
                break;
        }
    }
}
