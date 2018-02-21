package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.VideoData;
import com.example.mao.beautylife.listener.FootItemListener;
import com.example.mao.beautylife.listener.OnItemClickListener;
import com.example.mao.beautylife.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by -- Mao on 2017/12/2.
 */

public class TwoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VideoData> list;
    private ImageListener listener;
    private FootItemListener footItemListener;

    public interface ImageListener{
        void imageListener(ImageView view, String imageUrl, String title, String videoUrl);
    }


    public TwoRecyclerAdapter(List<VideoData> list, ImageListener listener, FootItemListener footItemListener) {
        this.list = list;
        this.listener = listener;
        this.footItemListener = footItemListener;
    }

    private class ItemHolder extends SmartViewHolder{

        ImageView imageViewOne, imageViewTwo;
        TextView titleOne, titleTwo;
        TextView likeOne, likeTwo;
        TextView nameOne, nameTwo;

        private ItemHolder(View itemView) {
            super(itemView);
            imageViewOne = itemView.findViewById(R.id.two_item_image_one);
            imageViewTwo = itemView.findViewById(R.id.two_item_image_two);
            titleOne = itemView.findViewById(R.id.two_item_image_one_title);
            titleTwo = itemView.findViewById(R.id.two_item_image_two_title);
            likeOne = itemView.findViewById(R.id.two_item_image_one_name_like);
            likeTwo = itemView.findViewById(R.id.two_item_image_two_name_like);
            nameOne = itemView.findViewById(R.id.two_item_image_one_name);
            nameTwo = itemView.findViewById(R.id.two_item_image_two_name);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_two_item, parent, false);
            return new ItemHolder(view);
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item, parent, false);
            return new SmartViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < list.size()){
            ItemHolder itemHolder = (ItemHolder) holder;
            VideoData data = list.get(position);
            listener.imageListener(itemHolder.imageViewOne, data.getImageOne(), data.getTitleOne(), data.getVideoUrlOne());
            listener.imageListener(itemHolder.imageViewTwo, data.getImageTwo(), data.getTitleTwo(), data.getVideoUrlTwo());
            itemHolder.nameOne.setText(data.getNameOne());
            itemHolder.nameTwo.setText(data.getNameTwo());
            itemHolder.titleOne.setText(data.getTitleOne());
            itemHolder.titleTwo.setText(data.getTitleTwo());
            itemHolder.likeOne.setText(data.getLikeNumOne());
            itemHolder.likeTwo.setText(data.getLikeNumTwo());
        }else {
            footItemListener.itemClick(holder.itemView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < list.size())
            return 0;
        else
            return 1;
    }
}
