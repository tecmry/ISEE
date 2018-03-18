package com.example.mao.beautylife.fragment.collect;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.databinding.FragmentBaCaoBinding;


public class BaCaoFragment extends Fragment {

    private FragmentBaCaoBinding binding;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ba_cao, container, false);
        bundle = getArguments();
        binding.recyclerView.setAdapter(new CommonRecyclerAdapter((parent, viewType) -> {

            return null;
        }, (holder, position) -> {

        }, () -> {
            return 0;
        }));

        return binding.getRoot();
    }


}
