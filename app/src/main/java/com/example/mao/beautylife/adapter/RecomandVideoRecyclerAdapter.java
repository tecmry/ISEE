package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mao.beautylife.R;
import com.example.mao.beautylife.data.NetArticleItemData;

import java.util.List;

/**
 * Created by Tecmry on 2018/3/26.
 */

public class RecomandVideoRecyclerAdapter extends RecyclerView.Adapter<RecomandVideoRecyclerAdapter.ViewHolder> {
    private List<NetArticleItemData.VideosBean> dataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public RecomandVideoRecyclerAdapter(List<NetArticleItemData.VideosBean> dataList, Context mContext )
    {
        this.dataList = dataList;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public RecomandVideoRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecomandVideoRecyclerAdapter.ViewHolder viewHolder = new RecomandVideoRecyclerAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.fragment_video_news_recommendvideoitems,parent,false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecomandVideoRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
