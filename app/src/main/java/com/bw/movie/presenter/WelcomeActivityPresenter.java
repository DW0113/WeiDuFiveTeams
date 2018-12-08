package com.bw.movie.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.WelcomeActivity;
import com.bw.movie.adapter.WelcomeAdapter;
import com.bw.movie.listener.ViewPagerListener;
import com.bw.movie.mvp.view.AppDelegate;

public class WelcomeActivityPresenter extends AppDelegate {
    private Context context;
    private ViewPager vp_welcome_viewpager;
    private LinearLayout ll_welcome_dot;
    private Button bt_welcome_jump;
    private SharedPreferences sp;
    private boolean flag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        //初始化控件
        vp_welcome_viewpager = get(R.id.vp_welcome_viewpager);
        ll_welcome_dot = get(R.id.ll_welcome_dot);
        bt_welcome_jump = get(R.id.bt_welcome_jump);
        //获取SharedPrefereces
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        flag = sp.getBoolean("flag", false);
        //判断是否是首次登录，非首次登录直接跳转
        if (flag){
            Intent intent = new Intent(((WelcomeActivity)context), LoginActivity.class);
            context.startActivity(intent);
            ((WelcomeActivity) context).finish();
            return;
        }
        //创建adapter
        WelcomeAdapter welcomeAdapter = new WelcomeAdapter(context);
        //设置adapter
        vp_welcome_viewpager.setAdapter(welcomeAdapter);
        //设置小圆点
        setDot(0);
        //小圆点和viewpager联动
        vp_welcome_viewpager.addOnPageChangeListener(new ViewPagerListener() {
            @Override
            public void onPageSelected(int i) {
                setDot(i);
                //滑动到最后一页展示立即观影按钮，否则则隐藏
                if (i == 3){
                    bt_welcome_jump.setVisibility(View.VISIBLE);
                }else{
                    bt_welcome_jump.setVisibility(View.GONE);
                }
            }
        });
        //立即观影点击事件
        bt_welcome_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(((WelcomeActivity)context), MainActivity.class);
                context.startActivity(intent);
                ((WelcomeActivity) context).finish();
                sp.edit().putBoolean("flag",true).commit();
            }
        });
    }
    //小圆点
    private void setDot(int position) {
        ll_welcome_dot.removeAllViews();
        for (int i = 0; i <4 ; i++) {
            ImageView imageView = new ImageView(context);
            if (i == position){
                imageView.setImageResource(R.drawable.shape_welcome_dot_select);//选中
            }else{
                imageView.setImageResource(R.drawable.shape_welcome_dot_default);//未选中
            }
            ll_welcome_dot.addView(imageView);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params.leftMargin = 10;
            params.height = 12;
            params.width = 12;
            imageView.setLayoutParams(params);
        }
    }
}
