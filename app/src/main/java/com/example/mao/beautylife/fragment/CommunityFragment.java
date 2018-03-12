package com.example.mao.beautylife.fragment;

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


import com.avos.avoscloud.IntentUtil;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by -- Mao on 2017/11/27.
 */

public class CommunityFragment extends Fragment {

    private static final String TAG = "CommunityFragment";
    private FragmentCommunityBinding binding;
    private List<ColorData> colorDataList = new ArrayList<>();
    private List<VideoData> videoDataList = new ArrayList<>();
    private List<InfoData> infoDataList = new ArrayList<>();
    private final int[] COMMUNITY_BANNER = {R.drawable.commnuity_banner_one,
            R.drawable.commnuity_banner_two,
            R.drawable.community_banner_three,
            R.drawable.community_banner_four};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false);
        GlideApp.with(this).load(R.drawable.one_selected).into(binding.fragmentCommunityOne);
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
        binding.fragmentCommunityPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return COMMUNITY_BANNER.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = inflater.inflate(R.layout.gallery_item, container, false);
                ImageLoaderUtil.ImageLoader(view.findViewById(R.id.galley_item_image), COMMUNITY_BANNER[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @Override
            public float getPageWidth(int position) {
                return 0.8f;
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

    private void request(StringCallback callback){
        OkGo.<String>get(HttpUtil.URL + "/home")
                .tag(this)
                .cacheKey(TAG)
                .cacheMode(CacheMode.DEFAULT)
                .retryCount(5)
                .execute(callback);
    }

}
