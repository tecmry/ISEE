package com.example.mao.beautylife.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.GlideApp;
import com.example.mao.beautylife.GridDivider;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.adapter.CommonRecyclerAdapter;
import com.example.mao.beautylife.data.BrandData;
import com.example.mao.beautylife.databinding.FragmentHomeThreeBinding;
import com.example.mao.beautylife.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by --Mao on 2018/2/21.
 */

public class HomeThreeFragment extends Fragment {

    private FragmentHomeThreeBinding binding;
    private List<BrandData> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_three, container, false);
        binding.homeThreeRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.homeThreeRecyclerBrand.setLayoutManager(new GridLayoutManager(getContext(), 4));
        binding.homeThreeRecyclerBrand.addItemDecoration(new GridDivider(getContext()));
        initAdapter();
        return binding.getRoot();
    }

    private void initAdapter(){
        binding.homeThreeRecycler.setAdapter(new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_three_item, parent, false);
            return new ItemViewHolder(view);
        }, (holder, position) -> {
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            char ch = (char)('A' + position);
            itemViewHolder.textView.setText(String.valueOf(ch));
        }, () -> {
            return 26;
        }));
        binding.homeThreeRecyclerBrand.setAdapter(new CommonRecyclerAdapter((parent, viewType) -> {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_three_item_brand, parent, false);
            return new ItemViewHolder(view);
        }, (holder, position) -> {
//            BrandData data = list.get(position);
//            BrandViewHolder viewHolder = (BrandViewHolder)holder;
//            ImageLoaderUtil.ImageLoader(viewHolder.imageView, data.getImageUrl());
//            viewHolder.textView.setText(data.getName());
        },()->{
            return list.size();
        }));
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.home_three_item_text);
        }

    }

    class BrandViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public BrandViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_brand_text);
            imageView = itemView.findViewById(R.id.item_brand_image);
        }
    }


}
