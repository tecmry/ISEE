package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.util.ImageLoaderUtil;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by -- Mao on 2017/12/2.
 */

public class OneRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private class NineImageAdapter extends NineGridImageViewAdapter<String> {

        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String url) {
            ImageLoaderUtil.ImageLoader(context, imageView, url);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {

        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView name, info, share_num, collect_num, like_num, color_info, content, all;
        NineGridImageView nineGridImageView;


        private ItemHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.one_item_head_image);
            name = itemView.findViewById(R.id.one_item_name);
            info = itemView.findViewById(R.id.one_item_info);
            share_num = itemView.findViewById(R.id.one_item_text_share);
            collect_num = itemView.findViewById(R.id.one_item_text_collect);
            like_num = itemView.findViewById(R.id.one_item_text_like);
            color_info = itemView.findViewById(R.id.one_item_text_color_info);
            content = itemView.findViewById(R.id.one_item_text_content);
            all = itemView.findViewById(R.id.one_item_text_all);
            nineGridImageView = itemView.findViewById(R.id.one_item_nine_image);
            nineGridImageView.setAdapter(new NineImageAdapter());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_one_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
