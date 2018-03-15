package com.example.mao.beautylife.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.TabSelected;
import com.example.mao.beautylife.adapter.PageAdapter;

import com.example.mao.beautylife.databinding.ActivityHomeBinding;
import com.example.mao.beautylife.fragment.CommunityFragment;
import com.example.mao.beautylife.fragment.HomeFragment;
import com.example.mao.beautylife.fragment.SelfFragment;
import com.example.mao.beautylife.listener.TabLayoutListener;
import com.example.mao.beautylife.util.ModeUtil;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements TabLayoutListener {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ModeUtil.MIUISetStatusBarLightMode(getWindow(), true);
        ModeUtil.FlymeSetStatusBarLightMode(getWindow(), true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding.activityHomeViewPager.setNoScroll(true);
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CommunityFragment());
        list.add(new SelfFragment());
        binding.activityHomeViewPager.setOffscreenPageLimit(2);
        binding.activityHomeViewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), list));
        binding.activityHomeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.activityHomeTab));
        binding.activityHomeTab.addOnTabSelectedListener(new TabSelected(binding.activityHomeViewPager, this));
    }

    @Override
    public void onBackPressed() {
        Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
        mHomeIntent.addCategory(Intent.CATEGORY_HOME);
        mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(mHomeIntent);
    }

    @Override
    public void tabSelectedListener(TabLayout.Tab tab) {
        switch (tab.getPosition()){
            case 0:
                tab.setIcon(R.drawable.home_selected);
                binding.activityHomeTab.getTabAt(1).setIcon(R.drawable.community_unselected);
                binding.activityHomeTab.getTabAt(2).setIcon(R.drawable.user_unselected);
                break;
            case 1:
                tab.setIcon(R.drawable.community_selected);
                binding.activityHomeTab.getTabAt(0).setIcon(R.drawable.home_unselected);
                binding.activityHomeTab.getTabAt(2).setIcon(R.drawable.user_unselected);
                break;
            case 2:
                tab.setIcon(R.drawable.user_selected);
                binding.activityHomeTab.getTabAt(0).setIcon(R.drawable.home_unselected);
                binding.activityHomeTab.getTabAt(1).setIcon(R.drawable.community_unselected);
                break;
            default:
                break;
        }
    }

}
