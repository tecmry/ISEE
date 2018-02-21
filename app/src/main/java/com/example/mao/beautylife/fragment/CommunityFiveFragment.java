package com.example.mao.beautylife.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.FiveRecyclerAdapter;
import com.example.mao.beautylife.data.InfoData;
import com.example.mao.beautylife.data.VideoData;
import com.example.mao.beautylife.databinding.FragmentCommunityFiveBinding;
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

public class CommunityFiveFragment extends Fragment implements View.OnClickListener, FiveRecyclerAdapter.OnItemListener, FootItemListener, OnRefreshListener {

    private int current = 0;
    private static final String TAG = "CommunityFiveFragment";
    private FragmentCommunityFiveBinding binding;
    private List<InfoData> list = new ArrayList<>();
    private FiveRecyclerAdapter adapter = new FiveRecyclerAdapter(list, this, this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_five, container, false);
        binding.fragmentCommunityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.fragmentCommunityRecycler.setAdapter(adapter);
        binding.fragmentFiveTextOne.setOnClickListener(this);
        binding.fragmentFiveRefresh.setOnRefreshListener(this);
        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
                    parseJSON(response.body());
                }else {
                    Toast.makeText(getActivity(), "服务器连接失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        request(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.isSuccessful()){
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
    public void itemImage(View itemView, ImageView imageView, String url, String content) {
        itemView.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("content", content);

        });
        ImageLoaderUtil.ImageLoader(this, imageView, url);
    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home/article")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .params("requestTime", current)
                .execute(callback);
    }

    private void parseJSON(String jsonData){
        current++;
        try {
            JSONArray array = new JSONObject(jsonData).getJSONArray("articles");
            for (int i = 0; i < array.length(); i++){
                InfoData data = new InfoData();
                JSONObject object = array.getJSONObject(i);
                data.setImageUrl(object.getString("coverUrl"));
                data.setCollect(object.getString("likeTimes"));
                data.setInfo(object.getString("content"));
                data.setContent(object.getString("articlecontent"));
                data.setTitle(object.getString("title"));
                data.setName(object.getString("nickname"));
                list.add(data);
                adapter.notifyItemChanged(list.size() - 1);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void itemClick(View footItem) {
        footItem.setOnClickListener(view -> {
            binding.fragmentCommunityRecycler.smoothScrollToPosition(0);
        });
    }

    @Override
    public void onClick(View view) {

    }
}
