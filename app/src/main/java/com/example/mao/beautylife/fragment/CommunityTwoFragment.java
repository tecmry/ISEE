package com.example.mao.beautylife.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.activity.VideoActivity;
import com.example.mao.beautylife.adapter.TwoRecyclerAdapter;
import com.example.mao.beautylife.data.VideoData;
import com.example.mao.beautylife.databinding.FragmentCommunityTwoBinding;
import com.example.mao.beautylife.listener.FootItemListener;
import com.example.mao.beautylife.util.HttpUtil;
import com.example.mao.beautylife.util.ImageLoaderUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by -- Mao on 2017/11/30.
 */

public class CommunityTwoFragment extends Fragment implements View.OnClickListener, TwoRecyclerAdapter.ImageListener, OnRefreshListener, FootItemListener {

    private static final String TAG = "CommunityTwoFragment";
    private FragmentCommunityTwoBinding binding;
    private List<VideoData>list = new ArrayList<>();
    private TwoRecyclerAdapter adapter;
    private int current = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_two, container, false);
        binding.fragmentCommunityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.fragmentCommunityRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new TwoRecyclerAdapter(list, this, this);
        binding.fragmentCommunityRecycler.setAdapter(adapter);
        binding.fragmentTwoTextOne.setOnClickListener(this);
        binding.fragmentTwoTextTwo.setOnClickListener(this);
        binding.fragmentTwoTextThree.setOnClickListener(this);
        binding.fragmentTwoTextFour.setOnClickListener(this);
        binding.fragmentTwoRefresh.setOnRefreshListener(this);

        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    current++;
                    parseJSON(response.body());
                }else {
                    Toast.makeText(getActivity(), "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(TAG);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    current++;
                    list.clear();
                    parseJSON(response.body());
                    refreshlayout.finishRefresh();
                    refreshlayout.resetNoMoreData();//恢复上拉状态
                }else {
                    Toast.makeText(getActivity(), "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void itemClick(View footItem) {
        footItem.setOnClickListener(view -> {
            binding.fragmentCommunityRecycler.smoothScrollToPosition(0);
        });
    }

    @Override
    public void imageListener(ImageView view, String imageUrl, String title, String videoUrl) {
        ImageLoaderUtil.ImageLoader(this, view, imageUrl);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VideoActivity.class);
            intent.putExtra("videoUrl", videoUrl);
            intent.putExtra("title", title);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View view) {

    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home/video")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .params("requestTime", current)
                .execute(callback);
    }

    private void parseJSON(String jsonData){
        try {
            JSONArray array = new JSONObject(jsonData).getJSONArray("videos");
            for (int i = 0; i < array.length(); i += 2){
                JSONObject object1 = array.getJSONObject(i);
                JSONObject object2 = array.getJSONObject(i + 1);
                VideoData data = new VideoData();
                data.setLikeNumOne(object1.getString("likeTimes"));
                data.setImageOne(object1.getString("coverUrl"));
                data.setNameOne(object1.getString("nickname"));
                data.setTitleOne(object1.getString("title"));
                data.setVideoUrlOne(object1.getString("videourl"));
                data.setLikeNumTwo(object2.getString("likeTimes"));
                data.setImageTwo(object2.getString("coverUrl"));
                data.setNameTwo(object2.getString("nickname"));
                data.setTitleTwo(object2.getString("title"));
                data.setVideoUrlTwo(object2.getString("videourl"));
                list.add(data);
                adapter.notifyItemChanged(list.size() - 1);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
