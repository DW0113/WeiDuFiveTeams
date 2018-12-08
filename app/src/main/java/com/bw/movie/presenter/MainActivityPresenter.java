package com.bw.movie.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.MainActivity;
import com.bw.movie.activity.VersionSuccessActivity;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.MovieFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.model.VersionBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.HttpListener;
import com.bw.movie.utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityPresenter  extends AppDelegate implements View.OnClickListener{
    //全局控件
    private ViewPager vp_main_viewpager;
    private List<Fragment> fragmentlist=new ArrayList<>();
    private ImageView im_move_fragment;
    private ImageView im_cinema_fragment;
    private ImageView im_my_fragment;
    private AlertDialog.Builder builder;
    private SharedPreferences login;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
     private Context context;
    @Override
    public void getContext(Context context) {
  this.context=context;
    }

    @Override
    public void initData() {
        //找控件
        vp_main_viewpager=(ViewPager)get(R.id.vp_main_viewpager);
        im_move_fragment = (ImageView) get(R.id.im_move_fragment);
        im_cinema_fragment = (ImageView) get(R.id.im_cinema_fragment);
        im_my_fragment = (ImageView) get(R.id.im_my_fragment);
        //把fragment放到集合里面
        fragmentlist.add(new MovieFragment());
        fragmentlist.add(new CinemaFragment());
        fragmentlist.add(new MyFragment());
        //适配器
        MyAdpater myAdpater = new MyAdpater(((MainActivity)context).getSupportFragmentManager());
        vp_main_viewpager.setAdapter(myAdpater);
        vp_main_viewpager.setOffscreenPageLimit(3);
        //点击事件
        im_my_fragment.setOnClickListener(this);
        im_cinema_fragment.setOnClickListener(this);
        im_move_fragment.setOnClickListener(this);
        //让他默认是0
        setClick(0);
        //滑动的时候，跟着变
        vp_main_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setClick(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //获取getSharedPreferences中存储的数据
        login = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String userld = login.getString("userld", "");
        String sessionId = login.getString("sessionId", "");
        //解析版本更新
        doHttpVersion(userld,sessionId);
    }
    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_move_fragment:
                setClick(0);
                break;
            case R.id.im_cinema_fragment:
                setClick(1);
                break;
            case R.id.im_my_fragment:
                setClick(2);
                break;

        }
    }
  //改变图片
    public void setClick(int click) {
        vp_main_viewpager.setCurrentItem(click);
        switch (click){
            case 0:
                im_move_fragment.setImageResource(R.drawable.fragment_movie_selected);
                im_cinema_fragment.setImageResource(R.drawable.fragment_cinema);
                im_my_fragment.setImageResource(R.drawable.fragment_my);
                break;
            case 1:
                im_move_fragment.setImageResource(R.drawable.fragment_movie);
                im_cinema_fragment.setImageResource(R.drawable.fragment_cinema_selected);
                im_my_fragment.setImageResource(R.drawable.fragment_my);
                break;
            case 2:
                im_move_fragment.setImageResource(R.drawable.fragment_movie);
                im_cinema_fragment.setImageResource(R.drawable.fragment_cinema);
                im_my_fragment.setImageResource(R.drawable.fragment_my_selected);
                break;
        }






    }

//适配器
    class MyAdpater extends FragmentPagerAdapter {

        public MyAdpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentlist.get(i);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }
    }



    //查询新版本
    private void doHttpVersion(String userld, String sessionId) {
        //创建一个map
        Map<String, String> map = new HashMap<>();
        //保存信息
        map.put("userId", userld);
        map.put("sessionId", sessionId);
        map.put("ak", "0110010010000");
        //解析版本更新接口的数据
        new Utility().get("movieApi/tool/v1/findNewVersion", map).result(new HttpListener() {
            @Override
            public void success(String data) {
                //获取版本更新的对象
                VersionBean versionBean = new Gson().fromJson(data, VersionBean.class);
                String downloadUrl = versionBean.getDownloadUrl();
                //获取flag的值
                int flag = versionBean.getFlag();
                //保存url
                login.edit().putString("url", versionBean.getDownloadUrl()).commit();
                //判断flag是否==1，则去下载
                if (flag == 1) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //提示更新
                            builder = new AlertDialog.Builder(context);
                            builder.setTitle("发现新版本")
                                    .setMessage("发现新版本2.0.3,是否更新?")
                                    .setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Uri uri = Uri.parse(downloadUrl.trim());
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    ((MainActivity) context).startActivity(intent);

                                }
                            });
                            builder.create().show();
                        }
                    }, 1000);//3秒后执行Runnable中的run方法
                } else {
                    Toast.makeText(context, "暂时没有新版本", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void fail(String error) {

            }
        });
    }

}
