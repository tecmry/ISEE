package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.base.BaseFragment;
import com.example.mao.beautylife.databinding.FragmentSelfHisBinding;

/**
 * Created by --Mao on 2018/3/15.
 */

public class SelfHisFragment extends BaseFragment {

    private FragmentSelfHisBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_self_his, container, false);

        return binding.getRoot();
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected boolean isFragmentVisible() {
        return super.isFragmentVisible();
    }
}
