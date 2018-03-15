package com.example.mao.beautylife.fragment;

import android.app.ActivityOptions;
import android.content.Context;
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

import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.activity.SearchActivity;
import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.adapter.SmartViewHolder;
import com.example.mao.beautylife.databinding.FragmentHomeOneBinding;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeOneFragment extends Fragment implements ImageLoaderInterface {

    private FragmentHomeOneBinding binding;
    private CommonRecyclerAdapter recyclerOneAdapter, recyclerTwoAdapter, recyclerThreeAdapter;

    class ItemViewHolder extends SmartViewHolder{

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_one, container, false);
        initBanner();
        initAdapter();
        binding.fragmentHomeSearch.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), binding.fragmentHomeSearch, "shareAnim").toBundle());
        });
        return binding.getRoot();
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

    private void initAdapter(){
        recyclerOneAdapter = new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_one_item, parent, false);
            return new ItemViewHolder(view);
        }, (holder, position) -> {

        }, () -> {
            return 0;
        });
        recyclerTwoAdapter = new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_one_item, parent, false);
            return new ItemViewHolder(view);
        }, (holder, position) -> {

        }, () -> {
            return 0;
        });
        recyclerThreeAdapter = new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_one_item, parent, false);
            return new ItemViewHolder(view);
        }, (holder, position) -> {

        }, () -> {
            return 0;
        });
        binding.homeOneRecyclerOne.setAdapter(recyclerOneAdapter);
        binding.homeOneRecyclerTwo.setAdapter(recyclerTwoAdapter);
        binding.homeOneRecyclerThree.setAdapter(recyclerThreeAdapter);
        binding.homeOneRecyclerOne.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.homeOneRecyclerTwo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.homeOneRecyclerThree.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
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
