package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.FourRecyclerAdapter;
import com.example.mao.beautylife.databinding.FragmentCommunityFourBinding;

/**
 * Created by -- Mao on 2017/11/30.
 */

public class CommunityFourFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private FragmentCommunityFourBinding binding;
    private FourRecyclerAdapter adapter = new FourRecyclerAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_four, container, false);
        binding.fragmentFourEditText.addTextChangedListener(this);
        binding.fragmentCommunityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.fragmentCommunityRecycler.setAdapter(adapter);
        binding.fragmentFourTextOne.setOnClickListener(this);
        binding.fragmentFourTextTwo.setOnClickListener(this);
        binding.fragmentFourTextThree.setOnClickListener(this);
        binding.fragmentFourTextFour.setOnClickListener(this);
        binding.fragmentFourBtn.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
