package com.example.mao.beautylife.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.ColorData;
import com.example.mao.beautylife.data.InfoData;
import com.example.mao.beautylife.data.VideoData;
import com.example.mao.beautylife.databinding.FragmentCommunityBinding;
import com.example.mao.beautylife.util.HttpUtil;
import com.example.mao.beautylife.util.ImageLoaderUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by -- Mao on 2017/11/27.
 */

public class CommunityFragment extends Fragment implements ImageLoaderInterface {

    private static final String TAG = "CommunityFragment";
    private FragmentCommunityBinding binding;
    private List<ColorData> colorDataList = new ArrayList<>();
    private List<VideoData> videoDataList = new ArrayList<>();
    private List<InfoData> infoDataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false);
        initBanner();
        final Intent intent;
        binding.fragmentCommunityOne.setOnClickListener(v -> {

        });
        binding.fragmentCommunityTwo.setOnClickListener(v -> {

        });
        binding.fragmentCommunityThree.setOnClickListener(v -> {

        });
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

    private void parseJSON(String jsonData){
        try {
            JSONObject object = new JSONObject(jsonData);
            JSONObject jsonObject = null;
            JSONArray video = object.getJSONArray("videos");
            JSONArray color = object.getJSONArray("tColors");
            for (int i = 0; i < video.length(); i++) {
                jsonObject = video.getJSONObject(i);
                videoDataList.add(new VideoData(jsonObject.getString("nickname"),
                                                    jsonObject.getString("title"),
                                                    jsonObject.getString("coverUrl"),
                                                    jsonObject.getInt("likeTimes"),
                                                    jsonObject.getString("videourl")));

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void initBanner(){
        binding.fragmentCommunityPager.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        binding.fragmentCommunityPager.setImageLoader(this);
        List<Integer> list = new LinkedList<>();
        list.add(R.drawable.commnuity_banner_one);
        list.add(R.drawable.commnuity_banner_two);
        list.add(R.drawable.community_banner_three);
        list.add(R.drawable.community_banner_four);
        binding.fragmentCommunityPager.setImages(list);
        binding.fragmentCommunityPager.setBannerAnimation(Transformer.DepthPage);
        binding.fragmentCommunityPager.isAutoPlay(true);
        binding.fragmentCommunityPager.setDelayTime(1500);
        binding.fragmentCommunityPager.start();
    }

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .execute(callback);
    }

    @Override
    public void displayImage(Context context, Object path, View imageView) {
        GlideApp.with(this)
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
                .into((ImageView) imageView);
    }

    @Override
    public View createImageView(Context context) {
        return null;
    }

}
