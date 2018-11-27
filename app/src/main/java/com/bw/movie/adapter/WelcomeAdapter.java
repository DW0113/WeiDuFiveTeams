package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：马利亚
 * 日期：2018/11/27
 * 内容：
 */
public class WelcomeAdapter extends PagerAdapter{
    private Context context;

    public WelcomeAdapter(Context context) {
        this.context = context;
    }

    private int [] images = {
            R.drawable.welcome_one,
            R.drawable.welcome_two,
            R.drawable.welcome_three,
            R.drawable.welcome_four};
    private String [] text_top = {
            "一场电影",
            "一场电影",
            "一场电影",
            "We are TheFiveTeams"};
    private String [] text_bottom = {
            "净化你的灵魂",
            "看遍人生百态",
            "荡涤你的心灵",
            "带您开启一段美好的电影之旅"};
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        RelativeLayout view = (RelativeLayout) View.inflate(context, R.layout.welcome_adapter_item_slide, null);
        ImageView iv_welcome_image = view.findViewById(R.id.iv_welcome_image);
        TextView tv_welcome_texttop = view.findViewById(R.id.tv_welcome_texttop);
        TextView tv_welcome_textbottom = view.findViewById(R.id.tv_welcome_textbottom);
        iv_welcome_image.setImageResource(images[position]);
        tv_welcome_texttop.setText("xczvasdfsad");
        tv_welcome_textbottom.setText(text_bottom[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
