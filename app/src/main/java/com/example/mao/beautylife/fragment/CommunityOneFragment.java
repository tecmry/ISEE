package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.OneRecyclerAdapter;
import com.example.mao.beautylife.data.VideoData;
import com.example.mao.beautylife.databinding.FragmentCommunityOneBinding;
import com.example.mao.beautylife.util.HttpUtil;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by -- Mao on 2017/11/30.
 */

public class CommunityOneFragment extends Fragment {

    private int current = 0;
    private static final String TAG = "CommunityOneFragment";
    private FragmentCommunityOneBinding binding;
    private OneRecyclerAdapter adapter = new OneRecyclerAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_one, container, false);
        binding.fragmentCommunityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.fragmentCommunityRecycler.setAdapter(adapter);
        binding.fragmentOneTextOne.setOnClickListener(view -> {
            setTextViewColor();
            binding.fragmentOneTextOne.setTextColor(Color.BLACK);

        });
        binding.fragmentOneTextTwo.setOnClickListener(view -> {
            setTextViewColor();
            binding.fragmentOneTextTwo.setTextColor(Color.BLACK);

        });
        binding.fragmentOneTextThree.setOnClickListener(view -> {
            setTextViewColor();
            binding.fragmentOneTextThree.setTextColor(Color.BLACK);

        });
        binding.fragmentOneTextFour.setOnClickListener(view -> {
            setTextViewColor();
            binding.fragmentOneTextFour.setTextColor(Color.BLACK);

        });

        return binding.getRoot();
    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home/brand")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .params("requestTime", current)
                .execute(callback);
    }

    private void parseJSON(String jsonData){

    }

    private void setTextViewColor(){
        binding.fragmentOneTextOne.setTextColor(Color.WHITE);
        binding.fragmentOneTextTwo.setTextColor(Color.WHITE);
        binding.fragmentOneTextThree.setTextColor(Color.WHITE);
        binding.fragmentOneTextFour.setTextColor(Color.WHITE);
    }

}
