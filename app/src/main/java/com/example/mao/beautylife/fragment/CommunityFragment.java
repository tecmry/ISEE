package com.example.mao.beautylife.fragment;

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


import com.avos.avoscloud.IntentUtil;
import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.databinding.FragmentCommunityBinding;
import com.example.mao.beautylife.util.ImageLoaderUtil;


/**
 * Created by -- Mao on 2017/11/27.
 */

public class CommunityFragment extends Fragment implements View.OnClickListener {

    private FragmentCommunityBinding binding;
    private FragmentManager manager;
    private CommunityOneFragment oneFragment = new CommunityOneFragment();
    private CommunityTwoFragment twoFragment = new CommunityTwoFragment();
    private CommunityThreeFragment threeFragment = new CommunityThreeFragment();
    private CommunityFourFragment fourFragment = new CommunityFourFragment();
    private CommunityFiveFragment fiveFragment = new CommunityFiveFragment();
    private final int[] COMMUNITY_BANNER = {R.drawable.commnuity_banner_one,
            R.drawable.commnuity_banner_two,
            R.drawable.community_banner_three,
            R.drawable.community_banner_four};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false);
        initCircleImage();
        GlideApp.with(this).load(R.drawable.one_selected).into(binding.fragmentCommunityOne);
        binding.fragmentCommunityOne.setOnClickListener(this);
        binding.fragmentCommunityTwo.setOnClickListener(this);
        binding.fragmentCommunityThree.setOnClickListener(this);
        binding.fragmentCommunityFour.setOnClickListener(this);
        binding.fragmentCommunityFive.setOnClickListener(this);
        manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_community_frame, oneFragment).commit();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_community_one:
                manager.beginTransaction().replace(R.id.fragment_community_frame, oneFragment).commit();
                initCircleImage();
                binding.fragmentCommunityPager.setNarrowFactor(0.9f);
                GlideApp.with(this).load(R.drawable.one_selected).into(binding.fragmentCommunityOne);
                break;
            case R.id.fragment_community_two:
                manager.beginTransaction().replace(R.id.fragment_community_frame, twoFragment).commit();
                initCircleImage();
                GlideApp.with(this).load(R.drawable.two_selected).into(binding.fragmentCommunityTwo);
                break;
            case R.id.fragment_community_three:
                manager.beginTransaction().replace(R.id.fragment_community_frame, threeFragment).commit();
                initCircleImage();
                GlideApp.with(this).load(R.drawable.three_selected).into(binding.fragmentCommunityThree);
                break;
            case R.id.fragment_community_four:
                manager.beginTransaction().replace(R.id.fragment_community_frame, fourFragment).commit();
                initCircleImage();
                GlideApp.with(this).load(R.drawable.four_selected).into(binding.fragmentCommunityFour);
                break;
            case R.id.fragment_community_five:
                manager.beginTransaction().replace(R.id.fragment_community_frame, fiveFragment).commit();
                initCircleImage();
                GlideApp.with(this).load(R.drawable.five_selected).into(binding.fragmentCommunityFive);
                break;
            default:
                break;
        }
    }

    private void initCircleImage(){
        GlideApp.with(this).load(R.drawable.one_unselected).into(binding.fragmentCommunityOne);
        GlideApp.with(this).load(R.drawable.two_unselected).into(binding.fragmentCommunityTwo);
        GlideApp.with(this).load(R.drawable.three_unselected).into(binding.fragmentCommunityThree);
        GlideApp.with(this).load(R.drawable.four_unselected).into(binding.fragmentCommunityFour);
        GlideApp.with(this).load(R.drawable.five_unselected).into(binding.fragmentCommunityFive);
    }
}
