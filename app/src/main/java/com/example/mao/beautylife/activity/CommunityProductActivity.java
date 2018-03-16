package com.example.mao.beautylife.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.ActivityCommunityProductBinding;

public class CommunityProductActivity extends AppCompatActivity {

    private ActivityCommunityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_product);

    }
}
