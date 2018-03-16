package com.example.mao.beautylife;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mao.beautylife.listener.TabLayoutListener;

import java.lang.reflect.Field;

import static com.tencent.plus.DensityUtil.dip2px;

/**
 * Created by -- Mao on 2017/11/26.
 */

public class TabSelected extends TabLayout.ViewPagerOnTabSelectedListener {

    private TabLayoutListener listener;

    public TabSelected(ViewPager viewPager, TabLayoutListener listener) {
        super(viewPager);
        this.listener = listener;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        super.onTabSelected(tab);
        listener.tabSelectedListener(tab);
    }



}
