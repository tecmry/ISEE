package com.example.mao.beautylife.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mao.beautylife.R;
import com.example.mao.beautylife.activity.VideoNewsActivity;
import com.example.mao.beautylife.data.NetArticleItemData;

import java.util.List;

/**
 * Created by Tecmry on 2018/3/26.
 */

public class RecomandVideoRecyclerAdapter extends RecyclerView.Adapter<RecomandVideoRecyclerAdapter.ViewHolder> {
    private List<NetArticleItemData.VideosBean> dataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mItemClickListener;

    //item的回调接口
    public interface OnItemClickListener{
        void onItemClick(View view,int Position);
    }
    //定义一个设置点击监听器的方法
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
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
        if ((position+1)%2==0)
        {
            if (dataList.size()!=0)
            {
                holder.Tv_LeftTitle.setText(dataList.get(2).getTitle());
                holder.Tv_RightTitle.setText(dataList.get(3).getTitle());
                Glide.with(mContext).load(dataList.get(2).getCoverUrl()).into(holder.Iv_Left);
                Glide.with(mContext).load(dataList.get(3).getCoverUrl()).into(holder.Iv_Right);
                holder.Tv_LeftAuthor.setText(dataList.get(2).getNickname());
                holder.Tv_RightAuthor.setText(dataList.get(3).getNickname());
                holder.LL_LeftItem.setOnClickListener(view -> {
                    Intent intent = new Intent();
                    intent.setClass(mContext, VideoNewsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("newsData", dataList.get(2));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                });
                holder.LL_RightItem.setOnClickListener(view -> {
                    Intent intent = new Intent();
                    intent.setClass(mContext, VideoNewsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("newsData", dataList.get(3));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                });
            }
        }else {
            holder.Tv_LeftTitle.setText(dataList.get(0).getTitle());
            holder.Tv_RightTitle.setText(dataList.get(1).getTitle());
            Glide.with(mContext).load(dataList.get(0).getCoverUrl()).into(holder.Iv_Left);
            Glide.with(mContext).load(dataList.get(1).getCoverUrl()).into(holder.Iv_Right);
            holder.Tv_LeftAuthor.setText(dataList.get(0).getNickname());
            holder.Tv_RightAuthor.setText(dataList.get(1).getNickname());
            holder.LL_LeftItem.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.setClass(mContext, VideoNewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsData", dataList.get(0));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            });
            holder.LL_RightItem.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.setClass(mContext, VideoNewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("newsData", dataList.get(1));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView Iv_Left;
        private ImageView Iv_Right;
        private TextView  Tv_LeftTitle;
        private TextView  Tv_RightTitle;
        private TextView  Tv_LeftAuthor;
        private TextView  Tv_RightAuthor;
        private LinearLayout LL_LeftItem;
        private LinearLayout LL_RightItem;
        public ViewHolder(View itemView) {
            super(itemView);
            Iv_Left = itemView.findViewById(R.id.Iv_RecommendvideoItemLeft);
            Iv_Right = itemView.findViewById(R.id.Iv_RecommendvideoItemRight);
            Tv_LeftAuthor = itemView.findViewById(R.id.Tv_RecommendvideoItemAuthorLeft);
            Tv_RightAuthor = itemView.findViewById(R.id.Tv_RecommendvideoItemAuthorRight);
            Tv_LeftTitle = itemView.findViewById(R.id.Tv_RecommendvideoItemTitleLeft);
            Tv_RightTitle = itemView.findViewById(R.id.Tv_RecommendvideoItemTimeRight);
            LL_LeftItem = itemView.findViewById(R.id.LL_LeftItem);
            LL_RightItem = itemView.findViewById(R.id.LL_RightItem);
        }
    }
}
