package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.databinding.FragmentCommunityVideoBinding;

/**
 * Created by --Mao on 2018/3/12.
 */

public class VideoFragment extends Fragment {

    private FragmentCommunityVideoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_video, container, false);
        binding.communityVideoRecycler.setAdapter(new CommonRecyclerAdapter((parent, viewType) -> {
            return null;
        }, (holder, position) -> {

        }, () -> {
            return 0;
        }));
        return binding.getRoot();
    }
}
