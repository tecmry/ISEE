package com.example.mao.beautylife.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.FragmentOfficialBinding;
import com.example.mao.beautylife.event.LocalEvent;
import com.example.mao.beautylife.service.MapService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by -- Mao on 2017/12/10.
 */

public class ProductOfficialFragment extends Fragment implements Inputtips.InputtipsListener {

    private FragmentOfficialBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_official, container, false);
        EventBus.getDefault().register(this);
        Intent intent = new Intent(getActivity(), MapService.class);
        intent.putExtra("isFirstLoc", false);
        getActivity().startService(intent);
        return binding.getRoot();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocalEvent(LocalEvent event){
        //第一个参数搜索内容  第二个参数城市
        InputtipsQuery inputQuery = new InputtipsQuery("", event.getLocal());
        Inputtips inputTips = new Inputtips(getContext(), inputQuery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
        getActivity().stopService(new Intent(getActivity(), MapService.class));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {

    }
}
