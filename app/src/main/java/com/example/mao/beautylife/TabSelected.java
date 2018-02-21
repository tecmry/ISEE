package com.example.mao.beautylife;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.mao.beautylife.listener.TabLayoutListener;

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
