package com.example.mao.beautylife.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.base.BaseFragment;
import com.example.mao.beautylife.data.NetArticleItemData;
import com.example.mao.beautylife.service.RecommendVideoInterface;
import com.example.mao.beautylife.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Tecmry on 2018/3/24.
 */

public class RecommendVideoFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<NetArticleItemData.VideosBean> list = new ArrayList<>();
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_news_recommendvideo,container,false);
        GetVideo();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        //在这里处理通过网路请求获得的视频列表
                        ArrayList data = msg.getData().getParcelableArrayList("data");
                        list = (List<NetArticleItemData.VideosBean>)data.get(0);
                }
            }
        };
        mRecyclerView = view.findViewById(R.id.Rv_video_news);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected boolean isFragmentVisible() {
        return super.isFragmentVisible();
    }

    private void GetVideo()
    {
        new Thread(() -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUtil.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RecommendVideoInterface videoService = retrofit.create(RecommendVideoInterface.class);
            Call<NetArticleItemData> call = videoService.listRespnse(1);
            call.enqueue(new Callback<NetArticleItemData>() {
                @Override
                public void onResponse(Call<NetArticleItemData> call, Response<NetArticleItemData> response) {
                    Log.d("GetRecommendVideo", "Get is success");
                    NetArticleItemData netVideoItemData = response.body();
                    List<NetArticleItemData.VideosBean> list = netVideoItemData.getVideos();
                    Bundle bundle = new Bundle();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(list);
                    bundle.putStringArrayList("data", arrayList);
                    Message message = new Message();
                    message.what = 1;
                    message.setData(bundle);
                    handler.sendMessage(message);
                }

                @Override
                public void onFailure(Call<NetArticleItemData> call, Throwable t) {
                    Log.d("GetRecommendVideo","Get is out");
                }
            });
        }).start();
    }
}
