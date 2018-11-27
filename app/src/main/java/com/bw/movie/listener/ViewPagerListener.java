package com.bw.movie.listener;

import android.support.v4.view.ViewPager;

public abstract class ViewPagerListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public abstract void onPageSelected(int i);

    @Override
    public void onPageScrollStateChanged(int i) {
    }
}
