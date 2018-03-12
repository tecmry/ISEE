package com.example.mao.beautylife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by --Mao on 2018/3/7.
 */

public class CommonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnCreateView onCreateView;
    private OnBindView onBindView;
    private ItemCount itemCount;
    private GetItemViewType getItemViewType;

    public CommonRecyclerAdapter(OnCreateView onCreateView, OnBindView onBindView, ItemCount itemCount) {
        this.onCreateView = onCreateView;
        this.onBindView = onBindView;
        this.itemCount = itemCount;
    }

    public CommonRecyclerAdapter(OnCreateView onCreateView, OnBindView onBindView, ItemCount itemCount, GetItemViewType getItemViewType) {
        this.onCreateView = onCreateView;
        this.onBindView = onBindView;
        this.itemCount = itemCount;
        this.getItemViewType = getItemViewType;
    }

    public interface OnCreateView{
        RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    }

    public interface OnBindView{
        void onBind(RecyclerView.ViewHolder holder, int position);
    }

    public interface ItemCount{
        int getItemCount();
    }

    public interface GetItemViewType{
        int getItemViewType(int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateView.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindView.onBind(holder, position);
    }

    @Override
    public int getItemCount() {
        return itemCount.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemViewType == null)
            return super.getItemViewType(position);
        else
            return getItemViewType.getItemViewType(position);
    }
}
