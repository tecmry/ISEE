package com.example.mao.beautylife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.InfoData;
import com.example.mao.beautylife.listener.FootItemListener;

import java.util.List;

/**
 * Created by -- Mao on 2017/12/4.
 */

public class FiveRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemListener{
        void itemImage(View itemView, ImageView imageView, String url, String content);
    }

    private List<InfoData> list;

    private FootItemListener listener;

    private OnItemListener itemListener;

    public FiveRecyclerAdapter(List<InfoData> list, FootItemListener listener, OnItemListener itemListener) {
        this.list = list;
        this.listener = listener;
        this.itemListener = itemListener;
    }

    private class ItemHolder extends SmartViewHolder{

        ImageView imageView;
        TextView title, name, info, textCollect;

        private ItemHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.five_item_image);
            title = itemView.findViewById(R.id.five_item_title);
            name = itemView.findViewById(R.id.five_item_name);
            info = itemView.findViewById(R.id.five_item_info);
            textCollect = itemView.findViewById(R.id.five_item_collect_text);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_five_item, parent, false);
            return new ItemHolder(view);
        } else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foot_item, parent, false);
            return new SmartViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < list.size()){
            InfoData data = list.get(position);
            ItemHolder itemHolder = (ItemHolder) holder;
            itemListener.itemImage(itemHolder.itemView, itemHolder.imageView, data.getImageUrl(), data.getContent());
            itemHolder.name.setText(data.getName());
            itemHolder.info.setText(data.getInfo());
            itemHolder.title.setText(data.getTitle());
            itemHolder.textCollect.setText(data.getCollect());
        }else {
            listener.itemClick(holder.itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < list.size())
            return 0;
        else
            return 1;
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
