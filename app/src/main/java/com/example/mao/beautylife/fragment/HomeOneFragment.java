package com.example.mao.beautylife.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.ListData;
import com.example.mao.beautylife.databinding.FragmentHomeBinding;
import com.example.mao.beautylife.databinding.FragmentHomeOneBinding;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeOneFragment extends Fragment {

    private FragmentHomeOneBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_one, container, false);

        return binding.getRoot();
    }


}
