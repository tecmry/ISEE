package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.adapter.SmartViewHolder;
import com.example.mao.beautylife.databinding.FragmentHomeTwoBinding;

/**
 * Created by --Mao on 2018/2/21.
 */

public class HomeTwoFragment extends Fragment {

    private FragmentHomeTwoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_two, container, false);

        binding.homeTwoRecycler.setAdapter(new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_two_item, parent, false);
            return new ItemHolder(view);
        }, (holder, position) -> {

        }, () -> {
            return 0;
        }));
        binding.homeTwoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }


    class ItemHolder extends SmartViewHolder{

        public ItemHolder(View itemView) {
            super(itemView);
        }

    }
}
