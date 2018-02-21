package com.example.mao.beautylife.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.TabSelected;
import com.example.mao.beautylife.activity.SearchActivity;
import com.example.mao.beautylife.activity.VideoActivity;
import com.example.mao.beautylife.adapter.PageAdapter;
import com.example.mao.beautylife.databinding.FragmentHomeBinding;

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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by -- Mao on 2017/11/27.
 */

public class HomeFragment extends Fragment implements ImageLoaderInterface, View.OnClickListener {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private List<Fragment> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initBanner();
        list.add(new HomeOneFragment());
        list.add(new HomeTwoFragment());
        list.add(new HomeThreeFragment());
        binding.fragmentHomeViewPager.setAdapter(new PageAdapter(getFragmentManager(), list));
        binding.fragmentHomeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.fragmentHomeTab));
        binding.fragmentHomeSearch.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(TAG);
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

    private void initBanner(){
        binding.fragmentHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        binding.fragmentHomeBanner.setImageLoader(this);
        List<Integer> list = new LinkedList<>();
        list.add(R.drawable.banner_one);
        list.add(R.drawable.banner_two);
        list.add(R.drawable.banner_three);
        list.add(R.drawable.banner_four);
        binding.fragmentHomeBanner.setImages(list);
        binding.fragmentHomeBanner.setBannerAnimation(Transformer.DepthPage);
        binding.fragmentHomeBanner.isAutoPlay(true);
        binding.fragmentHomeBanner.setDelayTime(1500);
        binding.fragmentHomeBanner.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_home_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), binding.fragmentHomeSearch, "shareAnim").toBundle());
                break;
            default:
                break;
        }
    }

}
