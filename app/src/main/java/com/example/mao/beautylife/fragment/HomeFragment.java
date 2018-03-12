package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.PageAdapter;
import com.example.mao.beautylife.databinding.FragmentHomeBinding;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by -- Mao on 2017/11/27.
 */

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private List<Fragment> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        list.add(new HomeOneFragment());
        list.add(new HomeTwoFragment());
        list.add(new HomeThreeFragment());
        binding.fragmentHomeViewPager.setAdapter(new PageAdapter(getFragmentManager(), list));
        binding.fragmentHomeTab.setupWithViewPager(binding.fragmentHomeViewPager);
        binding.fragmentHomeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.fragmentHomeTab));
        binding.fragmentHomeTab.getTabAt(0).setText("推荐");
        binding.fragmentHomeTab.getTabAt(1).setText("关注");
        binding.fragmentHomeTab.getTabAt(2).setText("分类");
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        OkGo.getInstance().cancelTag(TAG);
    }


}
